/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasport;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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

    private float vel, inc, cal, calAcum, K, distanciaAcum, kms;
    private int vuelta, modo, distVuelta;
    //private int intervalo
    private Programa progPrest;
    private DecimalFormatSymbols simbolos;                                         //Para colocar el simbolo de "." a cal y km

    public DataSport(int distVuelta) {
        //Modo Libre
         this.distVuelta = distVuelta;
        modo = 0;
        K = 10f;
        simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');

    }

    public DataSport(int distVuelta, Programa progPrest) {
        //Prestablecido
        this.distVuelta = distVuelta;
        this.progPrest = progPrest;
        simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        modo = 1;
        K = 10f;
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
                imprVel();
            } else {
                if (x == limSup) {
                    if (x < 10) {
                        etiqueta.setText("  " + limSup);
                    } else {
                        etiqueta.setText("" + limSup);
                    }
                    vel = limSup;
                    imprVel();
                } else {
                    x = x + var;
                    x = Math.rint(x * 10) / 10;
                    if (x < 10) {
                        etiqueta.setText("  " + x);
                    } else {
                        etiqueta.setText("" + x);
                    }
                    vel = (float) x;
                    imprVel();
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
                imprInc();
            } else {
                if (x == limSup) {
                    if (x < 10) {
                        etiqueta.setText("  " + limSup);
                    } else {
                        etiqueta.setText("" + limSup);
                    }
                    inc = limSup;
                    imprInc();
                } else {
                    x = x + var;
                    x = Math.rint(x * 10) / 10;
                    if (x < 10) {
                        etiqueta.setText("  " + x);
                    } else {
                        etiqueta.setText("" + x);
                    }//redondea a dos dígitos
                    inc = (float) x;
                    imprInc();
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
                imprVel();
            } else {
                x = x - var;
                x = Math.rint(x * 10) / 10;
                if (x < 10) {
                    etiqueta.setText("  " + x);
                } else {
                    etiqueta.setText("" + x);
                }
                vel = (float) x;
                imprVel();
            }
        } else {
            if (x == limInf) {
                if (x < 10) {
                    etiqueta.setText("  " + x);
                } else {
                    etiqueta.setText("" + x);
                }
                inc = limInf;
                imprInc();
            } else {
                x = x - var;
                x = Math.rint(x * 10) / 10;
                if (x < 10) {
                    etiqueta.setText("  " + x);
                } else {
                    etiqueta.setText("" + x);
                }
                inc = (float) x;
                imprInc();
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
            imprVel();
        } else {
            inc = n;
            imprInc();
        }
    }

    public String getVelPrestablecido() {
        DecimalFormat formateador = new DecimalFormat("00.0", simbolos);          //Formateo lo que muestro en pantalla
        String vel1 = "" + formateador.format(vel);
        return vel1;
    }

    public String getIncPrestablecido() {
        DecimalFormat formateador = new DecimalFormat("0.0", simbolos);          //Formateo lo que muestro en pantalla
        String inc1 = "" + formateador.format(inc);
        return inc1;
    }

    public void imprVel() {
        System.out.println("La velocidad es: " + vel);
    }

    public void imprInc() {
        System.out.println("La inclinación es: " + inc);
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

    public String getDistAcumString() {
        DecimalFormat formateador = new DecimalFormat(" 00.00", simbolos);          //Formateo lo que muestro en pantalla
        String kms = "      " + formateador.format(distanciaAcum);
        return kms;

    }

    public String getVueltaString() {
        String sV = "" + vuelta;
        return sV;
    }

    public void calcularVuelta() { //replantear con division entera. Modulo no sirve y usar la funcion round de java para redondear
//          System.out.println("Km recorridos (impreso desde calcularVuelta()) "+distanciaAcum);
        float distanciaM = distanciaAcum * 1000; //pasa la distancia metros

        int distanciaRedondeada = Math.round(distanciaM);
//        System.out.println("Distancia redondeada "+distanciaRedondeada);
         vuelta = distanciaRedondeada / distVuelta;
//        System.out.println("va en la vuelta " +vuelta);
        if(modo == 1){
                vel = progPrest.getVelVuelta(vuelta);
//                System.out.println("La velocidad de la vuelta cambiando vuelta es: "+vel);
                inc = progPrest.getIncVuelta(vuelta);
//                System.out.println("La inclinación de la vuelta cambiando vuelta es: "+inc);
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

        // K = 10 + 10*((30-e)/10) + 10*(p/100);                                 Por si necesita modificar   las calorías deben ser calculadas
        //
    }

    public void calcularKm(long tiempo) {
//        System.out.println("La velocidad en calcularKm() es: "+vel);
        distanciaAcum= (vel * tiempo / 3600);
//        distanciaAcum = km;
//        kms = km;
        distanciaAcum = (float) Math.rint(distanciaAcum * 100) / 100;
//        System.out.println("Km recorridos (impreso desde calcularKM()) "+distanciaAcum);

    }

}
