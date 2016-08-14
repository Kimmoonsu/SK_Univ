package dao.impl;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdatePlayer extends SqlUpdate {
	private static String SQL_UPDATE_PLAYER = "UPDATE context set date=?, context_title=?, context_sub=?, location=?, img=? WHERE context_id=?";
	
	public UpdatePlayer(DataSource ds) {
		super(ds, SQL_UPDATE_PLAYER);
		super.declareParameter(new SqlParameter("date", Types.DATE));
		super.declareParameter(new SqlParameter("context_title", Types.VARCHAR));
		super.declareParameter(new SqlParameter("context_sub", Types.VARCHAR));
		super.declareParameter(new SqlParameter("location", Types.VARCHAR));
		super.declareParameter(new SqlParameter("img", Types.VARCHAR));
		super.declareParameter(new SqlParameter("context_id", Types.INTEGER));
		compile();
	}
}
