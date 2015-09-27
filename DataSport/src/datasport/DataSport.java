/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasport;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 * Esta sera la clase principal de la app Aclaro no sera el ejecutable.
 *
 * @author Reinaldo Pabonh
 */
public class DataSport {
    /*
     vel=velocidad
     inc=inclinación
     limSup=Limite Superior de las etiquetas del ModoLibre
     limInf=Limite Inferior de las etiquetas del ModoLibre
     var= Variación de la vel/inc con +y -
     tiempo= tiempo transcurrido
     intervalo= cada cuanto se hace el cálculo de las calorías
     modo=0:ModoLibre, 1:Prestablecido
     idAtributo=0:Velocidad, 1:Inclinacion
     */

    private float vel, inc, cal, calAcum, K, distanciaAcum, peso;
    private int vuelta, modo, distVuelta, edad;
    //private int intervalo
    private Programa progPrest;
    private JLabel lblVel, lblInc;
    private DecimalFormatSymbols simbolos;                                         //Para colocar el simbolo de "." a cal y km
    private String estadoPrest, vueltaPre;

    public DataSport(int distVuelta) {
        //Modo Libre
        this.distVuelta = distVuelta;
        modo = 0;
        calcularK(edad, peso);                                                   //Por ahora va retornar 10, cuando sea la versión dos se calcula verdaderamente
        simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');

    }

    public DataSport(int distVuelta, Programa progPrest, JLabel lblVel, JLabel lblInc) {
        //Prestablecido
        this.distVuelta = distVuelta;
        this.progPrest = progPrest;
        simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        modo = 1;
        calcularK(edad, peso);
        this.lblVel = lblVel;
        this.lblInc = lblInc;
    }

    public String getEstadoPrest() {
        return estadoPrest;
    }

    public void setProgPrest(Programa progPrest) {
        this.progPrest = progPrest;
    }

    public Programa getProgPrest() {
        return progPrest;
    }

