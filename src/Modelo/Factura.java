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
public class Factura {
    public String rfcEmisor;
    public String nombreEmisor;
    public String folio;
    public String rfcReceptor;
    public String usoCFDI;
    public String folioFiscal;
    public String noSerieEmisor;
    public String serie;
    public String codigoPostalFechaHoraEmision;
    public String efectoComprobante;
    public String regimenFiscal;
    public String fechaHoraCertificacion;
    public String moneda;
    public String formaPago;
    public String metodoPago;
    public String subtotal;
    public String impuestos;
    public String total;

    public Factura(String rfcEmisor, String nombreEmisor, String folio, String rfcReceptor, String usoCFDI, String folioFiscal, String noSerieEmisor, String serie, String codigoPostalFechaHoraEmision, String efectoComprobante, String regimenFiscal, String fechaHoraCertificacion, String moneda, String formaPago, String metodoPago, String subtotal,String impuestos, String total) {
        this.rfcEmisor = rfcEmisor;
        this.nombreEmisor = nombreEmisor;
        this.folio = folio;
        this.rfcReceptor = rfcReceptor;
        this.usoCFDI = usoCFDI;
        this.folioFiscal = folioFiscal;
        this.noSerieEmisor = noSerieEmisor;
        this.serie = serie;
        this.codigoPostalFechaHoraEmision = codigoPostalFechaHoraEmision;
        this.efectoComprobante = efectoComprobante;
        this.regimenFiscal = regimenFiscal;
        this.fechaHoraCertificacion = fechaHoraCertificacion;
        this.moneda = moneda;
        this.formaPago = formaPago;
        this.metodoPago = metodoPago;
        this.subtotal = subtotal;
        this.impuestos = impuestos;
        this.total = total;
    }

    public String getRfcEmisor() {
        return rfcEmisor;
    }

    public void setRfcEmisor(String rfcEmisor) {
        this.rfcEmisor = rfcEmisor;
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getRfcReceptor() {
        return rfcReceptor;
    }

    public void setRfcReceptor(String rfcReceptor) {
        this.rfcReceptor = rfcReceptor;
    }

    public String getUsoCFDI() {
        return usoCFDI;
    }

    public void setUsoCFDI(String usoCFDI) {
        this.usoCFDI = usoCFDI;
    }

    public String getFolioFiscal() {
        return folioFiscal;
    }

    public void setFolioFiscal(String folioFiscal) {
        this.folioFiscal = folioFiscal;
    }

    public String getNoSerieEmisor() {
        return noSerieEmisor;
    }

    public void setNoSerieEmisor(String noSerieEmisor) {
        this.noSerieEmisor = noSerieEmisor;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCodigoPostalFechaHoraEmision() {
        return codigoPostalFechaHoraEmision;
    }

    public void setCodigoPostalFechaHoraEmision(String codigoPostalFechaHoraEmision) {
        this.codigoPostalFechaHoraEmision = codigoPostalFechaHoraEmision;
    }

    public String getEfectoComprobante() {
        return efectoComprobante;
    }

    public void setEfectoComprobante(String efectoComprobante) {
        this.efectoComprobante = efectoComprobante;
    }

    public String getRegimenFiscal() {
        return regimenFiscal;
    }

    public void setRegimenFiscal(String regimenFiscal) {
        this.regimenFiscal = regimenFiscal;
    }

    public String getFechaHoraCertificacion() {
        return fechaHoraCertificacion;
    }

    public void setFechaHoraCertificacion(String fechaHoraCertificacion) {
        this.fechaHoraCertificacion = fechaHoraCertificacion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(String impuestos) {
        this.impuestos = impuestos;
    } 

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    public void imprimirDatos(){
        System.out.println(rfcEmisor+" "+nombreEmisor+" "+folio+" "+rfcReceptor+" "+usoCFDI+" "+folioFiscal+" "+noSerieEmisor+" "+serie+" "+
        codigoPostalFechaHoraEmision+" "+efectoComprobante+" "+regimenFiscal+" "+fechaHoraCertificacion+" "+moneda+" "+formaPago+" "+metodoPago+" "+subtotal+" "+total);
    }
    
}
