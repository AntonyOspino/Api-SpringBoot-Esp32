package com.miprojecto.esp32.api.conexionesp32.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre_usuario", length = 50, nullable = false)
    private String nombreUsuario;

    @Column(name = "contrasena", length = 255, nullable = false)
    private String contrasena;

    @Column(name = "correo_electronico", length = 100, nullable = false)
    private String correoElectronico;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

}

