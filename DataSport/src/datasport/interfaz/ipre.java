/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasport.interfaz;

import datasport.ActualizarMetricas;
import datasport.ActualizarReloj;
import datasport.DataSport;
import datasport.Relojrun;
import datasport.Lectora;
import datasport.Programa;
import datasport.Vuelta;
import java.util.ArrayList;
import basededatos.Queries;

/**
 *
 * @author anton lbl=label btt=button
 */
public class ipre extends javax.swing.JFrame {

    /**
     * Creates new form Gui
     */
    Thread hiloReloj, hiloMetricas;
    private ActualizarReloj actualizadorReloj;
    private Relojrun reloj;
    private DataSport sesion, sesionPre;
//    private int modo = 0, click = 0;  //seleccion de modo en 2, 
    private ActualizarMetricas actualizadorMetricas;
    private String boton = "";
    private long intervaloCalculoCalorias, intervaloMostrarPantallaCal;
    private Programa progPrest2, progPrest0, progPrest1;
    private Queries q;

    //Datos que se obtienen del txt 
    private Lectora leer;
    private float limInf, limSup, inc, vel, k, limSupInc, limInfInc, increVel, increInc;
    private int noLaps, modo, distVuelta;

    /**
     * Creates new form inface
     */
    public ipre() {
        initComponents();
        q = new Queries();
        q.crearTabla();
        esconder();
        inicializarDatosLectora();
        inicializar();
        cambio();
        sesion = new DataSport(distVuelta);
        reloj = new Relojrun();
        actualizadorReloj = new ActualizarReloj(lblReloj, lblTiempo, reloj);
        hiloReloj = new Thread(actualizadorReloj, "hiloReloj");
        hiloReloj.start();

        actualizadorMetricas = new ActualizarMetricas(lblCalorias, lblKms,
                lblNoVuelta, lblVel, lblInc, reloj, sesion, intervaloCalculoCalorias, intervaloMostrarPantallaCal);
        hiloMetricas = new Thread(actualizadorMetricas, "hiloMetricas");
        hiloMetricas.start();
//        inicializarHilosRunnables();
        modo = 0;

        actualizadorMetricas.llevarEtiquetas(VueltasFin, IncPromFin, VelPromFin,
                CalFin, KmFin, TiempoTFin, Resultados, UVueltasFin, UIncPromFin,
                UVelProm, UCalFin, UKmFin, UTiempoTFin, lblConsola);
    }
    /*
     Esconde los elementos que se muestran al terminar las sesiones
     */

    public void esconder() {
        VueltasFin.setVisible(false);
        IncPromFin.setVisible(false);
        VelPromFin.setVisible(false);
        CalFin.setVisible(false);
        KmFin.setVisible(false);
        TiempoTFin.setVisible(false);
        Resultados.setVisible(false);
        UVueltasFin.setVisible(false);
        UIncPromFin.setVisible(false);
        UVelProm.setVisible(false);
        UCalFin.setVisible(false);
        UKmFin.setVisible(false);
        UTiempoTFin.setVisible(false);
    
    }

    //datos a partir del txt
    public void inicializarDatosLectora() {
        leer = new Lectora("D:\\Ingenieria de Software I\\proye\\fn\\datasport\\DataSport\\src\\datasport\\config.txt");
        limInf = leer.getLimInf();
        limSup = leer.getLimSup();
        //inc = leer.getInc();
        // vel = leer.getVel();
        // k = leer.getK();
        noLaps = leer.getNoLaps();
        intervaloCalculoCalorias = leer.getIntCal();
        intervaloMostrarPantallaCal = leer.getIntPan();
        limInfInc = leer.getLimInfInc();
        limSupInc = leer.getLimSupInc();
        increVel = leer.getIncreVel();
        increInc = leer.getIncreInc();
        distVuelta = leer.getDistVuelta();

    }

    public void inicializar() {
//        reloj = new Relojrun();
//        actualizadorReloj = new ActualizarReloj(lblReloj, lblTiempo, reloj);
        ArrayList<Vuelta> vueltasProg0 = new ArrayList<Vuelta>();
        //Creación del programa 0
        Vuelta vuelta0 = new Vuelta(0, 0.0f, 0.0f);
        vueltasProg0.add(vuelta0);
        progPrest0 = new Programa(0, vueltasProg0);
        //Creación del programa1
        ArrayList<Vuelta> vueltasProg1 = new ArrayList<Vuelta>();
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
        //Creación del programa2
        ArrayList<Vuelta> vueltasProg2 = new ArrayList<Vuelta>();
        Vuelta vuelta20 = new Vuelta(0, 1.0f, 1.0f);
        vueltasProg2.add(vuelta20);
        Vuelta vuelta21 = new Vuelta(1, 1.0f, 1.0f);
        vueltasProg2.add(vuelta21);
        Vuelta vuelta22 = new Vuelta(2, 2.0f, 3.0f);
        vueltasProg2.add(vuelta22);
        Vuelta vuelta23 = new Vuelta(3, 3.0f, 5.0f);
        vueltasProg2.add(vuelta23);
        progPrest2 = new Programa(2, vueltasProg2);
        /*
         Se inicializa la instancia del modoLibre
         */
//        sesion = new DataSport(distVuelta);
//        actualizadorMetricas = new ActualizarMetricas(lblCalorias, lblKms,
//                lblVueltaFijo, lblVel, lblInc, reloj, sesion, intervaloCalculoCalorias, intervaloMostrarPantallaCal);

    }

