package com.miprojecto.esp32.api.conexionesp32.controller;

import com.miprojecto.esp32.api.conexionesp32.entity.RegistrosSensor;
import com.miprojecto.esp32.api.conexionesp32.jwt.Token;
import com.miprojecto.esp32.api.conexionesp32.request.DataRequest;
import com.miprojecto.esp32.api.conexionesp32.services.RegistrosSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1/registrosSensor")
public class RegistroSensorController {

    @Autowired
    private RegistrosSensorService registrosSensorService;

    @Autowired
    private Token token3;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token){
        // Remover el prefijo 'Bearer ' del token
        String tokenReal = token.replace("Bearer ", "");

        if(tokenReal.equals(token3.getToken())){
            return ResponseEntity.ok(registrosSensorService.obtenerTodosLosRegistros());
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
    }

    @GetMapping("/{idRegistro}")
    public Optional<RegistrosSensor> getById(@PathVariable("idRegistro") Integer idRegistro){
        return registrosSensorService.obtenerUnRegistro(idRegistro);
    }

    @PutMapping("/registrarValorSensor")
    public ResponseEntity<String> registrarValorSensor(@RequestBody DataRequest request) {
        try {
            // Intenta registrar el valor del sensor
            boolean registroExitoso = registrosSensorService.registrarValorSensor(request.getEstado());
            if (registroExitoso) {
                return ResponseEntity.ok("Registro de sensor guardado con éxito.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No se pudo guardar el registro: la comunicación está desactivada.");
            }
        } catch (Exception e) {
            // Si ocurre un error, se devuelve un mensaje de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el registro del sensor: " + e.getMessage());
        }
    }
}


