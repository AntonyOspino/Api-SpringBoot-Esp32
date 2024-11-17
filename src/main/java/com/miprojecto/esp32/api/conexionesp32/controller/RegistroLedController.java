package com.miprojecto.esp32.api.conexionesp32.controller;


import com.miprojecto.esp32.api.conexionesp32.entity.RegistrosLed;
import com.miprojecto.esp32.api.conexionesp32.jwt.Token;
import com.miprojecto.esp32.api.conexionesp32.request.DataRequest;
import com.miprojecto.esp32.api.conexionesp32.services.RegistrosLedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/registrosLed")
public class RegistroLedController {

    @Autowired
    private RegistrosLedService registrosLedService;

    @Autowired
    private Token token2;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token){
        // Remover el prefijo 'Bearer ' del token
        String tokenReal = token.replace("Bearer ", "");
        if(tokenReal.equals(token2.getToken())){
            List<RegistrosLed> registros = registrosLedService.obtenerTodosLosRegistros();
            return ResponseEntity.ok(registros);
        }else {
            // Token inválido
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }

    }

    @GetMapping("/{idRegistro}")
    public Optional<RegistrosLed> getById(@PathVariable("idRegistro")Integer idRegistro){
        return registrosLedService.obtenerUnRegistro(idRegistro);
    }

    @PutMapping("/registrarValorLedWeb")
    public ResponseEntity<String> registrarValorLed(@RequestBody DataRequest request, @RequestHeader("Authorization") String token) {
        // Remover el prefijo 'Bearer ' del token
        String tokenReal = token.replace("Bearer ", "");
        if(tokenReal.equals(token2.getToken())){
            try {
                // Intenta registrar el valor del pulsador led
                boolean registroExitoso = registrosLedService.registrarValorLed(request.getEstado());
                if (registroExitoso) {
                    return ResponseEntity.ok("Registro de LED guardado con éxito.");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("No se pudo guardar el registro: la comunicación está desactivada.");
                }
            } catch (Exception e) {
                // Si ocurre un error, se devuelve un mensaje de error
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al guardar el registro del Led: " + e.getMessage());
            }
        }else {
            // Token inválido
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
    }

    @PutMapping("/registrarValorLed")
    public ResponseEntity<String> registrarValorLed(@RequestBody DataRequest request) {
        try {
            // Intenta registrar el valor del pulsador led
            boolean registroExitoso = registrosLedService.registrarValorLed(request.getEstado());
            if (registroExitoso) {
                return ResponseEntity.ok("Registro de LED guardado con éxito.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No se pudo guardar el registro: la comunicación está desactivada.");
            }
        } catch (Exception e) {
            // Si ocurre un error, se devuelve un mensaje de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el registro del Led: " + e.getMessage());
        }
    }


    @GetMapping("/ultimo")
    public ResponseEntity<?> obtenerUltimoRegistroLed(@RequestHeader("Authorization") String token) {
        // Remover el prefijo 'Bearer ' del token
        String tokenReal = token.replace("Bearer ", "");
        if(tokenReal.equals(token2.getToken())){
            RegistrosLed ultimoRegistro = registrosLedService.obtenerUltimoRegistroLed();
            if (ultimoRegistro == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(ultimoRegistro);
        }else {
            // Token inválido
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
    }

    @GetMapping("/ultimoEsp32")
    public ResponseEntity<?> obtenerUltimoRegistroLedEsp32() {
        RegistrosLed ultimoRegistro = registrosLedService.obtenerUltimoRegistroLed();
        if (ultimoRegistro == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ultimoRegistro.getValorLed());
    }

    /*@GetMapping("/ultimos5")
    public List<RegistrosLed> obtenerUltimos5Registros() {
        return registrosLedService.obtenerUltimos5Registros();
    }*/
}