    public void inicializarHilosRunnables() {
//        reloj = new Relojrun();
//        actualizadorReloj = new ActualizarReloj(lblReloj, lblTiempo, reloj);
//        hiloReloj = new Thread(actualizadorReloj, "hiloReloj");
//        hiloReloj.start();
//
//        actualizadorMetricas = new ActualizarMetricas(lblCalorias, lblKms,
//                lblNoVuelta, lblVel, lblInc, reloj, sesion, intervaloCalculoCalorias, intervaloMostrarPantallaCal);
//        hiloMetricas = new Thread(actualizadorMetricas, "hiloMetricas");
//        hiloMetricas.start();

    }

    public void cambio() {
        if (modo == 0) {

            lblTitulo.setText("          MODO LIBRE          ");
            lblModoNo.setVisible(false);

        } else {

            lblTitulo.setVisible(true);
            lblModoNo.setVisible(true);
            lblTitulo.setText("MODO PRESTABLECIDO");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        bttVel2 = new javax.swing.JButton();
        bttVel1 = new javax.swing.JButton();
        bttVel4 = new javax.swing.JButton();
        bttVel3 = new javax.swing.JButton();
        bttVel5 = new javax.swing.JButton();
        bttVel6 = new javax.swing.JButton();
        bttVel8 = new javax.swing.JButton();
        bttVel7 = new javax.swing.JButton();
        bttVel9 = new javax.swing.JButton();
        bttVel10 = new javax.swing.JButton();
        bttVel12 = new javax.swing.JButton();
        bttVelMenos = new javax.swing.JButton();
        bttVelMas = new javax.swing.JButton();
        bttVel11 = new javax.swing.JButton();
        panelPpal = new javax.swing.JPanel();
        lblModoNo = new javax.swing.JLabel();
        lblNoVuelta = new javax.swing.JLabel();
        lblCalorias = new javax.swing.JLabel();
        lblCaloriasFijo = new javax.swing.JLabel();
        lblKmFijo = new javax.swing.JLabel();
        lblVel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblInc = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblGradosFijo = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblKmFIjo = new javax.swing.JLabel();
        lblKms = new javax.swing.JLabel();
        lblReloj = new javax.swing.JLabel();
        lblTiempo = new javax.swing.JLabel();
        lblConsola = new javax.swing.JLabel();
        lblVueltaFijo = new javax.swing.JLabel();
        lblTiempoFijo = new javax.swing.JLabel();
        VueltasFin = new javax.swing.JLabel();
        VelPromFin = new javax.swing.JLabel();
        CalFin = new javax.swing.JLabel();
        KmFin = new javax.swing.JLabel();
        TiempoTFin = new javax.swing.JLabel();
        IncPromFin = new javax.swing.JLabel();
        UVueltasFin = new javax.swing.JLabel();
        UIncPromFin = new javax.swing.JLabel();
        UVelProm = new javax.swing.JLabel();
        UCalFin = new javax.swing.JLabel();
        UKmFin = new javax.swing.JLabel();
        UTiempoTFin = new javax.swing.JLabel();
        Resultados = new javax.swing.JLabel();
        bttPlay = new javax.swing.JButton();
        bttPause = new javax.swing.JButton();
        bttStop = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        bttInc2 = new javax.swing.JButton();
        bttInc3 = new javax.swing.JButton();
        bttInc4 = new javax.swing.JButton();
        bttInc5 = new javax.swing.JButton();
        bttInc6 = new javax.swing.JButton();
        bttIncMenos = new javax.swing.JButton();
        bttIncMas = new javax.swing.JButton();
        bttInc0 = new javax.swing.JButton();
        bttInc1 = new javax.swing.JButton();
        bttSpeed = new javax.swing.JButton();
        bttOff = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        bttVel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/2.png"))); // NOI18N
        bttVel2.setBorder(null);
        bttVel2.setBorderPainted(false);
        bttVel2.setContentAreaFilled(false);
        bttVel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttVel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttVel2.setIconTextGap(-3);
        bttVel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttVel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttVel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttVel2MouseClicked(evt);
            }
        });

        bttVel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1.png"))); // NOI18N
        bttVel1.setBorder(null);
        bttVel1.setBorderPainted(false);
        bttVel1.setContentAreaFilled(false);
        bttVel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttVel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttVel1.setIconTextGap(-3);
        bttVel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttVel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttVel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttVel1MouseClicked(evt);
            }
        });

        bttVel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/4.png"))); // NOI18N
        bttVel4.setBorder(null);
        bttVel4.setBorderPainted(false);
        bttVel4.setContentAreaFilled(false);
        bttVel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttVel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttVel4.setIconTextGap(-3);
        bttVel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttVel4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttVel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttVel4MouseClicked(evt);
            }
        });

        bttVel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/3.png"))); // NOI18N
        bttVel3.setBorder(null);
        bttVel3.setBorderPainted(false);
        bttVel3.setContentAreaFilled(false);
        bttVel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttVel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttVel3.setIconTextGap(-3);
        bttVel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttVel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttVel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttVel3MouseClicked(evt);
            }
        });

        bttVel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/5.png"))); // NOI18N
        bttVel5.setBorder(null);
        bttVel5.setBorderPainted(false);
        bttVel5.setContentAreaFilled(false);
        bttVel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttVel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttVel5.setIconTextGap(-3);
        bttVel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttVel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttVel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttVel5MouseClicked(evt);
            }
        });
        bttVel5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttVel5ActionPerformed(evt);
            }
        });

        bttVel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/6.png"))); // NOI18N
        bttVel6.setBorder(null);
        bttVel6.setBorderPainted(false);
        bttVel6.setContentAreaFilled(false);
        bttVel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttVel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttVel6.setIconTextGap(-3);
        bttVel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttVel6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttVel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttVel6MouseClicked(evt);
            }
        });

        bttVel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/8.png"))); // NOI18N
        bttVel8.setBorder(null);
        bttVel8.setBorderPainted(false);
        bttVel8.setContentAreaFilled(false);
        bttVel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttVel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttVel8.setIconTextGap(-3);
        bttVel8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttVel8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttVel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttVel8MouseClicked(evt);
            }
        });

        bttVel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/7.png"))); // NOI18N
        bttVel7.setBorder(null);
        bttVel7.setBorderPainted(false);
        bttVel7.setContentAreaFilled(false);
        bttVel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttVel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttVel7.setIconTextGap(-3);
        bttVel7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttVel7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttVel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttVel7MouseClicked(evt);
            }
        });
        bttVel7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttVel7ActionPerformed(evt);
            }
        });

        bttVel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/9.png"))); // NOI18N
        bttVel9.setBorder(null);
        bttVel9.setBorderPainted(false);
        bttVel9.setContentAreaFilled(false);
        bttVel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttVel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttVel9.setIconTextGap(-3);
        bttVel9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttVel9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttVel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttVel9MouseClicked(evt);
            }
        });

        bttVel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/10.png"))); // NOI18N
        bttVel10.setBorder(null);
        bttVel10.setBorderPainted(false);
        bttVel10.setContentAreaFilled(false);
        bttVel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttVel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttVel10.setIconTextGap(-3);
        bttVel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttVel10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttVel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttVel10MouseClicked(evt);
            }
        });

        bttVel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/12.png"))); // NOI18N
        bttVel12.setBorder(null);
        bttVel12.setBorderPainted(false);
        bttVel12.setContentAreaFilled(false);
        bttVel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttVel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttVel12.setIconTextGap(-3);
        bttVel12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttVel12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttVel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttVel12MouseClicked(evt);
            }
        });

        bttVelMenos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menos.png"))); // NOI18N
        bttVelMenos.setBorder(null);
        bttVelMenos.setBorderPainted(false);
        bttVelMenos.setContentAreaFilled(false);
        bttVelMenos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttVelMenos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttVelMenos.setIconTextGap(-3);
        bttVelMenos.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttVelMenos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttVelMenos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttVelMenosMouseClicked(evt);
            }
        });

        bttVelMas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mas.png"))); // NOI18N
        bttVelMas.setBorder(null);
        bttVelMas.setBorderPainted(false);
        bttVelMas.setContentAreaFilled(false);
        bttVelMas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttVelMas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttVelMas.setIconTextGap(-3);
        bttVelMas.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttVelMas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttVelMas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttVelMasMouseClicked(evt);
            }
        });

        bttVel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/11.png"))); // NOI18N
        bttVel11.setBorder(null);
        bttVel11.setBorderPainted(false);
        bttVel11.setContentAreaFilled(false);
        bttVel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttVel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttVel11.setIconTextGap(-3);
        bttVel11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttVel11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttVel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttVel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bttVel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttVel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bttVel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bttVel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bttVel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bttVel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bttVel8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bttVel9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bttVel10, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bttVel11, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bttVel6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttVel12, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bttVelMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bttVelMas, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bttVel2)
                    .addComponent(bttVel1)
                    .addComponent(bttVel5)
                    .addComponent(bttVel4)
                    .addComponent(bttVel6)
                    .addComponent(bttVel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bttVel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bttVel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bttVel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bttVel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bttVel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bttVel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bttVelMas)
                    .addComponent(bttVelMenos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPpal.setBackground(new java.awt.Color(240, 103, 33));
        panelPpal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 60)));
        panelPpal.setToolTipText("");
        panelPpal.setMaximumSize(new java.awt.Dimension(640, 360));
        panelPpal.setMinimumSize(new java.awt.Dimension(640, 360));
        panelPpal.setPreferredSize(new java.awt.Dimension(640, 360));
        panelPpal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblModoNo.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 36)); // NOI18N
        lblModoNo.setForeground(new java.awt.Color(51, 51, 60));
        lblModoNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblModoNo.setText("...");
        lblModoNo.setToolTipText("");
        panelPpal.add(lblModoNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, 90, -1));

        lblNoVuelta.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 55)); // NOI18N
        lblNoVuelta.setForeground(new java.awt.Color(51, 51, 60));
        lblNoVuelta.setText("0");
        panelPpal.add(lblNoVuelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, -1));

        lblCalorias.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 24)); // NOI18N
        lblCalorias.setForeground(new java.awt.Color(51, 51, 60));
        lblCalorias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCalorias.setText("00000.00");
        panelPpal.add(lblCalorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 180, 120, -1));

        lblCaloriasFijo.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 24)); // NOI18N
        lblCaloriasFijo.setForeground(new java.awt.Color(51, 51, 60));
        lblCaloriasFijo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCaloriasFijo.setText("Calorias");
        panelPpal.add(lblCaloriasFijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 90, -1));

        lblKmFijo.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblKmFijo.setForeground(new java.awt.Color(51, 51, 60));
        lblKmFijo.setText("Km/h");
        panelPpal.add(lblKmFijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, -1, -1));

        lblVel.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 80)); // NOI18N
        lblVel.setForeground(new java.awt.Color(51, 51, 60));
        lblVel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVel.setText("  0.0");
        lblVel.setToolTipText("");
        panelPpal.add(lblVel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 190, -1));
        panelPpal.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 92, -1, -1));

        lblInc.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 80)); // NOI18N
        lblInc.setForeground(new java.awt.Color(51, 51, 60));
        lblInc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblInc.setText("  0.0");
        panelPpal.add(lblInc, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 190, -1));
        panelPpal.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(506, 92, -1, -1));

        lblGradosFijo.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 90)); // NOI18N
        lblGradosFijo.setForeground(new java.awt.Color(51, 51, 60));
        lblGradosFijo.setText("°");
        panelPpal.add(lblGradosFijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 180, -1, 70));
        panelPpal.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(738, 74, -1, -1));

        lblTitulo.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(51, 51, 60));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Modo Prestablecido");
        panelPpal.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 450, -1));

        lblKmFIjo.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 24)); // NOI18N
        lblKmFIjo.setForeground(new java.awt.Color(51, 51, 60));
        lblKmFIjo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKmFIjo.setText("Km recorridos");
        panelPpal.add(lblKmFIjo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 160, -1));

        lblKms.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 24)); // NOI18N
        lblKms.setForeground(new java.awt.Color(51, 51, 60));
        lblKms.setText("       00.00");
        lblKms.setToolTipText("");
        panelPpal.add(lblKms, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 150, -1));

        lblReloj.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 36)); // NOI18N
        lblReloj.setForeground(new java.awt.Color(51, 51, 60));
        lblReloj.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblReloj.setText("00:00:00");
        panelPpal.add(lblReloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 44));

        lblTiempo.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 70)); // NOI18N
        lblTiempo.setForeground(new java.awt.Color(51, 51, 60));
        lblTiempo.setText("00:00:00");
        lblTiempo.setToolTipText("");
        panelPpal.add(lblTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 92));

        lblConsola.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 36)); // NOI18N
        lblConsola.setForeground(new java.awt.Color(51, 51, 60));
        lblConsola.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelPpal.add(lblConsola, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 720, 40));

        lblVueltaFijo.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 24)); // NOI18N
        lblVueltaFijo.setForeground(new java.awt.Color(51, 51, 60));
        lblVueltaFijo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVueltaFijo.setText("Vuelta No.");
        panelPpal.add(lblVueltaFijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 125, 42));

        lblTiempoFijo.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 24)); // NOI18N
        lblTiempoFijo.setForeground(new java.awt.Color(51, 51, 60));
        lblTiempoFijo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTiempoFijo.setText("Tiempo Transcurrido");
        panelPpal.add(lblTiempoFijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        VueltasFin.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 24)); // NOI18N
        VueltasFin.setForeground(new java.awt.Color(51, 51, 60));
        VueltasFin.setText("Vueltas");
        panelPpal.add(VueltasFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        VelPromFin.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 24)); // NOI18N
        VelPromFin.setForeground(new java.awt.Color(51, 51, 60));
        VelPromFin.setText("Velocidad Promedio");
        panelPpal.add(VelPromFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        CalFin.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 24)); // NOI18N
        CalFin.setForeground(new java.awt.Color(51, 51, 60));
        CalFin.setText("Calorias quemadas");
        panelPpal.add(CalFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, -1, -1));

        KmFin.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 24)); // NOI18N
        KmFin.setForeground(new java.awt.Color(51, 51, 60));
        KmFin.setText("Kilometros recorridos");
        panelPpal.add(KmFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, -1, -1));

        TiempoTFin.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 24)); // NOI18N
        TiempoTFin.setForeground(new java.awt.Color(51, 51, 60));
        TiempoTFin.setText("Tiempo total");
        panelPpal.add(TiempoTFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, -1, -1));

        IncPromFin.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 24)); // NOI18N
        IncPromFin.setForeground(new java.awt.Color(51, 51, 60));
        IncPromFin.setText("Inclinacion Promedio");
        panelPpal.add(IncPromFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        UVueltasFin.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 24)); // NOI18N
        UVueltasFin.setForeground(new java.awt.Color(51, 51, 60));
        UVueltasFin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UVueltasFin.setText("0");
        panelPpal.add(UVueltasFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 270, 30, -1));

        UIncPromFin.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 24)); // NOI18N
        UIncPromFin.setForeground(new java.awt.Color(51, 51, 60));
        UIncPromFin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UIncPromFin.setText("0.0");
        panelPpal.add(UIncPromFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 310, 70, -1));

        UVelProm.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 24)); // NOI18N
        UVelProm.setForeground(new java.awt.Color(51, 51, 60));
        UVelProm.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UVelProm.setText("0.0");
        panelPpal.add(UVelProm, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 350, 70, -1));

        UCalFin.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 24)); // NOI18N
        UCalFin.setForeground(new java.awt.Color(51, 51, 60));
        UCalFin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UCalFin.setText("00000.00");
        panelPpal.add(UCalFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 270, 110, -1));

        UKmFin.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 24)); // NOI18N
        UKmFin.setForeground(new java.awt.Color(51, 51, 60));
        UKmFin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UKmFin.setText("0");
        panelPpal.add(UKmFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 310, 70, -1));

        UTiempoTFin.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 24)); // NOI18N
        UTiempoTFin.setForeground(new java.awt.Color(51, 51, 60));
        UTiempoTFin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UTiempoTFin.setText("00:00:00");
        panelPpal.add(UTiempoTFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 350, 100, -1));
        panelPpal.add(Resultados, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, -1, -1));

        bttPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/play.png"))); // NOI18N
        bttPlay.setBorder(null);
        bttPlay.setBorderPainted(false);
        bttPlay.setContentAreaFilled(false);
        bttPlay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttPlay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttPlay.setIconTextGap(-3);
        bttPlay.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttPlay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttPlayMouseClicked(evt);
            }
        });

        bttPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pause.png"))); // NOI18N
        bttPause.setBorder(null);
        bttPause.setBorderPainted(false);
        bttPause.setContentAreaFilled(false);
        bttPause.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttPause.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttPause.setIconTextGap(-3);
        bttPause.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttPause.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttPause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttPauseMouseClicked(evt);
            }
        });
        bttPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttPauseActionPerformed(evt);
            }
        });

        bttStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/stop.png"))); // NOI18N
        bttStop.setBorder(null);
        bttStop.setBorderPainted(false);
        bttStop.setContentAreaFilled(false);
        bttStop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttStop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttStop.setIconTextGap(-3);
        bttStop.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttStop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttStop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttStopMouseClicked(evt);
            }
        });
        bttStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttStopActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Play", 0, 18)); // NOI18N
        jLabel1.setText("Seleccion de modo prestablecido");

        jLabel2.setFont(new java.awt.Font("Play", 0, 18)); // NOI18N
        jLabel2.setText("Panel de Control de Inclinacion");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        bttInc2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/2.png"))); // NOI18N
        bttInc2.setBorder(null);
        bttInc2.setBorderPainted(false);
        bttInc2.setContentAreaFilled(false);
        bttInc2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttInc2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttInc2.setIconTextGap(-3);
        bttInc2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttInc2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttInc2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttInc2MouseClicked(evt);
            }
        });

        bttInc3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/3.png"))); // NOI18N
        bttInc3.setBorder(null);
        bttInc3.setBorderPainted(false);
        bttInc3.setContentAreaFilled(false);
        bttInc3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttInc3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttInc3.setIconTextGap(-3);
        bttInc3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttInc3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttInc3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttInc3MouseClicked(evt);
            }
        });

        bttInc4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/4.png"))); // NOI18N
        bttInc4.setBorder(null);
        bttInc4.setBorderPainted(false);
        bttInc4.setContentAreaFilled(false);
        bttInc4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttInc4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttInc4.setIconTextGap(-3);
        bttInc4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttInc4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttInc4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttInc4MouseClicked(evt);
            }
        });

        bttInc5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/5.png"))); // NOI18N
        bttInc5.setBorder(null);
        bttInc5.setBorderPainted(false);
        bttInc5.setContentAreaFilled(false);
        bttInc5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttInc5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttInc5.setIconTextGap(-3);
        bttInc5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttInc5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttInc5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttInc5MouseClicked(evt);
            }
        });

        bttInc6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/6.png"))); // NOI18N
        bttInc6.setBorder(null);
        bttInc6.setBorderPainted(false);
        bttInc6.setContentAreaFilled(false);
        bttInc6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttInc6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttInc6.setIconTextGap(-3);
        bttInc6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttInc6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttInc6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttInc6MouseClicked(evt);
            }
        });

        bttIncMenos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menos.png"))); // NOI18N
        bttIncMenos.setBorder(null);
        bttIncMenos.setBorderPainted(false);
        bttIncMenos.setContentAreaFilled(false);
        bttIncMenos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttIncMenos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttIncMenos.setIconTextGap(-3);
        bttIncMenos.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttIncMenos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttIncMenos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttIncMenosMouseClicked(evt);
            }
        });

        bttIncMas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mas.png"))); // NOI18N
        bttIncMas.setBorder(null);
        bttIncMas.setBorderPainted(false);
        bttIncMas.setContentAreaFilled(false);
        bttIncMas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttIncMas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttIncMas.setIconTextGap(-3);
        bttIncMas.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttIncMas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttIncMas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttIncMasMouseClicked(evt);
            }
        });

        bttInc0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/0.png"))); // NOI18N
        bttInc0.setBorder(null);
        bttInc0.setBorderPainted(false);
        bttInc0.setContentAreaFilled(false);
        bttInc0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttInc0.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttInc0.setIconTextGap(-3);
        bttInc0.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttInc0.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttInc0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttInc0MouseClicked(evt);
            }
        });

        bttInc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1.png"))); // NOI18N
        bttInc1.setBorder(null);
        bttInc1.setBorderPainted(false);
        bttInc1.setContentAreaFilled(false);
        bttInc1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttInc1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttInc1.setIconTextGap(-3);
        bttInc1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttInc1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttInc1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttInc1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(bttInc4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bttInc5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bttInc6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bttInc0, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bttInc1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                        .addGap(2, 2, 2)
                        .addComponent(bttInc2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bttInc3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addComponent(bttIncMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bttIncMas, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bttInc3)
                            .addComponent(bttInc2)
                            .addComponent(bttInc0)
                            .addComponent(bttInc1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bttInc6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bttInc5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bttInc4, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bttIncMenos)
                            .addComponent(bttIncMas))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        bttSpeed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/speed.png"))); // NOI18N
        bttSpeed.setBorder(null);
        bttSpeed.setBorderPainted(false);
        bttSpeed.setContentAreaFilled(false);
        bttSpeed.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttSpeed.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttSpeed.setIconTextGap(-3);
        bttSpeed.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttSpeed.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttSpeed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttSpeedMouseClicked(evt);
            }
        });
        bttSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttSpeedActionPerformed(evt);
            }
        });

        bttOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/off.png"))); // NOI18N
        bttOff.setBorder(null);
        bttOff.setBorderPainted(false);
        bttOff.setContentAreaFilled(false);
        bttOff.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttOff.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttOff.setIconTextGap(-3);
        bttOff.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bttOff.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttOff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttOffMouseClicked(evt);
            }
        });
        bttOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttOffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bttSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bttOff, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelPpal, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bttPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bttPause, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bttStop, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(bttOff)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bttSpeed))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bttStop)
                                    .addComponent(bttPause)
                                    .addComponent(bttPlay))))
                        .addGap(211, 211, 211))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(panelPpal, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttInc1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttInc1MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblInc, 1.0f, 1);
        } else {
            //
        }
    }//GEN-LAST:event_bttInc1MouseClicked

    private void bttVel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttVel11MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblVel, 11.0f, 0);
        } else {
            //
        }
    }//GEN-LAST:event_bttVel11MouseClicked

    private void bttVel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttVel2MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblVel, 2.0f, 0);
        } else {
            sesion.setProgPrest(progPrest2);
            lblModoNo.setText("No. 2");
            lblVel.setText(sesion.getVelPrestablecido());
            lblInc.setText(sesion.getIncPrestablecido());
        }
    }//GEN-LAST:event_bttVel2MouseClicked

    private void bttVel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttVel3MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblVel, 3.0f, 0);
        } else {

        }

    }//GEN-LAST:event_bttVel3MouseClicked

    private void bttVel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttVel5MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblVel, 5.0f, 0);
        } else {
            //
        }

    }//GEN-LAST:event_bttVel5MouseClicked

    private void bttVel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttVel7MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblVel, 7.0f, 0);
        } else {
            //
        }

    }//GEN-LAST:event_bttVel7MouseClicked

    private void bttVel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttVel9MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblVel, 9.0f, 0);
        } else {
            //
        }

    }//GEN-LAST:event_bttVel9MouseClicked

    private void bttVelMenosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttVelMenosMouseClicked
        if (modo == 0) {
            sesion.reducir(lblVel, limInf, increVel, 0);
        } else {
            //
        }


    }//GEN-LAST:event_bttVelMenosMouseClicked

    private void bttVel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttVel1MouseClicked
        if (modo == 0) {

            sesion.valorBoton(lblVel, 1.0f, 0);
        } else {

            sesion.setProgPrest(progPrest1);
            lblModoNo.setText("No. 1");
            lblVel.setText(sesion.getVelPrestablecido());
            lblInc.setText(sesion.getIncPrestablecido());
//            sesion.setModo(1);
//            System.out.println("Velocidad del prestablecido al oprimir 1 " + sesion.getVel());
//            System.out.println("Inclinación del prestablecido al oprimir 1 " + sesion.getInc());
        }

    }//GEN-LAST:event_bttVel1MouseClicked

    private void bttVel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttVel4MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblVel, 4.0f, 0);
        } else {
            //
        }

    }//GEN-LAST:event_bttVel4MouseClicked

    private void bttVel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttVel6MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblVel, 6.0f, 0);
        } else {
            //
        }

    }//GEN-LAST:event_bttVel6MouseClicked

    private void bttVel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttVel8MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblVel, 8.0f, 0);
        } else {
            //
        }

    }//GEN-LAST:event_bttVel8MouseClicked

    private void bttVel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttVel10MouseClicked

        if (modo == 0) {
            sesion.valorBoton(lblVel, 10.0f, 0);
        } else {
            //
        }

    }//GEN-LAST:event_bttVel10MouseClicked

    private void bttVel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttVel12MouseClicked

        if (modo == 0) {
            sesion.valorBoton(lblVel, 12.0f, 0);
        } else {
            //
        }
    }//GEN-LAST:event_bttVel12MouseClicked

    private void bttVelMasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttVelMasMouseClicked
        if (modo == 0) {
            sesion.aumentar(lblVel, limSup, limInf, increVel, 0);
        } else {
            //
        }
    }//GEN-LAST:event_bttVelMasMouseClicked

    private void bttVel7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttVel7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bttVel7ActionPerformed

    private void bttInc2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttInc2MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblInc, 2.0f, 1);
        } else {
            //
        }
    }//GEN-LAST:event_bttInc2MouseClicked

    private void bttInc3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttInc3MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblInc, 3.0f, 1);
        } else {
            //
        }
    }//GEN-LAST:event_bttInc3MouseClicked

    private void bttInc4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttInc4MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblInc, 4.0f, 1);
        } else {
            //
        }
    }//GEN-LAST:event_bttInc4MouseClicked

    private void bttInc5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttInc5MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblInc, 5.0f, 1);
        } else {
            //
        }
    }//GEN-LAST:event_bttInc5MouseClicked

    private void bttInc6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttInc6MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblInc, 6.0f, 1);
        } else {
            //
        }
    }//GEN-LAST:event_bttInc6MouseClicked

    private void bttIncMenosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttIncMenosMouseClicked
        if (modo == 0) {
            sesion.reducir(lblInc, limInfInc, increInc, 1);
        } else {
            //
        }
    }//GEN-LAST:event_bttIncMenosMouseClicked

    private void bttIncMasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttIncMasMouseClicked
        if (modo == 0) {
            sesion.aumentar(lblInc, limSupInc, limInfInc, increInc, 1);
        } else {
            //
        }
    }//GEN-LAST:event_bttIncMasMouseClicked

    private void bttSpeedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttSpeedMouseClicked
