package br.com.carlosjunior.cliente.escola.gradecurricular.services;

import java.util.List;

import br.com.carlosjunior.cliente.escola.gradecurricular.dto.MateriaDto;
import br.com.carlosjunior.cliente.escola.gradecurricular.entities.MateriaEntity;

public interface IMateraService {

	public Boolean cadastrar(final MateriaDto materia);
	
	public Boolean atualizar(final MateriaDto materia);

	public Boolean excluir(final Long id);

	public List<MateriaDto> listar();
	
	public MateriaDto consultar(final Long id);

	public List<MateriaDto> listarPorHorarioMinimo(int horaMinima);
	
	public List<MateriaDto> listarPorFrequencia(int frequencia);	
}
