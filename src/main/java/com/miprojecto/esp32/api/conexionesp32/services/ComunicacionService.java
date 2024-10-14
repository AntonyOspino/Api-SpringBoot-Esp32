package com.miprojecto.esp32.api.conexionesp32.services;

import com.miprojecto.esp32.api.conexionesp32.entity.Comunicacion;
import com.miprojecto.esp32.api.conexionesp32.repository.ComunicacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ComunicacionService {

    private final ComunicacionRepository comunicacionRepository;

    @Autowired
    public ComunicacionService (ComunicacionRepository comunicacionRepository){
        this.comunicacionRepository = comunicacionRepository;
    }


    public List<Comunicacion> obtenerTodosLosValores() {
        return comunicacionRepository.findAll();
    }


    public Comunicacion obtenerUltimoRegistroValor(){
        return comunicacionRepository.findTopByOrderByFechaHoraDesc();
    }

    @Transactional
    public void registrarComunicacion(String valor) {
        // Crear un nuevo objeto Comunicacion
        Comunicacion comunicacion = new Comunicacion();
        comunicacion.setValor(valor);
        comunicacion.setFechaHora(new Timestamp(System.currentTimeMillis())); // Registrar la fecha y hora actual

        // Guardar en la base de datos
        comunicacionRepository.save(comunicacion);
    }

}
