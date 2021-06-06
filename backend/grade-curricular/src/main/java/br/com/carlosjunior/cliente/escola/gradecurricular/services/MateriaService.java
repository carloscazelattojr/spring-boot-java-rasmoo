package br.com.carlosjunior.cliente.escola.gradecurricular.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.carlosjunior.cliente.escola.gradecurricular.entities.MateriaEntity;
import br.com.carlosjunior.cliente.escola.gradecurricular.exceptions.MateriaException;
import br.com.carlosjunior.cliente.escola.gradecurricular.repositories.IMateriaRepository;

@Service
public class MateriaService implements IMateraService {

	@Autowired
	private IMateriaRepository materiaRepository;

	@Override
	public Boolean atualizar(MateriaEntity materia) {
		try {

			// Irei utilizar o buscar por ID, onde irá retornar a materiaEntity.
			MateriaEntity materiaEntity = this.consultar(materia.getId());

			// Atualizar a matéria que está vindo no banco.
			materiaEntity.setNome(materia.getNome());
			materiaEntity.setHoras(materia.getHoras());
			materiaEntity.setCodigo(materia.getCodigo());
			materiaEntity.setFrequencia(materia.getFrequencia());

			// Salvar as alterações.
			this.materiaRepository.save(materiaEntity);
			
			return Boolean.TRUE;

		}catch (MateriaException m) {
			throw m;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean excluir(Long id) {
		try {
			this.consultar(id);
			this.materiaRepository.deleteById(id);
			return true;
		} catch (MateriaException m) {
			throw m;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean cadastrar(MateriaEntity materia) {
		try {
			this.materiaRepository.save(materia);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<MateriaEntity> listar() {
		try {
			return this.materiaRepository.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public MateriaEntity consultar(Long id) {
		try {
			// Implemento o Optional por causa do Retorno do findById
			Optional<MateriaEntity> materiaEntityOptional = this.materiaRepository.findById(id);
			if (materiaEntityOptional.isPresent()) {
				return materiaEntityOptional.get();
			}
			throw new MateriaException("Materia não encontrada! [MateriaService.java]", HttpStatus.NOT_FOUND);
		} catch (MateriaException m) {
			throw m;
		} catch (Exception e) {
			throw new MateriaException("Erro interno no servidor! [MateriaService.java]", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
