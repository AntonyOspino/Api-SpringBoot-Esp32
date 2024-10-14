package com.miprojecto.esp32.api.conexionesp32.repository;

import com.miprojecto.esp32.api.conexionesp32.entity.Comunicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunicacionRepository extends JpaRepository<Comunicacion,Integer> {

    // Método para obtener el último registro ordenado por fecha_hora descendente
    Comunicacion findTopByOrderByFechaHoraDesc();
}
