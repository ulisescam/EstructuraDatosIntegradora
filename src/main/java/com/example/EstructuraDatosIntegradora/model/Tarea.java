package com.example.EstructuraDatosIntegradora.model;

public class Tarea implements Comparable<Tarea> {

    private int id;
    private String titulo;
    private String descripcion;
    private String prioridad;  // Alta, Media, Baja
    private String estado;     // Pendiente, En proceso, Completada

    public Tarea() {}

    public Tarea(int id, String titulo, String descripcion, String prioridad, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    // ===== Getters y Setters =====

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // ===== CompareTo para ordenar por prioridad =====
    @Override
    public int compareTo(Tarea otra) {
        // Orden de prioridad: Alta < Media < Baja
        return prioridadValor(this.prioridad) - prioridadValor(otra.prioridad);
    }

    private int prioridadValor(String p) {
        return switch (p.toLowerCase()) {
            case "alta" -> 1;
            case "media" -> 2;
            case "baja" -> 3;
            default -> 99;
        };
    }

    @Override
    public String toString() {
        return "[" + id + "] " + titulo + " (" + prioridad + ") - " + estado;
    }
}
