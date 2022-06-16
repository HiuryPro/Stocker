/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.entrega;

import br.com.cliente.*;
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
public class DadosEntrega extends javax.swing.JInternalFrame {

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

    public DadosEntrega() {
        initComponents();
        conexao = ModuloConexao.conector();
        pegaEA();
        pegaEF();
        pegaEC();

        telaC.setVisible(false);
    }

    public void pegaEA() {
        String sql = "SELECT NF FROM entregas_detalhado where status = 0";
        try {

            st = conexao.createStatement();
            rs = st.executeQuery(sql);

            cbEntregaA.addItem(" ");

            while (rs.next()) {

                cbEntregaA.addItem(rs.getString("NF"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DadosEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pegaEF() {
        String sql = "SELECT NF FROM entregas_detalhado where status = 1";
        try {

            st = conexao.createStatement();
            rs = st.executeQuery(sql);

            cbEntregaF.addItem(" ");
            while (rs.next()) {

                cbEntregaF.addItem(rs.getString("NF"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DadosEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pegaEC() {
        String sql = "SELECT NF FROM entregas_detalhado where status = 2";
        try {

            st = conexao.createStatement();
            rs = st.executeQuery(sql);

            cbEntregaC.addItem(" ");

            while (rs.next()) {

                cbEntregaC.addItem(rs.getString("NF"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DadosEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pegaDadosEA() {

        try {
            String sql = "SELECT *,date_format(data_entrega, '%d/%m/%Y') as teste  FROM entregas_detalhado where NF = '" + String.valueOf(cbEntregaA.getSelectedItem()) + "' and status = 0";

            st = conexao.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                edtEntregador.setText(rs.getString("entregador"));
                telefoneE.setText(rs.getString("telefone"));
                edtProduto.setText(rs.getString("produto"));
                edtQtd.setText(String.valueOf(rs.getInt("qtd")));

                edtNF.setText(rs.getString("NF"));
                dataE.setText(rs.getString("teste"));
                edtCliente.setText(rs.getString("cliente"));

                edtCidade.setText(rs.getString("cidade"));
                edtEstado.setText(rs.getString("estado"));

                edtEndereco.setText(rs.getString("endereco"));
                status.setText("Em andamento");

                finalizaE.setVisible(true);
                cancelaE.setVisible(true);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DadosEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pegaDadosEF() {

        try {
            String sql = "SELECT *,date_format(data_entrega, '%d/%m/%Y') as teste  FROM entregas_detalhado where NF = '" + String.valueOf(cbEntregaF.getSelectedItem()) + "' and status = 1";

            st = conexao.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                edtEntregador.setText(rs.getString("entregador"));
                telefoneE.setText(rs.getString("telefone"));
                edtProduto.setText(rs.getString("produto"));
                edtQtd.setText(String.valueOf(rs.getInt("qtd")));

                edtNF.setText(rs.getString("NF"));
                dataE.setText(rs.getString("teste"));
                edtCliente.setText(rs.getString("cliente"));

                edtCidade.setText(rs.getString("cidade"));
                edtEstado.setText(rs.getString("estado"));

                edtEndereco.setText(rs.getString("endereco"));
                status.setText("Finalizada");

                finalizaE.setVisible(false);
                cancelaE.setVisible(false);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DadosEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pegaDadosEC() {

        try {
            String sql = "SELECT *,date_format(data_entrega, '%d/%m/%Y') as teste  FROM entregas_detalhado where NF = '" + String.valueOf(cbEntregaC.getSelectedItem()) + "' and status = 2";

            st = conexao.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                edtEntregador.setText(rs.getString("entregador"));
                telefoneE.setText(rs.getString("telefone"));
                edtProduto.setText(rs.getString("produto"));
                edtQtd.setText(String.valueOf(rs.getInt("qtd")));

                edtNF.setText(rs.getString("NF"));
                dataE.setText(rs.getString("teste"));
                edtCliente.setText(rs.getString("cliente"));

                edtCidade.setText(rs.getString("cidade"));
                edtEstado.setText(rs.getString("estado"));

                edtEndereco.setText(rs.getString("endereco"));
                status.setText("Cancelado");

                finalizaE.setVisible(false);
                cancelaE.setVisible(false);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DadosEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atualizaStatusEntrega(int i) {

        String sql = "UPDATE entregas_detalhado SET status = " + i + " WHERE status = 0 and NF = '" + edtNF.getText() + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void ConfirmaAC(int i) {
        if (i == 1) {
            int resultado = JOptionPane.showConfirmDialog(null, "Deseja Finalizar a entrega?", "Confirmação", JOptionPane.YES_NO_OPTION);

            if (resultado == JOptionPane.YES_OPTION) {
                atualizaStatusEntrega(i);

                cbEntregaA.removeAllItems();
                cbEntregaF.removeAllItems();

                pegaEA();
                pegaEF();

                telaC.setVisible(false);

            } else {
                // tabela.setValueAt()
            }

        } else if (i == 2) {
            int resultado = JOptionPane.showConfirmDialog(null, "Deseja Cancelar a Entrega?", "Confirmação", JOptionPane.YES_NO_OPTION);

            if (resultado == JOptionPane.YES_OPTION) {
                atualizaStatusEntrega(i);

                cbEntregaA.removeAllItems();
                cbEntregaC.removeAllItems();

                pegaEA();
                pegaEC();

                telaC.setVisible(false);

            } else {
                // tabela.setValueAt()
            }
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

        entregaF = new javax.swing.JLabel();
        cbEntregaF = new javax.swing.JComboBox<>();
        telaC = new javax.swing.JPanel();
        edtQtd = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        edtEntregador = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        edtNF = new javax.swing.JTextField();
        edtEndereco = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        edtProduto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        edtEstado = new javax.swing.JTextField();
        edtCliente = new javax.swing.JTextField();
        edtCidade = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        telefoneE = new javax.swing.JFormattedTextField();
        dataE = new javax.swing.JFormattedTextField();
        status = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        finalizaE = new javax.swing.JButton();
        cancelaE = new javax.swing.JButton();
        entregaA = new javax.swing.JLabel();
        cbEntregaA = new javax.swing.JComboBox<>();
        entregaC = new javax.swing.JLabel();
        cbEntregaC = new javax.swing.JComboBox<>();

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        entregaF.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        entregaF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        entregaF.setText("Entregas Finalizadas");
        getContentPane().add(entregaF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 210, 40));

        cbEntregaF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbEntregaF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEntregaFActionPerformed(evt);
            }
        });
        getContentPane().add(cbEntregaF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 210, 35));

        telaC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        edtQtd.setEditable(false);
        edtQtd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        telaC.add(edtQtd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 180, 35));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Quantidade");
        telaC.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        edtEntregador.setEditable(false);
        edtEntregador.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        telaC.add(edtEntregador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 256, 35));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Entregador");
        telaC.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Produto");
        telaC.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Numero Nota Fiscal");
        telaC.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Data Entrega");
        telaC.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        edtNF.setEditable(false);
        edtNF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        telaC.add(edtNF, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 180, 35));

        edtEndereco.setEditable(false);
        edtEndereco.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        telaC.add(edtEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 280, 35));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Endereço");
        telaC.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 80, -1));

        edtProduto.setEditable(false);
        edtProduto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        telaC.add(edtProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 180, 35));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Estado");
        telaC.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Cliente");
        telaC.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

        edtEstado.setEditable(false);
        edtEstado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        edtEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEstadoActionPerformed(evt);
            }
        });
        telaC.add(edtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, 180, 35));

        edtCliente.setEditable(false);
        edtCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        telaC.add(edtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 230, 35));

        edtCidade.setEditable(false);
        edtCidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        telaC.add(edtCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 200, 35));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("Cidade");
        telaC.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 80, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Telefone:");
        telaC.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 87, -1));

        telefoneE.setEditable(false);
        try {
            telefoneE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        telefoneE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        telaC.add(telefoneE, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 190, 35));

        dataE.setEditable(false);
        try {
            dataE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dataE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        telaC.add(dataE, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 200, 35));

        status.setEditable(false);
        status.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        status.setText("Em andamento");
        telaC.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 280, 35));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Status");
        telaC.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 80, -1));

