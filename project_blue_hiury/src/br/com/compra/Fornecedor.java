
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.compra;

import java.sql.*;
import br.com.dal_connexao.ModuloConexao;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author samue
 */
public class Fornecedor {

    private int Id;
    private String Nome;
    private int Cnpj;

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conexao = ModuloConexao.conector();

/*    public Fornecedor(int pId, int pCnpj, String pNome) {
        id = pId;
        nome = pNome;
        cnpj = pCnpj;
    }*/


    

    public void setId(int pId) {
        Id = pId;
    }

    public int getId() {
        return Id;
    }

    public void setNome(String pNome) {
        Nome = pNome;
    }

    public String getNome() {
        return Nome;
    }

    public void setCnpj(int pCnpj) {
        Cnpj = pCnpj;
    }

    public int getCnpj() {
        return Cnpj;
    }

    @Override
    public String toString() {
        return this.getNome();
    }


}
