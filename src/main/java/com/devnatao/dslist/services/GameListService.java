package com.devnatao.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devnatao.dslist.dto.GameListDTO;
import com.devnatao.dslist.entities.GameList;
import com.devnatao.dslist.repositories.GameListRepository;

@Service
public class GameListService {
	
	@Autowired
	private GameListRepository repository;

	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() { 
		List<GameList> response = repository.findAll();
		return response.stream().map(x -> new GameListDTO(x)).toList();
	}
}
