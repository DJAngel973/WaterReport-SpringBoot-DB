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
@Table(name = "ubicacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion")
    private Integer idUbicacion;

    @Column(name = "barrio", nullable = false, length = 100)
    private String barrio;

    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    // Relación ManyToOne con Ciudad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")
    private Ciudad ciudad;

    // Si una Ubicacion puede tener MUCHOS Usuarios (OneToMany):
    @OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
}