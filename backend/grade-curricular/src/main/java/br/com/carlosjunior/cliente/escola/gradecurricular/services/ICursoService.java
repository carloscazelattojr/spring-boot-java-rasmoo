package br.com.carlosjunior.cliente.escola.gradecurricular.services;

import java.util.List;

import br.com.carlosjunior.cliente.escola.gradecurricular.entities.CursoEntity;
import br.com.carlosjunior.cliente.escola.gradecurricular.models.CursoModel;

public interface ICursoService {
	
	Boolean cadastrar(CursoModel cursoModel);
	
	Boolean atualizar(CursoModel cursoModel);
	
	Boolean excluir(Long cursoId);
	
	CursoEntity consultarPorCodigo(String codCurso);
	
	List<CursoEntity> listar();

}