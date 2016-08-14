package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Context;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import dao.ContextDao;

public class ContextDaoImpl extends JdbcDaoSupport implements ContextDao{

	@Override
	public List getContextList() throws DataAccessException {
		RowMapper rowMapper = new ContextRowMapper();
		return getJdbcTemplate().query("SELECT * FROM context",
				rowMapper);
		//context_id, context_writer,date,context_title
	}
	protected class ContextRowMapper implements RowMapper {
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
			context.setLatitude(rs.getDouble("latitude"));
			context.setLongitude(rs.getDouble("longitude"));
			return context;
		}
	}
	
	
}
