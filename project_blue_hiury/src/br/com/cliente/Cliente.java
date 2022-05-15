/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cliente;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import br.com.dal_connexao.ModuloConexao;
import java.sql.*;
/**
 *
 * @author samue
 */
public class Cliente {
    String Uf; 
    ArrayList<String> lista = new ArrayList();
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conexao = ModuloConexao.conector();
    
    public void estado(String estado){
        if(estado == "Acre"){
            this.Uf = "Acre"; 
        }
        if(estado == "Alagoas"){
            this.Uf = "Alagoas"; 
        }
        if(estado == "Amapá"){
            this.Uf = "Amapá"; 
        }
        if(estado == "Amazonas"){
            this.Uf = "Amazonas"; 
        }
        if(estado == "Bahia"){
            this.Uf = "Bahia"; 
        }
        if(estado == "Ceará"){
            this.Uf = "Ceará"; 
        }
        if(estado == "Distrito Federal"){
            this.Uf = "Distrito Federal"; 
        }
        if(estado == "Espírito Santo"){
            this.Uf = "Espírito Santo"; 
        }
        if(estado == "Goiás"){
            this.Uf = "Goiás"; 
        }
        if(estado == "Maranhão"){
            this.Uf = "Maranhão"; 
        }
        if(estado == "Mato Grosso"){
            this.Uf = "Mato Grosso"; 
        }
        if(estado == "Mato Grosso do Sul"){
            this.Uf = "Mato Grosso do Sul"; 
        }
        if(estado == "Minas Gerais"){
            this.Uf = "Minas Gerais"; 
        }
        if(estado == "Pará"){
            this.Uf = "Pará"; 
        }
        if(estado == "Paraíba"){
            this.Uf = "Paraíba"; 
        }
        if(estado == "Paraná"){
            this.Uf = "Paraná"; 
        }
        if(estado == "Pernambuco"){
            this.Uf = "Pernambuco"; 
        }
        if(estado == "Piauí"){
            this.Uf = "Piauí"; 
        }
        if(estado == "Rio de Janeiro"){
            this.Uf = "Rio de Janeiro"; 
        }
        if(estado == "Rio Grande do Norte"){
            this.Uf = "Rio Grande do Norte"; 
        }
        if(estado == "Rio Grande do Sul"){
            this.Uf = "Rio Grande do Sul"; 
        }
        if(estado == "Rondônia"){
            this.Uf = "Rondônia"; 
        }
        if(estado == "Roraima"){
            this.Uf = "Roraima"; 
        }
        if(estado == "Santa Catarina"){
            this.Uf = "Santa Catarina"; 
        }
        if(estado == "São Paulo"){
            this.Uf = "São Paulo"; 
        }
        if(estado == "Sergipe"){
            this.Uf = "Sergipe"; 
        }
        if(estado == "Tocantins"){
            this.Uf = "Tocantins"; 
        }        
    }
    

        
    
}
