package com.example.demo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Integer idUsuario;
    private String nombre;
    private String telefono;
    // Solo incluimos los IDs de las relaciones o DTOs simplificados
    private Integer idComunidad; // O un ComunidadDTO simplificado
    private String nombreComunidad; // Ejemplo: Si solo quieres el nombre de la comunidad
    private Integer idUbicacion; // O un UbicacionDTO simplificado
    private String direccionUbicacion; // Ejemplo: Si solo quieres la dirección
}