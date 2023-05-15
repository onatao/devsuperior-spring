package com.devnatao.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devnatao.dslist.dto.GameListDTO;
import com.devnatao.dslist.dto.GameMinDTO;
import com.devnatao.dslist.dto.ReplacementDTO;
import com.devnatao.dslist.services.GameListService;
import com.devnatao.dslist.services.GameService;

@RestController
@RequestMapping("/lists")
public class GameListController {

	@Autowired
	private GameListService gameListService;
	
	@Autowired
	private GameService gameService;
	
	@GetMapping
	public ResponseEntity<List<GameListDTO>> findAll() {
		List<GameListDTO> responseList = gameListService.findAll();
		return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	@GetMapping("/{listId}/games")
	public ResponseEntity<List<GameMinDTO>> findByList (@PathVariable Long listId) {
		List<GameMinDTO> response = gameService.findByList(listId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/{listId}/replacement")
	public ResponseEntity<?> move(@PathVariable Long listId, @RequestBody ReplacementDTO body){
		gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
