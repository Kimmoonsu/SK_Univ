package dao.impl;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DeletePlayer extends SqlUpdate {
	private static String SQL_DELETE_PLAYER = "DELETE FROM context where context_id=?";
	
	public DeletePlayer(DataSource ds)
	{
		super(ds, SQL_DELETE_PLAYER);
		super.declareParameter(new SqlParameter("context_id", Types.INTEGER));
		compile();
	}
}
