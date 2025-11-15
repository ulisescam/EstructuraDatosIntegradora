package com.example.EstructuraDatosIntegradora.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class Tarea {

    private long id;
    private String titulo;
    private String descripcion;
    private String prioridad; // Alta, Media, Baja
    private String estado;    // Pendiente, En progreso, Completada

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVencimiento;

    public Tarea() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
