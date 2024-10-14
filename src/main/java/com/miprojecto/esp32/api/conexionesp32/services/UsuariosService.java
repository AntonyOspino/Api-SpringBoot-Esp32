package com.miprojecto.esp32.api.conexionesp32.services;

import com.miprojecto.esp32.api.conexionesp32.entity.Usuarios;
import com.miprojecto.esp32.api.conexionesp32.repository.UsuariosRepository;
import com.miprojecto.esp32.api.conexionesp32.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;
    private final InicioSesionService inicioSesionService;


    public Integer obtenerIdUsuarioPorNombreYContrasena(String nombreUsuario, String contrasena) {
        Usuarios usuario = usuariosRepository.findByNombreUsuarioAndContrasena(nombreUsuario, contrasena);
        if(usuario != null){
            inicioSesionService.registrarInicioSesion(usuario);
            System.out.println("objeto usuario "+usuario);
            return usuario.getIdUsuario();
        }else{
            return null;
        }
        // Retorna el ID del usuario o null si no lo encuentra
    }

    public Optional<Usuarios> validarCredenciales(LoginRequest request) {
        // Buscar usuario por nombre de usuario y contraseña
        return usuariosRepository.findByCorreoElectronicoAndContrasena(request.getCorreoElectronico(), request.getContrasena());
    }

    public List<Usuarios> obtenerTodosLosUsuarios() {
        return usuariosRepository.findAll(); // Esto obtiene todos los usuarios de la tabla
    }

    public Optional<Usuarios> obtenerUnUsuario(Integer idUsuario) {
        return usuariosRepository.findById(idUsuario);
    }

    public void guardarOactualizar(Usuarios usuario){
        usuariosRepository.save(usuario);
    }

    public void eliminar(Integer idUsuario){
        usuariosRepository.deleteById(idUsuario);
    }


}
