package com.example.demo.Service;

import com.example.demo.DTO.UsuarioDTO;
import com.example.demo.Entity.Usuario;
import com.example.demo.Repository.ComunidadRepository;
import com.example.demo.Repository.UbicacionRepository;
import com.example.demo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ComunidadRepository comunidadRepository; // Necesario para buscar Comunidad por ID
    private final UbicacionRepository ubicacionRepository; // Necesario para buscar Ubicacion por ID

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository,
                          ComunidadRepository comunidadRepository, // Inyecta los nuevos repositorios
                          UbicacionRepository ubicacionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.comunidadRepository = comunidadRepository;
        this.ubicacionRepository = ubicacionRepository;
    }

    // --- Métodos para GET (devuelven DTOs) ---

    @Transactional(readOnly = true) // Importante para LazyLoading en GETs
    public List<UsuarioDTO> getAllUsuarios() { // Renombrado a 'getAllUsuariosDTO' o similar si quieres
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::convertToDto) // Convierte cada Usuario a UsuarioDTO
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true) // Importante para LazyLoading en GETs
    public Optional<UsuarioDTO> getUsuarioById(Integer id) {
        return usuarioRepository.findById(id)
                .map(this::convertToDto); // Convierte el Usuario encontrado a UsuarioDTO
    }

    // --- Métodos para POST/PUT (reciben DTOs y guardan Entidades) ---

    @Transactional // Asegura que la operación sea transaccional
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDto) {
        Usuario usuario = convertToEntity(usuarioDto); // Convierte DTO a Entidad
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToDto(savedUsuario); // Devuelve la Entidad guardada convertida a DTO
    }

    @Transactional // Asegura que la operación sea transaccional
    public UsuarioDTO updateUsuario(Integer id, UsuarioDTO usuarioDetailsDto) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            // Actualiza los campos básicos
            usuario.setNombre(usuarioDetailsDto.getNombre());
            usuario.setTelefono(usuarioDetailsDto.getTelefono());

            // Actualiza las relaciones si se proporcionan en el DTO
            if (usuarioDetailsDto.getIdComunidad() != null) {
                comunidadRepository.findById(usuarioDetailsDto.getIdComunidad())
                        .ifPresent(usuario::setComunidad); // Si existe, asigna la comunidad
            }
            if (usuarioDetailsDto.getIdUbicacion() != null) {
                ubicacionRepository.findById(usuarioDetailsDto.getIdUbicacion())
                        .ifPresent(usuario::setUbicacion); // Si existe, asigna la ubicacion
            }

            Usuario updatedUsuario = usuarioRepository.save(usuario);
            return convertToDto(updatedUsuario); // Devuelve la Entidad actualizada convertida a DTO
        }
        return null; // O lanza una excepción NotFoundException
    }

    @Transactional // Asegura que la operación sea transaccional
    public boolean deleteUsuario(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // --- Métodos de Conversión (Mapper) ---

    // Convierte una Entidad Usuario a un UsuarioDTO
    private UsuarioDTO convertToDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setTelefono(usuario.getTelefono());

        // Manejo de relaciones LAZY: Se accede a ellas aquí dentro de un contexto transaccional
        if (usuario.getComunidad() != null) {
            dto.setIdComunidad(usuario.getComunidad().getIdComunidad());
            dto.setNombreComunidad(usuario.getComunidad().getNombreComunidad());
        }
        if (usuario.getUbicacion() != null) {
            dto.setIdUbicacion(usuario.getUbicacion().getIdUbicacion());
            dto.setDireccionUbicacion(usuario.getUbicacion().getDireccion());
        }
        return dto;
    }

    // Convierte un UsuarioDTO a una Entidad Usuario
    private Usuario convertToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        // Nota: idUsuario no se setea aquí para POST porque es auto_increment
        if (dto.getIdUsuario() != null) { // Para PUT, si el DTO trae el ID
            usuario.setIdUsuario(dto.getIdUsuario());
        }
        usuario.setNombre(dto.getNombre());
        usuario.setTelefono(dto.getTelefono());

        // Buscar y asignar las entidades relacionadas
        if (dto.getIdComunidad() != null) {
            comunidadRepository.findById(dto.getIdComunidad())
                    .ifPresent(usuario::setComunidad); // Si existe, la asigna
            // TODO: Manejar caso en que la comunidad no exista (lanzar excepción o error)
        }
        if (dto.getIdUbicacion() != null) {
            ubicacionRepository.findById(dto.getIdUbicacion())
                    .ifPresent(usuario::setUbicacion); // Si existe, la asigna
            // TODO: Manejar caso en que la ubicacion no exista (lanzar excepción o error)
        }
        return usuario;
    }
}