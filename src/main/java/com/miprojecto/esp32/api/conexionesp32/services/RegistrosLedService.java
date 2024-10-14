package com.miprojecto.esp32.api.conexionesp32.services;

import com.miprojecto.esp32.api.conexionesp32.entity.Comunicacion;
import com.miprojecto.esp32.api.conexionesp32.entity.InicioSesion;
import com.miprojecto.esp32.api.conexionesp32.entity.RegistrosLed;
import com.miprojecto.esp32.api.conexionesp32.repository.RegistrosLedRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrosLedService {

    private final RegistrosLedRepository registrosLedRepository;
    private final InicioSesionService inicioSesionService;
    private final ComunicacionService comunicacionService;

    @Autowired
    public RegistrosLedService(RegistrosLedRepository registrosLedRepository, InicioSesionService inicioSesionService, ComunicacionService comunicacionService) {
        this.registrosLedRepository = registrosLedRepository;
        this.inicioSesionService = inicioSesionService;
        this.comunicacionService = comunicacionService;
    }


    public List<RegistrosLed> obtenerTodosLosRegistros(){
        return registrosLedRepository.findAll();
    }


    public Optional<RegistrosLed> obtenerUnRegistro(Integer idRegistro){
        return registrosLedRepository.findById(idRegistro);
    }

    @Transactional
    public boolean registrarValorLed(String estado) {
        Comunicacion ultimoValor = comunicacionService.obtenerUltimoRegistroValor();
        String valor = ultimoValor.getValor();
        if(valor.equalsIgnoreCase("activado")){
            // Obtener el último inicio de sesión
            InicioSesion ultimoInicio = inicioSesionService.obtenerUltimoInicioSesion();

            if (ultimoInicio != null) {
                RegistrosLed registro = new RegistrosLed();
                registro.setIdUsuario(ultimoInicio.getUsuario());
                registro.setValorLed(estado);
                registro.setFechaHora(new Timestamp(System.currentTimeMillis())); // Fecha y hora actual
                registrosLedRepository.save(registro);
                return true;
            }
        }
        return false;
    }


    public RegistrosLed obtenerUltimoRegistroLed() {
        return registrosLedRepository.findTopByOrderByFechaHoraDesc();
    }
}
