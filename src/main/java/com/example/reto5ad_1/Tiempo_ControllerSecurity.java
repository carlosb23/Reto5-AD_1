package com.example.reto5ad_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ciudad")
public class Tiempo_ControllerSecurity {

    @Autowired
    private TiempoRepository tiempoRepository;

    @Autowired
    private Security security;

    @GetMapping("/all")
    public List<Tiempo> getAll(){
        return tiempoRepository.findAll();
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id){
        if(tiempoRepository.existsById(id)){
            return tiempoRepository.findById(id).get().toString();
        }
        return "id no encontrado por favor comprube su base de datos";
    }

    @PostMapping
    public ResponseEntity<Tiempo> addTiempo(@RequestBody Tiempo tiempo, @RequestParam String token) {
        ResponseEntity<Tiempo> respuesta = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if (security.validateToken(token)) {
            Tiempo nuevoTiempo = tiempoRepository.save(tiempo);
            respuesta = ResponseEntity.ok(nuevoTiempo);
        }
        return respuesta;
    }

    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}")
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
