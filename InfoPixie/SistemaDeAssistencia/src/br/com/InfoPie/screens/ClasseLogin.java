/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.screens;
import br.com.InfoPie.dal.ModuloConexao;
import javax.swing.JFrame;
import java.sql.*;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author debs
 */
public class ClasseLogin extends JFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    
    public void logar() {
        
        
        String sql = "select * from tb_usuarios where login=? and senha=?";
        try {
            String user = this.txtUser.getText();
            pst = conexao.prepareStatement(sql);
            String senha = new String(txtSenha.getPassword());
            pst.setString(1, user);
            pst.setString(2, senha);
            rs = pst.executeQuery();

            if (rs.next()) {
                String perfil = rs.getString(6);
                if (perfil.equals("admin")) {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.menuRelatorio.setEnabled(true);
                    TelaPrincipal.MenuCadUser.setEnabled(true);
                    TelaPrincipal.lblUser.setText(rs.getString(2));
                    TelaPrincipal.lblUser.setForeground(Color.red);
                    this.dispose();
                    conexao.close();
                } else {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.lblUser.setText(rs.getString(2));
                    this.dispose();
                    conexao.close();
                }
            } else {
                JOptionPane.showMessageDialog(null, "usuário e/ou senha inválido(s)");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /* public class EncriptaSenha {
     
     /** Creates a new instance of EncriptaSenha
     public EncriptaSenha() {        
     }
     
      public static String encripta (String senha) {
          try    {
              MessageDigest digest = MessageDigest.getInstance("MD5");
              digest.update(senha.getBytes());
              BASE64Encoder encoder = new BASE64Encoder (); 
              return encoder.encode(digest.digest()); 
          }catch (NoSuchAlgorithmException ns) {
              ns.printStackTrace ();
              return senha;
          }
      }**/    
     
 }
}


