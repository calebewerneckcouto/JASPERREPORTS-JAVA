package projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import projeto.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("select u from Usuario u where u.salario >= ?1 and u.salario <= ?2 order by u.salario")
	List<Usuario> listbySalario(Double ini, Double fim);
	
	
	

}
