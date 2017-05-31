package com.emptyshit.hsay.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class PlayerComponent implements PlayerComponentInterface {

	private PlayerRepository playerRepository;

	private Player player;

	public PlayerComponent(PlayerRepository playerRepository){
		this.playerRepository = playerRepository;
	}

	@Override
	public boolean register(String playername, String emailString, String password, String passwordConfirm) {
		if (!(checkValidString(playername) || checkValidString(emailString) || checkValidString(password)
				|| checkValidString(passwordConfirm))) {
			return false;
		}
		if (password.equals(passwordConfirm)) {
			EmailType email;
			try {
				email = new EmailType(emailString);
			} catch(Exception e){
				return false;
			}
			Player player = new Player();
			player.setPlayerName(playername);
			player.setEmail(email);
			player.setPassword(password);
			saveLocalPlayer(player);
			this.playerRepository.save(player);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean withoutRegister() {
		Player player = loadLocalPlayer();
		if(player == null){
			player = new Player();
			saveLocalPlayer(player);
			this.playerRepository.save(player);
			this.playerRepository.delete(player.getPlayerID());
			return true;
		}
		return false;
	}

	@Override
	public boolean login(String playername, String password) {
		Player player = this.playerRepository.findPlayerByName(playername);
		if(player != null && player.comparePassword(password)){
			this.player = player;
			saveLocalPlayer(player);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete() {
		if(this.player != null){
			if(this.player.getPlayerID() != null) {
				this.playerRepository.delete(this.player.getPlayerID());
			}
			deleteLocalPlayer();
			return true;
		}
		return false;
	}

	@Override
	public long getMyId(){
		if(this.player != null){
			return this.player.getPlayerID();
		}
		return 0;
	}

	@Override
	public boolean updateMyEmail(String email) {
		EmailType newEmail;
		try {
			newEmail = new EmailType(email);
		} catch(Exception e){
			return false;
		}
		if(newEmail != null) {
			this.player.setEmail(newEmail);
			this.playerRepository.update(this.player);
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePassword(String password, String passwordConfirm) {
		if(password.equals(passwordConfirm)){
			this.player.setPassword(password);
			this.playerRepository.update(this.player);
			return true;
		}
		return false;
	}

	@Override
	public String getMyUsername() {
		if(this.player != null){
			return this.player.getPlayerName();
		}
		return null;
	}

	@Override
	public EmailType getMyEmail() {
		if(this.player != null) {
			return this.player.getEmail();
		}
		return null;
	}

	@Override
	public Player getPlayerById(long id) {
		return this.playerRepository.findPlayerById(id);
	}

	@Override
	public Player getPlayerByName(String name) {
		return this.playerRepository.findPlayerByName(name);
	}

	@Override
	public Player getPlayerByEmail(String email) {
		return this.playerRepository.findPlayerByEmail(email);
	}

	@Override
	public List<Player> getAllPlayers() {
		return this.playerRepository.getAllPlayers();
	}

	private boolean saveLocalPlayer(Player player){
		this.player = player;
		return this.player != null;
	}

	private Player loadLocalPlayer(){
		return this.player;
	}

	private boolean deleteLocalPlayer(){
		this.player = null;
		return this.player == null;
	}

	private boolean checkValidString(String text) {
		return !text.equals("") && !(text == null);
	}
}
