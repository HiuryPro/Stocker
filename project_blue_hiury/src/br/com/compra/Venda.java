/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.compra;

import br.com.dal_connexao.ModuloConexao;
import br.com.estoque.Estoque;
import br.com.fornecedor.Fornecedores;
import br.com.fornecedor.Validacao;
import java.beans.PropertyVetoException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author Hiury
 */
public class Venda extends javax.swing.JInternalFrame {

    /**
     * Creates new form Venda
     */
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs2 = null;
    ResultSet rs = null;
    ResultSet rs3 = null;
    Statement st2;
    Statement st3;
    Statement st;
    public String nomes;
    Validacao altera = new Validacao();
    public int estoque;
    public int id;
    public String numeroNF;
    public NotaF nota = new NotaF();

    public Venda(int i) {

        initComponents();
        conexao = ModuloConexao.conector();

        id = i;
        try {
            this.setMaximum(true);
        } catch (PropertyVetoException ex) {
            java.util.logging.Logger.getLogger(Venda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        pegarProduto();
        pegarCliente();
        inserirTabela();

    }

    public void pegarProduto() {

        try {
            String sql = "SELECT * FROM produto";
            st = conexao.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                nomes = rs.getString("nome");
                combo.addItem(nomes);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Fornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    public void pegarCliente() {

        try {
            String sql = "SELECT * FROM cliente";
            st = conexao.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                nomes = rs.getString("nome");
                clientC.addItem(nomes);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Fornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    private void pegaEstoque() {
        String sql = "Select * from  estoque where nome_produto = '" + String.valueOf(combo.getSelectedItem()).trim() + "'";
        try {

            st2 = conexao.createStatement();
            rs2 = st2.executeQuery(sql);
            while (rs2.next()) {
                estoque = rs2.getInt("qtdestoque");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void inserir() {

        if (estoque >= Integer.parseInt(qtd.getText())) {
            String sql = "update  estoque set qtdestoque = ?  where nome_produto = '" + String.valueOf(combo.getSelectedItem()).trim() + "'";
            try {
                estoque = estoque - Integer.parseInt(qtd.getText());
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, estoque);
                pst.executeUpdate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            sql = "insert into  produto_venda(nome_produto, preco_unitario, quantidade, total, data_saida) values(?, ?, ?, ?, ? )";

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            try {
                java.util.Date utilDate = format.parse(dataS.getText());
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                pst = conexao.prepareStatement(sql);
                pst.setString(1, String.valueOf(combo.getSelectedItem()));
                pst.setFloat(2, Float.parseFloat(edtValor.getText()));
                pst.setInt(3, Integer.parseInt(qtd.getText()));
                pst.setFloat(4, Float.parseFloat(edtValor.getText()) * Integer.parseInt(qtd.getText()));
                pst.setDate(5, sqlDate);

                pst.executeUpdate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            inserirTabela();
            nota.pegaNF();
            nota.clienteID(clientC);
            nota.inseriNotaFiscal();
        } else {
            JOptionPane.showMessageDialog(null, "Erro", "Quantidade de Estoque insuficiente", 1);
        }

    }

    public void confirmacao() {
        int confirmacao;
        confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realizar está venda?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            inserir();
        }
    }

    public void inserirTabela() {

        int i, j;
        i = 0;
        j = 0;
        String data;
        String pattern3 = "####/##/##";
        try {

            String sql = "SELECT * FROM produto_venda";

            st2 = conexao.createStatement();
            rs3 = st2.executeQuery(sql);
            while (rs3.next()) {
                tabela.setValueAt(rs3.getString("nome_produto"), i, j);
                j++;
                tabela.setValueAt(rs3.getFloat("preco_unitario"), i, j);
                j++;
                tabela.setValueAt(rs3.getInt("quantidade"), i, j);
                j++;
                tabela.setValueAt(rs3.getFloat("total"), i, j);
                j++;
                data = String.valueOf(rs3.getDate("data_saida")).replaceAll("[^0-9]+", "");
                tabela.setValueAt(altera.format(pattern3, data), i, j);
                j++;

                j = 0;
                i++;

            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Fornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        qtd = new javax.swing.JTextField();
        btSair = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btSalavr = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        edtValor = new javax.swing.JTextField();
        combo = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        dataS = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        total = new javax.swing.JLabel();
        clientC = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Total");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Valor");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        qtd.setText("0");
        qtd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                qtdMouseClicked(evt);
            }
        });
        qtd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtdKeyReleased(evt);
            }
        });
        getContentPane().add(qtd, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 82, 30));

        btSair.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btSair.setText("Sair");
        getContentPane().add(btSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 300, 80, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Quantidade");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        btSalavr.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btSalavr.setText("Vender");
        btSalavr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalavrActionPerformed(evt);
            }
        });
        getContentPane().add(btSalavr, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, -1, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Data De Saida");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, -1, -1));

        edtValor.setText("0.00");
        edtValor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edtValorMouseClicked(evt);
            }
        });
        edtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edtValorKeyReleased(evt);
            }
        });
        getContentPane().add(edtValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 56, 30));

        getContentPane().add(combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 131, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Cliente");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        try {
            dataS.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(dataS, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 130, 30));

        tabela.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "produto", "valor", "quantidade", "total", "data de Saida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabela);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 180));
        getContentPane().add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 131, 30));

        getContentPane().add(clientC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 130, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Produto");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSalavrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalavrActionPerformed
        int teste;
        pegaEstoque();
        confirmacao();

    }//GEN-LAST:event_btSalavrActionPerformed

    private void edtValorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edtValorMouseClicked
        if (edtValor.getText().equals("0.00"))
            edtValor.setText(null);  // TODO add your handling code here:
    }//GEN-LAST:event_edtValorMouseClicked

    private void qtdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qtdMouseClicked
        if (qtd.getText().equals("0"))
            qtd.setText(null); // TODO add your handling code here:
    }//GEN-LAST:event_qtdMouseClicked

    private void qtdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtdKeyReleased
        if (qtd.getText() != null && edtValor.getText() != null)
            total.setText(String.valueOf(Integer.parseInt(qtd.getText()) * Float.parseFloat(edtValor.getText())));  // TODO add your handling code here:
    }//GEN-LAST:event_qtdKeyReleased

    private void edtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtValorKeyReleased
        if (qtd.getText() != null && edtValor.getText() != null)
            total.setText(String.valueOf(Integer.parseInt(qtd.getText()) * Float.parseFloat(edtValor.getText())));  // TODO add your handling code here:
    }//GEN-LAST:event_edtValorKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSalavr;
    private javax.swing.JComboBox<String> clientC;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JFormattedTextField dataS;
    private javax.swing.JTextField edtValor;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField qtd;
    private javax.swing.JTable tabela;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
