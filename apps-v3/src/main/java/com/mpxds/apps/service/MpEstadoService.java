package com.mpxds.apps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mpxds.apps.exception.MpEntidadeEmUsoException;
import com.mpxds.apps.exception.MpEntidadeNaoEncontradaException;
import com.mpxds.apps.model.MpEstado;
import com.mpxds.apps.repository.MpEstadoRepository;

@Service
public class MpEstadoService {
	//
	@Autowired
	private MpEstadoRepository estadoRepository;
	
	public MpEstado salvar(MpEstado estado) {
		//
		return estadoRepository.save(estado);
	}
	
	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
			
		} catch (EmptyResultDataAccessException e) {
			//
			throw new MpEntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de estado com código %d", 
									estadoId));
		
		} catch (DataIntegrityViolationException e) {
			//
			throw new MpEntidadeEmUsoException(
				String.format(
					"Estado de código %d não pode ser removido, pois está em uso",
					 estadoId));
		}
	}
	
}
