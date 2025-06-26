package com.example.demo.Entity;

import com.example.demo.Entity.enums.TipoNivel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "nivel_desarrollo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NivelDesarrollo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel_desarrollo")
    private Integer idNivelDesarrollo;

    @Enumerated(EnumType.STRING) // Le dice a JPA que guarde el nombre del enum (ej. "Urbano")
    @Column(name = "tipo_nivel", nullable = false, length = 20)
    private TipoNivel tipoNivel; // El campo es de tipo TipoNivel
}