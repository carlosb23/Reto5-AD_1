package com.example.reto5ad_1;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TiempoRepository extends JpaRepository<Tiempo, Long> {

    Tiempo findByCiudad(String ciudad);
}
