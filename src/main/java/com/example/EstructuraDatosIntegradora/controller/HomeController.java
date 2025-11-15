package com.example.EstructuraDatosIntegradora.controller;

import com.example.EstructuraDatosIntegradora.model.Tarea;
import com.example.EstructuraDatosIntegradora.service.HistorialService;
import com.example.EstructuraDatosIntegradora.service.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    private final TareaService tareaService;
    private final HistorialService historialService;

    public HomeController(TareaService tareaService, HistorialService historialService) {
        this.tareaService = tareaService;
        this.historialService = historialService;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        int totalTareas = tareaService.listarTareas().size();
        int tareasPendientes = tareaService.obtenerPendientes().size();
        Tarea ultimoMovimiento = historialService.verUltimoMovimiento();

        model.addAttribute("totalTareas", totalTareas);
        model.addAttribute("tareasPendientes", tareasPendientes);
        model.addAttribute("ultimoMovimiento", ultimoMovimiento);

        return "index";
    }
}
