/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasport;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Esta clase se encarga de crear tres vectores. Intensidades,velocidades y
 * vueltas. Inicialmente estos valores son incrementales. Mas adelante se podran
 * modificar desde un txt
 *
 * @author 123
 */
public class Programa {

    private int noPrograma;
//Tipo [ ] nombreDelArray = new Tipo [número];
    private ArrayList<Vuelta> vueltas;
    private Iterator<Vuelta> iterador;


    public Programa(int noPrograma, ArrayList<Vuelta> vuelta) {
        this.vueltas = vuelta;
        this.noPrograma = noPrograma;
    }

    public ArrayList<Vuelta> getVuelta() {
        return vueltas;
    }

    public void setVuelta(ArrayList<Vuelta> vuelta) {
        this.vueltas = vuelta;
    }
    

    public int getNoPrograma() {
        return noPrograma;
    }



    public void setNoPrograma(int noPrograma) {
        this.noPrograma = noPrograma;
    }

 

    public int getNoVueltaA(int vuelta){
        int noVuelta = vueltas.get(vuelta).getNoVuelta();
        return noVuelta;
    }
    
    public Iterator getIterador(){
        iterador = vueltas.iterator();
        return iterador;
    }
    public Vuelta nextIterador(){
        Vuelta element = iterador.next(); 
        return element;
    }

    public int getVueltas(){
        int noVueltas = vueltas.size();
        return noVueltas;
    }
    public float getVelVuelta(int vuelta){
        float velVuelta = vueltas.get(vuelta).getVel();
        return velVuelta;
    }
    
    public float getIncVuelta(int vuelta){
        float incVuelta = vueltas.get(vuelta).getInc();
        return incVuelta;
    }
    
  

    }


