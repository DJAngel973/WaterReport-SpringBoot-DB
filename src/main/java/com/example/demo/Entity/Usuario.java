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

@Entity // Marca esta clase como una entidad de JPA
@Table(name = "usuario") // Mapea esta entidad a la tabla 'usuario' en la BD
@Data // Anotación de Lombok para getters, setters, toString, equals y hashCode
@NoArgsConstructor // Constructor sin argumentos (necesario para JPA)
@AllArgsConstructor // Constructor con todos los argumentos (útil)
public class Usuario {

    @Id // Marca idUsuario como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estrategia de autoincremento para IDs (si tu BD los autoincrementa)
    @Column(name = "id_usuario") // Mapea el campo al nombre de la columna en la BD
    private Integer idUsuario; // Usa Integer para que pueda ser null si no está generado todavía

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "telefono", length = 20)
    private String telefono;

    // Relaciones de Clave Foránea (FKs)

    // Relación ManyToOne con Comunidad
    // @JoinColumn mapea idComunidad a la columna id_comunidad en la tabla Usuario
    @ManyToOne(fetch = FetchType.LAZY) // Muchos usuarios a una comunidad. Lazy loading es mejor para rendimiento.
    @JoinColumn(name = "id_comunidad", referencedColumnName = "id_comunidad")
    private Comunidad comunidad; // Aquí se representa la relación con la entidad Comunidad

    // Relación ManyToOne con Ubicacion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ubicacion", referencedColumnName = "id_ubicacion")
    private Ubicacion ubicacion;
}
