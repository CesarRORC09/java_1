package datos;
import java.sql.*;
public class Conexion {
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://localhost/sga?useSSL=false";
    private static String JDBC_USER = "root";
    private static String JDBC_PASSWORD = "password";
    private static Driver driver=null;
    
    public static synchronized Connection getConecction() throws SQLException{
        if(driver == null){
            try{
                Class jdbcClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcClass.newInstance();
                DriverManager.registerDriver(driver);
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Fallo conexion...");
            }
        }
        return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
            
    }
    
    public static void close(Connection conn){
        try{
            if(conn== null) conn.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
    public static void close(PreparedStatement ps){
        try{
            if(ps== null) ps.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
    public static void close(ResultSet rs){
        try{
            if(rs==null) rs.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
    
    
    
    
}
