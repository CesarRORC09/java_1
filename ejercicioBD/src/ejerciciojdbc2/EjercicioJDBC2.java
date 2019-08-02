
package ejerciciojdbc2;
import java.sql.*;
import domain.Usuario;
import datos.*;

public class EjercicioJDBC2 {
    public static void main(String[] args) {
        UsuarioJDBC usuariosjdbc = new UsuarioJDBC();
        usuariosjdbc.insert("Wendy", "morita");
        
    }
    
}
