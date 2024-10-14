package com.miprojecto.esp32.api.conexionesp32.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "comunicacion")
public class Comunicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro")
    private Integer idRegistro;

    @Column(name = "valor", nullable = false)
    private String valor;

    @Column(name = "fecha_hora", nullable = false)
    private Timestamp fechaHora;
}
