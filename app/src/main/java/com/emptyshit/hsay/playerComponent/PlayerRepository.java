package com.emptyshit.hsay.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


/**
 * Created by tungu on 18/04/2017.
 */

public class PlayerRepository{

	private PlayerDao playerDao;
	private QueryBuilder<Player> queryBuilder;

	public PlayerRepository(DaoSession daoSession){
		this.playerDao = daoSession.getPlayerDao();
	}

	List<Player> getAllPlayers() {
		return playerDao.loadAll();
	}

	Player save(Player player) {
		long id = this.playerDao.insert(player);
		player = this.playerDao.load(id);
		return player;
	}

	Player update(Player player){
		this.playerDao.update(player);
		return player;
	}

	Player delete(long id) {
		Player player = findPlayerById(id);
		this.playerDao.deleteByKey(id);
		return player;
	}

	Player findPlayerById(long id) {
		return this.playerDao.load(id);
	}

	Player findPlayerByName(String username) {
		this.queryBuilder = this.playerDao.queryBuilder().where(PlayerDao.Properties.PlayerName.eq(username));
		List<Player> playerList = this.queryBuilder.list();
		if(playerList.size() == 1){
			return playerList.get(0);
		}
		return null;
	}

	Player findPlayerByEmail(String email) {
		queryBuilder = this.playerDao.queryBuilder().where(PlayerDao.Properties.Email.eq(new EmailType(email)));
		List<Player> playerList = this.queryBuilder.list();
		if(playerList.size() == 1){
			return playerList.get(0);
		}
		return null;
	}
}