        finalizaE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        finalizaE.setText("Finalizar Entrega");
        finalizaE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizaEActionPerformed(evt);
            }
        });
        telaC.add(finalizaE, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, -1, 35));

        cancelaE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cancelaE.setText("Cancelar Entrega");
        cancelaE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelaEActionPerformed(evt);
            }
        });
        telaC.add(cancelaE, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 410, -1, 35));

        getContentPane().add(telaC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 735, 460));

        entregaA.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        entregaA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        entregaA.setText("Entregas em Andamento");
        getContentPane().add(entregaA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 210, 40));

        cbEntregaA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbEntregaA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEntregaAActionPerformed(evt);
            }
        });
        getContentPane().add(cbEntregaA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 210, 35));

        entregaC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        entregaC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        entregaC.setText("Entregas Canceladas");
        getContentPane().add(entregaC, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 210, 40));

        cbEntregaC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbEntregaC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEntregaCActionPerformed(evt);
            }
        });
        getContentPane().add(cbEntregaC, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 210, 35));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbEntregaFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEntregaFActionPerformed

        if (!" ".equals(String.valueOf(cbEntregaF.getSelectedItem()))) {
            pegaDadosEF();
            telaC.setVisible(true);
        } else {
            telaC.setVisible(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEntregaFActionPerformed

    private void edtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtEstadoActionPerformed

    private void cbEntregaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEntregaAActionPerformed
        if (!" ".equals(String.valueOf(cbEntregaA.getSelectedItem()))) {
            pegaDadosEA();
            telaC.setVisible(true);
        } else {
            telaC.setVisible(false);
        }
    }//GEN-LAST:event_cbEntregaAActionPerformed

    private void cbEntregaCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEntregaCActionPerformed
        if (!" ".equals(String.valueOf(cbEntregaC.getSelectedItem()))) {
            pegaDadosEC();
            telaC.setVisible(true);
        } else {
            telaC.setVisible(false);
        } // TODO add your handling code here:
    }//GEN-LAST:event_cbEntregaCActionPerformed

    private void finalizaEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizaEActionPerformed
        ConfirmaAC(1);   // TODO add your handling code here:
    }//GEN-LAST:event_finalizaEActionPerformed

    private void cancelaEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelaEActionPerformed
        ConfirmaAC(2);      // TODO add your handling code here:
    }//GEN-LAST:event_cancelaEActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelaE;
    private javax.swing.JComboBox<String> cbEntregaA;
    private javax.swing.JComboBox<String> cbEntregaC;
    private javax.swing.JComboBox<String> cbEntregaF;
    private javax.swing.JFormattedTextField dataE;
    private javax.swing.JTextField edtCidade;
    private javax.swing.JTextField edtCliente;
    private javax.swing.JTextField edtEndereco;
    private javax.swing.JTextField edtEntregador;
    private javax.swing.JTextField edtEstado;
    private javax.swing.JTextField edtNF;
    private javax.swing.JTextField edtProduto;
    private javax.swing.JTextField edtQtd;
    private javax.swing.JLabel entregaA;
    private javax.swing.JLabel entregaC;
    private javax.swing.JLabel entregaF;
    private javax.swing.JButton finalizaE;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField status;
    private javax.swing.JPanel telaC;
    private javax.swing.JFormattedTextField telefoneE;
    // End of variables declaration//GEN-END:variables
}
