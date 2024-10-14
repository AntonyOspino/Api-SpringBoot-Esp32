package com.miprojecto.esp32.api.conexionesp32.repository;

import com.miprojecto.esp32.api.conexionesp32.entity.InicioSesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InicioSesionRepository extends JpaRepository<InicioSesion,Integer> {

    // Método para obtener el último registro ordenado por fecha_hora descendente
    InicioSesion findTopByOrderByFechaHoraDesc();
}
