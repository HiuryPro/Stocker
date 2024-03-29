/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fornecedor;

import br.com.dal_connexao.ModuloConexao;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hiury
 */
public class FornecedorDados extends javax.swing.JInternalFrame {

    /**
     * Creates new form FornecedorDados
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

    Validacao altera = new Validacao();

    public FornecedorDados() {
        initComponents();
        conexao = ModuloConexao.conector();
        pr.setVisible(false);
        ip.setVisible(false);
        pegaF();
    }

    public void pegaF() {
        String sql = "SELECT nome FROM fornecedor";
        try {

            st = conexao.createStatement();
            rs = st.executeQuery(sql);
            forn.addItem(" ");
            while (rs.next()) {

                forn.addItem(rs.getString("nome"));
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Fornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    public void AdicionaTabela() {

        String sql = "SELECT produto, preco FROM fornecedor_produto where fornecedor = '" + String.valueOf(forn.getSelectedItem()) + "'";
        try {

            st = conexao.createStatement();
            rs = st.executeQuery(sql);
            rowCount = 0;
            produto.clear();
            preco.clear();
            tabela.removeAll();

            while (rs.next()) {
                produto.add(rs.getString("produto"));
                preco.add(rs.getFloat("preco"));
                rowCount++;
                ((DefaultTableModel) tabela.getModel()).setRowCount(rowCount);
                tabela.setValueAt(rs.getString("produto"), rowCount - 1, 0);
                tabela.setValueAt(rs.getFloat("preco"), rowCount - 1, 1);

            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Fornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    public void pegaDados() {

        String sql = "SELECT *, date_format(data_nascimento, '%d/%m/%Y') as teste FROM fornecedor where nome = '" + String.valueOf(forn.getSelectedItem()) + "'";
        try {

            st = conexao.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                nome.setText(rs.getString("nome"));
                cnpj.setText(rs.getString("cnpj"));
                inscE.setText(rs.getString("inscE"));
                email.setText(rs.getString("email"));
                cidade.setText(rs.getString("Cidade"));
                estado.setText(rs.getString("estado"));
                telefone.setText(rs.getString("telefone"));
                descri.setText(rs.getString("descricao"));
                dataNasc.setText(rs.getString("teste"));

            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Fornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    public void atualizaDadosP() {
        int count, qtd;
        qtd = ((DefaultTableModel) tabela.getModel()).getRowCount();
        for (count = 0; count < qtd; count++) {
            String sql = "UPDATE fornecedor_produto SET produto = ?, preco = ? WHERE produto = '" + produto.get(count) + "' and preco = " + preco.get(count);
            try {
                pst = conexao.prepareStatement(sql);

                pst.setString(1, String.valueOf(tabela.getValueAt(count, 0)));
                pst.setFloat(2, Float.parseFloat(String.valueOf(tabela.getValueAt(count, 1))));

                pst.executeUpdate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public void deletaDadosP() {
        escolhido = Integer.parseInt(linha.getText()) - 1;
        String sql = "Delete from fornecedor_produto where produto = '" + tabela.getValueAt(escolhido, 0) + "' and preco = " + tabela.getValueAt(escolhido, 1);
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

    public void atualizaForn() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String sql = "UPDATE fornecedor SET nome = ?, cnpj = ?, inscE = ?, email = ?, cidade = ?, estado = ?, telefone = ?, descricao = ?, data_nascimento = ? WHERE nome = '" + String.valueOf(forn.getSelectedItem()) + "'";
        try {
            java.util.Date utilDate = format.parse(dataNasc.getText());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pst = conexao.prepareStatement(sql);

            pst.setString(1, nome.getText());
            pst.setString(2, cnpj.getText().replaceAll("[^0-9]+", ""));
            pst.setString(3, inscE.getText().replaceAll("[^0-9]+", ""));
            pst.setString(4, email.getText());
            pst.setString(5, cidade.getText());
            pst.setString(6, estado.getText());
            pst.setString(7, telefone.getText().replaceAll("[^0-9]+", ""));
            pst.setString(8, descri.getText());
            pst.setDate(9, sqlDate);

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        sql = "UPDATE fornecedor_produto SET fornecedor = ? WHERE fornecedor = '" + String.valueOf(forn.getSelectedItem()) + "'";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, nome.getText());
            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void deletaForn() {

        String sql = "Delete from fornecedor_produto where fornecedor ='" + String.valueOf(forn.getSelectedItem()) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        sql = "Delete from fornecedor where nome ='" + String.valueOf(forn.getSelectedItem()) + "'";
        try {
            pst = conexao.prepareStatement(sql);

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void ConfirmaADP() {
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja Alterar os Dados de Produto?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            atualizaDadosP();
            AdicionaTabela();

        } else {
            // tabela.setValueAt()
        }

    }

    public void ConfirmaDDP() {
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja Apagar os Dados de Produto?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            deletaDadosP();
            deletaLinha();

        } else {
            // tabela.setValueAt()
        }

    }

    public void ConfirmaAF() {
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja Alterar os Dados do Fornecedor?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            atualizaForn();
            forn.removeAllItems();
            pegaF();
            forn.setSelectedItem(nome.getText());

        } else {
            // tabela.setValueAt()
        }

    }

    public void ConfirmaDF() {
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja Deletar todos os Dados do Fornecedor?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            deletaForn();
            forn.removeAllItems();
            pegaF();
            pr.setVisible(false);
            ip.setVisible(false);

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

        forn = new javax.swing.JComboBox<>();
        ip = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descri = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        estado = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nome = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        cnpj = new javax.swing.JFormattedTextField();
        inscE = new javax.swing.JFormattedTextField();
        telefone = new javax.swing.JFormattedTextField();
        AtualizaForn = new javax.swing.JButton();
        delF = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cidade = new javax.swing.JTextField();
        dataNasc = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        pr = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        deletaP = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        linha = new javax.swing.JTextField();
        AtualizaP = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        forn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        forn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fornActionPerformed(evt);
            }
        });
        getContentPane().add(forn, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 62, 180, 35));

        ip.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Email:");
        ip.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Estado:");
        ip.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, -1, -1));

        email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ip.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 240, 35));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Descrição");
        ip.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        descri.setColumns(20);
        descri.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        descri.setLineWrap(true);
        descri.setRows(5);
        descri.setWrapStyleWord(true);
        jScrollPane1.setViewportView(descri);

        ip.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 410, 90));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Inscrição Estadual");
        ip.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));
        ip.add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 170, 35));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Nome:");
        ip.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 67, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Telefone:");
        ip.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 87, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("CNPJ:");
        ip.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 67, -1));

        nome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeActionPerformed(evt);
            }
        });
        ip.add(nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 230, 35));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Cidade:");
        ip.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        try {
            cnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cnpj.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ip.add(cnpj, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 150, 35));

        try {
            inscE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###.###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inscE.setText("   .   .   .");
        inscE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ip.add(inscE, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 130, 35));

        try {
            telefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        telefone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ip.add(telefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 150, 35));

        AtualizaForn.setText("Atualizar Dados");
        AtualizaForn.setToolTipText("Atualiza os dados pessoais do fornecedor");
        AtualizaForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtualizaFornActionPerformed(evt);
            }
        });
        ip.add(AtualizaForn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, -1, 30));

        delF.setText("Deletar Fornecedor");
        delF.setToolTipText("Deleta todos os dados deste fornecedor");
        delF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delFActionPerformed(evt);
            }
        });
        ip.add(delF, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 500, -1, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Informações Pessoais");
        ip.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 180, 40));
        ip.add(cidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 170, 35));

        try {
            dataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dataNasc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ip.add(dataNasc, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 150, 35));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Data de Nascimento");
        ip.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, -1, -1));

        getContentPane().add(ip, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 10, -1, 533));

        pr.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabela.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Produto", "Preço"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabela.setPreferredSize(new java.awt.Dimension(210, 403));
        tabela.setRowHeight(30);
        tabela.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tabela);
        if (tabela.getColumnModel().getColumnCount() > 0) {
            tabela.getColumnModel().getColumn(0).setResizable(false);
            tabela.getColumnModel().getColumn(0).setPreferredWidth(200);
            tabela.getColumnModel().getColumn(1).setResizable(false);
        }

        pr.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 280, 203));

        deletaP.setText("Deletar linha:");
        deletaP.setToolTipText("Selecione a linha da tabela a ser deletada na tela e no banco");
        deletaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletaPActionPerformed(evt);
            }
        });
        pr.add(deletaP, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, -1, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Produtos");
        pr.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 80, 40));
        pr.add(linha, new org.netbeans.lib.awtextra.AbsoluteConstraints(228, 250, 50, 30));

        AtualizaP.setText("Atualizar Dados");
        AtualizaP.setToolTipText("Clicar duas vezes da tabela para habilitar edição");
        AtualizaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtualizaPActionPerformed(evt);
            }
        });
        pr.add(AtualizaP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, -1, 30));

        getContentPane().add(pr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 430));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Fornecedores");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fornActionPerformed
        if (!" ".equals(String.valueOf(forn.getSelectedItem()))) {
            pr.setVisible(true);
            ip.setVisible(true);
            rowCount = 0;
            ((DefaultTableModel) tabela.getModel()).setRowCount(rowCount);
            AdicionaTabela();
            pegaDados();
        }

    }//GEN-LAST:event_fornActionPerformed

    private void AtualizaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtualizaPActionPerformed
        ConfirmaADP();

    }//GEN-LAST:event_AtualizaPActionPerformed

    private void deletaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletaPActionPerformed
        ConfirmaDDP();

    }//GEN-LAST:event_deletaPActionPerformed

    private void delFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delFActionPerformed
        ConfirmaDF();
    }//GEN-LAST:event_delFActionPerformed

    private void AtualizaFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtualizaFornActionPerformed
        ConfirmaAF();
    }//GEN-LAST:event_AtualizaFornActionPerformed

    private void nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AtualizaForn;
    private javax.swing.JButton AtualizaP;
    private javax.swing.JTextField cidade;
    private javax.swing.JFormattedTextField cnpj;
    private javax.swing.JFormattedTextField dataNasc;
    private javax.swing.JButton delF;
    private javax.swing.JButton deletaP;
    private javax.swing.JTextArea descri;
    private javax.swing.JTextField email;
    private javax.swing.JTextField estado;
    private javax.swing.JComboBox<String> forn;
    private javax.swing.JFormattedTextField inscE;
    private javax.swing.JPanel ip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField linha;
    private javax.swing.JFormattedTextField nome;
    private javax.swing.JPanel pr;
    private javax.swing.JTable tabela;
    private javax.swing.JFormattedTextField telefone;
    // End of variables declaration//GEN-END:variables
}
