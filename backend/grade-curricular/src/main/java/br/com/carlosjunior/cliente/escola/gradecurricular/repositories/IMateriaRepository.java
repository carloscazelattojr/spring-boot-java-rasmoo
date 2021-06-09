package br.com.carlosjunior.cliente.escola.gradecurricular.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.carlosjunior.cliente.escola.gradecurricular.entities.MateriaEntity;

public interface IMateriaRepository extends JpaRepository<MateriaEntity, Long> {

	@Query("SELECT m FROM MateriaEntity m where m.horas >= :horaMinima")
	public List<MateriaEntity> findByHoraMinima(@Param("horaMinima") int horaMinima);
	
	@Query("select m from MateriaEntity m where m.frequencia =:frequencia")
	public List<MateriaEntity> findByFrequencia(@Param("frequencia")int frequencia);
}
