package com.miprojecto.esp32.api.conexionesp32.controllerPublic;

import com.miprojecto.esp32.api.conexionesp32.entity.Usuarios;
import com.miprojecto.esp32.api.conexionesp32.jwt.Token;
import com.miprojecto.esp32.api.conexionesp32.request.LoginRequest;
import com.miprojecto.esp32.api.conexionesp32.services.InicioSesionService;
import com.miprojecto.esp32.api.conexionesp32.services.MqttService;
import com.miprojecto.esp32.api.conexionesp32.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/public")
public class UsuarioControllerPublico {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private InicioSesionService inicioSesionService;

    @Autowired
    private MqttService mqttService;

    @Autowired
    private Token token;


    @PostMapping("/login")
    public ResponseEntity<?> validarUsuario(@RequestBody LoginRequest request) {
        try{
            // Obtener usuario por correo electrónico y contraseña
            Optional<Usuarios> usuario = usuariosService.validarCredenciales(request);
            if (usuario.isPresent()) {
                String nombre = usuario.get().getNombreUsuario();
                try{
                    mqttService.sendLoginMessage(nombre);
                }catch (Exception e){
                    e.getMessage();
                }

                inicioSesionService.registrarInicioSesion(usuario.get());

                // Responder al cliente con el nombre y token
                Map<String, Object> response = new HashMap<>();
                response.put("name", nombre);
                response.put("token", token.getToken());

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }

    }
}
