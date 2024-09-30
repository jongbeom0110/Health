package Util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ExUtil {
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException cne) {
            System.out.println("드라이버 로딩 실패");
            cne.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "rok", "1111");
        } catch (SQLException se) {
            System.out.println("DB 접속 실패");
            se.printStackTrace();
        }
        return con;
    }
}