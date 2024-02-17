package com.example.reto5ad_1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para manejar las solicitudes relacionadas con el tiempo.
 */
@RestController
@RequestMapping("/tiempo")
public class Tiempo_Controller {

    /**
     * Repositorio para acceder a los datos del tiempo.
     */
    @Autowired
    private TiempoRepository tiempoRepository;

    /**
     * Encuentra todos los registros de tiempo cuya ciudad contiene el nombre proporcionado.
     *
     * @param nombre El nombre de la ciudad o una parte de él.
     * @return Lista de objetos Tiempo que coinciden con el criterio de búsqueda.
     */
    @GetMapping("/ciudad/contiene/{nombre}")
    public List<Tiempo> findTiempoByCiudadContaining(@PathVariable String nombre) {
        return tiempoRepository.findAllByCiudadContaining(nombre);
    }

    /**
     * Obtiene todos los registros de tiempo.
     *
     * @return Lista de todos los objetos Tiempo en la base de datos.
     */
    @GetMapping("/all")
    public List<Tiempo> getAll(){
        return tiempoRepository.findAll();
    }

    /**
     * Obtiene un solo registro de tiempo por su ID.
     *
     * @param id El ID del registro de tiempo que se desea obtener.
     * @return Una cadena que representa el registro de tiempo si se encuentra, de lo contrario, un mensaje de error.
     */
    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id){
        if(tiempoRepository.existsById(id)){
            return tiempoRepository.findById(id).get().toString();
        }
        return "id no encontrado por favor comprube su base de datos";
    }

    /**
     * Encuentra todos los registros de tiempo con una temperatura y condiciones climáticas específicas.
     *
     * @param temperatura La temperatura mínima requerida.
     * @return Lista de objetos Tiempo que cumplen con los criterios de temperatura y condiciones climáticas.
     */
    @GetMapping("/temperatura/{temperatura}")
    public List<Tiempo>findAllByTemperaturaAndCondicionesClimaticas(@PathVariable double temperatura){
        return tiempoRepository.findAllByTemperaturaAndCondicionesClimaticas(temperatura);
    }

    /**
     * Obtiene el número de ciudades con una temperatura superior a la proporcionada.
     *
     * @param temperatura La temperatura mínima requerida.
     * @return Un mensaje que indica el número de ciudades con temperatura alta.
     */
    @GetMapping("/count/{temperatura}")
    public String countCitiesWithHighTemperature(@PathVariable double temperatura){
        long count = tiempoRepository.countCitiesWithHighTemperature(temperatura);
        return "Numero de ciudades: " +count;
    }

    /**
     * Obtiene el promedio de temperatura de todos los registros de tiempo.
     *
     * @return Un mensaje que indica el promedio de temperatura.
     */
    @GetMapping("/promedio")
    public String findAverageTemperature(){
        double promedio = tiempoRepository.findAverageTemperature();
        return "Promedio de temperatura: " +promedio;
    }

    /**
     * Agrega un nuevo registro de tiempo a la base de datos.
     *
     * @param tiempo El objeto Tiempo que se desea agregar.
     * @return El objeto Tiempo agregado.
     */
    @PostMapping("/add")
    public Tiempo addTiempo(@RequestBody Tiempo tiempo) {
        return tiempoRepository.save(tiempo);
    }

    /**
     * Actualiza un registro de tiempo existente en la base de datos.
     *
     * @param id El ID del registro de tiempo que se desea actualizar.
     * @param tiempoDetails El objeto Tiempo con los detalles actualizados.
     * @return Una respuesta ResponseEntity que indica el estado de la operación de actualización.
     */
    @PutMapping("/solicito/{id}")
    public ResponseEntity<Tiempo> updateTiempo(@PathVariable Long id, @RequestBody Tiempo tiempoDetails) {
        Optional<Tiempo> optionalTiempo = tiempoRepository.findById(id);
        if (optionalTiempo.isPresent()) {
            Tiempo tiempo = optionalTiempo.get();
            tiempo.setCiudad(tiempoDetails.getCiudad());
            tiempo.setPais(tiempoDetails.getPais());
            tiempo.setTemperatura_actual(tiempoDetails.getTemperatura_actual());
            tiempo.setCondiciones_climaticas(tiempoDetails.getCondiciones_climaticas());
            tiempo.setHumedad(tiempoDetails.getHumedad());
            tiempo.setVelocidad_viento(tiempoDetails.getVelocidad_viento());

            Tiempo updatedTiempo = tiempoRepository.save(tiempo);
            return ResponseEntity.ok(updatedTiempo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene un registro de tiempo por el nombre de la ciudad.
     *
     * @param nombre El nombre de la ciudad.
     * @return Una respuesta ResponseEntity que contiene el registro de tiempo si se encuentra, de lo contrario, un mensaje de error.
     */
    @GetMapping("/ciudad/{nombre}")
    public ResponseEntity<Tiempo> getTiempoByCiudad(@PathVariable String nombre) {
        Tiempo tiempo = tiempoRepository.findByCiudad(nombre);
        if (tiempo != null) {
            return ResponseEntity.ok(tiempo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}