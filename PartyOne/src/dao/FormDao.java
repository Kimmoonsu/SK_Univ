package dao;

import model.Context;

import org.springframework.dao.DataAccessException;

public interface FormDao {
	void insertPlayer(Context context)throws DataAccessException;
	void deletePlayer(Context context)throws DataAccessException;
	void updatePlayer(Context context)throws DataAccessException;
}
