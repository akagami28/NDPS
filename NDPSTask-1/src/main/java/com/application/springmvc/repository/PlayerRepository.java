package com.application.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.springmvc.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer>{

}
