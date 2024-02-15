package com.example.reto5ad_1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TiempoRepository extends JpaRepository<Tiempo, Long> {

    Tiempo findByCiudad(String ciudad);

    List<Tiempo> findAllByCiudadContaining(String fragmentoNombreCiudad);

    @Query("SELECT t FROM Tiempo t WHERE t.temperatura_actual > :temperatura AND t.condiciones_climaticas= 'Soleado'")
    List<Tiempo> findAllByTemperaturaAndCondicionesClimaticas(double temperatura);

    //Consulta para contar el número de ciudades con una temperatura actual superior a un valor dado
    @Query("SELECT COUNT(*) FROM Tiempo t WHERE t.temperatura_actual > :temperatura")
    Long countCitiesWithHighTemperature(double temperatura);

    @Query("SELECT AVG(t.temperatura_actual) FROM Tiempo t")
    Double findAverageTemperature();







}
