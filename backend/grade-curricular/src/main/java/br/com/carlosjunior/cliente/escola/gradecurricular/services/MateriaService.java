package br.com.carlosjunior.cliente.escola.gradecurricular.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.carlosjunior.cliente.escola.gradecurricular.controllers.MateriaController;
import br.com.carlosjunior.cliente.escola.gradecurricular.dto.MateriaDto;
import br.com.carlosjunior.cliente.escola.gradecurricular.entities.MateriaEntity;
import br.com.carlosjunior.cliente.escola.gradecurricular.exceptions.MateriaException;
import br.com.carlosjunior.cliente.escola.gradecurricular.repositories.IMateriaRepository;

@CacheConfig(cacheNames = "materia")
@Service
public class MateriaService implements IMateraService {

	private static final String MSG_ERRO_INTERNO = "Erro interno no servidor! [MateriaService.java]";
	private static final String MSG_MATERIA_NAO_ENCONTRADA = "Materia não encontrada! [MateriaService.java]";

	private IMateriaRepository materiaRepository;
	private ModelMapper mapper;

	@Autowired
	public MateriaService(IMateriaRepository materiaRepository) {
		this.mapper = new ModelMapper();
		this.materiaRepository = materiaRepository;
	}

	@Override
	public Boolean atualizar(MateriaDto materia) {
		try {
			// Verificar se tem matéria cadastrada;
			this.consultar(materia.getId());

			// Tratar via ModelMapper todos os Sets.
			MateriaEntity materiaEntity = this.mapper.map(materia, MateriaEntity.class);

			// Salvar as alterações.
			this.materiaRepository.save(materiaEntity);

			return Boolean.TRUE;

		} catch (MateriaException m) {
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
			MateriaEntity materiaEntity = this.mapper.map(materia, MateriaEntity.class);
			this.materiaRepository.save(materiaEntity);
			return Boolean.TRUE;

		} catch (Exception e) {
			throw new MateriaException(MSG_ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CachePut(unless = "#result.size()<3")
	@Override
	public List<MateriaDto> listar() {
		try {
			
			List<MateriaDto> materiaDto = this.mapper.map(this.materiaRepository.findAll(), new TypeToken<List<MateriaDto>>() {
			}.getType());  
			
			materiaDto.forEach(materia -> {
				materia.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).consultarMateria(materia.getId()))
						.withSelfRel());	
			});
			
			return materiaDto;
		} catch (Exception e) {
			throw new MateriaException(MSG_ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CachePut(key = "#id")
	@Override
	public MateriaDto consultar(Long id) {
		try {
			// Implemento o Optional por causa do Retorno do findById
			Optional<MateriaEntity> materiaEntityOptional = this.materiaRepository.findById(id);
			if (materiaEntityOptional.isPresent()) {
				return this.mapper.map(materiaEntityOptional.get(), MateriaDto.class);
			}
			throw new MateriaException(MSG_MATERIA_NAO_ENCONTRADA, HttpStatus.NOT_FOUND);
		} catch (MateriaException m) {
			throw m;
		} catch (Exception e) {
			throw new MateriaException(MSG_ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<MateriaDto> listarPorHorarioMinimo(int horaMinima) {
		return this.mapper.map(this.materiaRepository.findByHoraMinima(horaMinima), new TypeToken<List<MateriaDto>>() {
		}.getType());
	}

	@Override
	public List<MateriaDto> listarPorFrequencia(int frequencia) {
		return this.mapper.map(this.materiaRepository.findByFrequencia(frequencia), new TypeToken<List<MateriaDto>>() {
		}.getType());
	}
 
	
}
