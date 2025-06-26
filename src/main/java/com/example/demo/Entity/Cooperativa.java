package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "cooperativa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cooperativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cooperativa")
    private Integer idCooperativa;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "telefono", nullable = false, length = 50)
    private String telefono;

    @Column(name = "numero_personas", nullable = false)
    private Integer numeroPersonas;

    // Relación ManyToOne con Ubicacion
    // Una Cooperativa está en UNA Ubicacion.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ubicacion", referencedColumnName = "id_ubicacion")
    private Ubicacion ubicacion;

    //@OneToMany(mappedBy = "cooperativa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Organizacion> organizaciones;
}
