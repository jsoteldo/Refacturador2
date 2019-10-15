package mifarma.ptoventa.cnx;


import java.sql.*;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

public class MTSample extends Thread
{
  // Default no of threads to 10
  private static int NUM_OF_THREADS = 1;

  int m_myId;

  static  int c_nextId = 1;
  static  Connection s_conn = null;
  static  boolean   share_connection = false;

  synchronized static int getNextId()
  {
      return c_nextId++;
  }
    public static void main_dos ()
    {
      try  
      {  
    
        // Create the threads
        Thread[] threadList = new Thread[NUM_OF_THREADS];

        // spawn threads
        for (int i = 0; i < NUM_OF_THREADS; i++)
        {
            threadList[i] = new MTSample();
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
    
  public static void main (String args [])
  {
    try  
    {  
  
      // Create the threads
      Thread[] threadList = new Thread[NUM_OF_THREADS];

      // spawn threads
      for (int i = 0; i < NUM_OF_THREADS; i++)
      {
          threadList[i] = new MTSample();
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

  public MTSample()
  {
     super();
     // Assign an Id to the thread
     m_myId = getNextId();
  }

  public void run()
  {
    Connection conn = null;
    ResultSet     rs   = null;
    CallableStatement  stmt = null;

    try
    {    
      // Get the connection

        OracleDataSource ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@10.11.1.204:1521/XE");
        ods.setUser("ptoventa");
        ods.setPassword("mundial265");
        conn = ods.getConnection();
        //stmt = conn.createStatement (); // Create a Statement
        stmt = conn.prepareCall("{ call ? := " +"PTOVENTA_VTA_LISTA.VTA_LISTA_PROD(?,?)" + " }");   
        stmt.registerOutParameter(1, OracleTypes.CURSOR);
        stmt.setString(2, "001");
        stmt.setString(3, "506");
        
      while (!getGreenLight())
        yield();
          
      // Execute the Query
        //rs = stmt.executeQuery ("select * from lgt_prod where  est_prod = 'A'");
        stmt.execute();
        rs = (ResultSet)stmt.getObject(1);    
        
        
        
        
      // Loop through the results
      while (rs.next())
      {
        /*System.out.println("Thread " + m_myId + 
                           " Employee Id : " + rs.getInt(1) + 
                           " Name : " + rs.getString(2));*/
        yield();  // Yield To other threads
      }
          
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
      System.out.println("Thread " + m_myId +  " is finished. ");
    }
    catch (Exception e)
    {
      System.out.println("Thread " + m_myId + " got Exception: " + e);
      e.printStackTrace();
      return;
    }
  }

  static boolean greenLight = false;
  static synchronized void setGreenLight () { greenLight = true; }
  synchronized boolean getGreenLight () { return greenLight; }

}