package com.miprojecto.esp32.api.conexionesp32.controller;

import com.miprojecto.esp32.api.conexionesp32.entity.Usuarios;
import com.miprojecto.esp32.api.conexionesp32.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;


    @GetMapping
    public List<Usuarios> getAll(){
        return usuariosService.obtenerTodosLosUsuarios();
    }

    @GetMapping("/{idUsuario}")
    public Optional<Usuarios> getById(@PathVariable("idUsuario") Integer idUsuario){
        return usuariosService.obtenerUnUsuario(idUsuario);
    }

    @GetMapping("/validarUsuario")
    public Integer validarUsuario(@RequestParam String nombreUsuario, @RequestParam String contrasena) {
        return usuariosService.obtenerIdUsuarioPorNombreYContrasena(nombreUsuario, contrasena);
    }

    @PostMapping("/guardar")
    public void saveUpdate(@RequestBody Usuarios usuario){
        usuariosService.guardarOactualizar(usuario);
    }


    @DeleteMapping("{idUsuario}")
    public void deleteId(@PathVariable("idUsuario") Integer idUsuario){
        usuariosService.eliminar(idUsuario);
    }

}
