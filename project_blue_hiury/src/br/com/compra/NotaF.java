/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.compra;

import br.com.dal_connexao.ModuloConexao;
import br.com.fornecedor.Validacao;
import java.lang.System.Logger;
import java.sql.*;
import java.util.logging.Level;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Hiury
 */
public class NotaF {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs2 = null;
    ResultSet rs = null;
    ResultSet rs3 = null;
    Statement st2;
    Statement st3;
    Statement st;
    public int clienteID;
    public String numeroNF;

    public NotaF() {
        conexao = ModuloConexao.conector();
        numeroNF = "000000000";
    }

    public void pegaNF() {

        try {
            String CodigoDeConsulta = "SELECT COUNT(id) from notafiscal_saida";

            pst = conexao.prepareStatement(CodigoDeConsulta);
            ResultSet Resultado = pst.executeQuery(CodigoDeConsulta);
            Resultado.next();

            if (Resultado.getInt("COUNT(id)") == 0) {
                numeroNF = "000000001";

            } else {
                int tamanho, incremento;

                CodigoDeConsulta = "SELECT MAX(ID) FROM notafiscal_saida";
                pst = conexao.prepareStatement(CodigoDeConsulta);
                Resultado = pst.executeQuery(CodigoDeConsulta);
                Resultado.next();
                incremento = Resultado.getInt("MAX(ID)") + 1;
                tamanho = String.valueOf(incremento).length();
                numeroNF = numeroNF.substring(0, 9 - tamanho) + String.valueOf(incremento);

            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(NotaF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void inseriNotaFiscal() {

        try {
            String CodigoConsulta = "insert into notafiscal_saida(numero, serie) values(?, ?)";

            pst = conexao.prepareStatement(CodigoConsulta);
            pst.setString(1, numeroNF);
            pst.setInt(2, clienteID);

            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(NotaF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void clienteID(JComboBox<String> clientC) {
        try {
            String sql;
            sql = "Select * from cliente where nome = '" + String.valueOf(clientC.getSelectedItem()) + "'";

            st2 = conexao.createStatement();
            rs3 = st2.executeQuery(sql);
            while (rs3.next()) {

                clienteID = rs3.getInt("id");

            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(NotaF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
