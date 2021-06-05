package br.com.carlosjunior.cliente.escola.gradecurricular.controllers;

import java.util.List;

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

import br.com.carlosjunior.cliente.escola.gradecurricular.entities.MateriaEntity;
import br.com.carlosjunior.cliente.escola.gradecurricular.repositories.IMateriaRepository;

@RestController
@RequestMapping(value = "/materia")
public class MateriaController {

	@Autowired
	private IMateriaRepository materiaRepository;

	@GetMapping
	public ResponseEntity<List<MateriaEntity>> listarMaterias() {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<MateriaEntity> consultarMateria(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaRepository.findById(id).get());
	}

	@PostMapping
	public ResponseEntity<Boolean> cadastrarMateria(@RequestBody MateriaEntity materia) {
		try {
			this.materiaRepository.save(materia);
			return ResponseEntity.status(HttpStatus.OK).body(true);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(false);
		}
	}

	@PutMapping
	public ResponseEntity<Boolean> atualizarMateria(@RequestBody MateriaEntity materia) {
		try {
			
			//Irei utilizar o buscar por ID, onde irá retornar a materiaEntity.
			MateriaEntity materiaEntity = this.materiaRepository.findById(materia.getId()).get();
			
			//Atualizar a matéria que está vindo no banco.
			materiaEntity.setNome(materia.getNome());
			materiaEntity.setHoras(materia.getHoras());
			materiaEntity.setCodigo(materia.getCodigo());
			materiaEntity.setFrequencia(materia.getFrequencia());
			
			//Salvar as alterações.
			this.materiaRepository.save(materiaEntity);
			return ResponseEntity.status(HttpStatus.OK).body(true);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(false);
		}
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirMateria(@PathVariable Long id) {
		try {
			this.materiaRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(false);
		}
	}

}
