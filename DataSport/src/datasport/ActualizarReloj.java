/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasport;

import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author Reinaldo Pabon
 */
public class ActualizarReloj implements Runnable {

    //Codigo que se ejecutara durante el thread

    private int stop, llave;
    private JLabel lblReloj, lblTiempo;
    public boolean vivo;                                                        //Para que se repita el run
    private Relojrun reloj;
    private String botonR;
    private DataSport programa;

    public ActualizarReloj(JLabel lblReloj, JLabel lblTiempo, Relojrun reloj/*, int intervalo*/ ) {
        this.lblReloj = lblReloj;
        this.lblTiempo = lblTiempo;
        this.reloj = reloj;
//        this.programa = programa;
        vivo = true;
        stop = 0;
        llave =0;
        botonR="";
    
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    
    public void setBoton(String b)
    {
    botonR=b;
    }
    
   

    @Override
    public void run() {

        while (vivo) {
        
            
            try {
                //calculo innecesario, el cual va a dar 0
                String hora = reloj.getHoraString();   
                //Obtención de la Hora a mostrar en pantalla en el reloj
                //Se ejecuta solo cuando dan la accion de play
                if (botonR.equals("play"))
                {
                    if(stop==0)
                    {
                        reloj.setHoraInicial();
                    }
                String tiempoTranscurrido = reloj.calcularTiempoTranscurrido();
                    
                
                System.out.println("El tiempo del reloj es"+reloj.getTiempoTranscurrido());
                lblTiempo.setText(tiempoTranscurrido);
                stop = stop + 1;
                }
                else
                {
                    if (botonR.equals("pause"))
                    {
                       reloj.saveT(); //guarda el tiempo transcurrido en un temporal
                       stop=0; //para que ingrese al setHora inicial del play
                    }
                    else
                    {
                       reloj.resetsaveT();
                       stop=0;
                       reloj.setHora0(lblTiempo);
                    }
                }
//                if("stop".equals(programa.getEstadoPrest())){
//                   reloj.resetsaveT();
//                       stop=0;
//                       lblTiempo.setText("00:00:00"); 
//                }
                lblReloj.setText(hora);
                Thread.sleep(1000);
//               
            } catch (InterruptedException ex) {
            };
            
            
        }
    
       //Aqui ya no existe el thread, pero puedo seguir trabajando
    }
    
    
}
