package main.utils;

import org.springframework.stereotype.Component;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseManager {

    private DataSource ds;

    public DatabaseManager() {
        try {
            InitialContext cxt = new InitialContext();
            ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnectionFromPool() {
        Connection conn = null;

        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}

