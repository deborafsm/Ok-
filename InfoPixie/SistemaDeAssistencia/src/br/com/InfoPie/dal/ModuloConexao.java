package br.com.InfoPie.dal;
import java.sql.*;
//@author Debora Freire
public class ModuloConexao {
    public static Connection conector() {
        java.sql.Connection conexao = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/dbcad";
        String user = "root";
        String password = "";
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user,password);
            return conexao;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}
