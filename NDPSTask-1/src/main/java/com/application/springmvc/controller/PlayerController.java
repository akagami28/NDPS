package com.application.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.application.springmvc.entity.Player;
import com.application.springmvc.entity.Response;
import com.application.springmvc.helper.ExcelHelper;
import com.application.springmvc.service.PlayerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api/xlsx")
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@PostMapping("/upload")
	public ResponseEntity<Response> uploadExcelData(@RequestParam("file") MultipartFile file)
	{
		String message="";
		
		if(ExcelHelper.hasExcelFormat(file))
		{
			try
			{
				playerService.save(file);
				message = "Uploaded the file successfully...: "+file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new Response(message));
			}
			catch(Exception e)
			{
				message = "Could not upload the file....";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Response(message));
			}
		}
		message = "Please upload an excel file...";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(message));
	}
	
	@GetMapping("/players")
	public ResponseEntity<List<Player>> getAll()
	{
		try
		{
			List<Player> players = playerService.getAll();
			
			if(players.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<>(players,HttpStatus.OK);
		}
		catch(Exception e)
		{
			log.error("Unable to fetch the players list: "+e.getMessage());
			return new ResponseEntity<> (null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/players/{id}")
	public ResponseEntity<Player> getById(@PathVariable Integer id)
	{
		try
		{
			Player player = playerService.getPlayerById(id);
			return new ResponseEntity<Player>(player, HttpStatus.OK);
		}
		catch(Exception e)
		{
			log.error("Unable to fetch the player: "+e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
