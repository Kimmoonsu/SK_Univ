package dao.impl;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertPlayer extends SqlUpdate{
	public InsertPlayer(DataSource ds)
	{
		super(ds, "INSERT INTO context (context_writer, date, context_title, context_sub, location, img, latitude, longitude) VALUES(?,?,?,?,?,?,?,?)");
		super.declareParameter(new SqlParameter("context_writer", Types.VARCHAR));
		super.declareParameter(new SqlParameter("date", Types.DATE));
		super.declareParameter(new SqlParameter("context_title", Types.VARCHAR));
		super.declareParameter(new SqlParameter("context_sub", Types.VARCHAR));
		super.declareParameter(new SqlParameter("location", Types.VARCHAR));
		super.declareParameter(new SqlParameter("img", Types.VARCHAR));
		super.declareParameter(new SqlParameter("latitude", Types.DOUBLE));
		super.declareParameter(new SqlParameter("longitude", Types.DOUBLE));
		compile();
	}
}
