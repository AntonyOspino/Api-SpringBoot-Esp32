package com.miprojecto.esp32.api.conexionesp32.repository;

import com.miprojecto.esp32.api.conexionesp32.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    Usuarios findByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena);

    Optional<Usuarios>findByCorreoElectronicoAndContrasena(String correoElectronico, String contrasena);

}
