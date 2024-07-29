package com.application.springmvc.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.springmvc.entity.Player;
import com.application.springmvc.helper.ExcelHelper;
import com.application.springmvc.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService{

	@Autowired
	private PlayerRepository playerRepository;
	
	@Override
	public void save(MultipartFile file) {
		// TODO Auto-generated method stub
		try
		{
			List<Player> players = ExcelHelper.excelRead(file.getInputStream());
			playerRepository.saveAll(players);
		}
		catch (IOException e) {
			// TODO: handle exception
			throw new RuntimeException("Failed to store the excel data: "+e.getMessage());
		}
	}

	@Override
	public List<Player> getAll() {
		// TODO Auto-generated method stub
		return playerRepository.findAll();
	}

	@Override
	public Player getPlayerById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Player> optional = playerRepository.findById(id);
		Player player = optional.get();
		return player;
	}

	
	
}
