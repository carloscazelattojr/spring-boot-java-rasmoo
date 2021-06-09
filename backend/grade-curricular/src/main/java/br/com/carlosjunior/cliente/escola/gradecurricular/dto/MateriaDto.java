package br.com.carlosjunior.cliente.escola.gradecurricular.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MateriaDto extends RepresentationModel<MateriaDto> {

	private Long id;

	@NotBlank(message = "Informe o nome da matéria [MateriaDto.java]")
	private String nome;

	@Min(value = 34, message = "Permitido o mínimo de 34 horas por matéria [MateriaDto.java]")
	@Max(value = 200, message = "Permitido o máximo de 200 horas por matéria [MateriaDto.java]")
	private int horas;

	@NotBlank(message = "Informe o código-prefixo da matéria [MateriaDto.java]")
	private String codigo;

	@Min(value = 1, message = "Permitido o mínimo 1 vez ao ano [MateriaDto.java]")
	@Max(value = 4, message = "Permitido o máximo 4 vezes ao ano [MateriaDto.java]")
	private int frequencia;

}
