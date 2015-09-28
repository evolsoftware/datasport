/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasport;

import javax.swing.JLabel;
import basededatos.Queries;

/**
 *
 * @author Reinaldo Pabon Esta clase es el runnable para mostrar las calorías,
 * kms, vuelta
 */
public class ActualizarMetricas implements Runnable {

    //Codigo que se ejecutara durante el thread
    private long intervaloCalculoCalorias, intervaloMostrarPantalla;
    private JLabel lblCal, lblKm, lblVuelta, lblVel, lblInc,VueltasFin,
    IncPromFin,VelPromFin,CalFin,KmFin,TiempoTFin,Resultados,UVueltasFin,
    UIncPromFin,UVelProm,UCalFin,UKmFin,UTiempoTFin,lblConsola;
    private boolean vivoM;                                                        //Para que se repita el run
    private DataSport programa;
    private Relojrun reloj;
    private int t;
    private int salto;
    private String botonM, vueltas, calSeg, kmAcum;
    Queries queriBD;

    public void setVelIncPrestablecido() {

    }

    public ActualizarMetricas(JLabel lblCal, JLabel lblKm, JLabel lblVuelta, JLabel lblVel, JLabel lblInc,
            Relojrun reloj, DataSport programa, long intervaloCalculoCalorias, long intervaloMostrarPantalla) {
        this.lblCal = lblCal;
        this.lblKm = lblKm;
        this.reloj = reloj;
        this.lblVuelta = lblVuelta;
        this.lblVel = lblVel;
        this.lblInc = lblInc;
        this.programa = programa;
        this.intervaloCalculoCalorias = intervaloCalculoCalorias;
        this.intervaloMostrarPantalla = intervaloMostrarPantalla;
        vivoM = true;
        queriBD = new Queries();

    }
    
    public void llevarEtiquetas(JLabel VueltasFin,JLabel IncPromFin,JLabel VelPromFin,JLabel CalFin,JLabel KmFin,
    JLabel TiempoTFin,JLabel Resultados,JLabel UVueltasFin,JLabel UIncPromFin,
    JLabel UVelProm,JLabel UCalFin,JLabel UKmFin,JLabel UTiempoTFin,JLabel lblConsola)
    {
    this.VueltasFin=VueltasFin;
    this.IncPromFin=IncPromFin;
    this.VelPromFin=VelPromFin;
    this.CalFin=CalFin;
    this.KmFin=KmFin;
    this.TiempoTFin=TiempoTFin;
    this.Resultados=Resultados;
    this.UVueltasFin=UVueltasFin;
    this.UIncPromFin=UIncPromFin;
    this.UVelProm=UVelProm;
    this.UCalFin=UCalFin;
    this.UKmFin=UKmFin;
    this.UTiempoTFin=UTiempoTFin;
    this.lblConsola=lblConsola;
    }
    
    public void mostrar()
    {
    lblConsola.setVisible(false);
    VueltasFin.setVisible(true);
    IncPromFin.setVisible(true);
    VelPromFin.setVisible(true);
    CalFin.setVisible(true);
    KmFin.setVisible(true);
    TiempoTFin.setVisible(true);
    Resultados.setVisible(true);
    UVueltasFin.setVisible(true);
    UIncPromFin.setVisible(true);
    UVelProm.setVisible(true);
    UCalFin.setVisible(true);
    UKmFin.setVisible(true);
    UTiempoTFin.setVisible(true);
    }
    
    public void calcularFinales(String vueltas,String cal)
    {
    String temp = vueltas;
    String CalTemp = cal;
    programa.promedios();
    UVueltasFin.setText(temp);
    UIncPromFin.setText(""+programa.getPromInc());
    UVelProm.setText(""+programa.getPromVel());
    UKmFin.setText(programa.getDistAcumStringFin());
    UTiempoTFin.setText(reloj.calcularTiempoTranscurrido());
    UCalFin.setText(CalTemp);
    }

