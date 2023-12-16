package com.mpxds.apps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpxds.apps.model.MpEstado;

@Repository
public interface MpEstadoRepository extends JpaRepository<MpEstado, Long> {
	//
}
