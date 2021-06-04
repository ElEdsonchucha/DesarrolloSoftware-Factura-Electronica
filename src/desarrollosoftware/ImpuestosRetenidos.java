/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desarrollosoftware;

import Modelo.Concepto;
import Modelo.ConexionSQL;
import Modelo.ConsultaSQL;
import java.awt.Color;
import java.awt.Image;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.ImageIcon;

public class ImpuestosRetenidos extends javax.swing.JFrame {
    DefaultTableModel modelo;
    ConsultaSQL consulta = new ConsultaSQL();
    ConexionSQL conexion = new ConexionSQL();
    Connection cn = conexion.getConnection();
    
    public ImpuestosRetenidos() {
        initComponents();
        
        this.setTitle("Impuestos Retenidos");
        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1000,485);
        this.setResizable(false);
        iconoAplicacion();
        modeloTabla();   
    }
    
    public void iconoAplicacion(){
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Imagenes/logo sat.png")).getImage());
    }
    public void modeloTabla(){
        modelo = (DefaultTableModel) jTable1.getModel();      
        modelo.addColumn("No");
        modelo.addColumn("ClaveProductoServicio");
        modelo.addColumn("Descripción");
        modelo.addColumn("ClaveUnidad");
        modelo.addColumn("No.Identificación");
        modelo.addColumn("unidad");
        modelo.addColumn("cantidad");
        modelo.addColumn("ValorUnitario");
        modelo.addColumn("importe");
        modelo.addColumn("TipoImpuesto");
        jTable1.setModel(modelo);
        obtenerRegistros();
    }

    public void obtenerRegistros() {
        String sql = "select * from concepto where tipoImpuesto = 'Retencion'";
        ArrayList<Concepto> lista = consulta.listarConcepto(sql);
        ingresarRegistros(lista);
    }

   public void ingresarRegistros(ArrayList<Concepto> lista) {
       modelo = (DefaultTableModel) jTable1.getModel();
       Object registros[] = new Object[10];
       for (int i = 0; i < lista.size(); i++) {
           registros[0] = lista.get(i).getNumero();
           registros[1] = lista.get(i).getClaveProductoServicio();
           registros[2] = lista.get(i).getClaveUnidad();
           registros[3] = lista.get(i).getCantidad();
           registros[4] = lista.get(i).getDescripcion();
           registros[5] = lista.get(i).getNumeroIdentificacion();
           registros[6] = lista.get(i).getUnidad();
           registros[7] = lista.get(i).getTipoImpuesto();
           registros[8] = lista.get(i).getValorUnitario();
           registros[9] = lista.get(i).getImporte();
           modelo.addRow(registros);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ImpuestosRetenidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImpuestosRetenidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImpuestosRetenidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImpuestosRetenidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImpuestosRetenidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
