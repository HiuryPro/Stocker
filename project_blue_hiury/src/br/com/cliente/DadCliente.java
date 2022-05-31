/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cliente;

import br.com.dal_connexao.ModuloConexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Hiury
 */
public class DadCliente extends javax.swing.JInternalFrame {

    /**
     * Creates new form DadCliente
     */
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement st;
    public int estoque;
    public String nomes;
    public int id;
    public int rowCount = 0;
    public int escolhido;
    public ArrayList<String> produto = new ArrayList();
    public ArrayList<Float> preco = new ArrayList();

    public DadCliente() {
        initComponents();
        conexao = ModuloConexao.conector();
        pegaC();
        telaC.setVisible(false);
    }

    public void pegaC() {
        try {
            String sql = "SELECT nome FROM cliente";

            st = conexao.createStatement();
            rs = st.executeQuery(sql);
            clien.addItem(" ");
            while (rs.next()) {

                clien.addItem(rs.getString("nome"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pegaDados() {

        try {
            String sql = "SELECT * FROM cliente where nome = '" + String.valueOf(clien.getSelectedItem()) + "'";

            st = conexao.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                edtNome.setText(rs.getString("nome"));
                edtCnpj.setText(rs.getString("cnpj"));
                edtCategoria.setText(rs.getString("categoria"));
                edtFaixa.setText(String.valueOf(rs.getFloat("faixaR")));
                cidade.setText(rs.getString("Cidade"));
                estado.setText(rs.getString("estado"));

                descricao.setText(rs.getString("descricao"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atualizaClien() {

        String sql = "UPDATE cliente SET nome = ?, cnpj = ?, categoria = ?, faixaR = ?, cidade = ?, estado = ?, descricao = ? WHERE nome = '" + String.valueOf(clien.getSelectedItem()) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, edtNome.getText());
            pst.setString(2, edtCnpj.getText().replaceAll("[^0-9]+", ""));
            pst.setString(3, edtCategoria.getText());
            pst.setFloat(4, Float.parseFloat(edtFaixa.getText()));
            pst.setString(5, cidade.getText());
            pst.setString(6, estado.getText());
            pst.setString(7, descricao.getText());

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void ConfirmaAC() {
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja Alterar os Dados do Cliente?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            atualizaClien();

        } else {
            // tabela.setValueAt()
        }

    }

    public void deletaForn() {

        String sql = "Delete from cliente where nome ='" + String.valueOf(clien.getSelectedItem()) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void ConfirmaDC() {
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja Deletar todos os Dados do Fornecedor?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            deletaForn();
            clien.removeAllItems();
            pegaC();
            telaC.setVisible(false);
           

        } else {
            // tabela.setValueAt()
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        clien = new javax.swing.JComboBox<>();
        telaC = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        edtCategoria = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        edtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricao = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        edtFaixa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        edtCnpj = new javax.swing.JFormattedTextField();
        altera = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        cidade = new javax.swing.JTextField();
        estado = new javax.swing.JTextField();

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Cliente");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        clien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienActionPerformed(evt);
            }
        });
        getContentPane().add(clien, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 130, 30));

        telaC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Descrição");
        telaC.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));
        telaC.add(edtCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 260, 35));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Categoria");
        telaC.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));
        telaC.add(edtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 256, 35));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Nome");
        telaC.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        descricao.setColumns(20);
        descricao.setRows(5);
        jScrollPane1.setViewportView(descricao);

        telaC.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 680, 130));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Cnpj");
        telaC.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Cidade");
        telaC.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, -1, -1));
        telaC.add(edtFaixa, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 200, 35));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Estado");
        telaC.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Faixa De Renda");
        telaC.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, -1, -1));

        try {
            edtCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        telaC.add(edtCnpj, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 200, 35));

        altera.setText("Alterar");
        altera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alteraActionPerformed(evt);
            }
        });
        telaC.add(altera, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 420, -1, -1));

        jButton2.setText("Deletar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        telaC.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 420, -1, -1));
        telaC.add(cidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 180, 35));
        telaC.add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 180, 35));

        getContentPane().add(telaC, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 735, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienActionPerformed
        if (!" ".equals(String.valueOf(clien.getSelectedItem()))) {
            pegaDados();
            telaC.setVisible(true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_clienActionPerformed

    private void alteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alteraActionPerformed
        ConfirmaAC();
    }//GEN-LAST:event_alteraActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ConfirmaDC();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton altera;
    private javax.swing.JTextField cidade;
    private javax.swing.JComboBox<String> clien;
    private javax.swing.JTextArea descricao;
    private javax.swing.JTextField edtCategoria;
    private javax.swing.JFormattedTextField edtCnpj;
    private javax.swing.JTextField edtFaixa;
    private javax.swing.JTextField edtNome;
    private javax.swing.JTextField estado;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel telaC;
    // End of variables declaration//GEN-END:variables
}
