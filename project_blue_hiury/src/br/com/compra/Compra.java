/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.compra;

import br.com.dal_connexao.ModuloConexao;
import br.com.estoque.TelaEstoque;
import br.com.fornecedor.Fornecedores;
import br.com.fornecedor.Validacao;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Hiury
 */
public class Compra extends javax.swing.JInternalFrame {

    /**
     * Creates new form Compra
     */
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs2 = null;
    ResultSet rs = null;
    ResultSet rs3 = null;
    Statement st2;
    Statement st3;
    Statement st;
    public int estoque;
    public String nomes;
    public int id;
    Validacao altera = new Validacao();

    public Compra(int i) {
        initComponents();
        id = i;
        conexao = ModuloConexao.conector();

        pegaF();
        inserirTabela();
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

    public void pegar() {

        String sql = "SELECT * FROM fornecedor_produto where fornecedor = '" + String.valueOf(cbfornecedo.getSelectedItem()) + "'";
        try {

            st = conexao.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                nomes = rs.getString("produto");
                combo.addItem(nomes);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Fornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    public void pegaF() {
        String sql = "SELECT DISTINCT fornecedor FROM fornecedor_produto";
        try {

            st = conexao.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {

                cbfornecedo.addItem(rs.getString("fornecedor"));
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Fornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    private void inserir() {
        String sql = "update  estoque set qtdestoque = ?  where nome_produto = '" + String.valueOf(combo.getSelectedItem()).trim() + "'";
        estoque = estoque + Integer.parseInt(qtd.getText());
        try {

            pst = conexao.prepareStatement(sql);

            pst.setInt(1, estoque);

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void inseriprodutoC() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String sql = "insert into  produto_compra(produtoC, precoC, quantidadeC, frete, totalC, data_entrada, fornecedor) values(?, ?, ?, ?, ?, ?, ?)";
        try {
            java.util.Date utilDate = format.parse(dataE.getText());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            pst = conexao.prepareStatement(sql);
            pst.setString(1, String.valueOf(combo.getSelectedItem()));
            pst.setFloat(2, Float.parseFloat(edtValor.getText()));
            pst.setInt(3, Integer.parseInt(qtd.getText()));
            pst.setFloat(4, Float.parseFloat(frete.getText()));
            pst.setFloat(5, (Float.parseFloat(edtValor.getText()) * Integer.parseInt(qtd.getText())) + Float.parseFloat(frete.getText()));
            pst.setDate(6, sqlDate);
            pst.setString(7, String.valueOf(cbfornecedo.getSelectedItem()));

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void inserirTabela() {

        int i;
        i = 0;

        String data;
        String pattern3 = "####/##/##";
        try {

            String sql = "SELECT *, date_format(data_entrada, '%d/%m/%Y') as teste FROM produto_compra";

            st2 = conexao.createStatement();
            rs3 = st2.executeQuery(sql);
            while (rs3.next()) {
                tabela.setValueAt(rs3.getString("produtoC"), i, 0);

                tabela.setValueAt(rs3.getFloat("precoC"), i, 1);

                tabela.setValueAt(rs3.getInt("quantidadeC"), i, 2);

                tabela.setValueAt(rs3.getFloat("frete"), i, 3);
                tabela.setValueAt(rs3.getFloat("totalC"), i, 4);
                tabela.setValueAt(rs3.getString("fornecedor"), i, 5);

                tabela.setValueAt(rs3.getString("teste"), i, 6);

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
            java.util.Date utilDate = format.parse(dataE.getText());
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
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja Comprar", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            inserir();
            inseriprodutoC();
            inserirTabela();
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

        edtValor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btSair = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btSalavr = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        cbfornecedo = new javax.swing.JComboBox<>();
        qtd = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        frete = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        dataE = new javax.swing.JFormattedTextField();
        total = new javax.swing.JLabel();

        setClosable(true);

        edtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edtValorKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Valor");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Fornecedor");

        btSair.setText("Sair");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Valor Frete");

        btSalavr.setText("Salvar");
        btSalavr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalavrActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Total");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Produto", "Preço", "Quantidade", "Frete", "Total", "Fornecedor", "Data entrada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabela.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tabela);
        tabela.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        cbfornecedo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbfornecedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbfornecedoActionPerformed(evt);
            }
        });

        qtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtdActionPerformed(evt);
            }
        });
        qtd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtdKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Quantidade");

        frete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                freteActionPerformed(evt);
            }
        });
        frete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                freteKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Data De Emissão");

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Produto");

        try {
            dataE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(cbfornecedo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(25, 25, 25)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(25, 25, 25)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(qtd, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(25, 25, 25)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(edtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addGap(35, 35, 35)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(frete, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(dataE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(363, 363, 363)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18))))
                .addGap(0, 78, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(btSalavr, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qtd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbfornecedo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataE, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSalavr, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void qtdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qtdActionPerformed

    private void freteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_freteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_freteActionPerformed

    private void btSalavrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalavrActionPerformed
        pegaEstoque();
        Confirma();
// TODO add your handling code here:
    }//GEN-LAST:event_btSalavrActionPerformed

    private void cbfornecedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbfornecedoActionPerformed
        if (String.valueOf(cbfornecedo.getSelectedItem()).equals(" ")) {
            combo.removeAllItems();
            combo.addItem(" ");
        } else {
            combo.removeAllItems();
            combo.addItem(" ");
            pegar();
        }

    }//GEN-LAST:event_cbfornecedoActionPerformed

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        if (String.valueOf(combo.getSelectedItem()).equals(" ")) {

        } else {
            String sql = "SELECT * FROM fornecedor_produto where fornecedor = '" + String.valueOf(cbfornecedo.getSelectedItem()) + "' and produto ='" + String.valueOf(combo.getSelectedItem()) + "'";
            try {

                st = conexao.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    edtValor.setText(String.valueOf(rs.getFloat("preco")));
                }

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Fornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        } // TODO add your handling code here:
    }//GEN-LAST:event_comboActionPerformed

    private void freteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_freteKeyReleased
        if (qtd.getText() != null && edtValor.getText() != null && frete.getText() != null)
            total.setText(String.valueOf((Integer.parseInt(qtd.getText()) * Float.parseFloat(edtValor.getText())) + Float.parseFloat(frete.getText())));  // TODO add your handling code here:
    }//GEN-LAST:event_freteKeyReleased

    private void qtdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtdKeyReleased
        if (qtd.getText() != null && edtValor.getText() != null && frete.getText() != null)
            total.setText(String.valueOf((Integer.parseInt(qtd.getText()) * Float.parseFloat(edtValor.getText())) + Float.parseFloat(frete.getText())));// TODO add your handling code here:
    }//GEN-LAST:event_qtdKeyReleased

    private void edtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtValorKeyReleased
        if (qtd.getText() != null && edtValor.getText() != null && frete.getText() != null)
            total.setText(String.valueOf((Integer.parseInt(qtd.getText()) * Float.parseFloat(edtValor.getText())) + Float.parseFloat(frete.getText())));
    }//GEN-LAST:event_edtValorKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSalavr;
    private javax.swing.JComboBox<String> cbfornecedo;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JFormattedTextField dataE;
    private javax.swing.JTextField edtValor;
    private javax.swing.JTextField frete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField qtd;
    private javax.swing.JTable tabela;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
