package com.emptyshit.hsay.playerComponent;

import android.content.Context;
import android.net.wifi.WifiConfiguration;

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

	private String fileName = "player";
	private Context context = null;

	public PlayerComponent(PlayerRepository playerRepository, Context context){
		this.playerRepository = playerRepository;
		this.context = context;
	}

	@Override
	public boolean register(String playername, String emailString, String password, String passwordConfirm) {
		if (!(checkValidString(playername) || checkValidString(emailString) || checkValidString(password)
				|| checkValidString(passwordConfirm))) {
			return false;
		}

		//TODO
		//check for Duobles
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
			this.playerRepository.save(player);
			saveLocalPlayer(player);
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
			playerRepository.save(player);
			playerRepository.delete(player.getPlayerID());
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
	public boolean loggedIn(){
		this.loadLocalPlayer();
		return this.player != null && !(this.player.getPlayerName() == null || this.player.getPassword() == null || this.player.getEmail() == null);
	}

	@Override
	public boolean logout(){
		this.context.deleteFile(this.fileName);
		this.player = null;
		return this.player == null;
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
		try{
			FileOutputStream fileOutputStream = this.context.openFileOutput(this.fileName, Context.MODE_PRIVATE);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(this.player);
			fileOutputStream.close();
			objectOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.player != null;
	}


	private Player loadLocalPlayer(){
		try {
			FileInputStream fileInputStream = this.context.openFileInput(this.fileName);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			Player player = (Player) objectInputStream.readObject();
			fileInputStream.close();
			objectInputStream.close();
			this.player = player;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return this.player;
	}

	private boolean deleteLocalPlayer(){
		this.context.deleteFile(this.fileName);
		this.player = null;
		return this.player == null;
	}

	private boolean checkValidString(String text) {
		return !text.equals("") && !(text == null);
	}
}
