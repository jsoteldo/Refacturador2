package mifarma.cpe;


import java.util.Timer;
import java.util.TimerTask;

import mifarma.common.FarmaVariables;

import mifarma.cpe.reference.FacadeCPE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SubProcesoCPE extends Thread {
    private static final Logger log = LoggerFactory.getLogger(SubProcesoCPE.class);

    private int tiempoInactividad; //tiempo en minutos
    UtilityCPE utilityCPE = new UtilityCPE();
    
    private boolean vIndEjecucion = false;
        
    public SubProcesoCPE() {
        super();
    }

    @Override
    public void run() {
        log.info("***************************************************");
        log.info("INICIA HILO DE SUBPROCESO DE ELECTRONICO");
        log.info("***************************************************");
        try{

            utilityCPE.cargarIndicadorElectronico();
            boolean isOperaHilo = utilityCPE.isActivoFuncionalidad();
            tiempoInactividad = utilityCPE.getTiempoSleepHilo();
            log.info("SE INICIARA? --> "+isOperaHilo);
            UtilityCPE.setVolverEjecutar(false);
            
            if(isOperaHilo){
                TimerTask v_tiempo = new TimerTask() {
                    public void run()  {
                        log.info("inicia v_tiempo");
                        ejecutar_cnx_epos(utilityCPE);
                        log.info("termino v_tiempo");
                    } 
                };
                
                TimerTask v_error = new TimerTask() { 
                    public void run()  {   
                        if(UtilityCPE.isVolverEjecutar()){
                            log.info("inicia v_error");
                            ejecutar_cnx_epos(utilityCPE);
                            UtilityCPE.setVolverEjecutar(false);
                            log.info("termino v_error");
                        }
                    } 
                };
                
                Timer vE = new Timer(); 
                vE.scheduleAtFixedRate(v_error, 0, 1000);
    
                Timer vT = new Timer(); 
                vT.scheduleAtFixedRate(v_tiempo, 0, 1000*tiempoInactividad*60);
            }
            
            /*while (isOperaHilo){
                
                log.info("INICIARA EL CONTADOR DEL CRONOMETRO");
                tiempoInactividad = utilityCPE.getTiempoSleepHilo();
            
            
                sleep(1000*tiempoInactividad*60);
                
                sleep(1000*tiempoInactividad*60);
                
                Cronometro c = new Cronometro();
                c.contar();
                
                UtilityCPE.setVolverEjecutar(false);
                
                do{
                    if(c.getSegundos() == (tiempoInactividad*60)){
                        UtilityCPE.setVolverEjecutar(true);
                    }
                }while(!UtilityCPE.isVolverEjecutar());
                c.Detener();
                
                log.info("TERMINO EL CONTADOR DEL CRONOMETRO");
                UtilityCPE.setVolverEjecutar(false);

            }*/
        }catch(Exception ex){
            log.error("", ex);
        }
    }
    
    public void ejecutar_cnx_epos(UtilityCPE utilityCPE){

        if (!vIndEjecucion) {
            //OBTIENE DATOS DE CONEXION
            vIndEjecucion = true;
            if (utilityCPE.obtenerDatosConexion()) {
                //utilityCPE.verificarEstadoServicioEPOS();
                if (!utilityCPE.isEstaContigenciaEPOS()) {
                    if (!utilityCPE.isRegistradoPCEnEpos()) {

                        //VERIFICA ESTADO DE PC EN EPOS S(REGISTRADA)/N(PENDIENTE DE REGISTRO)/X(NO FIGURA EN LISTA DE PC DEL FV)
                        utilityCPE.verificaEstadoRegistroPCEnEpos();

                        if (utilityCPE.isComunicaPCConEPOS()) {

                            if (!utilityCPE.isRegistradoPCEnEpos()) {
                                utilityCPE.registrarPCenEpos();
                                if (!utilityCPE.isRegistradoPCEnEpos()) {
                                    // ENVIA ALERTA
                                    String mensaje =
                                        "NO SE PUDO REGISTRAR CAJA EN EPOS, SERVICIO EPOS PASA A CONTIGENCIA<br>" +
                                        "IP PC / CAJA: " + FarmaVariables.vIpPc + "<br>";
                                    FacadeCPE facade = new FacadeCPE();
                                    facade.enviarCorreo(11, mensaje);
                                }
                            }

                        }
                    }
                } else {
                    utilityCPE.verificaEstadoRegistroPCEnEpos();
                    // ENVIA ALERTA
                    String mensaje =
                        "SERVICIO EPOS SE PASA A CONTINGENCIA<br>" + "IP PC / CAJA: " + FarmaVariables.vIpPc + "<br>";
                    FacadeCPE facade = new FacadeCPE();
                    facade.enviarCorreo(10, mensaje);
                }


            }
            vIndEjecucion = false;
        }
        else
            log.info("ya en ejecucion");
    }
}
