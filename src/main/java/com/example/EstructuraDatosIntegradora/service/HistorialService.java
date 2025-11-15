package com.example.EstructuraDatosIntegradora.service;

import com.example.EstructuraDatosIntegradora.model.Tarea;
import com.example.EstructuraDatosIntegradora.model.estructura.Pila;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialService {

    private final Pila<Tarea> pilaHistorial = new Pila<>();

    /**
     * Registra una tarea en el historial.
     */
    public void registrarAccion(Tarea tarea) {
        pilaHistorial.apilar(tarea);
    }

    /**
     * Devuelve todas las tareas que han pasado por el historial.
     */
    public List<Tarea> obtenerHistorial() {
        return pilaHistorial.obtenerElementos();
    }

    /**
     * Devuelve el último movimiento registrado en el historial.
     */
    public Tarea verUltimoMovimiento() {
        return pilaHistorial.verTope();
    }
}
