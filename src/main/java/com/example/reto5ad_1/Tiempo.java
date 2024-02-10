package com.example.reto5ad_1;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tiempo")
public class Tiempo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ciudad;
    private String pais;
    private double temperatura_actual;
    private String condiciones_climaticas;
    private double humedad;
    private double velocidad_viento;



}
