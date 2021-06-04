/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desarrollosoftware;


import Modelo.ConexionSQL;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class Concepto extends javax.swing.JFrame {       
    FormatoFactura formato;
    FormatoPDF formatoPDF;
    ConexionSQL conexion = new ConexionSQL();
    Connection cn = conexion.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    
    //datos
    public static String claveProductoServicio = "";
    public static String claveUnidad = "";
    public static String cantidadd = "";
    public static String unidad = "";
    public static String numeroIdentificacion = "";
    public static String descripcion = "";
    public static String valorUnitario = "";
    public static String importe = "";
  //  public static String descuento = "";
    public static String tipoImpuesto = "";
     
     boolean retencion = false;
     boolean traslado = false;
     
    public Concepto() {
        initComponents();
 
        this.setTitle("Concepto Factura");
        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1000,560);
        this.setResizable(false); 
     
        componentes();
        iconoAplicacion();
        ocultarEtiquetas();
        camposDesactivados();  
    }
    
        public void camposDesactivados(){
            jTextField11.setEnabled(false);
            jTextField8.setEnabled(false);
        }
    
        public void ocultarEtiquetas(){
         jLabel13.setVisible(false);
         jLabel14.setVisible(false);
         jLabel15.setVisible(false);
         jLabel18.setVisible(false);
         jLabel16.setVisible(false);
         jLabel30.setVisible(false);
         jLabel31.setVisible(false);
        }
    
        public void iconoAplicacion(){
            this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logo sat.png")).getImage());
        }
        
        public void componentes(){
            jButton1.setBackground(Color.GREEN);
            jButton2.setBackground(Color.WHITE);
            jButton2.setVisible(false);
            jPanel1.setVisible(true);
            jPanel2.setVisible(false);
            jPanel3.setVisible(false);
            jPanel4.setVisible(false);
            
            jCheckBox3.setEnabled(false);
            jCheckBox4.setEnabled(false);          
        }
        
        public void concepto(){
            jButton1.setBackground(Color.GREEN);
            jButton2.setBackground(Color.WHITE);
            jPanel1.setVisible(true);
            jPanel2.setVisible(false);
        }
        
        public void impuestos(){
            jButton1.setBackground(Color.WHITE);
            jButton2.setBackground(Color.GREEN);
            jPanel1.setVisible(false);
            jPanel2.setVisible(true);
        }
        
        //tipeado de campos obligatorios
        public void tipeadoCamposObligatorios(KeyEvent evt){
            String campo1 = jTextField6.getText();
            String campo2 = jTextField5.getText();
            String campo3 = jTextField1.getText();
            String campo4 = jTextField7.getText();
            String campo5 = jTextField10.getText();
            
             //campo1
            if (campo1.length() > 0){
                jLabel13.setVisible(false);            
            }
             //campo2
            if (campo2.length() > 0){
                jLabel14.setVisible(false);            
            }
             //campo3
            if (campo3.length() > 0){
                jLabel15.setVisible(false);            
            }
             //campo4
            if (campo4.length() > 0){
                jLabel18.setVisible(false);            
            }
             //campo5
            if (campo5.length() > 0){
                jLabel16.setVisible(false);            
            }         
        }
        
        //opciones y cajas de texto obligatorios
     public void comprobarOpciones(MouseEvent evt){
           //impuestos
              if (jCheckBox2.isSelected()){
                  jLabel30.setVisible(false);
              }
              //traslado o retencion
              if (jRadioButton1.isSelected() || jRadioButton2.isSelected()){
                  jLabel31.setVisible(false);
              }
     }
     
        //comprobar campos obligatorios
        public void comprobarCamposObligatorios(){
            String campo1 = jTextField6.getText();
            String campo2 = jTextField5.getText();
            String campo3 = jTextField1.getText();
            String campo4 = jTextField7.getText();
            String campo5 = jTextField10.getText();
            
            boolean verdadero1 = false;
            boolean verdadero2 = false;
            boolean verdadero3 = false;
            boolean verdadero4 = false;
            boolean verdadero5 = false;
            boolean verdadero6 = false;
            boolean verdadero7 = false;
            
            //campo1
            if (campo1.length() == 0){
                jLabel13.setVisible(true);
                
            }else{
                verdadero1 = true;
            }
            
            //campo2
            if (campo2.length() == 0){
                jLabel14.setVisible(true);
                
            }else{
                verdadero2 = true;
            }
            //campo3
             if (campo3.length() == 0){
                jLabel15.setVisible(true);
                
            }else{
                verdadero3 = true;
            }
             //campo4
              if (campo4.length() == 0){
                jLabel18.setVisible(true);
                
            }else{
                verdadero4 = true;
            }
                 //campo5
              if (campo5.length() == 0){
                jLabel16.setVisible(true);
                
            }else{
                verdadero5 = true;
            }
              
              //impuestos
              if (!jCheckBox2.isSelected()){
                  jLabel30.setVisible(true);
              }else{
                  verdadero6 = true;
              }
              
              //traslado o retencion
              if (jRadioButton1.isSelected() || jRadioButton2.isSelected()){
                  verdadero7 = true;
              }else{
                  jLabel31.setVisible(true);
              }
 
            //COMPROBAR Y AGREGAR DATOS
            if (verdadero1 == true && verdadero2 == true && verdadero3 == true && verdadero4 == true && verdadero5 == true && verdadero6 == true && verdadero7 == true){
                camposNormales(campo1,campo2,campo3,campo4,campo5);
            }
        }
          
        public void camposNormales(String texto1, String texto2, String texto3, String texto4, String texto5){         
            String campo1 = texto1;
            String campo2 = texto2;
            String campo3 = texto3;
            String campo4 = texto4;
            String campo5 = texto5;
            String campo6 = jTextField9.getText();
            String campo7 = jTextField4.getText();    
            String campo8 = jTextField8.getText();
            String campo9 = jTextField11.getText();
            
            //retencion
            if (jCheckBox2.isSelected() && jRadioButton1.isSelected()){
               retencion = true;
            }
            
            //traslado
            if(jCheckBox2.isSelected() && jRadioButton2.isSelected()){
                traslado = true;
            }
            
                String campo10 = "";
            if (jRadioButton1.isSelected()){
                campo10 = "Retencion";
            }
            if (jRadioButton2.isSelected()){
                campo10 = "Traslado";
            }
            guardarConcepto(campo1,campo2,campo3,campo4,campo5, campo6, campo7, campo8, campo9,campo10);      
        }

    
        
    //Guardar datos de concepto a formato PDF y XML de factura
    public void guardarConcepto(String campo1, String campo2, String campo3, String campo4, String campo5, String campo6, String campo7, String campo8, String campo9, String campo10) {
        claveProductoServicio = campo1;
        claveUnidad = campo2;
        cantidadd = campo3;
        unidad = campo6;
        numeroIdentificacion = campo7;
        descripcion = campo4;
        valorUnitario = campo5;
        importe = campo9;
        //descuento = campo8;    
        tipoImpuesto = campo10;
        registrosConcepto();

        //cerrar
        this.dispose();

    }

        //GUARDAR REGISTROS A TABLA SQL
        public void registrosConcepto(){
       try{      
          String llave = "select MAX(No) from concepto";
          ps = cn.prepareStatement(llave);
          rs = ps.executeQuery();
          Object variable="";
          while(rs.next()){
               variable = rs.getInt(0+1);
              //System.out.println("variable: "+variable);
          }

          String id = String.valueOf(variable);
          int no = Integer.valueOf(id)+1;
          //System.out.println("No: "+no);
          
          //importe de un concepto
          double valor = Double.parseDouble(valorUnitario);
          int cantidad = Integer.parseInt(cantidadd);
          double total = valor * cantidad;
          importe = String.valueOf(total);
 
          //insercion de registro
         String insercion = "INSERT INTO concepto values (?,?,?,?,?,?,?,?,?,?)";
          ps = cn.prepareStatement(insercion);        
          ps.setInt(1, no);
          ps.setString(2, claveProductoServicio);
          ps.setString(3, descripcion);
          ps.setString(4, claveUnidad);
          ps.setString(5, cantidadd);
          ps.setString(6, unidad);
          ps.setString(7, numeroIdentificacion);
          ps.setString(8, importe);
          ps.setString(9, tipoImpuesto);
          ps.setString(10, valorUnitario);
          ps.executeUpdate();
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
       }
       cargar();
    }
        //CARGAR DATOS DE CONCEPTO A VENTANA PRINCIPAL DE FACTURA
        public void cargar() {

        try {            
            //SUBTOTAL
            String subtotal = "select SUM(importe) from concepto";
            ps = cn.prepareStatement(subtotal);
                rs = ps.executeQuery();
                String variable1 = "";
                  
                ResultSetMetaData rsM = rs.getMetaData();
                int cantidadColumnas = rsM.getColumnCount();
                while(rs.next()){
                   String[] filas = new String[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getString(i + 1);
                    variable1 = filas[i]; 
                }   
                }
                if(variable1==null){
                
                }else{
                formato.jTextField5.setText(variable1);
                }              
                
                //impuestos traslado
                String impuestosTraslado = "SELECT ROUND(SUM(importe),3) from concepto where tipoImpuesto = 'Traslado'";
                ps = cn.prepareStatement(impuestosTraslado);
                rs = ps.executeQuery();
                String variable2 = "";
                while (rs.next()) {
                    String[] filas = new String[cantidadColumnas];
                    for (int i = 0; i < cantidadColumnas; i++) {
                        filas[i] = rs.getString(i + 1);
                        variable2 = filas[i];
                    }
                }
                
                double operacionT2=0;
                if (variable2==null){
                    
                }else{
                double operacionT = Double.parseDouble(variable2);
                operacionT2 = operacionT*0.16;
                String impuestoT = String.valueOf(operacionT2);
                formato.jTextField10.setText(impuestoT);
                }
                
                //impuestos Retencion
                 String impuestosRetencion = "SELECT ROUND(SUM(importe),3) from concepto where tipoImpuesto = 'Retencion'";
                ps = cn.prepareStatement(impuestosRetencion);
                rs = ps.executeQuery();
                String variable3 = "";
                while (rs.next()) {
                    String[] filas = new String[cantidadColumnas];
                    for (int i = 0; i < cantidadColumnas; i++) {
                        filas[i] = rs.getString(i + 1);
                        variable3 = filas[i];
                    }
                }
                
                double operacionR2=0;
                if(variable3==null){
                    
                }else{
                double operacionR = Double.parseDouble(variable3);
                 operacionR2 = operacionR*0.16;
                String impuestoR = String.valueOf(operacionR2);
                formato.jTextField6.setText(impuestoR);
                }
                
                //total impuestos
                double impuestosT = operacionT2 + operacionR2;
                String impuestos = String.valueOf(impuestosT);
                formato.jTextField16.setText(impuestos);
                
                //total
                double subtotalT = Double.parseDouble(variable1);
                double totalT = subtotalT +impuestosT;
                String total = String.valueOf(totalT);
                formato.jTextField9.setText(total);
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }        
        }
 
        ///////////////////////////////////////////////////////////////////////////////////////////
        public void cargarImpuestosRetencion(String base){
            double base1 = Double.parseDouble(base);
            
            jComboBox3.setSelectedIndex(2);
            jComboBox3.setEnabled(false);
            
            jComboBox2.setSelectedIndex(2);
            jComboBox2.setEnabled(false);
            
            jTextField12.setText("0.160000");
            jTextField12.setEnabled(false);
            
            double importee = base1 * 0.16;
            String importeF = String.valueOf(importee);
            jTextField2.setText(importeF);
            jTextField2.setEnabled(false);
        }
        
        public void valoresDefectoRetencion(){
            jComboBox3.setSelectedIndex(0);
            jComboBox3.setEnabled(true);
            
            jComboBox2.setSelectedIndex(0);
            jComboBox2.setEnabled(true);
            
            jTextField12.setText("");
            jTextField12.setEnabled(true);
            
            jTextField2.setText("");
            jTextField2.setEnabled(true);
        }
        
        
        ////////////////////////////////////////////////////////////////////////
        public void cargarImpuestosTraslado(String base){
            double base1 = Double.parseDouble(base);
            
            jComboBox5.setSelectedIndex(2);
            jComboBox5.setEnabled(false);
            
            jComboBox4.setSelectedIndex(2);
            jComboBox4.setEnabled(false);
            
            jTextField15.setText("0.160000");
            jTextField15.setEnabled(false);
            
            double importee = base1 * 0.16;
            String importeF = String.valueOf(importee);
            jTextField13.setText(importeF);
            jTextField13.setEnabled(false);
        }   
      
        public void valoresDefectoTraslado(){
            jComboBox3.setSelectedIndex(0);
            jComboBox3.setEnabled(true);
            
            jComboBox2.setSelectedIndex(0);
            jComboBox2.setEnabled(true);
            
            jTextField12.setText("");
            jTextField12.setEnabled(true);
            
            jTextField2.setText("");
            jTextField2.setEnabled(true);
        }
        
      //importe concepto
        public void importeConcepto(KeyEvent evt){
           String cantidad = jTextField10.getText();
           jTextField11.setText(cantidad);
           jTextField3.setText(cantidad);
           jTextField14.setText(cantidad);
           
           if (cantidad.length() > 0){
               cargarImpuestosRetencion(cantidad);
               cargarImpuestosTraslado(cantidad);
           }else{
               valoresDefectoRetencion();
               valoresDefectoTraslado();
           }
        }
           
        
        //importe base retencion
           public void baseRetencion(KeyEvent evt){
           String cantidad = jTextField3.getText();
           jTextField10.setText(cantidad);
           jTextField11.setText(cantidad);
           jTextField14.setText(cantidad);
           
           if (cantidad.length() > 0){
               cargarImpuestosRetencion(cantidad);
               cargarImpuestosTraslado(cantidad);
           }else{
               valoresDefectoRetencion();
               valoresDefectoTraslado();
           }           
        }
           
           //importe base traslado
           public void baseTraslado(KeyEvent evt){
           String cantidad = jTextField14.getText();
           jTextField10.setText(cantidad);
           jTextField11.setText(cantidad);
           jTextField3.setText(cantidad);
           
           if (cantidad.length() > 0){
               cargarImpuestosRetencion(cantidad);
               cargarImpuestosTraslado(cantidad);
           }else{
               valoresDefectoRetencion();
               valoresDefectoTraslado();
           }           
        }
        
        //adicionales
        public void adicionalImpuestos(MouseEvent evt){
            if(jCheckBox2.isSelected()){
                jButton2.setVisible(true);
            }
            if (!jCheckBox2.isSelected()){
                jButton2.setVisible(false);
        }
    }

        
    //tipo impuestos
    public void tipoImpuestos(MouseEvent evt) {
        //retencion
        if (jRadioButton1.isSelected()) {
            jPanel3.setVisible(true);
            jPanel4.setVisible(false);
        }
        //traslado
       if (jRadioButton2.isSelected()){
            jPanel4.setVisible(true);
            jPanel3.setVisible(false);
        }
    }

    //tipeado campos valor unitario - base retencion y traslado
    public void tipeadoValores(KeyEvent evt) {
        char c = evt.getKeyChar();
        if ((c > '.') && (c < '0' || c > '9')) {
            evt.consume();
        }
        if (jTextField10.getText().length() >= 7) {
            evt.consume();
        } 
        if (jTextField3.getText().length() >= 7) {
            evt.consume();
        } 
        if (jTextField14.getText().length() >= 7) {
            evt.consume();
        }  
    }

    public void cerrar() {
        this.dispose();
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField12 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField15 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Concepto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 110, 50));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("*Campos obligatorios");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 140, 50));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 440, 110, 40));

        jButton4.setBackground(new java.awt.Color(80, 172, 249));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Agregar");
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 440, 110, 40));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Impuestos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 110, 50));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Numero de identificación:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 176, 30));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 270, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Clave de unidad*:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 176, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Cantidad*:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 120, 30));

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 530, 30));

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 260, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Descuento:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 190, 150, 30));

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 330, 30));

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 870, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Unidad:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 176, 30));

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, 260, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Descripción*:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 176, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Adicionales");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 176, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Importe*:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 176, 30));

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 330, 30));

        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField10KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 330, 30));

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField11KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 260, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Valor unitario*:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 176, 30));

        jCheckBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckBox2.setText("Impuestos*:");
        jCheckBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox2MouseClicked(evt);
            }
        });
        jPanel1.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 110, 30));

        buttonGroup1.add(jCheckBox3);
        jCheckBox3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckBox3.setText("Informacion aduanera:");
        jPanel1.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 180, 30));

        buttonGroup1.add(jCheckBox4);
        jCheckBox4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckBox4.setText("Cuenta predial:");
        jPanel1.add(jCheckBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 150, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Clave de producto o servicio*:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 4, 190, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Este campo es obligatorio");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 150, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("Este campo es obligatorio");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 150, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("Este campo es obligatorio");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 170, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("Este campo es obligatorio");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 170, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("Este campo es obligatorio");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 170, 30));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 0, 0));
        jLabel30.setText("Este campo es obligatorio");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 170, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 900, 360));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 0, 0));
        jLabel31.setText("Este campo es obligatorio");
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 170, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText(" Tipo");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 90, 35));

        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton1.setText("Retención");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });
        jPanel2.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 60, 80, -1));

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton2.setText("Traslado");
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });
        jPanel2.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 80, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Retención");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 100, 40));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Valor de la tasa o cuota*:");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 200, 40));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Importe*:");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 80, 40));

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 400, 30));

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 290, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Base*:");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Impuesto*:");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 80, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("¿Tasa o cuota?*:");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 140, 30));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Exento", "Tasa" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 240, 30));

        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "001 ISR", "002 IVA", "003 IEPS", " " }));
        jComboBox3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 260, 30));

        jTextField12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 400, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 860, 190));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Traslado");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 100, 40));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Valor de la tasa o cuota*:");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 200, 40));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Importe*:");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 80, 40));

        jTextField13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 400, 30));

        jTextField14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField14KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField14KeyTyped(evt);
            }
        });
        jPanel4.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 290, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Base*:");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 30));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Impuesto*:");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 80, 30));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("¿Tasa o cuota?*:");
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 140, 30));

        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Exento", "Tasa" }));
        jComboBox4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 240, 30));

        jComboBox5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "001 ISR", "002 IVA", "003 IEPS", " " }));
        jComboBox5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 260, 30));

        jTextField15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 400, 30));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 860, 190));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 900, 360));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //CONCEPTO
        concepto();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // IMPUESTOS
        impuestos();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // BOTON CANCELAR
       cerrar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        // CAMPO CLAVE DE PRODUCTO O SERVICIO   
        tipeadoCamposObligatorios(evt);
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // BOTON AGREGAR
        comprobarCamposObligatorios();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        // CAMPO CLAVE DE UNIDAD
        tipeadoCamposObligatorios(evt);
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        // TODO add your handling code here:  
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // CAMPO CANTIDAD
        tipeadoCamposObligatorios(evt);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        //CAMPO DESCRIPCION
        tipeadoCamposObligatorios(evt);
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
        //CAMPO VALOR UNITARIO
        importeConcepto(evt);
        tipeadoCamposObligatorios(evt);  
    }//GEN-LAST:event_jTextField10KeyReleased

    private void jTextField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyReleased
        // CAMPO IMPORTE
    }//GEN-LAST:event_jTextField11KeyReleased

    private void jCheckBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox2MouseClicked
        // CHECK BOX IMPUESTOS
        comprobarOpciones(evt);
        adicionalImpuestos(evt);
    }//GEN-LAST:event_jCheckBox2MouseClicked

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
        // TIPO IMPUESTO - RETENCION
        comprobarOpciones(evt);
        tipoImpuestos(evt);
    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseClicked
        // TIPO IMPUESTO - TRASLADO
        comprobarOpciones(evt);
        tipoImpuestos(evt);
    }//GEN-LAST:event_jRadioButton2MouseClicked

    private void jTextField10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyTyped
        // TIPEADO CAMPO VALOR UNITARIO
        tipeadoValores(evt);
    }//GEN-LAST:event_jTextField10KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // BASE DE RETENCION
      tipeadoValores(evt);
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        //CAMPO BASE DE RETENCION
       baseRetencion(evt);
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyReleased
        // CAMPO BASE DE TRASLADO
        baseTraslado(evt);
    }//GEN-LAST:event_jTextField14KeyReleased

    private void jTextField14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyTyped
        // BASE DE TRASLADO
        tipeadoValores(evt);
    }//GEN-LAST:event_jTextField14KeyTyped

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
            java.util.logging.Logger.getLogger(Concepto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Concepto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Concepto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Concepto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Concepto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    public static javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JRadioButton jRadioButton1;
    public static javax.swing.JRadioButton jRadioButton2;
    public static javax.swing.JTextField jTextField1;
    public static javax.swing.JTextField jTextField10;
    public static javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    public static javax.swing.JTextField jTextField4;
    public static javax.swing.JTextField jTextField5;
    public static javax.swing.JTextField jTextField6;
    public static javax.swing.JTextField jTextField7;
    public static javax.swing.JTextField jTextField8;
    public static javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
