/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasport;

import java.util.ArrayList;
import javax.swing.JLabel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Reinaldo Pabon
 */
public class DataSportTest {

    Programa progPrest1;
    JLabel lblVel, lblInc;
    ArrayList<Vuelta> vueltasProg1;
    DataSport pruebaLibre, pruebaPre;

    public DataSportTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        lblVel = null;
        lblInc = null;
        vueltasProg1 = new ArrayList<Vuelta>();
        Vuelta vuelta10 = new Vuelta(0, 120.0f, 10.0f);
        vueltasProg1.add(vuelta10);
        Vuelta vuelta11 = new Vuelta(1, 130.0f, 10.0f);
        vueltasProg1.add(vuelta11);
        Vuelta vuelta12 = new Vuelta(2, 140.0f, 15.0f);
        vueltasProg1.add(vuelta12);
        Vuelta vuelta13 = new Vuelta(3, 150.0f, 20.0f);
        vueltasProg1.add(vuelta13);
        Vuelta vuelta14 = new Vuelta(4, 160.0f, 1.5f);
        vueltasProg1.add(vuelta14);
        Vuelta vuelta15 = new Vuelta(5, 170.0f, 2.0f);
        vueltasProg1.add(vuelta15);
        progPrest1 = new Programa(1, vueltasProg1);
        pruebaLibre = new DataSport(400);
        pruebaPre = new DataSport(400, progPrest1, lblVel, lblInc);
        pruebaLibre.setInc(6.0f);
        pruebaLibre.setVel(12.0f);

    }

    @After
    public void tearDown() {
    }



    /**
     * Test of calcularVuelta method, of class DataSport.
     */
    @Test
    public void testCalcularVuelta() {
        /*
        Se le coloca 0.8km para ver si calcula dos vueltas
        */
        System.out.println("calcularVuelta");
        pruebaPre.setDistanciaAcum(0.8f);
        int expResult = 2;
        pruebaPre.calcularVuelta();
        int result =  pruebaPre.getVuelta();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calcularCal method, of class DataSport.
     */
    @Test
    public void testCalcularCal() {
        System.out.println("calcularCal");
        pruebaPre.setCal(0);
        pruebaPre.setVel(1.0f);
        pruebaPre.setInc(1.0f);
        float cv = (float) (10 * (1 + (1.0 - 5) / 10));
        float ci = (float) (10 * (1.0 / 12));
        long intervalo = 60L;
        float calorias = (cv + ci) / intervalo;
        float expResult = calorias;
        float result = pruebaPre.calcularCal(intervalo);
        assertEquals(expResult, result, 0.1);

    }

    /**
     * Test of calcularKm method, of class DataSport.
     */
    @Test
    public void testCalcularKm() {
        System.out.println("calcularKm");
        pruebaPre.setVel(12.0f);
        float expResult = 0.003333333f;
        pruebaPre.calcularKm();
        float result =  pruebaPre.getDistanciaAcum();
        assertEquals(expResult, result, 0.1);
    }

}