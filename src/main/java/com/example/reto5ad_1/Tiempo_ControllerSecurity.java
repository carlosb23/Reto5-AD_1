package com.example.reto5ad_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para manejar las solicitudes relacionadas con el tiempo con seguridad.
 */
@RestController
@RequestMapping("/ciudad")
public class Tiempo_ControllerSecurity {

    /**
     * Repositorio para acceder a los datos del tiempo.
     */
    @Autowired
    private TiempoRepository tiempoRepository;

    /**
     * Componente de seguridad para validar tokens.
     */
    @Autowired
    private Security security;

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
        return "id no encontrado por favor compruebe su base de datos";
    }

    /**
     * Agrega un nuevo registro de tiempo a la base de datos con seguridad.
     *
     * @param tiempo El objeto Tiempo que se desea agregar.
     * @param token El token de seguridad necesario para realizar la operación.
     * @return Una respuesta ResponseEntity que indica el resultado de la operación.
     */
    @PostMapping("/addsecurity")
    public ResponseEntity<Tiempo> addTiempo(@RequestBody Tiempo tiempo, @RequestParam String token) {
        ResponseEntity<Tiempo> respuesta = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if (security.validateToken(token)) {
            Tiempo nuevoTiempo = tiempoRepository.save(tiempo);
            respuesta = ResponseEntity.ok(nuevoTiempo);
        }
        return respuesta;
    }

    /**
     * Actualiza un registro de tiempo existente en la base de datos con seguridad.
     *
     * @param id El ID del registro de tiempo que se desea actualizar.
     * @param tiempoDetails El objeto Tiempo con los detalles actualizados.
     * @param token El token de seguridad necesario para realizar la operación.
     * @return Una respuesta ResponseEntity que indica el resultado de la operación.
     */
    @PutMapping("/solicitosecurity/{id}")
    public ResponseEntity<Tiempo> updateTiempo(@PathVariable Long id, @RequestBody Tiempo tiempoDetails, @RequestParam String token) {
        ResponseEntity<Tiempo> respuesta = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if (security.validateToken(token)) {
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
                respuesta = ResponseEntity.ok(updatedTiempo);
            } else {
                respuesta = ResponseEntity.notFound().build();
            }
        }
        return respuesta;
    }

    /**
     * Elimina un registro de tiempo de la base de datos con seguridad.
     *
     * @param id El ID del registro de tiempo que se desea eliminar.
     * @param token El token de seguridad necesario para realizar la operación.
     * @return Una respuesta ResponseEntity que indica el resultado de la operación.
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Tiempo> delete(@PathVariable Long id, @RequestParam String token) {
        ResponseEntity<Tiempo> respuesta = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if (security.validateToken(token)) {
            Tiempo salida = new Tiempo();
            if (tiempoRepository.existsById(id)) {
                salida = tiempoRepository.findById(id).get();
                tiempoRepository.deleteById(id);
                respuesta = new ResponseEntity<>(salida, HttpStatus.OK);
            } else {
                respuesta = new ResponseEntity<>(salida, HttpStatus.NOT_FOUND);
            }
        }
        return respuesta;
    }

}
