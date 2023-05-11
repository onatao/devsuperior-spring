package com.devnatao.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devnatao.dslist.dto.GameMinDTO;
import com.devnatao.dslist.entities.Game;
import com.devnatao.dslist.services.GameService;

@RestController
@RequestMapping(value = "/games")
public class GameController {

	@Autowired
	private GameService service;
	
	@GetMapping
	public ResponseEntity<List<GameMinDTO>> findAll() {
		List<GameMinDTO> responseList = service.findAll();
		return new ResponseEntity<>(responseList, HttpStatus.OK); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Game> findById(@PathVariable Long id) {
		Game response = service.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
}
