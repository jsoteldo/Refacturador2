package mifarma.ptoventa.cnx;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.ArrayList;

import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.DBVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProcesoCnxPool extends Thread
{
    
    private static final Logger log = LoggerFactory.getLogger(ProcesoCnxPool.class);
  // Default no of threads to 10
  private static int NUM_OF_THREADS = 1;

  int m_myId;

  static  int c_nextId = 1;
  static  Connection s_conn = null;
  static  boolean   share_connection = false;
  
  public ArrayList pParameters;
  public String pStoredProcedure;
  public boolean pWithCheck;
  

  synchronized static int getNextId()
  {
      return c_nextId++;
  }

  public static void load()
  {
    try  
    {  
      // get a shared connection
      if (share_connection) 
          {
          OracleDataSource ods = new OracleDataSource();          
          ods.setURL("jdbc:oracle:thin:@"+FarmaVariables.vIPBD+":"+FarmaVariables.vPUERTO+"/XE");          
          ods.setUser(FarmaVariables.vUsuarioBD);
          ods.setPassword(FarmaVariables.vClaveBD);
          
          Connection s_conn = ods.getConnection();
          }
      // Create the threads
      Thread[] threadList = new Thread[NUM_OF_THREADS];

      // spawn threads
      for (int i = 0; i < NUM_OF_THREADS; i++)
      {
          ArrayList parametros = new ArrayList();
                  parametros.add(FarmaVariables.vCodGrupoCia);
                  parametros.add(FarmaVariables.vCodLocal);
          threadList[i] = new ProcesoCnxPool(parametros, "PTOVENTA_VTA_LISTA.VTA_LISTA_PROD_NEW(?,?)",true);
          threadList[i].start();
      }
        
     
    
      // Start everyone at the same time
      setGreenLight ();

      // wait for all threads to end
      for (int i = 0; i < NUM_OF_THREADS; i++)
      {
          threadList[i].join();
      }

      if (share_connection)
      {
          s_conn.close();
          s_conn = null;
      }
          
    }
    catch (Exception e)
    {
       e.printStackTrace();
    }
  
  }  

  public ProcesoCnxPool(ArrayList pParameters,String pStoredProcedure,boolean pWithCheck)
  {
   
     super();
      this.pParameters = pParameters;
      this.pStoredProcedure=pStoredProcedure;
      this.pWithCheck=pWithCheck;
     // Assign an Id to the thread
     m_myId = getNextId();
  }

  public void run()
  {
    log.info("Thread run");
    Connection conn = null;
    ResultSet     rs   = null;
    CallableStatement  stmt = null;

    try
    {    
      // Get the connection
      int numeroParametro = 2;
        
        if (share_connection){
            
            stmt = s_conn.prepareCall("{ call ? := " + pStoredProcedure+ " }");   
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            for (int i = 0; i < pParameters.size(); i++) {
                if (pParameters.get(i) instanceof String)
                    stmt.setString(numeroParametro, (String)pParameters.get(i));
                if (pParameters.get(i) instanceof Integer)
                    stmt.setInt(numeroParametro, 
                                Integer.parseInt(((Integer)pParameters.get(i)).toString()));
                if (pParameters.get(i) instanceof Double)
                    stmt.setDouble(numeroParametro, 
                                   Double.parseDouble(((Double)pParameters.get(i)).toString()));
                numeroParametro += 1;
            }
        }
      else 
      {
          OracleDataSource ods = new OracleDataSource();          
          ods.setURL("jdbc:oracle:thin:@"+FarmaVariables.vIPBD+":"+FarmaVariables.vPUERTO+"/XE");          
          ods.setUser(FarmaVariables.vUsuarioBD);
          ods.setPassword(FarmaVariables.vClaveBD);
        conn = ods.getConnection();
        //stmt = conn.createStatement (); // Create a Statement
        stmt = conn.prepareCall("{ call ? := " +pStoredProcedure + " }");   
        stmt.registerOutParameter(1, OracleTypes.CURSOR);
        for (int i = 0; i < pParameters.size(); i++) {
              if (pParameters.get(i) instanceof String)
                  stmt.setString(numeroParametro, (String)pParameters.get(i));
              if (pParameters.get(i) instanceof Integer)
                  stmt.setInt(numeroParametro, 
                              Integer.parseInt(((Integer)pParameters.get(i)).toString()));
              if (pParameters.get(i) instanceof Double)
                  stmt.setDouble(numeroParametro, 
                                 Double.parseDouble(((Double)pParameters.get(i)).toString()));
              numeroParametro += 1;
        }
      }

      while (!getGreenLight())
        yield();
          
      // Execute the Query
      log.info("antes stmt.execute");
        stmt.execute();
        log.info("despues stmt.execute");
       
        log.info("antes de LEER ");
        //Obteniendo el cursor con el resultado
        rs = (ResultSet)stmt.getObject(1);
        stmt.setFetchSize(4000);
        //obteniendo el metadato del resulset
        ResultSetMetaData metaDatos = rs.getMetaData(); 
        rs.setFetchSize(4000);
        
        int numeroColumnas = metaDatos.getColumnCount();
        Object[] etiquetas = new Object[numeroColumnas];
        // Se obtiene cada una de las etiquetas para cada columna
        for (int i = 0; i < numeroColumnas; i++)
        {
           // Nuevamente, para ResultSetMetaData la primera columna es la 1.
           etiquetas[i] = metaDatos.getColumnLabel(i + 1);
        }
        
        while (rs.next()) {
            ArrayList myArray = new ArrayList();
            if (pWithCheck)
                myArray.add(new Boolean(false));
            for(int i=0 ; i < numeroColumnas; i++){
                String pValor = rs.getString(etiquetas[i].toString());
                myArray.add(pValor);
            }
            VariablesVentas.tableModelListaGlobalProductos.insertRow(myArray);
            yield();  // Yield To other threads
        }
        log.info("despues de LEER ");
          
      // Close all the resources
      rs.close();
      rs = null;
  
      // Close the statement
      stmt.close();
      stmt = null;
  
      // Close the local connection
      if ((!share_connection) && (conn != null))
      {
         conn.close();
         conn = null;
      }
      log.info("Thread " + m_myId +  " is finished. " + VariablesVentas.tableModelListaGlobalProductos.getRowCount());
    }
    catch (Exception e)
    {
      log.info("Thread " + m_myId + " got Exception: " + e);
      e.printStackTrace();
      return;
    }
  }

  static boolean greenLight = false;
  static synchronized void setGreenLight () { greenLight = true; }
  synchronized boolean getGreenLight () { return greenLight; }


}