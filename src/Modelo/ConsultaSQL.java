/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import com.mysql.jdbc.Driver;
import com.mysql.cj.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class ConsultaSQL {
    ConexionSQL conexion = new ConexionSQL();
    Connection cn = conexion.getConnection();
    DefaultTableModel modelo = new DefaultTableModel();
    PreparedStatement ps;
    ResultSet rs;
    
    public ArrayList<Factura> listarFactura(){
        ArrayList<Factura> lista = new ArrayList<>();
        cn = null;
        try {
            Class.forName(conexion.driver);
            cn = DriverManager.getConnection(conexion.url, conexion.user, conexion.password);
            String sql = "select * from factura";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            Factura factura;
            while (rs.next()) {
                factura = new Factura(rs.getString("RFCEmisor"), rs.getString("NombreEmisor"), rs.getString("Folio"), rs.getString("RFCReceptor"), rs.getString("UsoCFDI"),
                         rs.getString("FolioFiscal"), rs.getString("NoSerieEmisor"), rs.getString("Serie"), rs.getString("CodigoPostalFechaHoraEmision"), rs.getString("EfectoComprobante"),
                         rs.getString("RegimenFiscal"), rs.getString("FechaHoraCertificacion"), rs.getString("Moneda"), rs.getString("FormaPago")
                        ,rs.getString("MetodoPago"), rs.getString("Subtotal"),rs.getString("Impuestos"), rs.getString("Total"));
                lista.add(factura);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    public void mostrarFactura() {
        modelo = new DefaultTableModel();
        ArrayList<Factura> lista = listarFactura();
        Object registros[] = new Object[18];
        for (int i = 0; i < lista.size(); i++) {
            registros[0] = lista.get(i).getRfcEmisor();
            registros[1] = lista.get(i).getNombreEmisor();
            registros[2] = lista.get(i).getFolio();
            registros[3] = lista.get(i).getRfcReceptor();
            registros[4] = lista.get(i).getUsoCFDI();
            registros[5] = lista.get(i).getFolioFiscal();
            registros[6] = lista.get(i).getNoSerieEmisor();
            registros[7] = lista.get(i).getSerie();
            registros[8] = lista.get(i).getCodigoPostalFechaHoraEmision();
            registros[9] = lista.get(i).getEfectoComprobante();
            registros[10] = lista.get(i).getRegimenFiscal();
            registros[11] = lista.get(i).getFechaHoraCertificacion();
            registros[12] = lista.get(i).getMoneda();
            registros[13] = lista.get(i).getFormaPago();
            registros[14] = lista.get(i).getMetodoPago();
            registros[15] = lista.get(i).getSubtotal();
            registros[16] = lista.get(i).getImpuestos();
            registros[17] = lista.get(i).getTotal();          
            modelo.addRow(registros);        
        }  
        for (Factura list: lista){
            list.imprimirDatos();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    
       public ArrayList<Concepto> listarConcepto(String sql){
        ArrayList<Concepto> lista = new ArrayList<>();
        cn = null;
        try {
            Class.forName(conexion.driver);
            cn = DriverManager.getConnection(conexion.url, conexion.user, conexion.password);
            //String sql = "select * from concepto";           
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            Concepto concepto;
            while (rs.next()) {
                concepto = new Concepto(rs.getInt("No"),rs.getString("claveProductoServicio"),rs.getString("descripcion"),rs.getString("claveUnidad"),rs.getString("cantidad")
                ,rs.getString("unidad"),rs.getString("numeroIdentificacion"),rs.getString("importe"),rs.getString("tipoImpuesto"),rs.getString("valorUnitario"));
                lista.add(concepto);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    public void mostrarConcepto() {
        modelo = new DefaultTableModel();
        String sql = "select * from concepto";
        ArrayList<Concepto> lista = listarConcepto(sql);
        Object registros[] = new Object[10];
        for (int i = 0; i < lista.size(); i++) {
            registros[0] = lista.get(i).getNumero();
            registros[1] = lista.get(i).getClaveProductoServicio();
            registros[2] = lista.get(i).getDescripcion();
            registros[3] = lista.get(i).getClaveUnidad();
            registros[4] = lista.get(i).getCantidad();
            registros[5] = lista.get(i).getUnidad();
            registros[6] = lista.get(i).getNumeroIdentificacion();
            registros[7] = lista.get(i).getValorUnitario();
            registros[8] = lista.get(i).getImporte();
            registros[9] = lista.get(i).getTipoImpuesto();
            modelo.addRow(registros);
        }
        for (Concepto list: lista){
            list.imprimirDatos();
        }
    }

    
}
