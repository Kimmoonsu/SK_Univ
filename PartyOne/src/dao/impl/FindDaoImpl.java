package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Context;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import dao.FindDao;



public class FindDaoImpl extends JdbcDaoSupport implements FindDao{

	@Override
	public List getContextList() throws DataAccessException {
		RowMapper rowMapper = new FindRowMapper();
		return getJdbcTemplate().query("SELECT * FROM context",
				rowMapper);
		//context_id, context_writer,date,context_title
	}
	protected class FindRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// ResultSet에서 오브젝트로 다시 채워 넣음
			Context context = new Context();
			context.setContext_id(rs.getInt("context_id"));
			context.setContext_writer(rs.getString("context_writer"));
			context.setDate(rs.getDate("date"));
			context.setContext_title(rs.getString("context_title"));
			context.setContext_sub(rs.getString("context_sub"));
			context.setLocation(rs.getString("location"));
			context.setImg(rs.getString("img"));
			return context;
		}
	}
	@Override
	public List getWriterList(String keyword) throws DataAccessException {
		RowMapper rowMapper = new FindRowMapper();
		return getJdbcTemplate().query("SELECT * FROM context where context_writer like '%"+keyword+"%'",	rowMapper);
	}
	@Override
	public List getTitleList(String keyword) throws DataAccessException {
		RowMapper rowMapper = new FindRowMapper();
		return getJdbcTemplate().query("SELECT * FROM context where context_title like '%"+keyword+"%'",	rowMapper);
	}
	@Override
	public List getSubList(String keyword) throws DataAccessException {
		RowMapper rowMapper = new FindRowMapper();
		return getJdbcTemplate().query("SELECT * FROM context where context_sub like '%"+keyword+"%'",	rowMapper);
	}
}
