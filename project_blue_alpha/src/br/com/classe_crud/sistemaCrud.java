/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.classe_crud;

import br.com.dal_connexao.ModuloConexao;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author samue
 */
public class sistemaCrud {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public int insert(PreparedStatement pst, String sql) {

        try {
            rs = pst.executeQuery(sql);

            if (rs.next()) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
}

