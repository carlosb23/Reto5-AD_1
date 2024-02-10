package com.example.reto5ad_1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tiempo")
public class Tiempo_Controller {

    @Autowired
    private TiempoRepository tiempoRepository;

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
    public Tiempo addTiempo(@RequestBody Tiempo tiempo) {
        return tiempoRepository.save(tiempo);
    }

    @PutMapping("/{id}")
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
