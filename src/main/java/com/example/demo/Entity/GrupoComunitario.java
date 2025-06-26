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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "grupo_comunitario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoComunitario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo_comunitario")
    private Integer idGrupoComunitario;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "telefono", nullable = false, length = 50)
    private String telefono;

    @Column(name = "numero_personas", nullable = false)
    private Integer numeroPersonas;

    // Relación ManyToOne con Ubicacion
    // Un GrupoComunitario está en UNA Ubicacion.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ubicacion", referencedColumnName = "id_ubicacion")
    private Ubicacion ubicacion;

    // @OneToMany(mappedBy = "grupoComunitario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<Organizacion> organizaciones;
}
