package dao.impl;

import model.Context;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import dao.FormDao;



public class FormDaoImpl extends JdbcDaoSupport implements FormDao{

	private InsertPlayer insertPlayer;
	private DeletePlayer deletePlayer;
	private UpdatePlayer updatePlayer;
	
	protected void initDao() throws Exception {
		this.insertPlayer = new InsertPlayer(getDataSource());
		this.deletePlayer = new DeletePlayer(getDataSource());
		this.updatePlayer = new UpdatePlayer(getDataSource());
	}

	@Override
	public void insertPlayer(Context context) throws DataAccessException {
		this.insertPlayer.update(new Object[] { context.getContext_writer(), context.getDate(), context.getContext_title(), context.getContext_sub(), context.getLocation(), context.getImg(), context.getLatitude(), context.getLongitude()});
	}

	
	@Override
	public void deletePlayer(Context context) throws DataAccessException {
		this.deletePlayer.update(context.getContext_id());
		
	}

	@Override
	public void updatePlayer(Context context) throws DataAccessException {
		//date=?, context_title=?, context_sub=?, location=?, img=? WHERE context_id=?
		this.updatePlayer.update(new Object[] { context.getDate(), context.getContext_title(), context.getContext_sub(), context.getLocation(), context.getImg(), context.getContext_id()});
	}
	
}
