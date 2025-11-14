package com.example.EstructuraDatosIntegradora.controller;

import com.example.EstructuraDatosIntegradora.model.Tarea;
import com.example.EstructuraDatosIntegradora.service.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    // =====================================================
    //                  MOSTRAR TODAS LAS TAREAS
    // =====================================================
    @GetMapping
    public String mostrarTareas(Model model) {
        model.addAttribute("tareas", tareaService.obtenerTareas());
        model.addAttribute("nuevaTarea", new Tarea()); // Para formulario
        return "tareas";
    }

    // =====================================================
    //                     AGREGAR TAREA
    // =====================================================
    @PostMapping("/agregar")
    public String agregarTarea(@ModelAttribute Tarea nueva) {
        tareaService.agregarTarea(nueva);
        return "redirect:/tareas";
    }

    // =====================================================
    //                     ELIMINAR TAREA
    // =====================================================
    @GetMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable int id) {
        tareaService.eliminarTarea(id);
        return "redirect:/tareas";
    }

    // =====================================================
    //                   ORDENAR LISTA POR PRIORIDAD
    // =====================================================
    @GetMapping("/ordenar")
    public String ordenar() {
        tareaService.ordenarPorPrioridad();
        return "redirect:/tareas";
    }

    // =====================================================
    //                       EDITAR TAREA
    // =====================================================
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Tarea tarea = tareaService.buscarPorId(id);
        model.addAttribute("tareaEditar", tarea);
        return "editar"; // crearemos editar.html si quieres edición real
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Tarea tarea) {
        tareaService.actualizarTarea(tarea);
        return "redirect:/tareas";
    }

    // =====================================================
    //                    VER HISTORIAL (PILA)
    // =====================================================
    @GetMapping("/historial")
    public String historial(Model model) {
        model.addAttribute("acciones", tareaService.obtenerHistorial());
        return "historial";
    }

    // =====================================================
    //               VER TAREAS PENDIENTES (COLA)
    // =====================================================
    @GetMapping("/pendientes")
    public String pendientes(Model model) {
        model.addAttribute("pendientes", tareaService.obtenerPendientes());
        return "pendientes"; // si quieres, te hago pendientes.html
    }
}
