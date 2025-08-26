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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the entity of a user (Usuario) in the system.
 * <p>
 *     This class mapeo the information at the users the table 'usuario' in the database.
 *     Included personal data such as name and telephone number, and relationships with entities.
 *     {@link Comunidad} and {@link Ubicacion}.
 * </p>
 * */
@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    /**
     * The user's unique identifier.
     * Mapped as the primary key of the 'user' table with auto increment.
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    /**
     * The user's full name.
     * This is a required field and has a maximum length of 100 characters.
     * */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * The user's phone number.
     * It is an optional field.
     * */
    @Column(name = "telefono", length = 20)
    private String telefono;

    // Foreign key relationships (FKs)

    /**
     * The community to witch the user.
     * <p>
     *     Establishes a many-to-one relationship with the {@link Comunidad} entity.
     *     Lazy loading to optimize performance.
     * </p>
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comunidad", referencedColumnName = "id_comunidad")
    private Comunidad comunidad;

    /**
     * The user's location.
     * <P>
     *     Establishes a many-to-one relationship with the {@link Ubicacion} entity.
     *     It also loads lazily to optimize performance.
     * </P>
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ubicacion", referencedColumnName = "id_ubicacion")
    private Ubicacion ubicacion;
}