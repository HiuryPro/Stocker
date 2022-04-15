/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project_blue_alpha;
import br.com.dal_connexao.ModuloConexao;
import java.sql.*;

/**
 *
 * @author samue
 */
public class Project_blue_alpha {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Project_blue_alpha();
    }
    
   public Project_blue_alpha(){
        conexao = ModuloConexao.conector();
        // a linha abaixo serve de apoio ao status da ModuloConexao
        //System.out.println(ModuloConexao);
        if(conexao != null){
            System.out.print("Conetado");
        }else{
            System.out.print("Não Conectado");
        }
   }
    
}