//        programa.setModo(1);
        //sesionPre = new DataSport(distVuelta, progPrest1);
        actualizadorMetricas.setVivo(false);
        actualizadorReloj.setVivo(false);
        sesion = new DataSport(distVuelta, progPrest0, lblVel, lblInc);
        reloj = new Relojrun();
        actualizadorReloj = new ActualizarReloj(lblReloj, lblTiempo, reloj);
        hiloReloj = new Thread(actualizadorReloj, "hiloReloj");
        hiloReloj.start();
        lblConsola.setText("Seleccione el nivel de intensidad deseado");
        actualizadorMetricas = new ActualizarMetricas(lblCalorias, lblKms,
                lblNoVuelta, lblVel, lblInc, reloj, sesion, intervaloCalculoCalorias, intervaloMostrarPantallaCal);
        hiloMetricas = new Thread(actualizadorMetricas, "hiloMetricas");
        hiloMetricas.start();
        actualizadorMetricas.llevarEtiquetas(VueltasFin, IncPromFin, VelPromFin,
                CalFin, KmFin, TiempoTFin, Resultados, UVueltasFin, UIncPromFin,
                UVelProm, UCalFin, UKmFin, UTiempoTFin, lblConsola);

//        inicializarHilosRunnables();
        modo = 1;
        cambio();
        esconder();
        sesion.resetArray();


    }//GEN-LAST:event_bttSpeedMouseClicked

    private void bttSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttSpeedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bttSpeedActionPerformed

    private void bttStopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttStopMouseClicked
        actualizadorReloj.setBoton("stop");
        actualizadorMetricas.setBoton("stop");
        esconder();
        sesion.resetArray();
        // TODO add your handling code here:
    }//GEN-LAST:event_bttStopMouseClicked

    private void bttOffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttOffMouseClicked

        q.eliminarTabla();
        System.exit(0);

        // TODO add your handling code here:
    }//GEN-LAST:event_bttOffMouseClicked

    private void bttPlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttPlayMouseClicked
        // TODO add your handling code here:
        String texto;

        if (modo == 0) {

            if (sesion.getVel() == 0) {
                texto = "<html><body>Seleccione la Velocidad y la Inclinación</body></html>";
                lblConsola.setText(texto);
            } else {
                texto = "<html><body>Modo Libre En Ejecución</body></html>";
                esconder();
                sesion.resetArray();

                lblConsola.setText(texto);
                actualizadorReloj.setBoton("play");
                actualizadorMetricas.setBoton("play");
            }
        } else {
            texto = "<html><body>Modo Prestablecido En Ejecución</body></html>";

            esconder();
            sesion.resetArray();

            lblConsola.setText(texto);
            actualizadorMetricas.setBoton("play");
            actualizadorReloj.setBoton("play");
        }

    }//GEN-LAST:event_bttPlayMouseClicked

    private void bttVel5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttVel5ActionPerformed
        if (modo == 0) {
            sesion.valorBoton(lblVel, 5.0f, 0);
        } else {
            //
        }   // TODO add your handling code here:
    }//GEN-LAST:event_bttVel5ActionPerformed

    private void bttInc0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttInc0MouseClicked
        if (modo == 0) {
            sesion.valorBoton(lblInc, 0.0f, 1);
        } else {
            esconder();
            sesion.resetArray();
            lblTitulo.setText("          MODO LIBRE          ");
            lblModoNo.setVisible(false);
            actualizadorMetricas.setVivo(false);
            actualizadorReloj.setVivo(false);
            sesion = new DataSport(distVuelta);
            reloj = new Relojrun();
            actualizadorReloj = new ActualizarReloj(lblReloj, lblTiempo, reloj);
            hiloReloj = new Thread(actualizadorReloj, "hiloReloj");
            hiloReloj.start();

            actualizadorMetricas = new ActualizarMetricas(lblCalorias, lblKms,
                    lblNoVuelta, lblVel, lblInc, reloj, sesion, intervaloCalculoCalorias, intervaloMostrarPantallaCal);
            hiloMetricas = new Thread(actualizadorMetricas, "hiloMetricas");
            hiloMetricas.start();
            modo = 0;
            actualizadorMetricas.llevarEtiquetas(VueltasFin, IncPromFin, VelPromFin, CalFin, KmFin, TiempoTFin, Resultados, UVueltasFin, UIncPromFin, UVelProm, UCalFin, UKmFin, UTiempoTFin, lblConsola);
        }

    }//GEN-LAST:event_bttInc0MouseClicked

    private void bttPauseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttPauseMouseClicked
        // TODO add your handling code here:
        actualizadorReloj.setBoton("pause");
        actualizadorMetricas.setBoton("pause");


    }//GEN-LAST:event_bttPauseMouseClicked

    private void bttPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttPauseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bttPauseActionPerformed

    private void bttStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttStopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bttStopActionPerformed

    private void bttOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttOffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bttOffActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ipre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ipre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ipre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ipre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ipre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CalFin;
    private javax.swing.JLabel IncPromFin;
    private javax.swing.JLabel KmFin;
    private javax.swing.JLabel Resultados;
    private javax.swing.JLabel TiempoTFin;
    private javax.swing.JLabel UCalFin;
    private javax.swing.JLabel UIncPromFin;
    private javax.swing.JLabel UKmFin;
    private javax.swing.JLabel UTiempoTFin;
    private javax.swing.JLabel UVelProm;
    private javax.swing.JLabel UVueltasFin;
    private javax.swing.JLabel VelPromFin;
    private javax.swing.JLabel VueltasFin;
    private javax.swing.JButton bttInc0;
    private javax.swing.JButton bttInc1;
    private javax.swing.JButton bttInc2;
    private javax.swing.JButton bttInc3;
    private javax.swing.JButton bttInc4;
    private javax.swing.JButton bttInc5;
    private javax.swing.JButton bttInc6;
    private javax.swing.JButton bttIncMas;
    private javax.swing.JButton bttIncMenos;
    private javax.swing.JButton bttOff;
    private javax.swing.JButton bttPause;
    private javax.swing.JButton bttPlay;
    private javax.swing.JButton bttSpeed;
    private javax.swing.JButton bttStop;
    private javax.swing.JButton bttVel1;
    private javax.swing.JButton bttVel10;
    private javax.swing.JButton bttVel11;
    private javax.swing.JButton bttVel12;
    private javax.swing.JButton bttVel2;
    private javax.swing.JButton bttVel3;
    private javax.swing.JButton bttVel4;
    private javax.swing.JButton bttVel5;
    private javax.swing.JButton bttVel6;
    private javax.swing.JButton bttVel7;
    private javax.swing.JButton bttVel8;
    private javax.swing.JButton bttVel9;
    private javax.swing.JButton bttVelMas;
    private javax.swing.JButton bttVelMenos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCalorias;
    private javax.swing.JLabel lblCaloriasFijo;
    private javax.swing.JLabel lblConsola;
    private javax.swing.JLabel lblGradosFijo;
    private javax.swing.JLabel lblInc;
    private javax.swing.JLabel lblKmFIjo;
    private javax.swing.JLabel lblKmFijo;
    private javax.swing.JLabel lblKms;
    private javax.swing.JLabel lblModoNo;
    private javax.swing.JLabel lblNoVuelta;
    private javax.swing.JLabel lblReloj;
    private javax.swing.JLabel lblTiempo;
    private javax.swing.JLabel lblTiempoFijo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVel;
    private javax.swing.JLabel lblVueltaFijo;
    private javax.swing.JPanel panelPpal;
    // End of variables declaration//GEN-END:variables
}
