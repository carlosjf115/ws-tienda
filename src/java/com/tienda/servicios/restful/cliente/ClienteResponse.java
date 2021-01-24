package com.tienda.servicios.restful.cliente;

import java.util.List;

/**
 *
 * @author cjflores
 */
public class ClienteResponse {

    /*private int codEstado;
    private String msjEstado;*/
    private List<ClienteResponseDetalle> listaDetalle;

    /*public int getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(int codEstado) {
        this.codEstado = codEstado;
    }

    public String getMsjEstado() {
        return msjEstado;
    }

    public void setMsjEstado(String msjEstado) {
        this.msjEstado = msjEstado;
    }
*/
    public List<ClienteResponseDetalle> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<ClienteResponseDetalle> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

}
