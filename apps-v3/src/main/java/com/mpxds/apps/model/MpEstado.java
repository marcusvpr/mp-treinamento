package com.mpxds.apps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper=false)
@Table(name = "mp_estado")
@Entity
public class MpEstado extends MpBaseEntity {
	//
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String sigla;
	
}