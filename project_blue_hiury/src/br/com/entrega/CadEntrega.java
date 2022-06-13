/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.entrega;

import br.com.dal_connexao.ModuloConexao;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hiury
 */
public class CadEntrega extends javax.swing.JInternalFrame {

    /**
     * Creates new form CadEntrega
     */
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement st;
    public int rowCount = 0;
    public int contador = 0;

    public CadEntrega() {
        initComponents();
        conexao = ModuloConexao.conector();
        pegaNNF();
    }

    public void pegaNNF() {
        String sql = "SELECT numero  FROM notafiscal_saida";
        try {

            st = conexao.createStatement();
            rs = st.executeQuery(sql);
            cbNumeroNF.addItem(" ");
            while (rs.next()) {

                cbNumeroNF.addItem(rs.getString("numero"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void inseriTabela() {

        rowCount++;
        ((DefaultTableModel) tabela.getModel()).setRowCount(rowCount);
        tabela.setValueAt(String.valueOf(entregador.getText()), rowCount - 1, 0);
        tabela.setValueAt(String.valueOf(cbNumeroNF.getSelectedItem()), rowCount - 1, 1);
        tabela.setValueAt(dataE.getText(), rowCount - 1, 2);
    }

    public void deletaLinha() {
        if (rowCount > 0) {
            rowCount--;
            ((DefaultTableModel) tabela.getModel()).removeRow(Integer.parseInt(del.getText()) - 1);
            ((DefaultTableModel) tabela.getModel()).setRowCount(rowCount);
        }

    }

    public void pegaBD() {
        ArrayList<String> valores = new ArrayList<String>();

        for (int i = 0; i < tabela.getRowCount(); i++) {

            int id = 0;
            String cliente = "";

            valores.clear();

            String sql = "Select id from notafiscal_saida where numero = '" + String.valueOf(tabela.getValueAt(i, 1)) + "'";
            try {
                st = conexao.createStatement();
                rs = st.executeQuery(sql);

                while (rs.next()) {
                    id = rs.getInt("id");
                }

            } catch (SQLException ex) {
                Logger.getLogger(CadEntrega.class.getName()).log(Level.SEVERE, null, ex);
            }

            valores.add(String.valueOf(tabela.getValueAt(i, 0)));
            valores.add(String.valueOf(tabela.getValueAt(i, 1)));
            valores.add(String.valueOf(tabela.getValueAt(i, 2)));

            sql = "Select * from produto_venda where id = " + id;

            try {
                st = conexao.createStatement();
                rs = st.executeQuery(sql);

                while (rs.next()) {
                    valores.add(rs.getString("nome_produto"));
                    valores.add(rs.getString("quantidade"));
                    valores.add(rs.getString("cliente"));

                    cliente = rs.getString("cliente");
                }

            } catch (SQLException ex) {
                Logger.getLogger(CadEntrega.class.getName()).log(Level.SEVERE, null, ex);
            }

            sql = "Select * from cliente where nome = '" + cliente + "'";

            try {
                st = conexao.createStatement();
                rs = st.executeQuery(sql);

                while (rs.next()) {
                    valores.add(rs.getString("endereco"));
                    valores.add(rs.getString("estado"));
                    valores.add(rs.getString("cidade"));

                }

            } catch (SQLException ex) {
                Logger.getLogger(CadEntrega.class.getName()).log(Level.SEVERE, null, ex);
            }

            inserirBD(valores);

        }
    }

    public void inserirBD(ArrayList<String> valores) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String sql = "insert into entregas_detalhado(entregador, NF, data_entrega, produto, qtd, cliente, endereco, estado, cidade) values(?, ? ,?, ?, ?, ?, ?, ?, ?)";
        try {
            java.util.Date utilDate = format.parse(valores.get(2));
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            pst = conexao.prepareStatement(sql);

            pst.setString(1, valores.get(0));
            pst.setString(2, valores.get(1));
            pst.setDate(3, sqlDate);
            pst.setString(4, valores.get(3));
            pst.setInt(5, Integer.parseInt(valores.get(4)));
            pst.setString(6, valores.get(5));
            pst.setString(7, valores.get(6));
            pst.setString(8, valores.get(7));
            pst.setString(9, valores.get(8));

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Confirma() {
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja Cadastrar", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            pegaBD();
            tabela.removeAll();
            rowCount = 0;
            ((DefaultTableModel) tabela.getModel()).setRowCount(rowCount);

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

        entregador = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbNumeroNF = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        dataE = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        del = new javax.swing.JTextField();
        Cad = new javax.swing.JButton();

        setClosable(true);

        entregador.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Entregador");

        cbNumeroNF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Número Nota Fiscal");

        try {
            dataE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Data de Entrega");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Entregador", "Número NF", "Data de Entrega"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabela);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setText("Adicionar");
        jButton1.setToolTipText("Adiciona os itens na tabela");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton2.setText("Deletar linha:");
        jButton2.setToolTipText("Deleta a linha escolhida na caixa de texto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delActionPerformed(evt);
            }
        });

        Cad.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Cad.setText("Cadastrar");
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
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addGap(192, 192, 192)
                        .addComponent(jLabel2)
                        .addGap(132, 132, 132)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(entregador, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(cbNumeroNF, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(dataE, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton1)
                        .addGap(13, 13, 13)
                        .addComponent(jButton2)
                        .addGap(9, 9, 9)
                        .addComponent(del, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(Cad)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(entregador, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNumeroNF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataE, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(del, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(Cad)
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        inseriTabela();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        deletaLinha();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void CadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadActionPerformed
        Confirma();
    }//GEN-LAST:event_CadActionPerformed

    private void delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cad;
    private javax.swing.JComboBox<String> cbNumeroNF;
    private javax.swing.JFormattedTextField dataE;
    private javax.swing.JTextField del;
    private javax.swing.JTextField entregador;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
