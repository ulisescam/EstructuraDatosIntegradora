package com.example.EstructuraDatosIntegradora.controller;

import com.example.EstructuraDatosIntegradora.model.Tarea;
import com.example.EstructuraDatosIntegradora.service.HistorialService;
import com.example.EstructuraDatosIntegradora.service.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador encargado de manejar las vistas relacionadas con las tareas,
 * los pendientes (cola) y el historial (pila).
 */
@Controller
public class TareaController {

    private final TareaService tareaService;
    private final HistorialService historialService;

    public TareaController(TareaService tareaService, HistorialService historialService) {
        this.tareaService = tareaService;
        this.historialService = historialService;
    }

    /**
     * Vista principal de tareas: muestra el formulario y la lista general.
     */
    @GetMapping("/tareas")
    public String verTareas(Model model, @ModelAttribute("mensaje") String mensaje) {
        model.addAttribute("tarea", new Tarea());
        model.addAttribute("listaTareas", tareaService.listarTareas());
        model.addAttribute("mensaje", mensaje);
        return "tareas";
    }

    /**
     * Recibe los datos del formulario y crea una nueva tarea.
     */
    @PostMapping("/tareas/guardar")
    public String guardarTarea(@ModelAttribute("tarea") Tarea tareaFormulario,
                               RedirectAttributes redirectAttributes) {
        tareaService.crearTarea(tareaFormulario);
        redirectAttributes.addFlashAttribute("mensaje", "Tarea registrada correctamente.");
        return "redirect:/tareas";
    }

    /**
     * Elimina una tarea por id.
     */
    @GetMapping("/tareas/eliminar/{id}")
    public String eliminarTarea(@PathVariable("id") long id,
                                RedirectAttributes redirectAttributes) {
        boolean eliminado = tareaService.eliminarTarea(id);
        if (eliminado) {
            redirectAttributes.addFlashAttribute("mensaje", "Tarea eliminada correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "La tarea no existe.");
        }
        return "redirect:/tareas";
    }

    /**
     * Muestra las tareas que están en la cola de pendientes.
     */
    @GetMapping("/pendientes")
    public String verPendientes(Model model, @ModelAttribute("mensaje") String mensaje) {
        model.addAttribute("pendientes", tareaService.obtenerPendientes());
        model.addAttribute("mensaje", mensaje);
        return "pendientes";
    }

    /**
     * Procesa la siguiente tarea en la cola de pendientes.
     */
    @PostMapping("/pendientes/procesar")
    public String procesarSiguientePendiente(RedirectAttributes redirectAttributes) {
        Tarea procesada = tareaService.procesarSiguientePendiente();
        if (procesada != null) {
            redirectAttributes.addFlashAttribute("mensaje",
                    "Se procesó la tarea: " + procesada.getTitulo());
        } else {
            redirectAttributes.addFlashAttribute("mensaje",
                    "No hay tareas en la cola de pendientes.");
        }
        return "redirect:/pendientes";
    }

    /**
     * Muestra el historial de tareas usando la pila.
     */
    @GetMapping("/historial")
    public String verHistorial(Model model) {
        model.addAttribute("historial", historialService.obtenerHistorial());
        return "historial";
    }

    @GetMapping("/arbol")
    public String mostrarArbol(Model model) {
        model.addAttribute("listaArbol", tareaService.obtenerArbolPorPrioridad());
        return "arbol";
    }

}
