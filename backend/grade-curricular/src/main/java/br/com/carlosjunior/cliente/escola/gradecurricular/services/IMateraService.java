package br.com.carlosjunior.cliente.escola.gradecurricular.services;

import java.util.List;

import br.com.carlosjunior.cliente.escola.gradecurricular.entities.MateriaEntity;

public interface IMateraService {

	public Boolean cadastrar(final MateriaEntity materia);
	
	public Boolean atualizar(final MateriaEntity materia);

	public Boolean excluir(final Long id);

	public List<MateriaEntity> listar();
	
	public MateriaEntity consultar(final Long id);

}
