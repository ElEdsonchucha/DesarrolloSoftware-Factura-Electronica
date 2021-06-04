/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desarrollosoftware;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Login extends javax.swing.JFrame {

    public String captcha1;
    public int xx, xy;

    public Login() {
        initComponents();

        this.setTitle("Iniciar Sesion");
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 650);
        this.setVisible(true);
        //this.setResizable(false);
        this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logo sat.png")).getImage());

        imagenGobierno();
        funcionMinimizar();
        funcionCerrar();
        cargarCaptcha();
    }

    public void imagenGobierno(){
        ImageIcon imagen = new ImageIcon(new ImageIcon(this.getClass().getResource("/Imagenes/gobierno de mexico.png")).getImage());
        Image icono = imagen.getImage();
        Image redimension = icono.getScaledInstance(jLabel3.getWidth(), jLabel3.getHeight(), Image.SCALE_SMOOTH);
        jLabel3.setIcon(new ImageIcon(redimension));       
    }
    
    public void funcionMinimizar(){
        ImageIcon imagen = new ImageIcon(new ImageIcon(this.getClass().getResource("/Imagenes/minimizar.png")).getImage());
        Image icono = imagen.getImage();
        Image redimension = icono.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_SMOOTH);
        jLabel11.setIcon(new ImageIcon(redimension));      
    }
    
    public void funcionCerrar(){
        ImageIcon imagen1 = new ImageIcon(new ImageIcon(this.getClass().getResource("/Imagenes/icono x pequeño.png")).getImage());
        Image icono1 = imagen1.getImage();
        Image redimension1 = icono1.getScaledInstance(jLabel10.getWidth(), jLabel10.getHeight(), Image.SCALE_SMOOTH);
        jLabel10.setIcon(new ImageIcon(redimension1));
}
    
    public void salir(){
        System.exit(0);
    }
    
    public void minimizar(){
        this.setState(JFrame.ICONIFIED);
    }
    
    public void campoCaptcha(){
       String texto = jTextField1.getText();
       String mayuscula = texto.toUpperCase();
       jTextField1.setText(mayuscula);
    }
    
    //java.awt.event.KeyEvent 
       public void limitarCaptcha(KeyEvent evt) {                                     
        // TIPEAR EN EL CAMPO
       String texto = jTextField1.getText();
       if (texto.length() >=6){
           getToolkit().beep();
           evt.consume();
       }
    }
    
    public void cargarCaptcha(){
        String imagen = "";
        Random aleatorio = new Random(); 
        int numero = aleatorio.nextInt(5);
       switch(numero){
           case 0:
               imagen = "/Imagenes/captcha1.png";
              captcha1= "W7MZZ5";
               break;
           case 1:
               imagen = "/Imagenes/captcha2.png";
               captcha1= "RQ657G";
               break;
           case 2:
               imagen = "/Imagenes/captcha3.png";
                captcha1= "6ZVYBP";
               break;
           case 3:
               imagen = "/Imagenes/captcha4.png";
               captcha1= "NFMPYL";
               break;
           case 4:
               imagen = "/Imagenes/captcha5.png";
                captcha1= "RR6LQP";
               break;
               
       }
       
       ImageIcon captcha = new ImageIcon(new ImageIcon(this.getClass().getResource(imagen)).getImage());
       Image fondo = captcha.getImage();
       Image comprobante = fondo.getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_SMOOTH);
       jLabel8.setIcon(new ImageIcon(comprobante));
    }
 
    public void comprobacion(){
         boolean verdadero1 = false;
         boolean verdadero2 = false;
        String rfc = jTextField2.getText();
        String contra = jTextField3.getText();
        String codigo = jTextField1.getText();
        //COMPROBACION
        if (rfc.equals("admin") && contra.equals("admin")) {
            verdadero1 = true;
        }
        if (codigo.equals(captcha1)) {
            verdadero2 = true;
        }
        //ENVIAR
         if (verdadero1 == true && verdadero2 == true) {
            FormatoFactura formato1 = new FormatoFactura();
            formato1.setVisible(true);
            dispose();
        } else {
           System.out.println("falso");
        }
        
    }
    
    public void mousePresionado(MouseEvent evt){
        xx = evt.getX();
        xy = evt.getY();
    }
    
    public void mouseArrastrado(MouseEvent evt){
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen(); 
        this.setLocation(x - xx, y- xy);
    }

    public void tipeadoRFC(KeyEvent evt){
        char c = evt.getKeyChar();
       if( (c<'a' || c> 'z') && (c<'A' || c> 'Z') && (c>' ') &&(c<'á'||c>'ú')&&(c<'Á'||c>'Ú') && (c<'0' || c>'9'))  evt.consume();
        if (jTextField2.getText().length() >=15){
            evt.consume();
        }
    }
    
    public void tipeadoContra(KeyEvent evt){
            char c = evt.getKeyChar();
        if( (c<'a' || c> 'z') && (c<'A' || c> 'Z') && (c>' ') &&(c<'á'||c>'ú')&&(c<'Á'||c>'Ú') && (c<'0' || c>'9'))  evt.consume();
        if (jTextField3.getText().length() >=15){
            evt.consume();
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

        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 70, 60));

        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, -10, 100, 70));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 100));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rectangulo verde.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 570, 940, 120));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rectangulo verde.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -20, 950, 120));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("            Captcha:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 130, 50));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Acceso por contraseña");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 330, 60));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("              RFC:");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 110, 50));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("          Contraseña:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 130, 50));

        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, 240, 70));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 400, 240, 30));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 240, 30));

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 240, 30));

        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 520, 300));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Enviar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 480, 120, 60));

        jLabel12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel12MouseDragged(evt);
            }
        });
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel12MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel12MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 850, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jLabel12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MousePressed
        //MOUSE PRESIONADO     
        mousePresionado(evt);
    }//GEN-LAST:event_jLabel12MousePressed

    private void jLabel12MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseReleased
        // MOUSE SOLTADO      
    }//GEN-LAST:event_jLabel12MouseReleased

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // ICONO X
       salir();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel12MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseDragged
        // MOUSE ARRASTRADO
        mouseArrastrado(evt);
    }//GEN-LAST:event_jLabel12MouseDragged

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // MINIMIZAR
        minimizar();
    }//GEN-LAST:event_jLabel11MouseClicked
  
   
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //BOTON ENVIAR
        comprobacion();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TIPEAR EN EL CAMPO
        limitarCaptcha(evt);
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TECLA SOLTADA
        campoCaptcha();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        //TIPEADO DE RFC
        tipeadoRFC(evt);
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TIPEADO DE CONTRASEÑA
        tipeadoContra(evt);
    }//GEN-LAST:event_jTextField3KeyTyped

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

}