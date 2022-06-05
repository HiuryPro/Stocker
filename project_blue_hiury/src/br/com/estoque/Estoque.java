/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque;

import Relatorio.Relatorio;
import br.com.dal_connexao.ModuloConexao;
import java.sql.Connection;
import java.sql.*;
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

        AdicionaTabela();
    }

    public void AdicionaTabela() {

        String sql = "SELECT nome_produto, qtdestoque FROM estoque";

        try {

            st = conexao.createStatement();
            rs = st.executeQuery(sql);

            rowCount = 0;
            produto.clear();
            qtd.clear();
            tabela.removeAll();

            while (rs.next()) {
                produto.add(rs.getString("nome_produto"));
                qtd.add(rs.getInt("qtdestoque"));
                rowCount++;
                ((DefaultTableModel) tabela.getModel()).setRowCount(rowCount);
                tabela.setValueAt(rs.getString("nome_produto"), rowCount - 1, 0);
                tabela.setValueAt(rs.getInt("qtdestoque"), rowCount - 1, 1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atualizaEstoque() {
        int count, qtd2;
        qtd2 = ((DefaultTableModel) tabela.getModel()).getRowCount();
        for (count = 0; count < qtd2; count++) {
            String sql = "UPDATE estoque SET nome_produto = ?, qtdestoque = ? WHERE nome_produto = '" + produto.get(count) + "' and qtdestoque = " + qtd.get(count);
            try {
                pst = conexao.prepareStatement(sql);

                pst.setString(1, String.valueOf(tabela.getValueAt(count, 0)));
                pst.setInt(2, Integer.parseInt(String.valueOf(tabela.getValueAt(count, 1))));

                pst.executeUpdate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public void deletaEstoque() {
        escolhido = Integer.parseInt(linha.getText()) - 1;
        String sql = "Delete from estoque where nome_produto = '" + tabela.getValueAt(escolhido, 0) + "' and qtdestoque = " + tabela.getValueAt(escolhido, 1);
        try {
            pst = conexao.prepareStatement(sql);

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        sql = "Delete from produto where nome = '" + tabela.getValueAt(escolhido, 0) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        sql = "Delete from varia_estoque where produto = '" + tabela.getValueAt(escolhido, 0) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void deletaLinha() {
        if (rowCount > 0) {
            rowCount--;
            ((DefaultTableModel) tabela.getModel()).removeRow(escolhido);
            ((DefaultTableModel) tabela.getModel()).setRowCount(escolhido);
        }

    }

    public void ConfirmaAE() {
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja Alterar os Dados de Produto?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            atualizaEstoque();
            AdicionaTabela();

        } else {
            // tabela.setValueAt()
        }

    }

    public void ConfirmaDE() {
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja Apagar os Dados de Produto?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            deletaEstoque();
            deletaLinha();

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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        altera = new javax.swing.JButton();
        deleta = new javax.swing.JButton();
        linha = new javax.swing.JTextField();

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

        rel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rel1.setText("Relatorio");
        rel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rel1ActionPerformed(evt);
            }
        });

        try {
            deData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            ateData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel1.setText("De");

        jLabel2.setText("Até");

        rel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rel2.setText("Relatorio");
        rel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rel2ActionPerformed(evt);
            }
        });

        try {
            deData1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            ateData1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setText("De");

        jLabel4.setText("Até");

        rel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rel3.setText("Relatorio");
        rel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rel3ActionPerformed(evt);
            }
        });

        try {
            deData2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            ateData2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setText("De");

        jLabel6.setText("Até");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Produto", "Quantidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabela);

        altera.setText("Alterar Dados");
        altera.setToolTipText("Clicar duas vezes da tabela para habilitar edição ");
        altera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alteraActionPerformed(evt);
            }
        });

        deleta.setText("Deletar linha:");
        deleta.setToolTipText("Selecione a linha da tabela a ser deletada na tela e no banco");
        deleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(altera)
                        .addGap(50, 50, 50)
                        .addComponent(deleta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deData, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(ateData, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deData1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(ateData1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deData2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(ateData2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rel1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rel2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rel3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(altera, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deData, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(ateData))
                .addGap(18, 18, 18)
                .addComponent(rel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deData1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(ateData1))
                .addGap(18, 18, 18)
                .addComponent(rel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deData2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(ateData2))
                .addGap(18, 18, 18)
                .addComponent(rel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

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

    private void deletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletaActionPerformed
        ConfirmaDE();
    }//GEN-LAST:event_deletaActionPerformed

    private void alteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alteraActionPerformed
        ConfirmaAE();
    }//GEN-LAST:event_alteraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton altera;
    private javax.swing.JFormattedTextField ateData;
    private javax.swing.JFormattedTextField ateData1;
    private javax.swing.JFormattedTextField ateData2;
    private javax.swing.JFormattedTextField deData;
    private javax.swing.JFormattedTextField deData1;
    private javax.swing.JFormattedTextField deData2;
    private javax.swing.JButton deleta;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField linha;
    private javax.swing.JButton rel1;
    private javax.swing.JButton rel2;
    private javax.swing.JButton rel3;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
