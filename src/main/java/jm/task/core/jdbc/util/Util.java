package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private final static String URL =
            "jdbc:mysql://localhost:3306/new_schema";
//    &useLegacyDatetimeCode=false&serverTimezone=UTC
//
    /*    private final static String URLFIXED =
                "jdbc:mysql://localhost:3306/new_schema?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                        "&useLegacyDatetimeCode=false&serverTimezone=UTC";*/
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection;
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                if (!connection.isClosed()) {
                    System.out.println("Соединение с БД Установлено!");
                }
                if (connection.isClosed()) {
                    System.out.println("Соединение с БД Закрыто!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return connection;
    }
// реализуйте настройку соеденения с БД
}
