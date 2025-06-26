// src/main/java/com/example/demo/Entity/Organizacion.java
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
@Table(name = "organizaciones") // El nombre de la tabla en la DB es "organizaciones" (plural)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organizacion { // Nombre de la clase en singular

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_organizaciones")
    private Integer idOrganizaciones;

    @Column(name = "ong", length = 100) // Puede ser nulo en la DB (YES)
    private String ong;

    // **Relaciones ManyToOne con otras tablas (las que representan los tipos de organización)**
    // Estas son claves foráneas, por lo que necesitaremos las clases de entidad para ellas.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cooperativa", referencedColumnName = "id_cooperativa")
    private Cooperativa cooperativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa_privada", referencedColumnName = "id_empresa_privada")
    private EmpresaPrivada empresaPrivada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo_comunitario", referencedColumnName = "id_grupo_comunitario")
    private GrupoComunitario grupoComunitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fundacion", referencedColumnName = "id_fundacion")
    private Fundacion fundacion;

    // Si una Organizacion puede tener MUCHAS Comunidades (OneToMany, si la relación es así):
    // @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<Comunidad> comunidades; // Esto iría si mapeas la relación inversa aquí
}
