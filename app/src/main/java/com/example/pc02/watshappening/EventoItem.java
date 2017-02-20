package com.example.pc02.watshappening;

/**
 * Created by adolf on 16/02/2017.
 */

public class EventoItem {

    private String nombre;
    private String id;
    private String descripcion;
    private String longitud;
    private String latitud;
    private String inicio;
    private String fin;
    private String direccion;
    private String cod_postal;
    private String localidad;
    private String provincia;

    public EventoItem(String id, String nombre){
        this.id = id;
        this.nombre = nombre;

    }

    public EventoItem(String id, String nombre, String descripcion, String inicio, String fin, String direccion, String cod_postal, String localidad, String provincia, String latitud, String longitud) {
        this.nombre = nombre;
        this.id = id;
        this.descripcion = descripcion;
        this.inicio = inicio;
        this.fin = fin;
        this.direccion = direccion;
        this.cod_postal = cod_postal;
        this.localidad = localidad;
        this.provincia = provincia;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setId(String id){
        this.id= id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
