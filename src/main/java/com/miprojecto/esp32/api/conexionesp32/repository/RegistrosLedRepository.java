package com.miprojecto.esp32.api.conexionesp32.repository;

import com.miprojecto.esp32.api.conexionesp32.entity.RegistrosLed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrosLedRepository extends JpaRepository<RegistrosLed, Integer> {

    RegistrosLed findTopByOrderByFechaHoraDesc();

    List<RegistrosLed> findTop5ByOrderByIdRegistroDesc();
}
