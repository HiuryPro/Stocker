/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.compra;
import java.util.*;
import br.com.compra.Fornecedor; 
import br.com.compra.ItemCompra;
/**
 *
 * @author samue
 */
public class Compra_1 {
    
    private Fornecedor fornecedor;
    private Integer chave_nf;
    private Integer id_fornecedor;
    private Integer id_produto;
    private float valor_frete;
    private float valor_total;
    private Date data_emissao;
    private Integer numero_nf;
    private Integer produto_compra;
    
    public void produto(){
        fornecedor = new Fornecedor();
    }
    public void compra(int Chave, int idFornecedor, int idProduto, float valorFrete, float valorTotal, Date dataEmissao, int numeroNf, int produtoCompra){
        
        this.chave_nf = Chave;
        this.id_fornecedor = idFornecedor;
        this.id_produto = idProduto;
        this.valor_frete = valorFrete;
        this.valor_total = valorTotal;
        this.data_emissao = dataEmissao;
        this.numero_nf = numeroNf;
        this.produto_compra = produtoCompra;
    
    }
    

}