package tw.com.billy.fastcat.core.db.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 透過 Spring JdbcTemplate 實現jdbc封裝，並保留以後的擴充彈性
 * 
 * @author Billy
 */
@Repository("jdbcDAO")
public class JdbcDAO extends JdbcTemplate {
    
    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }
    
	/**
	 * 開啟資料庫連線. (透過DataSource取得DAO的資料庫連線)
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		Connection conn = null;

		try {
			conn = getDataSource().getConnection();

			return conn;
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e) {
				private static final long serialVersionUID = 1L;
			};
		}
	}

	/**
	 * 關閉資料庫連線
	 * 
	 * @param conn
	 * @return boolean
	 */
	public boolean closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				return true;
			}
		} catch (SQLException ignore) {
			;
		}

		return false;
	}	
	
}