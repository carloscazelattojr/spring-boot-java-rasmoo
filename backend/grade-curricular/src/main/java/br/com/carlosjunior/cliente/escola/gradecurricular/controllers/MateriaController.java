package br.com.carlosjunior.cliente.escola.gradecurricular.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlosjunior.cliente.escola.gradecurricular.constants.HyperLinkConstant;
import br.com.carlosjunior.cliente.escola.gradecurricular.dto.MateriaDto;
import br.com.carlosjunior.cliente.escola.gradecurricular.entities.MateriaEntity;
import br.com.carlosjunior.cliente.escola.gradecurricular.models.Response;
import br.com.carlosjunior.cliente.escola.gradecurricular.services.MateriaService;

@RestController
@RequestMapping(value = "/materia")
public class MateriaController {

	@Autowired
	private MateriaService materiaService;

	@GetMapping
	public ResponseEntity<Response<List<MateriaDto>>> listarMaterias() {
		Response<List<MateriaDto>> response = new Response<>();
		response.setData(this.materiaService.listar());
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).listarMaterias())
				.withSelfRel());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<MateriaDto>> consultarMateria(@PathVariable Long id) {
		Response<MateriaDto> response = new Response<>();
		MateriaDto materia = this.materiaService.consultar(id);
		response.setData(materia);
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).consultarMateria(id))
				.withSelfRel());

		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).excluirMateria(id))
				.withRel(HyperLinkConstant.EXCLUIR.getValor()));
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).atualizarMateria(materia))
				.withRel(HyperLinkConstant.ATUALIZAR.getValor()));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping
	public ResponseEntity<Response<Boolean>> cadastrarMateria(@Valid @RequestBody MateriaDto materia) {
		Response<Boolean> response = new Response<>();
		response.setData(this.materiaService.cadastrar(materia));
		response.setStatusCode(HttpStatus.CREATED.value());

		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).cadastrarMateria(materia)).withSelfRel());

		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).listarMaterias())
				.withRel(HyperLinkConstant.LISTAR.getValor()));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping
	public ResponseEntity<Response<Boolean>> atualizarMateria(@Valid @RequestBody MateriaDto materia) {
		Response<Boolean> response = new Response<>();
		response.setData(this.materiaService.atualizar(materia));
		response.setStatusCode(HttpStatus.OK.value());

		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).listarMaterias())
				.withRel(HyperLinkConstant.LISTAR.getValor()));

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Boolean>> excluirMateria(@PathVariable Long id) {
		Response<Boolean> response = new Response<>();
		response.setData(this.materiaService.excluir(id));
		response.setStatusCode(HttpStatus.OK.value());

		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).listarMaterias())
				.withRel(HyperLinkConstant.LISTAR.getValor()));

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	
	@GetMapping("/hora-minimo/{horaMinima}")
	public ResponseEntity<Response<List<MateriaDto>>> consultarMateriaPorHoraMinino(@PathVariable int horaMinima){
		Response<List<MateriaDto>> response = new Response<>();
		List<MateriaDto> materia = this.materiaService.listarPorHorarioMinimo(horaMinima);
		response.setData(materia);
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).consultarMateriaPorHoraMinino(horaMinima)).withSelfRel());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/frequencia/{frequencia}")
	public ResponseEntity<Response<List<MateriaDto>>> consultaMateriaPorFrequencia(@PathVariable int frequencia) {
		Response<List<MateriaDto>> response = new Response<>();
		List<MateriaDto> materia = this.materiaService.listarPorFrequencia(frequencia);
		response.setData(materia);
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).consultaMateriaPorFrequencia(frequencia))
				.withSelfRel());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
