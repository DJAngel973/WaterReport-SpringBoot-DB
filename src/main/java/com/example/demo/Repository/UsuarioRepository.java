package com.example.demo.Repository;

import com.example.demo.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Spring Data JPA ya provee los métodos básicos (findAll, findById, save, deleteById)
}