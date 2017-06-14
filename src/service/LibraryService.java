package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Lib;
import dao.LibraryDao;

public class LibraryService {

	public List<Lib> getLibList() {

		Connection connection = null;
		try {
			connection = getConnection();

			LibraryDao listDao = new LibraryDao();
			List<Lib> ret = listDao.getLibList(connection);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

}
