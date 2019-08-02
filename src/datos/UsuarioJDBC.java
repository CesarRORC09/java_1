
package datos;
import domain.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsuarioJDBC {
    
    private static String SQL_INSERT ="insert into usuario (usuario,password) VALUES (?,?)";
    private static String SQL_UPDATE = "update usuario set usuario=?, password=? where id_usuario=?";
    private static String SQL_DELETE= "delete from usuario where id_usuario=?";
    private static String SQL_SELECT= "select id_usuario,usuario,password from usuario order by id_usuario";
    
    public int insert(String usuario, String password){
        Connection conn = null;
        PreparedStatement ps = null;
        int rows=0;
        try{
            conn=Conexion.getConecction();
            ps=conn.prepareStatement(SQL_INSERT);
            System.out.println("Ejecutando query: "+SQL_INSERT);
            int index=1;
            ps.setString(index++, usuario);
            ps.setString(index++, password);
            rows= ps.executeUpdate();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            Conexion.close(conn);
            Conexion.close(ps);
        }
        return rows;
    }
    public int update(int id_usuario,String usuario,String password){
        Connection conn = null;
        PreparedStatement ps = null;
        int rows =0;
        try{
            conn=Conexion.getConecction();
            ps=conn.prepareStatement(SQL_UPDATE);
            System.out.println("Ejecutando query: "+ SQL_UPDATE);
            int index=1;
            ps.setString(index++, usuario);
            ps.setString(index++, password);
            ps.setInt(index++, id_usuario);
            rows=ps.executeUpdate();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            Conexion.close(conn);
            Conexion.close(ps);
        }
        return rows;
    }
    public int delete(int id_usuario){
        Connection conn = null;
        PreparedStatement ps = null;
        int rows =0;
        try{
            conn=Conexion.getConecction();
            ps=conn.prepareStatement(SQL_DELETE);
            System.out.println("Ejecutando query: "+ SQL_DELETE);
            int index=1;
            ps.setInt(index, id_usuario);
            rows= ps.executeUpdate();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            Conexion.close(conn);
            Conexion.close(ps);
        }
        return rows;
    }
    public List<Usuario> select(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios =new ArrayList<Usuario>();
        try{
            conn=Conexion.getConecction();
            ps= conn.prepareStatement(SQL_SELECT);
            rs=ps.executeQuery();
            System.out.println("Ejecutando query: "+SQL_SELECT);
            while(rs.next()){
                int id_usuario = rs.getInt(1);
                String usr = rs.getString(2);
                String password = rs.getString(3);
                usuario=new Usuario();
                usuario.setId_usuario(id_usuario);
                usuario.setPassword(password);
                usuario.setUsuario(usr);
                usuarios.add(usuario);
            }
            
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);
        }
        return usuarios;
    }
}
