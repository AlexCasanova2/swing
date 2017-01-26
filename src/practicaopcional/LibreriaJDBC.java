package practicaopcional;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LibreriaJDBC {
    
    private Connection conexion;
    
    // Método que verifica si los datos de acceso son correctos
    public boolean comprobarUser(String user, String pass) throws SQLException {
        conectar();
        boolean existe = false;
        String query = "select * from user where username = ? and password = ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, user);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        ps.close();
        desconectar();
        return existe;
    }
    
    public void crearLibreria(String title, Integer npages, String genre, String author) throws SQLException{
        conectar();
        String query = "insert into book title, npages, genre, author";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, title);
        ps.setInt(2, npages);
        ps.setString(3, genre);
        ps.setString(4, author);
       
    }
    
    
     private void conectar() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/library";
        String usr = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url,usr,pass);
    }
    
    private void desconectar() throws SQLException{
        if(conexion != null){
            conexion.close();
        }
    }
    
}
