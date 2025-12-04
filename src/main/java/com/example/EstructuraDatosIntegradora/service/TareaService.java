package com.example.EstructuraDatosIntegradora.service;

import com.example.EstructuraDatosIntegradora.model.Tarea;
import com.example.EstructuraDatosIntegradora.model.estructura.ArbolBinario;
import com.example.EstructuraDatosIntegradora.model.estructura.Cola;
import com.example.EstructuraDatosIntegradora.model.estructura.ListaSimple;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class TareaService {

    private final ListaSimple<Tarea> listaTareas = new ListaSimple<>();
    private final Cola<Tarea> colaPendientes = new Cola<>();
    private long contadorId = 1;
    private final ArbolBinario<Tarea> arbolPrioridad =
            new ArbolBinario<>(t -> {
                return switch (t.getPrioridad()) {
                    case "Alta" -> 1;
                    case "Media" -> 2;
                    default -> 3;
                };
            });

    private final HistorialService historialService;

    public TareaService(HistorialService historialService) {
        this.historialService = historialService;
    }

    /**
     * Crea una nueva tarea con los datos del formulario
     * y la registra en las estructuras correspondientes.
     */
    public Tarea crearTarea(Tarea datosFormulario) {
        Tarea tarea = new Tarea();
        tarea.setId(contadorId++);
        tarea.setTitulo(datosFormulario.getTitulo());
        tarea.setDescripcion(datosFormulario.getDescripcion());
        tarea.setPrioridad(datosFormulario.getPrioridad());
        tarea.setEstado("Pendiente");
        tarea.setFechaCreacion(LocalDate.now());
        tarea.setFechaVencimiento(datosFormulario.getFechaVencimiento());

        // Lista general (lista simplemente enlazada genérica)
        listaTareas.agregar(tarea);

        // Cola de pendientes (FIFO)
        colaPendientes.encolar(tarea);

        // Historial (pila genérica)
        historialService.registrarAccion(tarea);

        arbolPrioridad.insertar(tarea);

        return tarea;
    }

    /**
     * Devuelve todas las tareas almacenadas en la lista.
     */
    public List<Tarea> listarTareas() {
        return listaTareas.obtenerTodos();
    }

    /**
     * Busca una tarea por su id recorriendo la lista genérica.
     */
    public Tarea buscarPorId(long id) {
        for (Tarea t : listaTareas.obtenerTodos()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    /**
     * Elimina una tarea por id de la lista principal y la registra en el historial.
     */
    public boolean eliminarTarea(long id) {
        Tarea tarea = buscarPorId(id);
        if (tarea == null) {
            return false;
        }

        boolean eliminado = listaTareas.eliminar(tarea);
        if (eliminado) {
            historialService.registrarAccion(tarea);
        }
        return eliminado;
    }

    /**
     * Devuelve la lista de tareas que están en la cola de pendientes.
     */
    public List<Tarea> obtenerPendientes() {
        return colaPendientes.obtenerElementos();
    }

    /**
     * Procesa la siguiente tarea de la cola:
     *  - Se desencola.
     *  - Se marca como completada.
     *  - Se registra en el historial.
     */
    public Tarea procesarSiguientePendiente() {
        Tarea tarea = colaPendientes.desencolar();
        if (tarea != null) {
            tarea.setEstado("Completada");
            historialService.registrarAccion(tarea);
        }
        return tarea;
    }

    public List<Tarea> obtenerArbolPorPrioridad() {
        return arbolPrioridad.recorridoInorden();
    }

}
