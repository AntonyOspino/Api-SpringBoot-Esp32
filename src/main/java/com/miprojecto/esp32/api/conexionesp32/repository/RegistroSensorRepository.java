package com.miprojecto.esp32.api.conexionesp32.repository;

import com.miprojecto.esp32.api.conexionesp32.entity.RegistrosSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroSensorRepository extends JpaRepository<RegistrosSensor, Integer> {

    // Obtener los 5 registros más recientes de la tabla sensor, ordenados por idRegistro de manera descendente
    List<RegistrosSensor> findTop5ByOrderByIdRegistroDesc();

}
