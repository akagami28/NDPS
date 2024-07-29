package com.application.springmvc.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.application.springmvc.entity.Player;

public interface PlayerService {

	void save(MultipartFile file);
	List<Player> getAll();
	Player getPlayerById(Integer id);
}
