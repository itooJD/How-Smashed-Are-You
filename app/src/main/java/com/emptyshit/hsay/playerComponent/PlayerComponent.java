package com.emptyshit.hsay.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;

import java.util.List;

public class PlayerComponent implements PlayerComponentInterface {

	private PlayerRepository playerRepository;

	//TODO
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
			EmailType email = new EmailType(emailString);
			Player player = new Player();
			player.setPlayerName(playername);
			player.setEmail(email);
			player.setPassword(password);
			saveLocalPlayer(player);
			playerRepository.save(player);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean withoutRegister() {
		if(getLocalPlayer() == null){
			Player player = new Player();
			saveLocalPlayer(player);
			return true;
		}
		return false;
	}

	@Override
	public boolean login(String playername, String password) {
		Player player = playerRepository.findPlayerByName(playername);
		if(player != null && player.comparePassword(password)){
			saveLocalPlayer(player);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete() {
		Player player = getLocalPlayer();
		if(player != null){
			playerRepository.delete(player.getPlayerID());
			return true;
		}
		return false;
	}

	@Override
	public Player getPlayerById(long id) {
		return playerRepository.findPlayerById(id);
	}

	@Override
	public Player getPlayerByName(String name) {
		return playerRepository.findPlayerByName(name);
	}

	@Override
	public Player getPlayerByEmail(String email) {
		return playerRepository.findPlayerByEmail(email);
	}

	@Override
	public List<Player> getAllPlayers() {
		return playerRepository.getAllPlayers();
	}

	@Override
	public long getMyId(){
		Player player = getLocalPlayer();
		if(player != null){
			return player.getPlayerID();
		}
		return 0;
	}

	@Override
	public String getMyUsername() {
		Player player = getLocalPlayer();
		if(player != null){
			return player.getPlayerName();
		}
		return null;
	}

	@Override
	public EmailType getMyEmail() {
		Player player = getLocalPlayer();
		if(player != null) {
			return player.getEmail();
		}
		return null;
	}

	private Player getLocalPlayer() {

		//TODO
		// load from local file
		// if file not exist
		// 		return null
		// else return Player
		return this.player;
	}

	private boolean saveLocalPlayer(Player player) {
		if(player != null){
			this.player = player;
			return true;
		}
		return false;

	}

	private boolean checkValidString(String text) {
		return !text.equals("") && !(text == null);
	}
}
