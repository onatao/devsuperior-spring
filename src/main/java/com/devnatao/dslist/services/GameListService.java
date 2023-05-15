package com.devnatao.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devnatao.dslist.dto.GameListDTO;
import com.devnatao.dslist.entities.GameList;
import com.devnatao.dslist.projections.GameMinProjection;
import com.devnatao.dslist.repositories.GameListRepository;
import com.devnatao.dslist.repositories.GameRepository;

@Service
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;
	

	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() { 
		List<GameList> response = gameListRepository.findAll();
		return response.stream().map(x -> new GameListDTO(x)).toList();
	}
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		// search all games from game list
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		/**
		 *  "game" is the element that I want to change
		 *  the position on game list, start removing it from list
		 *  using game id (sourceIndex)  
		 */
		GameMinProjection game = list.remove(sourceIndex);
		// then put the same game that was removed, but now on the destinationIndex
		list.add(destinationIndex, game);
		// returns the min value between source and destination
		int min = sourceIndex < destinationIndex ? sourceIndex: destinationIndex;
		// returns the max value between source and destination
		int max = sourceIndex > destinationIndex ? sourceIndex: destinationIndex;
		/*
		 * updating all the games between min and max, using the listId an then
		 * list.get(i).getId() to return the game ID e setting the new position 
		 * to i value
		 */
		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}
}
