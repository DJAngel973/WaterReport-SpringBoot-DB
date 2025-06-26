package com.example.demo.Entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

public enum TipoNivel {
    URBANO("Urbano"),
    RURAL("Rural"),
    INDIGENA("Indigena"),
    AFRODESCENDIENTE("Afrodescendiente");

    private final String dbValue;

    TipoNivel(String dbValue) {
        this.dbValue = dbValue;
    }

    @JsonValue // Indica a Jackson cómo serializar el enum a JSON
    public String getDbValue() {
        return dbValue;
    }

    // @JsonCreator permite a Jackson deserializar el enum desde JSON (útil para la API REST)
    public static TipoNivel fromDbValue(String dbValue) {
        return Stream.of(TipoNivel.values())
                .filter(tipo -> tipo.dbValue.equals(dbValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Valor de TipoNivel desconocido: " + dbValue));
    }
}