    /*
     INICIO Declaración de getters y Setters
     */
    public float getVel() {
        return vel;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public float getInc() {
        return inc;
    }

    public float getCal() {
        return cal;
    }

    public float getCalAcum() {
        return calAcum;
    }

    public int getVuelta() {
        return vuelta;
    }

    public float getDistanciaAcum() {
        return distanciaAcum;
    }

    public int getModo() {
        return modo;
    }

    public void setCal(float cal) {
        this.cal = cal;
    }

    public void setVel(float vel) {
        this.vel = vel;
    }

    public void setInc(float inc) {
        this.inc = inc;
    }

    /*
     FIN Declaración Getters y Setters
     */
    public void aumentar(JLabel etiqueta, float limSup, float limInf, float var,
            int idAtributo) {
        double x = Float.parseFloat(etiqueta.getText());

        if (idAtributo == 0) {
            if ((x < limInf)) {
                if (x < 10) {
                    etiqueta.setText("  " + limInf);
                } //Para que se vea de una buena forma
                else {
                    etiqueta.setText("" + limInf);
                }
                vel = limInf;                                                      //Se establece la velocidad  
            } else {
                if (x == limSup) {
                    if (x < 10) {
                        etiqueta.setText("  " + limSup);
                    } else {
                        etiqueta.setText("" + limSup);
                    }
                    vel = limSup;
                } else {
                    x = x + var;
                    x = Math.rint(x * 10) / 10;
                    if (x < 10) {
                        etiqueta.setText("  " + x);
                    } else {
                        etiqueta.setText("" + x);
                    }
                    vel = (float) x;
                }
            }
        } else {
            if ((x < limInf)) {
                if (x < 10) {
                    etiqueta.setText("  " + limInf);
                } else {
                    etiqueta.setText("" + limInf);
                }
                inc = limInf;                                                      //Se establece la velocidad  
            } else {
                if (x == limSup) {
                    if (x < 10) {
                        etiqueta.setText("  " + limSup);
                    } else {
                        etiqueta.setText("" + limSup);
                    }
                    inc = limSup;
                } else {
                    x = x + var;
                    x = Math.rint(x * 10) / 10;
                    if (x < 10) {
                        etiqueta.setText("  " + x);
                    } else {
                        etiqueta.setText("" + x);
                    }//redondea a dos dígitos
                    inc = (float) x;
                }
            }
        }
    }

    public void reducir(JLabel etiqueta, float limInf, float var, int idAtributo) {
        double x = Float.parseFloat(etiqueta.getText());
        if (idAtributo == 0) {

            if (x == limInf) {
                if (x < 10) {
                    etiqueta.setText("  " + limInf);
                } else {
                    etiqueta.setText("" + limInf);
                }
                vel = limInf;
            } else {
                x = x - var;
                x = Math.rint(x * 10) / 10;
                if (x < 10) {
                    etiqueta.setText("  " + x);
                } else {
                    etiqueta.setText("" + x);
                }
                vel = (float) x;
            }
        } else {
            if (x == limInf) {
                if (x < 10) {
                    etiqueta.setText("  " + x);
                } else {
                    etiqueta.setText("" + x);
                }
                inc = limInf;
            } else {
                x = x - var;
                x = Math.rint(x * 10) / 10;
                if (x < 10) {
                    etiqueta.setText("  " + x);
                } else {
                    etiqueta.setText("" + x);
                }
                inc = (float) x;
            }
        }
    }

    public void valorBoton(JLabel etiqueta, float n, int idAtritbuto) {
        if (n < 10) {
            etiqueta.setText("  " + n);
        } else {
            etiqueta.setText("" + n);
        }
        if (idAtritbuto == 0) {
            vel = n;
        } else {
            inc = n;
        }
    }

    public String getVelPrestablecido() {
        DecimalFormat formateador = new DecimalFormat("00.0", simbolos);
        float vel0 = progPrest.getVelVuelta(0);
        String vel1 = "" + formateador.format(vel0);
        return vel1;
    }

    public String getIncPrestablecido() {
        DecimalFormat formateador = new DecimalFormat("0.0", simbolos);
        float inc0 = progPrest.getIncVuelta(0);
        String inc1 = "" + formateador.format(inc0);
        return inc1;
    }

    public String getVelString() {
        DecimalFormat formateador = new DecimalFormat("00.0", simbolos);
        float vel0 = vel;
        String vel1 = "" + formateador.format(vel0);
        return vel1;
    }

    public String getIncString() {
        DecimalFormat formateador = new DecimalFormat("#0.0", simbolos);
        float inc0 = inc;
        String inc1 = "" + formateador.format(inc0);
        return inc1;
    }

    public void resetCalorias() {
        calAcum = 0;
    }

    public void resetVuelta() {
        vuelta = 0;
    }

    public void resetKm() {
        distanciaAcum = 0;
    }

    public void resetParametros() {
        vuelta = 0;
        cal = 0;

    }

    public String getCalString() {
        DecimalFormat formateador = new DecimalFormat("00000.00", simbolos);
        String calorias = formateador.format(cal);
        return calorias;
    }

    public String getVueltaPre() {
        return vueltaPre;

    }

    public void calcularVuelta() {
        float distanciaM = distanciaAcum * 1000; //pasa la distancia metros

        int distanciaRedondeada = Math.round(distanciaM);
        vuelta = distanciaRedondeada / distVuelta;
        if (modo == 1) {
            if (vuelta < progPrest.getVueltas()) {
                vel = progPrest.getVelVuelta(vuelta);
                inc = progPrest.getIncVuelta(vuelta);
                vueltaPre = "" + progPrest.getNoVueltaA(vuelta);

            } else {
                estadoPrest = "stop";
                System.out.println("Programa Finalizado");
            }
        }

    }

    public float calcularCal(long intervalo) {
        /*
         Calcula las calorias guardandolas en el atributo y retorna el String 
         para mostrar en pantall
         */
        float calorias;
        float cv;
        float ci;

        cv = (K * (1 + (vel - 5) / 10));
        ci = K * (inc / 12);
        calorias = (cv + ci) / intervalo;
        cal = cal + calorias;
        cal = (float) Math.rint(cal * 100) / 100;

        return cal;

    }

    private float calcularK(int edad, float peso) {
        K = 10 + 10 * ((30 - edad) / 10) + 10 * (peso / 100);                               //  Por si necesita modificar   las calorías deben ser calculadas
        K = 10;
        return K;

    }

    public void calcularKm() {
        distanciaAcum = distanciaAcum + (vel / 3600);
    }

    public String getDistAcumString() {

        DecimalFormat formateador = new DecimalFormat(" 00.00", simbolos);
        float km = (float) Math.rint(distanciaAcum * 100) / 100;
        String kms = "      " + formateador.format(km);
        return kms;

    }

}
