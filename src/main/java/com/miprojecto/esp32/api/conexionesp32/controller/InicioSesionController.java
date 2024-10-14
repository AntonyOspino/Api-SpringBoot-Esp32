package com.miprojecto.esp32.api.conexionesp32.controller;

import com.miprojecto.esp32.api.conexionesp32.entity.InicioSesion;
import com.miprojecto.esp32.api.conexionesp32.services.InicioSesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/registrosInicioSesion")
public class InicioSesionController {

    @Autowired
    private InicioSesionService inicioSesionService;

    @GetMapping
    public List<InicioSesion> getAll(){
        return inicioSesionService.obtenerTodosLosInicios();
    }

    public InicioSesion getById(Integer id) {
        return inicioSesionService.obtenerRegistroPorId(id);
    }
}
