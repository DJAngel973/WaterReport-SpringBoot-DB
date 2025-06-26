package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType; // Para FetchType.LAZY
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn; // Para @JoinColumn
import jakarta.persistence.ManyToOne;  // Para @ManyToOne
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "gobierno_local")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GobiernoLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Coincide con auto_increment en la DB
    @Column(name = "id_gobierno_local")
    private Integer idGobiernoLocal;

    @Column(name = "telefono", nullable = false, length = 50) // Coincide con tu DB
    private String telefono;

    // Relación ManyToOne con Ubicacion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ubicacion", referencedColumnName = "id_ubicacion")
    private Ubicacion ubicacion;

    @Column(name = "nombre_gobierno_local", nullable = false, length = 100)
    private String nombreGobierno;

    @OneToMany(mappedBy = "gobiernoLocal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comunidad> comunidades;
}