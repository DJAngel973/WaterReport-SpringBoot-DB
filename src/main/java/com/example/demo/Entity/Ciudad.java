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
@Table(name = "ciudad")
@Data // Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
public class Ciudad {

    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que el ID es auto_increment por la base de datos
    @Column(name = "id_ciudad") // Mapea el campo Java a la columna 'id_ciudad' en la tabla
    private Integer idCiudad;

    @Column(name = "nombre_ciudad", nullable = false, length = 100) // Mapea a 'nombre_ciudad', es NOT NULL, y varchar(100)
    private String nombreCiudad;

    // Relación ManyToOne con Pais
    // Tu tabla 'ciudad' tiene 'id_pais' como una clave foránea,
    // lo que significa que una Ciudad pertenece a UN País.
    // Por lo tanto, necesitamos mapear una relación ManyToOne aquí.
    @ManyToOne(fetch = FetchType.LAZY) // FetchType.LAZY es una buena práctica para cargar la entidad País solo cuando se necesita.
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais") // La columna 'id_pais' en 'ciudad' se une con 'id_pais' en la tabla 'pais'
    private Pais pais;
}