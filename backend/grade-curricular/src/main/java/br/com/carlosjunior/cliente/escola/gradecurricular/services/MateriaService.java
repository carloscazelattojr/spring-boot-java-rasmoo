package br.com.carlosjunior.cliente.escola.gradecurricular.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.carlosjunior.cliente.escola.gradecurricular.dto.MateriaDto;
import br.com.carlosjunior.cliente.escola.gradecurricular.entities.MateriaEntity;
import br.com.carlosjunior.cliente.escola.gradecurricular.exceptions.MateriaException;
import br.com.carlosjunior.cliente.escola.gradecurricular.repositories.IMateriaRepository;

@Service
public class MateriaService implements IMateraService {

	@Autowired
	private IMateriaRepository materiaRepository;

	@Override
	public Boolean atualizar(MateriaDto materia) {
		try {
			//Verificar se tem matéria cadastrada;
			this.consultar(materia.getId());
			
			//Tratar via ModelMapper todos os Sets.
			ModelMapper mapper = new ModelMapper();
			MateriaEntity materiaEntity = mapper.map(materia, MateriaEntity.class);

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
	public Boolean cadastrar(MateriaDto materia) {
		try {
			ModelMapper mapper = new ModelMapper();
			
			MateriaEntity materiaEntity = mapper.map(materia, MateriaEntity.class);
			
			this.materiaRepository.save(materiaEntity);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<MateriaDto> listar() {
		try {
			ModelMapper mapper =  new ModelMapper();
			return mapper.map(this.materiaRepository.findAll(),new TypeToken<List<MateriaDto>>() {}.getType());
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public MateriaDto consultar(Long id) {
		try {
			ModelMapper mapper = new ModelMapper();
			// Implemento o Optional por causa do Retorno do findById
			Optional<MateriaEntity> materiaEntityOptional = this.materiaRepository.findById(id);
			if (materiaEntityOptional.isPresent()) {
				return mapper.map(materiaEntityOptional.get(), MateriaDto.class);
			}
			throw new MateriaException("Materia não encontrada! [MateriaService.java]", HttpStatus.NOT_FOUND);
		} catch (MateriaException m) {
			throw m;
		} catch (Exception e) {
			throw new MateriaException("Erro interno no servidor! [MateriaService.java]", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
