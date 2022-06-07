/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque;

import Relatorio.Relatorio;
import br.com.dal_connexao.ModuloConexao;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hiury
 */
public class Estoque extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement st;
    public int rowCount = 0;
    public int escolhido;
    Relatorio rel = new Relatorio();
    public int compara;

    public ArrayList<String> produto = new ArrayList();
    public ArrayList<Integer> qtd = new ArrayList();

    /**
     * Creates new form Estoque
     */
    public Estoque() {
        initComponents();
        conexao = ModuloConexao.conector();

        rel1.setText("<html>Relatório de <br> compra<html>");
        rel2.setText("<html>Relatório de <br> venda<html>");
        rel3.setText("<html>Relatório de <br> variação de <br> estoque<html>");
        pr.setVisible(false);
        pegaP();

    }

    public void pegaP() {
        try {
            String sql = "SELECT nome FROM produto";

            st = conexao.createStatement();
            rs = st.executeQuery(sql);
            forn.addItem(" ");
            while (rs.next()) {

                forn.addItem(rs.getString("nome"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pegaDados() {

        try {
            String sql = "SELECT nome , descricao FROM produto where nome = '" + String.valueOf(forn.getSelectedItem()) + "'";

            st = conexao.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                nome.setText(rs.getString("nome"));
                descri.setText(rs.getString("descricao"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "SELECT qtdestoque FROM estoque where nome_produto = '" + String.valueOf(forn.getSelectedItem()) + "'";

            st = conexao.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                quant.setText(String.valueOf(rs.getInt("qtdestoque")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atualizaE() {

        String sql = "UPDATE produto SET nome = ?, descricao = ? WHERE nome = '" + String.valueOf(forn.getSelectedItem()) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, nome.getText());
            pst.setString(2, descri.getText());

            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        sql = "UPDATE estoque SET qtdestoque = ?, nome_produto = ? WHERE nome_produto = '" + String.valueOf(forn.getSelectedItem()) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setInt(1, Integer.parseInt(quant.getText()));
            pst.setString(2, nome.getText());

            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        sql = "UPDATE varia_estoque SET produto = ? WHERE produto = '" + String.valueOf(forn.getSelectedItem()) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, nome.getText());

            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        sql = "UPDATE fornecedor_produto SET produto = ? WHERE produto = '" + String.valueOf(forn.getSelectedItem()) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, nome.getText());

            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        sql = "UPDATE produto_compra SET produtoC = ? WHERE produtoC = '" + String.valueOf(forn.getSelectedItem()) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, nome.getText());

            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        sql = "UPDATE produto_venda SET nome_produto = ? WHERE nome_produto = '" + String.valueOf(forn.getSelectedItem()) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, nome.getText());

            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        sql = "UPDATE relatoriototal SET nome_produto = ? WHERE nome_produto = '" + String.valueOf(forn.getSelectedItem()) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, nome.getText());

            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void inserirVariaE() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String sqls = "insert into varia_estoque(produto, data, quantidadeE) values(?, ?, ?)";
        try {
            java.util.Date utilDate = format.parse(dtf.format(LocalDateTime.now()));
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pst = conexao.prepareStatement(sqls);

            pst.setString(1, nome.getText());
            pst.setDate(2, sqlDate);
            pst.setInt(3, Integer.parseInt(quant.getText()));

            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void deletaE() {

        String sql = "Delete from produto where nome ='" + String.valueOf(forn.getSelectedItem()) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        sql = "Delete from estoque where nome_produto ='" + String.valueOf(forn.getSelectedItem()) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void confereEstoque() {

        try {

            String sql = "SELECT qtdestoque FROM estoque where nome_produto = '" + String.valueOf(forn.getSelectedItem()) + "'";

            st = conexao.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                compara = rs.getInt("qtdestoque");

            }

        } catch (SQLException ex) {
            Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void ConfirmaADP() {
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja Alterar os Dados de Produto?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            confereEstoque();
            if (compara != Integer.parseInt(quant.getText())) {
                inserirVariaE();
            }
            atualizaE();
            forn.removeAllItems();
            pegaP();
            forn.setSelectedItem(nome.getText());
            confereEstoque();
            if (compara != Integer.parseInt(quant.getText())) {
                inserirVariaE();
            }

        } else {
            // tabela.setValueAt()
        }

    }

    public void ConfirmaDDP() {
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja Apagar os Dados de Produto?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            deletaE();

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

        jDialog1 = new javax.swing.JDialog();
        rel1 = new javax.swing.JButton();
        deData = new javax.swing.JFormattedTextField();
        ateData = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rel2 = new javax.swing.JButton();
        deData1 = new javax.swing.JFormattedTextField();
        ateData1 = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rel3 = new javax.swing.JButton();
        deData2 = new javax.swing.JFormattedTextField();
        ateData2 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        forn = new javax.swing.JComboBox<>();
        pr = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        quant = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descri = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        del = new javax.swing.JButton();
        alt = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(745, 582));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rel1.setText("Relatorio");
        rel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rel1ActionPerformed(evt);
            }
        });
        getContentPane().add(rel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 86, 181, 67));

        try {
            deData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(deData, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 38, 126, 30));

        try {
            ateData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(ateData, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 38, 126, 30));

        jLabel1.setText("De");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 20, 37, -1));

        jLabel2.setText("Até");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 20, 37, -1));

        rel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rel2.setText("Relatorio");
        rel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rel2ActionPerformed(evt);
            }
        });
        getContentPane().add(rel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 280, 181, 67));

        try {
            deData1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(deData1, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 232, 126, 30));

        try {
            ateData1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(ateData1, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 232, 126, 30));

        jLabel3.setText("De");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 214, 37, -1));

        jLabel4.setText("Até");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 214, 37, -1));

        rel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rel3.setText("Relatorio");
        rel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rel3ActionPerformed(evt);
            }
        });
        getContentPane().add(rel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 451, 181, 67));

        try {
            deData2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(deData2, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 403, 126, 30));

        try {
            ateData2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(ateData2, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 403, 126, 30));

        jLabel5.setText("De");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 389, 37, -1));

        jLabel6.setText("Até");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 389, 37, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Produto");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        forn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fornActionPerformed(evt);
            }
        });
        getContentPane().add(forn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 160, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Nome do Produto");

        quant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Quantidade");

        descri.setColumns(20);
        descri.setRows(5);
        jScrollPane1.setViewportView(descri);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Descrição do Produto");

        del.setText("Deletar");
        del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delActionPerformed(evt);
            }
        });

        alt.setText("Alterar");
        alt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout prLayout = new javax.swing.GroupLayout(pr);
        pr.setLayout(prLayout);
        prLayout.setHorizontalGroup(
            prLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prLayout.createSequentialGroup()
                .addGroup(prLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(prLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addGap(72, 72, 72)
                        .addComponent(jLabel8))
                    .addGroup(prLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(quant, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(prLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9))
                    .addGroup(prLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(prLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(alt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(del, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        prLayout.setVerticalGroup(
            prLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(prLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(8, 8, 8)
                .addGroup(prLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quant, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addComponent(jLabel9)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114)
                .addGroup(prLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(alt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(del, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(pr, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 380, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rel1ActionPerformed
        rel.criaRelatorio(deData, ateData);
    }//GEN-LAST:event_rel1ActionPerformed

    private void rel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rel2ActionPerformed
        rel.criaRelatorio2(deData1, ateData1);
    }//GEN-LAST:event_rel2ActionPerformed

    private void rel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rel3ActionPerformed
        rel.criaRelatorio3(deData2, ateData2);
    }//GEN-LAST:event_rel3ActionPerformed

    private void fornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fornActionPerformed
        if (!" ".equals(String.valueOf(forn.getSelectedItem()))) {
            pr.setVisible(true);
            pegaDados();
        } else {
            pr.setVisible(false);
        }

    }//GEN-LAST:event_fornActionPerformed

    private void quantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantActionPerformed

    private void altActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altActionPerformed
        ConfirmaADP();
    }//GEN-LAST:event_altActionPerformed

    private void delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delActionPerformed
        ConfirmaDDP();// TODO add your handling code here:
    }//GEN-LAST:event_delActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alt;
    private javax.swing.JFormattedTextField ateData;
    private javax.swing.JFormattedTextField ateData1;
    private javax.swing.JFormattedTextField ateData2;
    private javax.swing.JFormattedTextField deData;
    private javax.swing.JFormattedTextField deData1;
    private javax.swing.JFormattedTextField deData2;
    private javax.swing.JButton del;
    private javax.swing.JTextArea descri;
    private javax.swing.JComboBox<String> forn;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nome;
    private javax.swing.JPanel pr;
    private javax.swing.JTextField quant;
    private javax.swing.JButton rel1;
    private javax.swing.JButton rel2;
    private javax.swing.JButton rel3;
    // End of variables declaration//GEN-END:variables
}
