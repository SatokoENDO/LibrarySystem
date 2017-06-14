package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Lib;
import exception.SQLRuntimeException;

public class LibraryDao {

	public List<Lib> getLibList(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			String mySql = "select * from libraries";
			ResultSet rs = statement.executeQuery(mySql);
			List<Lib> libList = toLibList(rs);
			if (libList.isEmpty() == true) {
				return null;
			} else {
				return libList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	private List<Lib> toLibList(ResultSet rs) throws SQLException {

		List<Lib> ret = new ArrayList<Lib>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Lib library = new Lib();
				library.setId(id);
				library.setName(name);

				ret.add(library);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}