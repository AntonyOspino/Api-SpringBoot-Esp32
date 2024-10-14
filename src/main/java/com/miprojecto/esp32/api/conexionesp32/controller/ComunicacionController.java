package com.miprojecto.esp32.api.conexionesp32.controller;

import com.miprojecto.esp32.api.conexionesp32.entity.Comunicacion;
import com.miprojecto.esp32.api.conexionesp32.jwt.Token;
import com.miprojecto.esp32.api.conexionesp32.request.DataRequest;
import com.miprojecto.esp32.api.conexionesp32.services.ComunicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/registrosComunicacion")
public class ComunicacionController {

    @Autowired
    private ComunicacionService comunicacionService;

    @Autowired
    private Token token1;

    @GetMapping
    public List<Comunicacion> getAll(){
        return comunicacionService.obtenerTodosLosValores();
    }


    @PutMapping("/actualizarComunicacion")
    public ResponseEntity<?> actualizarComunicacion(@RequestBody DataRequest request, @RequestHeader("Authorization") String token) {
        // Remover el prefijo 'Bearer ' del token
        String tokenReal = token.replace("Bearer ", "");

        if(tokenReal.equals(token1.getToken())){
            // Registrar el valor recibido en la tabla comunicacion
            comunicacionService.registrarComunicacion(request.getEstado());

            // Preparar la respuesta en formato JSON
            Map<String, String> response = new HashMap<>();
            response.put("message", "Estado de comunicación actualizado con éxito.");

            return ResponseEntity.ok(response);
        }else {
            // Token inválido
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }

    }

}
