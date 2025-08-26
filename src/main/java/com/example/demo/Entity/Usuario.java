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
 * Represent the entity of a user en the system.
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
     * El identificador único del usuario.
     * Mapeado como la clave primaria de la tabla 'usuario' con auto incremento.
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    /**
     * El nombre completo del usuario.
     * Es un campo obligatorio y tiene una longitud máxima de 100 caracteres.
     * */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * El número de teléfono del usuario.
     * Es un campo opcional.
     * */
    @Column(name = "telefono", length = 20)
    private String telefono;

    // Relaciones de Clave Foránea (FKs)

    /**
     * La comunidad a la comunidad a la que pertenece el usuario.
     * <p>
     *     Establece una relación de muchos-a-uno (ManyToOne) con la entidad {@link Comunidad}.
     *     Se carga de forma (Lazy) para optimizar el rendimiento.
     * </p>
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comunidad", referencedColumnName = "id_comunidad")
    private Comunidad comunidad;

    /**
     * La ubicación del usuario.
     * <P>
     *     Establece una relación de muchos-a-uno (ManyToOne) con la entidad {@link Ubicacion}.
     *     También se carga de forma (Lazy) para optimizar el rendimiento.
     * </P>
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ubicacion", referencedColumnName = "id_ubicacion")
    private Ubicacion ubicacion;
}