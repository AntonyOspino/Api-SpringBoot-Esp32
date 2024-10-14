package com.miprojecto.esp32.api.conexionesp32.services;

import com.miprojecto.esp32.api.conexionesp32.entity.InicioSesion;
import com.miprojecto.esp32.api.conexionesp32.entity.Usuarios;
import com.miprojecto.esp32.api.conexionesp32.repository.InicioSesionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class InicioSesionService {

    private final InicioSesionRepository inicioSesionRepository;

    @Autowired
    public InicioSesionService (InicioSesionRepository inicioSesionRepository){
        this.inicioSesionRepository = inicioSesionRepository;
    }

    @Transactional
    public void registrarInicioSesion(Usuarios usuario) {
        InicioSesion inicioSesion = new InicioSesion();
        inicioSesion.setUsuario(usuario);
        inicioSesion.setFechaHora(new Timestamp(System.currentTimeMillis())); // Fecha y hora actual
        System.out.println("guardando inicio sesion registro: "+inicioSesion);
        inicioSesionRepository.save(inicioSesion);
    }


    public List<InicioSesion> obtenerTodosLosInicios() {
        return inicioSesionRepository.findAll();
    }


    public InicioSesion obtenerRegistroPorId(Integer id) {
        return inicioSesionRepository.findById(id).orElse(null);
    }


    public InicioSesion obtenerUltimoInicioSesion() {
        return inicioSesionRepository.findTopByOrderByFechaHoraDesc();
    }
}
