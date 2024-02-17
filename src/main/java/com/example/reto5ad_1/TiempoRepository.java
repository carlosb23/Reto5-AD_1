package com.example.reto5ad_1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interfaz que proporciona métodos para acceder y manipular los datos de la entidad Tiempo en la base de datos.
 */
public interface TiempoRepository extends JpaRepository<Tiempo, Long> {

    /**
     * Encuentra un registro de tiempo por el nombre de la ciudad.
     *
     * @param ciudad El nombre de la ciudad.
     * @return El registro de tiempo correspondiente a la ciudad especificada, o null si no se encuentra.
     */
    Tiempo findByCiudad(String ciudad);

    /**
     * Encuentra todos los registros de tiempo cuyo nombre de ciudad contiene el fragmento dado.
     *
     * @param fragmentoNombreCiudad El fragmento del nombre de la ciudad.
     * @return Lista de registros de tiempo que contienen el fragmento del nombre de la ciudad.
     */
    List<Tiempo> findAllByCiudadContaining(String fragmentoNombreCiudad);

    /**
     * Encuentra todos los registros de tiempo con una temperatura actual superior a un valor dado y condiciones climáticas soleadas.
     *
     * @param temperatura La temperatura mínima requerida.
     * @return Lista de registros de tiempo que cumplen con los criterios de temperatura y condiciones climáticas.
     */
    @Query("SELECT t FROM Tiempo t WHERE t.temperatura_actual > :temperatura AND t.condiciones_climaticas= 'Soleado'")
    List<Tiempo> findAllByTemperaturaAndCondicionesClimaticas(double temperatura);

    /**
     * Cuenta el número de ciudades con una temperatura actual superior a un valor dado.
     *
     * @param temperatura La temperatura mínima requerida.
     * @return El número de ciudades con una temperatura superior a la proporcionada.
     */
    @Query("SELECT COUNT(*) FROM Tiempo t WHERE t.temperatura_actual > :temperatura")
    Long countCitiesWithHighTemperature(double temperatura);

    /**
     * Calcula el promedio de la temperatura actual de todos los registros de tiempo.
     *
     * @return El promedio de la temperatura actual.
     */
    @Query("SELECT AVG(t.temperatura_actual) FROM Tiempo t")
    Double findAverageTemperature();

}
