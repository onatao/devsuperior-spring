package com.devnatao.dslist.projections;

public interface GameMinProjection {
	
	/*
	 *  É necessário inserir TODOS os gets que serão utilizados
	 *  na query personalizada, para que ela busque no banco de dados 
	 *  e retorne as informações
	 */
	Long getId();
	String getTitle();
	Integer getYear();
	String getImgUrl();
	String getShortDescription();
	Integer getPosition();
}
