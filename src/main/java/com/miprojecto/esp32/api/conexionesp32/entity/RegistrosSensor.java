package com.miprojecto.esp32.api.conexionesp32.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "registrossensor")
public class RegistrosSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro")
    private Integer idRegistro;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuarios idUsuario;

    @Column(name = "valor_sensor", nullable = false)
    private String valorSensor;

    @Column(name = "fecha_hora", nullable = false)
    private Timestamp fechaHora;

    // Getters y Setters
}

