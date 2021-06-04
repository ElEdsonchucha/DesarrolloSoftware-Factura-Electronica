/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desarrollosoftware;


import Modelo.ConexionSQL;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

//importaciones para generar XML
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.view.JasperViewer;


public class FormatoFactura extends javax.swing.JFrame {
   //datos de informacion
    public static String rfcEmisor = "";
    public static String nombreEmisor = "";
    public static String folio = "";
    public static String rfcReceptor = "";
    public static String usoCFDI = "";
    public static String folioFiscal = "";
    public static String serieCSDEmisor = "";
    public static String serie = "";
    public static String codigoPostalFechaHora = "";
    public static String efectoComprobante = "";
    public static String regimenFiscal = "";
    public static String fechaHoraCertificacion = "";
    public static String nombreReceptor ="";
    public static String moneda = "";
    public static String formaPago = "";
    public static String metodoPago = "";   
    public static String tipoCambio = "";
    public static String confirmacion = "";
    public static String condicionesPago = "";
    
    //datos numericos
    public static String subtotal = "";
    public static String impuestosTrasladados = "";
    public static String impuestosRetenidos = "";
    public static String impuestos = "";
    public static String total = "";
      
     Concepto concepto1;
     FormatoPDF pdf;
     ImpuestosRetenidos ir;
     ImpuestosTrasladados it;
     ConexionSQL conexion = new ConexionSQL();
     Connection cn = conexion.getConnection();
     PreparedStatement ps;
     ResultSet rs;
     
    public FormatoFactura() {
        initComponents();
     
        this.setTitle("Formato Factura");
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(1300,800);

        iconoAplicacion();
        imagenesFactura();
        componentes();
        cerrarVentana();
        camposDesactivados();
        ocultarEtiquetas();
        cargarFechaHora();
        cargarIconosComprobante();
        vaciarTablas();
         //PANTALLA COMPLETA
       //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
          
    public void componentes(){
        jButton5.setBackground(Color.WHITE); 
        jButton6.setBackground(Color.WHITE);
        jPanel2.setVisible(false);
        jPanel1.setVisible(true);
        jPanel3.setVisible(false);
        jButton1.setVisible(false);
        jButton1.setBackground(Color.WHITE);
        jButton4.setBackground(Color.WHITE);
        jButton7.setEnabled(false);
        
        //emisorReceptor
        jLabel4.setOpaque(true);
        jLabel2.setOpaque(true);
        jLabel4.setBackground(Color.lightGray);
        jLabel2.setBackground(Color.lightGray);
        //comprobante
        jLabel28.setOpaque(true);
        jLabel41.setOpaque(true);
        jLabel49.setOpaque(true);
        jLabel28.setBackground(Color.lightGray);
        jLabel41.setBackground(Color.lightGray);
        jLabel49.setBackground(Color.lightGray);
        jLabel33.setOpaque(true);
        jLabel33.setBackground(Color.lightGray);
        
        jPanel1.setBackground(Color.WHITE);
        jPanel2.setBackground(Color.WHITE);
        jPanel3.setBackground(Color.WHITE);

        jMenuItem4.setVisible(false);
        jMenuItem7.setVisible(false);
        jMenuItem6.setVisible(false);
    }

    public void camposDesactivados(){
        jTextField5.setEnabled(false);
        jTextField10.setEnabled(false);  
        jTextField9.setEnabled(false);  
        jTextField4.setEnabled(false);  
        jTextField6.setEnabled(false);  
        jTextField19.setEnabled(false);  
        jTextField16.setEnabled(false);
    }
    
    public void ocultarEtiquetas(){
        jLabel30.setVisible(false);
        jLabel50.setVisible(false);
        jLabel51.setVisible(false);
        jLabel52.setVisible(false);
        jLabel29.setVisible(false);
        
        jLabel54.setVisible(false);
        jLabel55.setVisible(false);
        jLabel53.setVisible(false);
    }
    
    
    public void nuevaFactura(){
        boolean verdadero1 = false;
        boolean verdadero2 = false;
        
        if (jTextField2.getText().length()>0 || jTextField3.getText().length()>0 || jTextField8.getText().length()>0 || jTextField1.getText().length()>0 || jTextField12.getText().length()>0 || jTextField18.getText().length()>0 || jTextField14.getText().length()>0 || jTextField15.getText().length()>0 || jTextField19.getText().length()>0 || jTextField7.getText().length()>0){
            verdadero1 = true;
        }
        if (jComboBox1.getSelectedIndex()> 0 || jComboBox2.getSelectedIndex()> 0 || jComboBox3.getSelectedIndex()> 0  || jComboBox10.getSelectedIndex() > 0 || jComboBox13.getSelectedIndex() > 0 || jComboBox9.getSelectedIndex() > 0){
            verdadero2 = true;
        }
        
        if (verdadero1 == true || verdadero2 == true){
      int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere crear nueva factura?", "Seleccione una opcion", 1,JOptionPane.YES_OPTION);
            if (seleccion == 0) {
                //campos
                jTextField2.setText("");
                jTextField3.setText("");
                jTextField8.setText("");
                jTextField1.setText("");
                jTextField12.setText("");
                jTextField18.setText("");
                jTextField14.setText("");
                jTextField15.setText("");
                jTextField19.setText("");
                jTextField7.setText("");
                //combo box
                jComboBox1.setSelectedIndex(0);
                jComboBox2.setSelectedIndex(0);
                jComboBox3.setSelectedIndex(0);
                jComboBox10.setSelectedIndex(0);
                jComboBox13.setSelectedIndex(0);
                jComboBox9.setSelectedIndex(0);
                
                //paneles y botones
                jPanel1.setVisible(true);
                jPanel2.setVisible(false);
                jPanel3.setVisible(false);
                jButton5.setBackground(Color.WHITE);
                jButton6.setBackground(Color.WHITE);
                jButton1.setVisible(false);
                jButton2.setVisible(true);
            }
        }
        vaciarTablas();
    }
    
    //VACIAR LOS REGISTROS DE LAS TABLAS FACTURA Y CONCEPTO
    public void vaciarTablas(){
        try{
            String truncate = "TRUNCATE TABLE factura";
        ps = cn.prepareStatement(truncate);
        ps.execute();
        String truncate2 = "TRUNCATE TABLE concepto";
        ps = cn.prepareStatement(truncate2);
        ps.execute();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
       // registroDefecto();
    }
    
    
    //QUITAR
    /*
    public void registroDefecto(){
      //REGISTROS POR DEFECTO*****
      
      try{
          String defecto = "INSERT INTO concepto values (?,?,?,?,?,?,?,?,?,?)";
          ps = cn.prepareStatement(defecto);        
          ps.setInt(1, 1);
          ps.setString(2, "");
          ps.setString(3, "");
          ps.setString(4, "");
          ps.setString(5, "1");
          ps.setString(6, "");
          ps.setString(7, "");
          ps.setString(8, "10");
          ps.setString(9, "Retencion");
          ps.setString(10, "10");
          ps.executeUpdate();
      }catch(SQLException ex){
          System.out.println(ex.getMessage());
      }
    }
    */
    
    public void salir(){
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere salir?", "Seleccione una opcion", 1,JOptionPane.YES_OPTION);
        if (seleccion == 0) {
            System.exit(0);
        } else {

        }
    }
    
    public void iconoAplicacion(){
         this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logo sat.png")).getImage());
    }  
    
    public void imagenesFactura(){
        ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/gobierno de mexico.png")).getImage());
        Image icono = imagen.getImage();
        Image redimension = icono.getScaledInstance(jLabel10.getWidth(), jLabel10.getHeight(), Image.SCALE_SMOOTH);
        jLabel10.setIcon(new ImageIcon(redimension));

        ImageIcon imagen2 = new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/logo sat.png")).getImage());
        Image icono2 = imagen2.getImage();
        Image redimension2 = icono2.getScaledInstance(jLabel75.getWidth(), jLabel75.getHeight(), Image.SCALE_SMOOTH);
        jLabel75.setIcon(new ImageIcon(redimension));
    }

    
    public void cargarIconosComprobante(){
        ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/logo pdf.png")).getImage());
        Image icono = imagen.getImage();
        Image redimension = icono.getScaledInstance(jButton12.getWidth(), jButton12.getHeight(), Image.SCALE_SMOOTH);
        jButton12.setIcon(new ImageIcon(redimension));
        
        ImageIcon imagen1 = new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/logo xml.png")).getImage());
        Image icono1 = imagen1.getImage();
        Image redimension1 = icono1.getScaledInstance(jButton14.getWidth(), jButton14.getHeight(), Image.SCALE_SMOOTH);
        jButton14.setIcon(new ImageIcon(redimension1));
        
        /*
        ImageIcon imagen2 = new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/icono imprimir.png")).getImage());
        Image icono2 = imagen2.getImage();
        Image redimension2 = icono2.getScaledInstance(jButton15.getWidth(), jButton15.getHeight(), Image.SCALE_SMOOTH);
        jButton15.setIcon(new ImageIcon(redimension2));
        */
        
          ImageIcon imagen3 = new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/icono confirmacion.png")).getImage());
        Image icono3 = imagen3.getImage();
        Image redimension3 = icono3.getScaledInstance(jLabel84.getWidth(), jLabel84.getHeight(), Image.SCALE_SMOOTH);
        jLabel84.setIcon(new ImageIcon(redimension3));
        
    }

    public void cargarFechaHora() {
        Date date = new Date();
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hora = new SimpleDateFormat("HH:mm:ss");
        jTextField11.setText(fecha.format(date)+"T"+hora.format(date));
        jTextField11.setEditable(false);
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String fechaEmision = formato.format(date);
        jLabel72.setText(fechaEmision);
    }

    
      //LIMITES DE CAMPOS
    ///////////////////////////////////////////////////////
    public void limiteRFCEmisor(KeyEvent evt) {
        String rfcE = jTextField2.getText();
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < 'á' || c > 'ú') && (c < 'Á' || c > 'Ú')) {
            evt.consume();
        }
        if (rfcE.length() >= 13) {
            evt.consume();
        }
    }

