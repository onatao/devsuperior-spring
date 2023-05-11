package com.devnatao.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devnatao.dslist.dto.GameDTO;
import com.devnatao.dslist.dto.GameMinDTO;
import com.devnatao.dslist.entities.Game;
import com.devnatao.dslist.projections.GameMinProjection;
import com.devnatao.dslist.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository repository;
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll() {
		List<Game> responseList = repository.findAll();
		List<GameMinDTO> dtoResponse = responseList
							.stream()
							.map(x -> new GameMinDTO(x)).toList();
		return dtoResponse;
	}
	
	@Transactional(readOnly = true)
	public GameDTO findById(Long id) {
		Game result = repository.findById(id).get();
		GameDTO dtoResponse = new GameDTO(result);
		return dtoResponse;
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(Long listId) {
		List<GameMinProjection> result = repository.searchByList(listId);
		return result.stream().map(x -> new GameMinDTO(x)).toList(); 
	}
}
