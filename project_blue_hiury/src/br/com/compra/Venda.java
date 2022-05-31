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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
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
    NotaF nota = new NotaF();
    public int estoque;
    public int id;

    public Venda(int i) {
        initComponents();
        conexao = ModuloConexao.conector();
        id = i;
        try {
            this.setMaximum(true);
        } catch (PropertyVetoException ex) {
            java.util.logging.Logger.getLogger(Venda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        pegarCliente();
        pegarProduto();
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
                cliente.addItem(nomes);
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
            sql = "insert into  produto_venda(nome_produto, preco_unitario, quantidade, total, data_saida, cliente) values(?, ?, ?, ?, ?, ? )";

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
                pst.setString(6, String.valueOf(cliente.getSelectedItem()));

                pst.executeUpdate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            nota.pegaNF();
            nota.clienteID(cliente);
            nota.inseriNotaFiscal();
            inserirTabela();
        } else {
            JOptionPane.showMessageDialog(null, "Erro", "Quantidade de Estoque insuficiente", 1);
        }

    }

    public void inserirTabela() {

        int i;
        i = 0;

        String data;
        String pattern3 = "####/##/##";
        try {

            String sql = "SELECT * FROM produto_venda";

            st2 = conexao.createStatement();
            rs3 = st2.executeQuery(sql);
            while (rs3.next()) {
                tabela.setValueAt(rs3.getString("nome_produto"), i, 0);

                tabela.setValueAt(rs3.getFloat("preco_unitario"), i, 1);

                tabela.setValueAt(rs3.getInt("quantidade"), i, 2);

                tabela.setValueAt(rs3.getFloat("total"), i, 3);

                data = String.valueOf(rs3.getDate("data_saida")).replaceAll("[^0-9]+", "");
                tabela.setValueAt(altera.format(pattern3, data), i, 4);

                i++;

            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Fornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    public void inserirVariaE() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String sqls = "insert into varia_estoque(produto,data, quantidadeE) values(?, ?, ?)";
        try {
            java.util.Date utilDate = format.parse(dataS.getText());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pst = conexao.prepareStatement(sqls);

            pst.setString(1, String.valueOf(combo.getSelectedItem()));
            pst.setDate(2, sqlDate);
            pst.setInt(3, estoque);
            
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Confirma() {
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja Vender", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            inserir();
            inserirVariaE();
        } else {

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
        cliente = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Total");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Valor");

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

        btSair.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btSair.setText("Sair");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Quantidade");

        btSalavr.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btSalavr.setText("Vender");
        btSalavr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalavrActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Data De Saida");

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

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Produto");

        try {
            dataS.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

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

        cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Cliente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btSalavr)
                        .addGap(18, 18, 18)
                        .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(122, 122, 122)
                                .addComponent(jLabel9)
                                .addGap(68, 68, 68)
                                .addComponent(jLabel10))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(qtd, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(edtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110)
                                .addComponent(jLabel11)))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataS, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qtd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btSalavr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(207, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSalavrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalavrActionPerformed
        pegaEstoque();
        Confirma(); // TODO add your handling code here:
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

    private void clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSalavr;
    private javax.swing.JComboBox<String> cliente;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JFormattedTextField dataS;
    private javax.swing.JTextField edtValor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField qtd;
    private javax.swing.JTable tabela;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
