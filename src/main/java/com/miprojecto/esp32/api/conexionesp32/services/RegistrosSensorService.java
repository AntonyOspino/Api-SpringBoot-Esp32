package com.miprojecto.esp32.api.conexionesp32.services;

import com.miprojecto.esp32.api.conexionesp32.entity.Comunicacion;
import com.miprojecto.esp32.api.conexionesp32.entity.InicioSesion;
import com.miprojecto.esp32.api.conexionesp32.entity.RegistrosSensor;
import com.miprojecto.esp32.api.conexionesp32.repository.RegistroSensorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrosSensorService {

    private final RegistroSensorRepository registroSensorRepository;
    private final InicioSesionService inicioSesionService;
    private final ComunicacionService comunicacionService;


    @Autowired
    public RegistrosSensorService(RegistroSensorRepository registroSensorRepository, InicioSesionService inicioSesionService, ComunicacionService comunicacionService ) {
        this.registroSensorRepository = registroSensorRepository;
        this.inicioSesionService = inicioSesionService;
        this.comunicacionService = comunicacionService;
    }

    public List<RegistrosSensor> obtenerTodosLosRegistros(){
        return registroSensorRepository.findAll();
    }

    public Optional<RegistrosSensor> obtenerUnRegistro(Integer idRegistro){
        return registroSensorRepository.findById(idRegistro);
    }

    @Transactional
    public boolean registrarValorSensor(String estado) {
        Comunicacion ultimoValor = comunicacionService.obtenerUltimoRegistroValor();
        String valor = ultimoValor.getValor();
        if(valor.equalsIgnoreCase("activado")){
            // Obtener el último inicio de sesión
            InicioSesion ultimoInicio = inicioSesionService.obtenerUltimoInicioSesion();

            if (ultimoInicio != null) {
                RegistrosSensor registro = new RegistrosSensor();
                registro.setIdUsuario(ultimoInicio.getUsuario());
                registro.setValorSensor(estado);
                registro.setFechaHora(new Timestamp(System.currentTimeMillis())); // Fecha y hora actual
                registroSensorRepository.save(registro);
                return true;
            }
        }
        return false;
    }

}
