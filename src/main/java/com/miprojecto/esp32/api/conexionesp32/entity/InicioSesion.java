package com.miprojecto.esp32.api.conexionesp32.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "iniciosesion")
public class InicioSesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro")
    private Integer idRegistro;

    @ManyToOne
    @JoinColumn(name = "usuario", nullable = false)
    private Usuarios usuario;

    @Column(name = "fecha_hora", nullable = false)
    private Timestamp fechaHora;
}
