package com.emptyshit.hsay.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;

public class PlayerComponent implements PlayerComponentInterface {

	private PlayerRepositoryInterface playerRepositoryInterface;

	//TODO
	private Player player;

	public PlayerComponent(PlayerRepositoryInterface playerRepositoryInterface){
		this.playerRepositoryInterface = playerRepositoryInterface;
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
			playerRepositoryInterface.save(player);
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
		Player player = playerRepositoryInterface.findPlayerByName(playername);
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
			playerRepositoryInterface.delete(player.getPlayerID());
			return true;
		}
		return false;
	}

	@Override
	public String getUsername() {
		Player player = getLocalPlayer();
		if(player != null){
			return player.getPlayerName();
		}
		return null;
	}

	@Override
	public EmailType getEmail() {
		Player player = getLocalPlayer();
		return player.getEmail();
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
