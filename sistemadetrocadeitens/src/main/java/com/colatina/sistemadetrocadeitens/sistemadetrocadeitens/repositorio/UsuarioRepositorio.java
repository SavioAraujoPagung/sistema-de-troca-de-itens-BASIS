package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}
