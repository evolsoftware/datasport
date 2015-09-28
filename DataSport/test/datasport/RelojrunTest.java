/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasport;

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
public class RelojrunTest {
    Relojrun reloj;
    
    public RelojrunTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        reloj = new Relojrun();
        reloj.setHoraInicial();
    }
    
    @After
    public void tearDown() {
    }

   

    /**
     * Test of calcularTiempoTranscurrido method, of class Relojrun.
     */
    @Test
    public void testCalcularTiempoTranscurrido() {
        System.out.println("calcularTiempoTranscurrido");
        reloj.calcularTiempoTranscurrido();
        long expResult = 0L;
        long result = reloj.getTiempoTranscurrido();
        assertEquals(expResult, result,0.000000001);
        // TODO review the generated test code and remove the default call to fail.
        
    }

  
    
}
