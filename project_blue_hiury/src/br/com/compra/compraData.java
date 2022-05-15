/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.compra;

import br.com.compra.Fornecedor;
import br.com.dal_connexao.ModuloConexao;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author samue
 */
public class compraData {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conexao = null;

    public compraData() throws Exception {
    }

    public Vector<Fornecedor> listarFornecedor() throws Exception {
        conexao = ModuloConexao.conector();
        Fornecedor obj = new Fornecedor();
        String sql = "select * from fornecedores";
        Vector<Fornecedor> fornecedor = new Vector<Fornecedor>();
         pst = conexao.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setCnpj(rs.getInt("cnpj"));

            fornecedor.add(obj);
        }
        return fornecedor;
    }
    

}
