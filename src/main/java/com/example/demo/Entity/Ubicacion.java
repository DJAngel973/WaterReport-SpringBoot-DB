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

/**
 * Represents the entity of a location (Ubicacion) in the system.
 * <p>
 *     This class maps location information to the 'ubicacion' table in the database.
 *     It includes address data, neighborhood data, and a relationship to the {@link Ciudad} entity.
 *     It also has a one-to-many relationship to the {@link Usuario} entity.
 * </p>
 * */
@Entity
@Table(name = "ubicacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ubicacion {

    /**
     * The unique identifier for the location.
     * Mapped as the primary key of the 'Ubicacion' table with auto-increment.
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion")
    private Integer idUbicacion;

    /**
     * The name of the location`s neighborhood(barrio)
     * This is a required field and has a maximum length of 100 characters.
     * */
    @Column(name = "barrio", nullable = false, length = 100)
    private String barrio;

    /**
     * The full address of the location.
     * This is a required field and has a maximum length of 255 characters.
     * */
    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    /**
     * The city to which the location belongs.
     * <p>
     *     Establishes a many-to-one relationship with the {@link Ciudad} entity.
     *     lazy-loaded to optimize performance.
     * </p>
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")
    private Ciudad ciudad;

    /**
     * The list of users located at this location.
     * <p>
     *     Establishes a one-to-many relationship with the {@link Usuario} entity.
     *     The 'mappedBy' attribute indicates that the relationship is managed by the 'Ubicacion' field
     *     in the 'usuario' class.
     *     'CascadeType.ALL' is used to propagate persistence operations.
     *     lazy-loaded to optimize performance.
     * </p>
     * */
    @OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
}