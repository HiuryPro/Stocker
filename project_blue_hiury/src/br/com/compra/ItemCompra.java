/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.compra;

/**
 *
 * @author samue
 */
public class ItemCompra {

    private int id_nota;
    private int id_produto;
    private float quantidade;
    private float unitario;
    private float total;
    private String nome;

    public void setIdNota(int idNota) {
        this.id_nota = idNota;
    }

    public void setIdProduto(int idProduto) {
        this.id_produto = idProduto;
    }

    public void setQuantidade(float Quant) {
        this.quantidade = Quant;
    }

    public void setUnitario(float Unitario) {
        this.unitario = Unitario;
    }

    public void setTotal(float Total) {
        this.total = Total;
    }
    
    public void setNomeProduto(String nomeProduto){
        this.nome = nomeProduto;
    }
    public int getIdNota() {
        return this.id_nota;
    }

    public int getIdProduto() {
        return this.id_produto;
    }

    public float getQuantidade() {
        return this.quantidade;
    }

    public float getUnitario() {
        return this.unitario;
    }

    public float getTotal() {
        return this.total;
    }
    
    public String getNomeProduto(){
        return this.nome;
}
}