package com.example.demo.Controller;

import com.example.demo.DTO.UsuarioDTO; // Importa tu DTO
import com.example.demo.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping // GET /api/usuarios
    public List<UsuarioDTO> getAllUsuarios() { // Ahora devuelve una lista de DTOs
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}") // GET /api/usuarios/{id}
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Integer id) { // Ahora devuelve un DTO
        Optional<UsuarioDTO> usuarioDto = usuarioService.getUsuarioById(id);
        return usuarioDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/usuarios
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDto) { // Recibe un DTO
        UsuarioDTO createdUsuarioDto = usuarioService.createUsuario(usuarioDto);
        return new ResponseEntity<>(createdUsuarioDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}") // PUT /api/usuarios/{id}
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDetailsDto) { // Recibe un DTO
        UsuarioDTO updatedUsuarioDto = usuarioService.updateUsuario(id, usuarioDetailsDto);
        if (updatedUsuarioDto != null) {
            return ResponseEntity.ok(updatedUsuarioDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // DELETE /api/usuarios/{id}
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        if (usuarioService.deleteUsuario(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}