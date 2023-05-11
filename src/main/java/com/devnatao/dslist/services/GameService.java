package com.devnatao.dslist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devnatao.dslist.dto.GameMinDTO;
import com.devnatao.dslist.entities.Game;
import com.devnatao.dslist.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository repository;
	
	public List<GameMinDTO> findAll() {
		List<Game> responseList = repository.findAll();
		List<GameMinDTO> dtoResponse = responseList
							.stream()
							.map(x -> new GameMinDTO(x)).toList();
		return dtoResponse;
	}
	
	public Game findById(Long id) {
		Optional<Game> response = repository.findById(id);
		return response.get();
	}
}
