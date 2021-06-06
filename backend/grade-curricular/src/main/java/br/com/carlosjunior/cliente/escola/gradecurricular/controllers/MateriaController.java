package br.com.carlosjunior.cliente.escola.gradecurricular.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.carlosjunior.cliente.escola.gradecurricular.dto.MateriaDto;
import br.com.carlosjunior.cliente.escola.gradecurricular.entities.MateriaEntity;
import br.com.carlosjunior.cliente.escola.gradecurricular.services.MateriaService;

@RestController
@RequestMapping(value = "/materia")
public class MateriaController {

	@Autowired
	private MateriaService materiaService;

	@GetMapping
	public ResponseEntity<List<MateriaDto>> listarMaterias() {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<MateriaDto> consultarMateria(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.consultar(id));
	}

	@PostMapping
	public ResponseEntity<Boolean> cadastrarMateria(@Valid @RequestBody MateriaDto materia) {
		return ResponseEntity.status(HttpStatus.OK).body(materiaService.cadastrar(materia));
	}

	@PutMapping
	public ResponseEntity<Boolean> atualizarMateria(@Valid @RequestBody MateriaDto materia) {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.atualizar(materia));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirMateria(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.excluir(id));
	}

}
