/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Gaming
 */
public class Concepto {
    public int numero;
    public String claveProductoServicio;
    public String claveUnidad;
    public String cantidad;
    public String unidad;
    public String numeroIdentificacion;
    public String descripcion;
    public String valorUnitario;
    public String importe;
    public String tipoImpuesto;

    public Concepto(int numero,String claveProductoServicio, String claveUnidad, String cantidad, String unidad, String numeroIdentificacion, String descripcion,String valorUnitario, String importe, String tipoImpuesto) {
        this.numero = numero;
        this.claveProductoServicio = claveProductoServicio;
        this.claveUnidad = claveUnidad;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.numeroIdentificacion = numeroIdentificacion;
        this.descripcion = descripcion;
        this.valorUnitario = valorUnitario;
        this.importe = importe;
        this.tipoImpuesto = tipoImpuesto;
    }
    
    public int getNumero(){
        return numero;
    }
    
    public void setNumero(int numero){
        this.numero = numero;
    }

    public String getClaveProductoServicio() {
        return claveProductoServicio;
    }

    public void setClaveProductoServicio(String claveProductoServicio) {
        this.claveProductoServicio = claveProductoServicio;
    }

    public String getClaveUnidad() {
        return claveUnidad;
    }

    public void setClaveUnidad(String claveUnidad) {
        this.claveUnidad = claveUnidad;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

      public String getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    
    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(String tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }
    
    public void imprimirDatos(){
        System.out.println(claveProductoServicio);
        System.out.println(claveUnidad);
        System.out.println(cantidad);
        System.out.println(unidad);
        System.out.println(numeroIdentificacion);
        System.out.println(descripcion);
        System.out.println(valorUnitario);
        System.out.println(importe);
        System.out.println(tipoImpuesto);
    }
    
}
