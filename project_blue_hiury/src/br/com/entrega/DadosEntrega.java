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
        pegaE();
        telaC.setVisible(false);
    }

    public void pegaE() {
        try {
            String sql = "SELECT id FROM entregas_detalhado";

            st = conexao.createStatement();
            rs = st.executeQuery(sql);
            entregas.addItem(" ");
            while (rs.next()) {

                entregas.addItem("Entrega nº " + rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DadosEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pegaDados() {

        try {
            String sql = "SELECT *,date_format(data_entrega, '%d/%m/%Y') as teste  FROM entregas_detalhado where id = " + entregas.getSelectedIndex();

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

            }
        } catch (SQLException ex) {
            Logger.getLogger(DadosEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/*
    public void atualizaClien() {

        String sql = "UPDATE cliente SET nome = ?, cnpj = ?, categoria = ?, faixaR = ?, cidade = ?, estado = ?, descricao = ?, telefone = ?, email = ?, endereco = ? WHERE nome = '" + String.valueOf(entregas.getSelectedItem()) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, edtEntregador.getText());
            pst.setString(2, edtCnpj.getText().replaceAll("[^0-9]+", ""));
            pst.setString(3, edtQtd.getText());
            pst.setFloat(4, Float.parseFloat(edtFaixa.getText()));
            pst.setString(5, edtNF.getText());
            pst.setString(6, edtDEntrega.getText());
            pst.setString(7, descricao.getText());
            pst.setString(8, tel.getText().replaceAll("[^0-9]+", ""));
            pst.setString(9, email.getText());
            pst.setString(10, edtEndereco.getText());

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void ConfirmaAC() {
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja Alterar os Dados do Cliente?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            atualizaClien();
            entregas.removeAllItems();
            pegaE();
            entregas.setSelectedItem(edtEntregador.getText());

        } else {
            // tabela.setValueAt()
        }

    }

    public void deletaForn() {

        String sql = "Delete from cliente where nome ='" + String.valueOf(entregas.getSelectedItem()) + "'";
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
            entregas.removeAllItems();
            pegaE();
            telaC.setVisible(false);

        } else {
            // tabela.setValueAt()
        }

    }
*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        entregas = new javax.swing.JComboBox<>();
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

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Entregas");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        entregas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        entregas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entregasActionPerformed(evt);
            }
        });
        getContentPane().add(entregas, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 160, 35));

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
        telaC.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 80, -1));

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

        getContentPane().add(telaC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 735, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void entregasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entregasActionPerformed
        if (!" ".equals(String.valueOf(entregas.getSelectedItem()))) {
            pegaDados();
            telaC.setVisible(true);
        } else {
            telaC.setVisible(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_entregasActionPerformed

    private void edtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtEstadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField dataE;
    private javax.swing.JTextField edtCidade;
    private javax.swing.JTextField edtCliente;
    private javax.swing.JTextField edtEndereco;
    private javax.swing.JTextField edtEntregador;
    private javax.swing.JTextField edtEstado;
    private javax.swing.JTextField edtNF;
    private javax.swing.JTextField edtProduto;
    private javax.swing.JTextField edtQtd;
    private javax.swing.JComboBox<String> entregas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel telaC;
    private javax.swing.JFormattedTextField telefoneE;
    // End of variables declaration//GEN-END:variables
}
