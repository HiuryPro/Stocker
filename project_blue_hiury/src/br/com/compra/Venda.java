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
import javax.swing.table.DefaultTableModel;

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
    NotaFSaida nota = new NotaFSaida();
    public int estoque;
    public int rowCount;
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
        tabela.setRowHeight(30);
        pegarCliente();
        pegarProduto();
        AdicionaTabela();

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
            AdicionaTabela();
        } else {
            JOptionPane.showMessageDialog(null, "Erro", "Quantidade de Estoque insuficiente", 1);
        }

    }

    public void AdicionaTabela() {

        String sql = "SELECT *, date_format(data_saida, '%d/%m/%Y') as teste FROM produto_venda order by data_saida";
        try {

            st = conexao.createStatement();
            rs = st.executeQuery(sql);
            rowCount = 0;
            tabela.removeAll();

            while (rs.next()) {
                rowCount++;
                ((DefaultTableModel) tabela.getModel()).setRowCount(rowCount);
                tabela.setValueAt(rs.getString("nome_produto")+" ", rowCount - 1, 0);
                tabela.setValueAt(rs.getFloat("preco_unitario"), rowCount - 1, 1);
                tabela.setValueAt(rs.getInt("quantidade"), rowCount - 1, 2);
                tabela.setValueAt(rs.getFloat("total"), rowCount - 1, 3);
                tabela.setValueAt(rs.getString("cliente")+" ", rowCount - 1, 4);
                tabela.setValueAt(rs.getString("teste"), rowCount - 1, 5);

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
            JOptionPane.showMessageDialog(null, "Venda feita com sucesso");
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
        jLabel9 = new javax.swing.JLabel();
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
        Cad = new javax.swing.JButton();

        setClosable(true);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Total");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Valor");

        qtd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        qtd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtdKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Quantidade");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Data De Saida");

        edtValor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        edtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edtValorKeyReleased(evt);
            }
        });

        combo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Produto");

        try {
            dataS.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dataS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dataS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataSActionPerformed(evt);
            }
        });

        tabela.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Produto", "Valor", "Quantidade", "Total", "Cliente", "Data de Saida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabela.setRowHeight(30);
        jScrollPane1.setViewportView(tabela);
        if (tabela.getColumnModel().getColumnCount() > 0) {
            tabela.getColumnModel().getColumn(0).setPreferredWidth(120);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(60);
        }

        cliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Cliente");

        Cad.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Cad.setText("Vender");
        Cad.setToolTipText("Salva no banco de dados os dados da tabela acima");
        Cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(Cad, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(dataS, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(428, 428, 428))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(460, 460, 460)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(81, 81, 81)
                                        .addComponent(jLabel10))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(qtd, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(edtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel4)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qtd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dataS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(Cad, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void dataSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataSActionPerformed

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        if ("".equals(String.valueOf(combo.getSelectedItem()))) {

        } else {
            pegaEstoque();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_comboActionPerformed

    private void CadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadActionPerformed
        Confirma();
    }//GEN-LAST:event_CadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cad;
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