    public void limiteRFCReceptor(KeyEvent evt) {
        String rfcR = jTextField8.getText();
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < 'á' || c > 'ú') && (c < 'Á' || c > 'Ú')) {
            evt.consume();
        }
        if (rfcR.length() >= 13) {
            evt.consume();
        }
    }
    
    
    public void limiteNombreEmisor(KeyEvent evt) {
        String nombreE = jTextField3.getText();
        char c = evt.getKeyChar();
        if ((c <' ') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < 'á' || c > 'ú') && (c < 'Á' || c > 'Ú')) {
            evt.consume();
        }
        if (nombreE.length() >= 80) {
            evt.consume();
        }
    }

    public void limiteNombreReceptor(KeyEvent evt) {
        String nombreR = jTextField1.getText();
        char c = evt.getKeyChar();
        if ((c <' ') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < 'á' || c > 'ú') && (c < 'Á' || c > 'Ú')) {
            evt.consume();
        }
        if (nombreR.length() >= 80) {
            evt.consume();
        }
    }

    public void limiteCodigoPostal(KeyEvent evt) {
        String codigoP = jTextField12.getText();
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
        if (codigoP.length() >= 15) {
            evt.consume();
        }
    }

    public void limiteTipoCambio(KeyEvent evt) {
        String tipoC = jTextField18.getText();
        char c = evt.getKeyChar();
        if ((c <' ') && (c < '0' || c > '9') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < 'á' || c > 'ú') && (c < 'Á' || c > 'Ú')) {
            evt.consume();
        }
        if (tipoC.length() >= 80) {
            evt.consume();
        }
    }

    public void limiteSerie(KeyEvent evt) {
        String limiteS = jTextField14.getText();
        char c = evt.getKeyChar();
        if ((c <' ') && (c < '0' || c > '9') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < 'á' || c > 'ú') && (c < 'Á' || c > 'Ú')) {
            evt.consume();
        }
        if (limiteS.length() >= 80) {
            evt.consume();
        }
    }

    public void limiteFolio(KeyEvent evt) {
        String limiteF = jTextField15.getText();
        char c = evt.getKeyChar();
        if ((c <' ') && (c < '0' || c > '9') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < 'á' || c > 'ú') && (c < 'Á' || c > 'Ú')) {
            evt.consume();
        }
        if (limiteF.length() >= 80) {
            evt.consume();
        }
    }

    public void limiteCondicionesPago(KeyEvent evt) {
        String condicionesP = jTextField7.getText();
        char c = evt.getKeyChar();
        if ((c <' ') && (c < '0' || c > '9') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < 'á' || c > 'ú') && (c < 'Á' || c > 'Ú')) {
            evt.consume();
        }
        if (condicionesP.length() >= 80) {
            evt.consume();
        }
    }
  //////////////////////////////////////////////////////////
    
    
    //comprobar campos obligatorios
    public void tipeadoCamposObligatorios(KeyEvent evt){
        String campo1 = jTextField2.getText();
        String campo2 = jTextField11.getText();
        String campo3 = jTextField12.getText();
        String campo4 = jTextField8.getText();
        
        if (campo1.length() > 0){
          jLabel30.setVisible(false);
         // confirmo1 = true;
        }
          if (campo2.length() > 0){
          jLabel54.setVisible(false);
        }
           if (campo3.length() > 0){
          jLabel55.setVisible(false);
          //confirmo6 = true;
        }
          if (campo4.length() > 0){
          jLabel52.setVisible(false);
          //confirmo2 = true;
        }   
    }
    
    //comprobar combo box obligatorios
    public void comprobarCombosObligatorios(ItemEvent evt){
        int combo1 = jComboBox1.getSelectedIndex();
        int combo2 = jComboBox3.getSelectedIndex();
        int combo3 = jComboBox2.getSelectedIndex();
        int combo4 = jComboBox10.getSelectedIndex();
        
        if(combo1>0){
            jLabel50.setVisible(false);
         //   confirmo3 = true;
        }
        if(combo2>0){
            jLabel51.setVisible(false);
           // confirmo4 = true;
        }
        if(combo3>0){
            jLabel29.setVisible(false);
          //  confirmo5 = true;
        }
        if(combo4>0){
            jLabel53.setVisible(false);
          //  confirmo7 = true;
        }
    }
    
    //campos obligatorios
    public void comprobarCamposObligatorios(){
        //textField
        String campo1 = jTextField2.getText();
        String campo2 = jTextField11.getText();
        String campo3 = jTextField12.getText();
        String campo4 = jTextField8.getText();
        String campo5 = jTextField9.getText(); //total conceptos
        
        //comboBox
        int combo1 = jComboBox1.getSelectedIndex();
        int combo2 = jComboBox3.getSelectedIndex();
        int combo4 = jComboBox2.getSelectedIndex();
        int combo5 = jComboBox10.getSelectedIndex();
        
        boolean verdadero1 = false;
        boolean verdadero2 = false;
        boolean verdadero3 = false;
        boolean verdadero4 = false;
        boolean verdadero5 = false;
        boolean verdadero6 = false;
        boolean verdadero7 = false;
        boolean verdadero8 = false;
        boolean verdadero9 = false;

        ///CAMPOS
        if(campo1.length() ==0){
            jLabel30.setVisible(true);
        }else{
            verdadero1 = true;
        }
        //
        if(campo2.length()== 0){
            jLabel54.setVisible(true);
        }else{
            verdadero2 = true;
        }
        //
        if(campo3.length() == 0){
            jLabel55.setVisible(true);
        }else{
            verdadero3 = true;
        }
         //
        if(campo4.length() == 0){
            jLabel52.setVisible(true);
        }else{
            verdadero6 = true;
        }
        
        //
        if (campo5.length()==0){
            JOptionPane.showMessageDialog(null,"Es necesario agregar un Concepto");
        }else{
            verdadero9 = true;
        }
        
        ///COMBOS
        if(combo1 == 0){
            jLabel50.setVisible(true);
        }else{
            verdadero4 = true;
        }
         if(combo2 == 0){
            jLabel51.setVisible(true);
        } else {
            verdadero5 = true;
        }
        if (combo4 == 0) {
            jLabel29.setVisible(true);
        } else {
            verdadero7 = true;
        }
        if (combo5 == 0) {
            jLabel53.setVisible(true);
        } else {
            verdadero8 = true;
        }

       
        if (verdadero3 == true && verdadero8 == true && verdadero9 == true) {
            jButton6.setBackground(Color.GREEN);
        }else{
            jButton6.setBackground(Color.WHITE);
        }
     
        //COMPROBAR CAMPOS Y GUARDAR COMPROBANTE
        if (verdadero1 == true && verdadero2 == true && verdadero3 == true && verdadero4 == true && verdadero5 == true && verdadero6 == true && verdadero7 == true && verdadero8 == true && verdadero9 == true) {
            guardarComprobante();
        }
    }
 
    
      //PDF
    public void guardarComprobante() {
        rfcEmisor = jTextField2.getText();
        nombreEmisor = jTextField3.getText();
        folio = jTextField15.getText();
        rfcReceptor = jTextField8.getText();
        
        String cfdi = jComboBox2.getSelectedItem().toString();
        String usoFDI = cfdi.substring(4);
        usoCFDI = usoFDI;
        folioFiscal = jTextField15.getText();
        
        String serieCSD = "00001000000";
        for (int i = 0; i < 9; i++) {
            Random aleatorio = new Random();
            int numero = aleatorio.nextInt(10);
            serieCSD = serieCSD + numero;
        } 
        serieCSDEmisor = serieCSD; 
        serie = jTextField14.getText();

        String fecha = jTextField11.getText();
        String nuevaFecha = fecha.replace("T", " ");
        codigoPostalFechaHora = jTextField12.getText() + " "+nuevaFecha ;
        
        
        if (jComboBox3.getSelectedIndex() == 0){
           efectoComprobante = "Egreso"; 
        }
        if (jComboBox3.getSelectedIndex() == 1){
           efectoComprobante = "Ingreso"; 
        }
        if (jComboBox3.getSelectedIndex() == 2){
           efectoComprobante = "Nómina"; 
        }
        if (jComboBox3.getSelectedIndex() == 3){
           efectoComprobante = "Pago"; 
        }
        if (jComboBox3.getSelectedIndex() == 4){
           efectoComprobante = "Traslado"; 
        }
        
        String regimen = jComboBox1.getSelectedItem().toString();
        String regimenF = regimen.substring(4);
        regimenFiscal = regimenF;   
        
        Date date = new Date();
        SimpleDateFormat formato1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String fechaCertificacion = formato1.format(date);       
        fechaHoraCertificacion = fechaCertificacion;
       
        nombreReceptor = jTextField1.getText();
        moneda = jComboBox10.getSelectedItem().toString().substring(4);
          
        int opcion1 = jComboBox13.getSelectedIndex();
        if (opcion1 == 0){
            formaPago = "";
        }else{
            formaPago = jComboBox13.getSelectedItem().toString().substring(3);
        }
        
        int opcion2 = jComboBox9.getSelectedIndex();
        if (opcion2 ==0){
            metodoPago = "";
        }else{
         metodoPago = jComboBox9.getSelectedItem().toString().substring(4);   
        }
        
        tipoCambio = jTextField18.getText();
        confirmacion = jTextField19.getText();
        condicionesPago = jTextField7.getText();
        
        subtotal =  jTextField5.getText();
        impuestosTrasladados = jTextField10.getText();
        impuestosRetenidos = jTextField6.getText();
        impuestos = jTextField16.getText();
        total = jTextField9.getText();
        
        Icon icono = new ImageIcon(this.getClass().getResource("/Imagenes/icono-reloj-arena3.gif"));
        int opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de proceder a generar\nel comprobante de la Factura Electrónica?","Aviso de confirmación",1,JOptionPane.YES_NO_CANCEL_OPTION, icono);
        if (opcion == 0){
            resultadoComprobante();
        }
    }
    
    /*
     //VER PDF
    public void verPDF() {
        rfcEmisor = jTextField2.getText();
        nombreEmisor = jTextField3.getText();
        folio = jTextField15.getText();
        rfcReceptor = jTextField8.getText();
        
        String cfdi = jComboBox2.getSelectedItem().toString();
        String usoFDI = cfdi.substring(4);
        usoCFDI = usoFDI;
        folioFiscal = jTextField15.getText();
        
        String serieCSD = "00001000000";
        for (int i = 0; i < 9; i++) {
            Random aleatorio = new Random();
            int numero = aleatorio.nextInt(10);
            serieCSD = serieCSD + numero;
        } 
        serieCSDEmisor = serieCSD; 
        serie = jTextField14.getText();

        String fecha = jTextField11.getText();
        String nuevaFecha = fecha.replace("T", " ");
        codigoPostalFechaHora = jTextField12.getText() + " "+nuevaFecha ;
        
        
        if (jComboBox3.getSelectedIndex() == 0){
           efectoComprobante = "Egreso"; 
        }
        if (jComboBox3.getSelectedIndex() == 1){
           efectoComprobante = "Ingreso"; 
        }
        if (jComboBox3.getSelectedIndex() == 2){
           efectoComprobante = "Nómina"; 
        }
        if (jComboBox3.getSelectedIndex() == 3){
           efectoComprobante = "Pago"; 
        }
        if (jComboBox3.getSelectedIndex() == 4){
           efectoComprobante = "Traslado"; 
        }
        
        String regimen = jComboBox1.getSelectedItem().toString();
        String regimenF = regimen.substring(4);
        regimenFiscal = regimenF;   
        
        Date date = new Date();
        SimpleDateFormat formato1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String fechaCertificacion = formato1.format(date);       
        fechaHoraCertificacion = fechaCertificacion;
       
        nombreReceptor = jTextField1.getText();
        moneda = jComboBox10.getSelectedItem().toString().substring(4);
          
        int opcion1 = jComboBox13.getSelectedIndex();
        if (opcion1 == 0){
            formaPago = "";
        }else{
            formaPago = jComboBox13.getSelectedItem().toString().substring(3);
        }
        
        int opcion2 = jComboBox9.getSelectedIndex();
        if (opcion2 ==0){
            metodoPago = "";
        }else{
         metodoPago = jComboBox9.getSelectedItem().toString().substring(4);   
        }
        
        tipoCambio = jTextField18.getText();
        confirmacion = jTextField19.getText();
        condicionesPago = jTextField7.getText();
        
        subtotal =  jTextField5.getText();
        impuestosTrasladados = jTextField10.getText();
        impuestosRetenidos = jTextField6.getText();
        impuestos = jTextField16.getText();
        total = jTextField9.getText(); 

        pdf = new FormatoPDF();
        pdf.setVisible(true);
    }
  */

    //generar archivo XML
    public void crearXML() throws ParserConfigurationException, TransformerException, TransformerConfigurationException, IOException, FileNotFoundException {
       String nomArchivo = "Factura";
       //rfc Emisor
       String rfcE = jTextField2.getText();
       String nombreE = jTextField3.getText(); 
       String regimenF = jComboBox1.getSelectedItem().toString().substring(0,3);
    //rfc Receptor
       String rfcR = jTextField8.getText();
       String nombreR = jTextField1.getText(); 
       String usocfd = jComboBox2.getSelectedItem().toString().substring(0,3);  
       //datos
       String cp = jTextField12.getText();
       String metodoP = jComboBox9.getSelectedItem().toString().substring(0,3);     
       String tipoComprobante = jComboBox3.getSelectedItem().toString().substring(0,1);
       String monedas = jComboBox10.getSelectedItem().toString().substring(0,3);
       String formaP = jComboBox13.getSelectedItem().toString().substring(0,2);
       
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();        
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, nomArchivo, null);
            document.setXmlVersion("1.0");
            
                // NODO RAIZ
                Element raiz = document.getDocumentElement();  
            
                //Factura
                Element itemNode = document.createElement("cfdi:Comprobante");
                
                
                //Comprobante
                Element comprobanteNode = document.createElement("cfdi:Comprobante");
                Text factura =document.createTextNode("LugarExpedicion="+cp+" MetodoPago="+metodoP+" TipoComprobante="+tipoComprobante+" Total="+total+" Moneda="+monedas
                +" Certificado=87H7huyh8yh87hyuH8Y9h8H8YhH8yhg8H7yH8h7Yh89H78h9H87h89H78H98h7HYgtydr56D45rfc5Rd5D54d45ybHUB09k0O'9J89+WWSDCFDSF987sdggfdgXCMMLKMPLM987ds897gY89"+" Subtotal="+subtotal
                +" NoCertificado="+serieCSDEmisor+" FormaPago="+formaP);
                comprobanteNode.appendChild(factura);
                
                //Emisor RFC
                Element emisorNode = document.createElement("cfdi:Emisor");
                Text nodeEmisorValue =document.createTextNode("Rfc="+rfcE + " Nombre="+nombreE+" RegimenFiscal="+regimenF);
                emisorNode.appendChild(nodeEmisorValue);
           
                //Receptor RFC
                Element receptorNode = document.createElement("cfdi:Receptor");
                Text nodeReceptorValue =document.createTextNode("Rfc="+rfcR+" Nombre="+nombreR+" UsoCFDI="+usocfd);
                receptorNode.appendChild(nodeReceptorValue);
                        
                /*
                //Concepto traslado
                Element telefonoNode = document.createElement("TELEFONO");
                Text nodeTelefonoValue =document.createTextNode("9311384307");
                telefonoNode.appendChild(nodeTelefonoValue);
                */
                
                 /*
                //Concepto retencion
                Element telefonoNode = document.createElement("TELEFONO");
                Text nodeTelefonoValue =document.createTextNode("9311384307");
                telefonoNode.appendChild(nodeTelefonoValue);
                */
                 
                
                //Complemento - timbreFiscalDigital
                Element complementoNode = document.createElement("tfd:TimbreFiscalDigital");
                Text nodeComplementoNode =document.createTextNode("UUID=A4E139E1-0FB6-4EC5-AE05-D7AACAD8DA33"+" FechaTimbrado="+fechaHoraCertificacion+" RfcProvCertif=SAT970701NN3"
                +" SelloCFD=CYEHbGMQUuhuhooije56890548f9gfdjfgjfsdf?¿!676trhgfdggdghfh=QREW+SDfsdgfdgfdg5464745?=1sdfdsf32fsedfsdf90uHUYUTYTRUuqeRRsSsTGAMEstvdfghdfg123dfgfd?*dgffdgfNYQK7/dfgfghgf+erQW54aDALÑS$87HG7"
                +" NoCertificadoSAT=00001000000504465028"+ " SelloSAT=dsdfsfdsfUIGHuiGOYgyoYGYUgyuGYuigYGYgyG798g=?PWDafdsgffde89789jfdsf7d8gds8g7fd87gfd78gfd87g78fdg78fdg87fdg7fd78gfd78gf87dg8fdgfd?¡dsfdsWQR7SDFD7trgfdg_fdgfhgf+WWSEO88s89F7GFGSyu?=7h8hr");
                complementoNode.appendChild(nodeComplementoNode);

                itemNode.appendChild(comprobanteNode);
                itemNode.appendChild(emisorNode);
                itemNode.appendChild(receptorNode);
                itemNode.appendChild(complementoNode);
                raiz.appendChild(itemNode);
                
            System.out.println("SE GENERO ARCHIVO XML");
            // GENERA XML
            //Source source = new DOMSource(document);
            
            // DONDE SE GUARDARA
            //Result result = new StreamResult(new java.io.File(nomArchivo + ".xml"));
            //Transformer transformer = TransformerFactory.newInstance().newTransformer(); 
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            DOMSource source = new DOMSource(document);
            
           // StreamResult console = new StreamResult(System.out);
            StreamResult result = new StreamResult(new File(nomArchivo + ".xml"));
            
           // transformer.transform(source,console);
            transformer.transform(source, result);
        
           //aviso
           JOptionPane.showMessageDialog(null,"Archivo XML creado");
        } catch(ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    
    /*
      //leer archivos XML
      public static void leerXML() {
        try {
            //nombre archivo
            File archivo = new File("pruebas.xml");
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            
            document.getDocumentElement().normalize();
            
            System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
            
            //obtener separadores 
            NodeList listaUsuarios = document.getElementsByTagName("USUARIO");
            
            for(int i = 0 ; i < listaUsuarios.getLength() ; i++) {
                Node nodo = listaUsuarios.item(i);
                System.out.println("Elemento: " + nodo.getNodeName());
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    System.out.println("ID: " + element.getElementsByTagName("ID").item(0).getTextContent());
                    System.out.println("Nombre: " + element.getElementsByTagName("NOMBRE").item(0).getTextContent());
                    System.out.println("Telefono: " + element.getElementsByTagName("TELEFONO").item(0).getTextContent());
                    
                    System.out.println("");
                }
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    */
    
    /*
    //leer archivos XML
      public static void leerXML2() {
        try {
            //nombre archivo
            File archivo = new File("pruebas.xml");
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            
            document.getDocumentElement().normalize();
            
            System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
            
            //obtener separadores 
            NodeList listaUsuarios = document.getElementsByTagName("USUARIO");
            
            for(int i = 0 ; i < listaUsuarios.getLength() ; i++) {
                Node nodo = listaUsuarios.item(i);
                System.out.println("Elemento: " + nodo.getNodeName());
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    //imprimir elementos que estan contenidos en usuario
                    System.out.println("ID: " + element.getElementsByTagName("ID").item(0).getTextContent());
                    System.out.println("Nombre: " + element.getElementsByTagName("NOMBRE").item(0).getTextContent());
                    System.out.println("Telefono: " + element.getElementsByTagName("TELEFONO").item(0).getTextContent());
                    
                    System.out.println("");
                }
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
      */
 
    //datos de resultado comprobante
    public void resultadoComprobante() {
        //obtener datos
        Random unico = new Random();
        String abecedario[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        String letras1 = "";
        String numeros1 = "";
        String letras2 = "";
        String numeros2 = "";
        String numeros3 = "";
        String numeros4 = "";

        String letras3 = "";
        String letras4 = "";
        String letras5 = "";
        String letras6 = "";
        String numeros5 = "";
        String numeros6 = "";
        String numeros7 = "";
        String numeros8 = "";

        int a = 0;
        while (a < 3) {
            int aleatorio = unico.nextInt(26);
            letras1 = letras1 + abecedario[aleatorio];
            a++;
        }

        int b = 0;
        while (b < 5) {
            int aleatorio = unico.nextInt(9 + 1);
            numeros1 = numeros1 + aleatorio;
            b++;
        }

        int c = 0;
        while (c < 2) {
            int aleatorio = unico.nextInt(9 + 1);
            letras2 = letras2 + abecedario[aleatorio];
            numeros2 = numeros2 + aleatorio;
            c++;
        }

        int d = 0;
        while (d < 4) {
            int aleatorio = unico.nextInt(9 + 1);
            numeros3 = numeros3 + aleatorio;
            d++;
        }

        int e = 0;
        while (e < 4) {
            int aleatorio = unico.nextInt(9 + 1);
            numeros4 = numeros4 + aleatorio;
            e++;
        }

///////////////////////////////////////////////
        int f = 0;
        while (f < 2) {
            int aleatorio = unico.nextInt(9 + 1);
            letras3 = letras3 + abecedario[aleatorio];
            f++;
        }
        int g = 0;
        while (g < 2) {
            int aleatorio = unico.nextInt(9 + 1);
            letras4 = letras4 + abecedario[aleatorio];
            g++;
        }
        int h = 0;
        while (h < 2) {
            int aleatorio = unico.nextInt(9 + 1);
            letras5 = letras5 + abecedario[aleatorio];
            h++;
        }
        int i = 0;
        while (i < 2) {
            int aleatorio = unico.nextInt(9 + 1);
            letras6 = letras6 + abecedario[aleatorio];
            i++;
        }

////////////////////////////////////////////////
        int j = 0;
        while (j < 1) {
            int aleatorio = unico.nextInt(9 + 1);
            numeros5 = numeros5 + aleatorio;
            j++;
        }
        int k = 0;
        while (k < 1) {
            int aleatorio = unico.nextInt(9 + 1);
            numeros6 = numeros6 + aleatorio;
            k++;
        }
        int l = 0;
        while (l < 1) {
            int aleatorio = unico.nextInt(9 + 1);
            numeros7 = numeros7 + aleatorio;
            l++;
        }
        int m = 0;
        while (m < 1) {
            int aleatorio = unico.nextInt(9 + 1);
            numeros8 = numeros8 + aleatorio;
            m++;
        }
        String cadena = letras3 + numeros5 + letras4 + numeros6 + letras5 + numeros7 + letras6 + numeros8;
///////////////////////////////

        String cadenaFinal = letras1 + numeros1 + "-" + letras2 + numeros2 + "-" + numeros3 + "-" + numeros4 + "-" + cadena;
        String folioF = cadenaFinal;

/////////////////////////////////
        String rfcRecep = jTextField8.getText();
        String nombreRecep = jTextField1.getText();
        

        Date date = new Date();
        SimpleDateFormat formato1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String fechaCertificacion = formato1.format(date);
        
        String efecto = "";
        if (jComboBox3.getSelectedIndex() == 1) {
            efecto = "E";
        }
        if (jComboBox3.getSelectedIndex() == 2) {
            efecto = "I";
        }
        if (jComboBox3.getSelectedIndex() == 3) {
            efecto = "N";
        }
        if (jComboBox3.getSelectedIndex() == 4) {
            efecto = "P";
        }
        if (jComboBox3.getSelectedIndex() == 5) {
            efecto = "T";
        }
       
        //colocar datos en resultado
        jLabel69.setText(folioF);
        jLabel70.setText(rfcRecep);
        jLabel71.setText(nombreRecep);
        jLabel73.setText(fechaCertificacion);
        jLabel74.setText(efecto);
        jLabel67.setText(total);
        cargarResultado();
    }
    
    
       //pestaña factura electronica
    public void cargarResultado(){
        jButton7.setEnabled(true);
        jButton7.setBackground(Color.GREEN);
        jPanel1.setVisible(false);
        jPanel2.setVisible(false);
        jPanel3.setVisible(true);
        jButton5.setEnabled(false);
        jButton6.setEnabled(false);
        
        jLabel11.setVisible(false);
        jButton1.setVisible(false);
        jButton4.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setBackground(Color.WHITE);   
        
        jMenuItem1.setEnabled(false);
        registrosFactura();
    }
    
    //GUARDAR REGISTROS A TABLA SQL
     public void registrosFactura(){
       try{
          String truncate = "TRUNCATE TABLE factura";
          ps = cn.prepareStatement(truncate);
          ps.execute();
          String insercion = "INSERT INTO factura values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
          ps = cn.prepareStatement(insercion);
          ps.setString(1, rfcEmisor);
          ps.setString(2, nombreEmisor);
          ps.setString(3, folio);
          ps.setString(4, rfcReceptor);
          ps.setString(5, usoCFDI);
          ps.setString(6, folioFiscal);
          ps.setString(7, serieCSDEmisor);
          ps.setString(8, serie);
          ps.setString(9, codigoPostalFechaHora);
          ps.setString(10, efectoComprobante);
          ps.setString(11, regimenFiscal);
          ps.setString(12, fechaHoraCertificacion);
          ps.setString(13, moneda);
          ps.setString(14, formaPago);
          ps.setString(15, metodoPago);
          ps.setString(16, subtotal);
          ps.setString(17,impuestos);
          ps.setString(18, total);
          ps.executeUpdate();
          System.out.println("REGISTROS GUARDADOS");
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
       }    
    }

    //GENERAR PDF CON JASPERREPORTS
    public void generarPDF() {
        try {
            JasperReport reporte = null;
            String path = "src\\Reportes\\ReportePDF.jasper";
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, cn);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        }
    }
 
    //boton regresar
    public void regresar(){
        jPanel1.setVisible(false);
        jPanel2.setVisible(true);
        jPanel3.setVisible(false);
        
        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton5.setBackground(Color.GREEN);
        jButton6.setBackground(Color.GREEN);
        jButton7.setEnabled(false);
        jButton1.setVisible(true);
        jButton4.setVisible(true);
        jButton2.setVisible(false);
        
        jLabel11.setVisible(true);
        jMenuItem1.setEnabled(true);
    }

    
    //ABRIR VENTANA CONCEPTO
   public void nuevoConcepto(){
       concepto1 = new Concepto();
       concepto1.setVisible(true);
   }
   
   //ABRIR VENTANA IMPUESTOS RETENIDOS
   public void impuestosRetenidos(){
       ir = new ImpuestosRetenidos();
       ir.setVisible(true);
   }
   
   //ABRIR VENTANA IMPUESTOS TRASLADADOS
   public void impuestosTrasladados(){
       it = new ImpuestosTrasladados();
       it.setVisible(true);
   }

    public void cerrarVentana(){
    try{
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                confirmacionCerrar();
            }
        });
        this.setVisible(true);
    }catch (Exception e){
    }
    }
    
    public void confirmacionCerrar(){
        int valor = JOptionPane.showConfirmDialog(null,"¿Esta seguro de cerrar el programa?","Advertencia",1,JOptionPane.YES_NO_OPTION);
        if (valor==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    public void botonSiguiente(ActionEvent evt) {
        boolean verdadero1 = false;
        boolean verdadero2 = false;
        boolean verdadero3 = false;
        boolean verdadero4 = false;
        boolean verdadero5 = false;
        boolean verdadero6 = false;
        boolean verdadero7 = false;

        String rfcE = jTextField2.getText();
        int regimen = jComboBox1.getSelectedIndex();
        int tipoF = jComboBox3.getSelectedIndex();
        String rfcR = jTextField8.getText();
        int usoF = jComboBox2.getSelectedIndex();

        if (rfcE.length() > 0) {
            verdadero1 = true;
        } else {
            jLabel30.setVisible(true);
        }
        if (regimen > 0) {
            verdadero2 = true;
        } else {
            jLabel50.setVisible(true);
        }

        if (tipoF > 0) {
            verdadero3 = true;
        } else {
            jLabel51.setVisible(true);
        }

        if (rfcR.length() > 0) {
            verdadero4 = true;
        } else {
            jLabel52.setVisible(true);
        }

        if (usoF > 0) {
            verdadero5 = true;
        } else {
            jLabel29.setVisible(true);
        }

        String cp = jTextField12.getText();
        int money = jComboBox10.getSelectedIndex();

        if (cp.length() > 0) {
            verdadero6 = true;
        } else {
            jLabel55.setVisible(true);
        }
        if (money > 0) {
            verdadero7 = true;
        } else {
            jLabel53.setVisible(true);
        }

        //comprobar campos
        if (jPanel1.isVisible() && verdadero1 == true && verdadero2 == true && verdadero3 == true && verdadero4 == true && verdadero5 == true) {
            jPanel2.setVisible(true);
            jPanel1.setVisible(false);
            jPanel3.setVisible(false);
            jLabel55.setVisible(false);
            jLabel53.setVisible(false);
            jButton5.setBackground(Color.GREEN);
            validate();
            deshabilitarBoton();
        }else{
            jButton5.setBackground(Color.WHITE);
        }
                 
            /*
        } else if (jPanel2.isVisible() && verdadero6 == true && verdadero7 == true) {
            jPanel2.setVisible(false);
            jPanel1.setVisible(false);
            jPanel3.setVisible(false);
            validate();
            deshabilitarBoton();
        }
*/              
    }

    public void botonAtras(ActionEvent e) {
        if (jPanel2.isVisible()) {
            jPanel2.setVisible(false);
            jPanel3.setVisible(false);
            jPanel1.setVisible(true);
            validate();
            deshabilitarBoton();
        }
    }

    public void deshabilitarBoton() {
        if (jPanel1.isVisible()) {
            jButton1.setVisible(false);
            jButton2.setVisible(true);

        } else if (jPanel2.isVisible()) {
            jButton1.setVisible(true);
            jButton2.setVisible(false);
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

        jPanel3 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jComboBox13 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel75 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel57.setText("Resultado del Comprobante");
        jPanel3.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 320, 60));

        jButton3.setText("Regresar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 400, 110, 40));

        jButton12.setBorder(null);
        jButton12.setContentAreaFilled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 40, 40));

        jButton14.setBorder(null);
        jButton14.setContentAreaFilled(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 40, 40));
        jPanel3.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 50));

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 90));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("         Total");
        jLabel59.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 130, 110, 60));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setText("    Acciones");
        jLabel60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 100, 60));

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setText("                Folio fiscal");
        jLabel61.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 190, 60));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setText("   RFC Receptor");
        jLabel62.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 110, 60));

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel63.setText(" Nombre o razón social del receptor");
        jLabel63.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 250, 60));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel64.setText("   Fecha de emisión");
        jLabel64.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 140, 60));

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setText("   Fecha de certificación");
        jLabel65.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 130, 170, 60));

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel66.setText("   Efecto");
        jLabel66.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 130, 80, 60));

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel67.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel67.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 190, 110, 170));

        jLabel68.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 100, 170));

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel69.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel69.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 190, 170));

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel70.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel70.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 110, 170));

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel71.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel71.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 250, 170));

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel72.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel72.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 140, 170));

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel73.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel73.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 190, 170, 170));

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel74.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel74.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 190, 80, 170));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 1200, 470));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText(" Datos del emisor");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1160, 40));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("RFC*:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 120, 40));

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 250, 30));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, 320, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText(" Datos del receptor");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 1160, 40));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText("Nombre o razón social:");
        jPanel1.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, 170, 40));

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 1140, 30));

        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "E Egreso", "I Ingreso", "N Nómina", "P Pago", "T Traslado" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 240, 540, 40));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "P01 Por definir", "D01 Honorarios médicos, dentales y gatos hospitalarios", "D02 Gastos médicos por incapacidad o discapacidad", "D03 Gastos funerales", "D04 Donativos", "D05 Intereses reales efectivamente pagados por créditos hipotecarios (casa habitación)", "D06 Aportaciones voluntarias al SAR", "D07 Primas por seguros de gastos médicos", "D08 Gastos por transportacion escolar obligatoria", "D09 Depósitos en cuentas para el ahorro, primas que tengan como base planes de pensiones", "D10 Pagos por servicios educativos (colegiaturas)", "G01 Adquisición de mercancias", "G02 Devoluciones, descuentos o bonificaciones", "G03 Gastos en general", "I01 Construcciones", "I02 Mobiliario y equipo de oficina por inversiones", "I03 Equipo de transporte", "I04 Equipo de computo y accesorios", "I05 Dados, troqueles, moldes, matrices y herramental", "I06 Comunicaciones telefónicas", "I07 Comunicaciones satelitales", "I08 Otra maquinaria y equipo" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 390, 540, 30));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 0, 0));
        jLabel29.setText("Este campo es obligatorio");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 350, 160, 40));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Uso de la factura*:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 350, 120, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Tipo de factura*:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 120, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("RFC*:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 90, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Régimen fiscal*:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 130, 40));

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 250, 30));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "605 Sueldos y Salarios e Ingresos Asimilados a Salarios", "606 Arrendamiento", "608 Demás ingresos", "610 Residentes en el Extranjero sin Establecimiento Permanente en México", "611 Ingresos por Dividendos (socios y accionistas)", "612 Personas Fisicas con Actividades Empresariales y Profesionales", "614 Ingresos por intereses", "615 Régimen de los ingresos por obtención de premios", "616 Sin obligaciones fiscales", "621 Régimen de Incorporación Fiscal", "622 Actividades Agrícolas, Ganaderas, Silvícolas y Pesqueras" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 580, 40));

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 0, 0));
        jLabel51.setText("Este campo es obligatorio");
        jPanel1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 210, 160, 20));

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 0, 0));
        jLabel50.setText("Este campo es obligatorio");
        jPanel1.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 160, 20));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 0, 0));
        jLabel30.setText("Este campo es obligatorio");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 160, 30));

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 0, 0));
        jLabel52.setText("Este campo es obligatorio");
        jPanel1.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 160, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Nombre o razón social: ");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 170, 30));

        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 1160, 110));

        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 1160, 230));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 1200, 470));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Codigo postal*:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 190, 20));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Moneda*:");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 55, 80, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Condiciones de pago:");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 180, 20));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Fecha y hora de expedición*:");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 190, 20));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Forma de pago:");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 180, 20));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Método de pago:");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 190, 20));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Folio:");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 140, 20));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Serie:");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 180, 20));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Tipo de cambio:");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 110, 190, 20));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Confirmación:");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 160, 190, 20));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText(" Comprobante");
        jLabel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1160, 30));

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 0, 0));
        jLabel55.setText("Este campo es obligatorio");
        jPanel2.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 160, 40));

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });
        jPanel2.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 360, -1));

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField11KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 360, -1));

        jTextField12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField12KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField12KeyTyped(evt);
            }
        });
        jPanel2.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 370, -1));

        jTextField14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField14KeyTyped(evt);
            }
        });
        jPanel2.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 360, 30));

        jTextField15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField15KeyTyped(evt);
            }
        });
        jPanel2.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 370, 30));

        jTextField18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });
        jPanel2.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 130, 320, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText("Descuento:");
        jPanel2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 390, 90, 30));

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 420, 180, 30));

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 420, 180, 30));

        jTextField19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 180, 320, 30));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setText("Total de impuestos retenidos:");
        jPanel2.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 390, 190, 30));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel38.setText("Total*:");
        jPanel2.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 330, 70, 30));

        jComboBox10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "MXN Peso Mexicano", "MXV México Unidad de Inversión (UDI)" }));
        jComboBox10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox10ItemStateChanged(evt);
            }
        });
        jPanel2.add(jComboBox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 80, 320, -1));

        jComboBox13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "01 Efectivo", "02 Cheque Nominativo", "03 Transferencia electrónica de fondos (incluye SPEI)", "04 Tarjeta de crédito", "05 Monedero electrónico", "06 Dinero electrónico", "08 Vales de despensa", "12 Dación en pago", "13 Pago por subrogación", "14 Pago por consignación", "15 Condonación ", "16 Compensación", "23 Novación", "24 Confusión", "25 Remisión de deuda", "26 Preinscripción o caducidad", "27 A satisfacción del acreedor", "28 Tarjeta de débito", "29 Tarjeta de servicios", "30 Aplicación de anticipos", "99 Por definir" }));
        jPanel2.add(jComboBox13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 360, 30));

        jComboBox9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "PPD Pago en parcialidades o diferido", "PUE Pago en una sola exhibición" }));
        jPanel2.add(jComboBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 370, 30));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 0, 0));
        jLabel53.setText("Este campo es obligatorio");
        jPanel2.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 50, 160, 40));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText("Total de impuestos trasladados:");
        jPanel2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 330, 190, 30));

        jLabel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 1160, 220));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText(" Impuestos trasladados:");
        jLabel41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 590, 20));

        jButton10.setBackground(new java.awt.Color(22, 147, 250));
        jButton10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton10.setText("VER");
        jButton10.setBorderPainted(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 350, 100, 40));

        jButton9.setBackground(new java.awt.Color(22, 147, 250));
        jButton9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton9.setText("VER");
        jButton9.setBorderPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 420, 100, 40));

        jButton8.setBackground(new java.awt.Color(22, 147, 250));
        jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton8.setText("Nuevo");
        jButton8.setBorder(null);
        jButton8.setBorderPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 280, 100, 40));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText(" Concepto");
        jLabel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 1160, 40));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel34.setText("Subtotal*:");
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 330, 90, 30));

        jTextField16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 420, 160, 30));

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 360, 180, 30));

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 360, 160, 30));

        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 360, 180, 30));

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel76.setText("Total de Impuestos:");
        jPanel2.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 390, 160, 30));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 330, 560, 130));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setText(" Impuestos retenidos:");
        jLabel49.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 590, 20));

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 0, 0));
        jLabel54.setText("Este campo es obligatorio");
        jPanel2.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 160, 40));

        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 590, 40));

        jLabel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 590, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 1200, 470));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("Emisor / Receptor");
        jButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 160, 50));

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton7.setText("Factura Electrónica");
        jButton7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 200, 50));

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("Comprobante");
        jButton6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 160, 50));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Guardar comprobante");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 640, 170, 40));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Atras");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 640, 90, 40));

        jButton2.setBackground(new java.awt.Color(80, 172, 249));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Siguiente");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 640, 100, 40));

        jLabel75.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 0, 110, 90));

        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 320, 90));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("FACTURA ELECTRÓNICA");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 270, 50));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rectangulo verde.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 690, 1330, 100));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rectangulo verde.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1320, 90));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("*Campos obligatorios");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 640, 130, 40));

        jLabel16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel16MouseDragged(evt);
            }
        });
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel16MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel16MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1300, 770));

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Nueva factura...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem4.setText("Crear XML");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem7.setText("Leer XML");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem6.setText("Leer XML2");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem5.setText("Salir");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // SALIR
        salir();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // BOTON ATRAS
        botonAtras(evt);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //GUARDAR COMPROBANTE
        comprobarCamposObligatorios(); 
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // BOTON SIGUIENTE
       botonSiguiente(evt);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //NUEVO CONCEPTO
        nuevoConcepto();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // PESTAÑA EMISOR / RECEPTOR
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // PESTAÑA COMPROBANTE
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // NUEVA FACTURA
        nuevaFactura();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTextField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyReleased
        // CAMPO CODIGO POSTAL
        tipeadoCamposObligatorios(evt);
    }//GEN-LAST:event_jTextField12KeyReleased

    private void jTextField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyReleased
        // CAMPO FECHA Y HORA DE EXPEDICION
        tipeadoCamposObligatorios(evt);
    }//GEN-LAST:event_jTextField11KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // CAMPO RFC
        tipeadoCamposObligatorios(evt);
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // COMBO REGIMEN FISCAL
        comprobarCombosObligatorios(evt);   
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // COMBO TIPO DE FACTURA
        comprobarCombosObligatorios(evt);     
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // COMBO USO DE LA FACTURA
         comprobarCombosObligatorios(evt);
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox10ItemStateChanged
        // CAMPO MONEDA
         comprobarCombosObligatorios(evt);
    }//GEN-LAST:event_jComboBox10ItemStateChanged

    private void jLabel16MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseReleased

    }//GEN-LAST:event_jLabel16MouseReleased

    //int xx;
    //int xy;
    private void jLabel16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MousePressed
        // MOUSE SOSTENIDO
        //  xx = evt.getX();
        // xy = evt.getY();
    }//GEN-LAST:event_jLabel16MousePressed

    private void jLabel16MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseDragged
        //MOUSE SOLTADO
        //int x = evt.getXOnScreen();
        //int y = evt.getYOnScreen();
        //this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jLabel16MouseDragged

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TIPEADO RFC EMISOR
        limiteRFCEmisor(evt);
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        // TIPEADO RFC RECEPTOR
        limiteRFCReceptor(evt);
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //BOTON REGRESAR - PESTAÑA FACTURA ELECTRONICA
        regresar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        // CAMPO RFC RECEPTOR
        tipeadoCamposObligatorios(evt);
    }//GEN-LAST:event_jTextField8KeyReleased

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            // BOTON GENERAR XML (MENU)
            crearXML();
        } catch (Exception ex){
           ex.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        //BOTON GENERAR PDF 
        generarPDF();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // BOTON CREAR XML
        try {
            // BOTON GENERAR XML
            crearXML();
        } catch (Exception ex){
           ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // NOMBRE O RAZON SOCIAL - EMISOR
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // NOMBRE O RAZON SOCIAL - RECEPTOR

    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // NOMBRE O RAZON SOCIAL - EMISOR
        limiteNombreEmisor(evt);
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // NOMBRE O RAZON SOCIAL - RECEPTOR
        limiteNombreReceptor(evt);
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyTyped
        // LIMITE CODIGO POSTAL
        limiteCodigoPostal(evt);
    }//GEN-LAST:event_jTextField12KeyTyped

    private void jTextField18KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField18KeyTyped
        // LIMITE TIPO DE CAMBIO
        limiteTipoCambio(evt);
    }//GEN-LAST:event_jTextField18KeyTyped

    private void jTextField14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyTyped
        // LIMITE SERIE
        limiteSerie(evt);
    }//GEN-LAST:event_jTextField14KeyTyped

    private void jTextField15KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyTyped
        //LIMITE FOLIO
        limiteFolio(evt);
    }//GEN-LAST:event_jTextField15KeyTyped

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
        //LIMITE CONDICIONES DE PAGO
        limiteCondicionesPago(evt);
    }//GEN-LAST:event_jTextField7KeyTyped

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // LEER XML
     //   leerXML2();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // LEER XML
       // leerXML();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // IMPUESTOS RETENIDOS
        impuestosRetenidos();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // IMPUESTOS TRASLADADOS
        impuestosTrasladados();
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(FormatoFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormatoFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormatoFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormatoFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormatoFactura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox9;
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
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    public static javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    public static javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    public static javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    public static javax.swing.JTextField jTextField4;
    public static javax.swing.JTextField jTextField5;
    public static javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    public static javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

}
