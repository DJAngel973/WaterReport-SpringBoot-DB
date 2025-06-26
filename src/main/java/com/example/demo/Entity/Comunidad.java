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
@Table(name = "comunidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comunidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comunidad")
    private Integer idComunidad;

    @Column(name = "nombre_comunidad", nullable = false, length = 100)
    private String nombreComunidad;

    // Relaciones @ManyToOne con NivelDesarrollo, GobiernoLocal, Organizaciones
    // Si tu tabla 'comunidad' tiene FKs a estas, NECESITARÁS sus entidades también.
    // Te daré las plantillas para ellas, asumiendo que son simples (ID y nombre).

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel_desarrollo", referencedColumnName = "id_nivel_desarrollo")
    private NivelDesarrollo nivelDesarrollo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gobierno_local", referencedColumnName = "id_gobierno_local")
    private GobiernoLocal gobiernoLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_organizaciones", referencedColumnName = "id_organizaciones")
    private Organizacion organizaciones;

    // Si una Comunidad puede tener MUCHOS Usuarios (OneToMany):
    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
}