    public void calculos() {
        programa.calcularCal(intervaloCalculoCalorias);
        programa.calcularKm();
        programa.calcularVuelta();

    }

    public void setBoton(String b) {
        botonM = b;

    }

    public void setVivo(boolean vivoM) {
        this.vivoM = vivoM;
    }

    @Override
    public synchronized void run() {

        while (vivoM) {

            try {

                //Se ejecuta si esta en play
                if (botonM == "play") {

                    if (salto != 0) /*
                     Evita que entre de una vez a ejecutar despues de dar pausa.
                     Antes se tenia el bug de que por ejemplo se pausaba en el segundo
                     5 justo antes de 6 mostraba el calculo de calorias para 5 y para 6
                     o sea por dos. Se hace esto para "desfasar un segundo" este thread
                     del otro No se adelanta
                     */ {

                        int tiempo = (int) reloj.getTiempoTranscurrido();
                        System.out.println("El tiempo de las metricas es " + tiempo);

                        calculos();

                        vueltas = programa.getVueltaPre();
                        kmAcum = programa.getDistAcumString();
                        calSeg = programa.getCalString();
//                        System.out.println("la velocidad  en Metricas " + programa.getVel());
//                        System.out.println("La inclinación en Metricas " + programa.getInc());
//
//                        System.out.println("Los km en metricas " + kmAcum);
//                        System.out.println("Las calorias en metricas son " + calSeg);

                        if ((tiempo) == 0) {
                            lblCal.setText("00000.00");
                            lblKm.setText("       00.00");
                            lblVuelta.setText("0");
                            
                           

                        } else {

                            if (((tiempo + 1) % intervaloMostrarPantalla) == 0) {

                                lblCal.setText(calSeg);
                                lblKm.setText(kmAcum);
                                lblVuelta.setText(vueltas);
                                if (programa.getModo() == 1) {
                                    lblVel.setText(programa.getVelString());
                                    lblInc.setText("" + programa.getIncString());

                                }

                            }
                        }
                    } else /*
                     una vez se salto el primer segundo despues de pausa incrementa
                     el salto para que entre en los demas ciclos siguientes.
                     */ {
                        salto++;
                    }

                }

                {
                    if (botonM == "pause") /*
                     si pausan coloca el salto en 0 para que haga el desfase.
                     */ {
                        salto = 0;
                    } else //usaron stop
                    {
                        if (botonM == "stop") {
//                            System.out.println("usaron stop");
                            calcularFinales(vueltas,calSeg);
                            mostrar();
                            queriBD.AgregarBD(programa.getVuelta(),programa.getPromInc(),programa.getPromVel(),programa.getCal(),programa.getKm(),
                                   reloj.getTiempoTranscurrido());
                            programa.resetCalorias();
                            programa.resetKm();
                            programa.resetVuelta();
                            programa.resetParametros();
                            lblCal.setText("00000.00");
                            lblKm.setText("       00.00");
                            lblVuelta.setText("0");
                            botonM = "";
                        }
                    }
                }
                
                if (programa.getEstadoPrest() == "stop") {
                    calcularFinales(vueltas,calSeg);
                    mostrar();
                    queriBD.AgregarBD(programa.getVuelta()-1,programa.getPromInc(),programa.getPromVel(),programa.getCal(),programa.getKm(),
                                   reloj.getTiempoTranscurrido());
                    programa.resetCalorias();
                    programa.resetKm();
                    programa.resetVuelta();
                    programa.resetParametros();
                    lblCal.setText("00000.00");
                    lblKm.setText("       00.00");
                    lblVuelta.setText("0");
                    reloj.setEstado("stop");
                    lblVel.setText("0.0");
                    lblInc.setText("0.0");
//                    programa.setEstadoPrest("");
                    System.out.println("detenido");
                    vivoM = false;
                    
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            };

        }
    }

}
