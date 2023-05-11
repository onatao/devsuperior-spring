package com.devnatao.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devnatao.dslist.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
