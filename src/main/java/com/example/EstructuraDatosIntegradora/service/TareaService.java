package com.example.EstructuraDatosIntegradora.service;

import com.example.EstructuraDatosIntegradora.model.Tarea;
import com.example.EstructuraDatosIntegradora.model.estructura.ListaSimple;
import com.example.EstructuraDatosIntegradora.model.estructura.Pila;
import com.example.EstructuraDatosIntegradora.model.estructura.Cola;
import org.springframework.stereotype.Service;

@Service
public class TareaService {

    private final ListaSimple<Tarea> listaTareas = new ListaSimple<>();
    private final Pila<String> historial = new Pila<>();
    private final Cola<Tarea> colaPendientes = new Cola<>();

    private int contadorId = 1; // Generador simple de IDs

    // =======================
    //     CRUD PRINCIPAL
    // =======================

    // Agregar tarea
    public void agregarTarea(Tarea tarea) {
        tarea.setId(contadorId++);
        listaTareas.agregarFinal(tarea);

        historial.insertar("Agregada tarea: " + tarea.getTitulo());

        if (tarea.getEstado().equalsIgnoreCase("Pendiente")) {
            colaPendientes.agregarElementos(tarea);
        }
    }

    // Obtener todas las tareas como arreglo (para Thymeleaf)
    public Tarea[] obtenerTareas() {
        return listaTareas.toArray();
    }

    // Eliminar tarea por ID
    public void eliminarTarea(int id) {
        Tarea objetivo = buscarPorId(id);
        if (objetivo != null) {
            listaTareas.eliminar(objetivo);

            historial.insertar("Eliminada tarea: " + objetivo.getTitulo());
        }
    }

    // Buscar tarea por ID (para edición o eliminación)
    public Tarea buscarPorId(int id) {
        for (Tarea t : obtenerTareas()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    // Actualizar tarea
    public void actualizarTarea(Tarea tareaActualizada) {
        Tarea original = buscarPorId(tareaActualizada.getId());
        if (original != null) {
            original.setTitulo(tareaActualizada.getTitulo());
            original.setDescripcion(tareaActualizada.getDescripcion());
            original.setPrioridad(tareaActualizada.getPrioridad());
            original.setEstado(tareaActualizada.getEstado());

            historial.insertar("Actualizada tarea: " + original.getTitulo());
        }
    }

    // Ordenar lista por prioridad
    public void ordenarPorPrioridad() {
        listaTareas.ordenar();
        historial.insertar("Lista ordenada por prioridad");
    }


    // =======================
    //      COLA DE PENDIENTES
    // =======================

    public Tarea[] obtenerPendientes() {
        return colaPendientes.toArray();
    }


    // =======================
    //        HISTORIAL
    // =======================

    public String[] obtenerHistorial() {
        return historial.toArray();
    }
}
