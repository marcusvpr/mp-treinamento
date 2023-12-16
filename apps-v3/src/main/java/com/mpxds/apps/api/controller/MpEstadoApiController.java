package com.mpxds.apps.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mpxds.apps.exception.MpEntidadeEmUsoException;
import com.mpxds.apps.exception.MpEntidadeNaoEncontradaException;
import com.mpxds.apps.model.MpEstado;
import com.mpxds.apps.repository.MpEstadoRepository;
import com.mpxds.apps.service.MpEstadoService;

@RestController
@RequestMapping("/estados")
public class MpEstadoApiController {
	//
	@Autowired
	private MpEstadoRepository estadoRepository;
	
	@Autowired
	private MpEstadoService cadastroEstado;
	
	@GetMapping
	public List<MpEstado> listar() {
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<MpEstado> buscar(@PathVariable Long estadoId) {
		Optional<MpEstado> estado = estadoRepository.findById(estadoId);
		
		if (estado.isPresent()) {
			return ResponseEntity.ok(estado.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MpEstado adicionar(@RequestBody MpEstado estado) {
		return cadastroEstado.salvar(estado);
	}
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<MpEstado> atualizar(@PathVariable Long estadoId,
			@RequestBody MpEstado estado) {
		MpEstado estadoAtual = estadoRepository.findById(estadoId).orElse(null);
		
		if (estadoAtual != null) {
			BeanUtils.copyProperties(estado, estadoAtual, "id");
			
			estadoAtual = cadastroEstado.salvar(estadoAtual);
			return ResponseEntity.ok(estadoAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId) {
		try {
			cadastroEstado.excluir(estadoId);	
			return ResponseEntity.noContent().build();
			
		} catch (MpEntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (MpEntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
		}
	}
	
}
