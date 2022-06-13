/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fornecedor;

import br.com.dal_connexao.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Hiury
 */
public class CadastroForn extends javax.swing.JInternalFrame {

    /**
     * Creates new form CadastroForn
     */
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Validacao valida = new Validacao();

    public CadastroForn() {
        initComponents();
        conexao = ModuloConexao.conector();
        ImageIcon icon = new ImageIcon("src/img/Stocker_blue_transp.png");
        icon.setImage(icon.getImage().getScaledInstance(278, 112, 1));
        Logo2.setIcon(icon);

    }

    public void inserirFornecedor() {
        String sql = "insert into fornecedor(nome, cnpj, inscE,cidade, estado, descricao, telefone, email) values(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, edtNome.getText());
            pst.setString(2, edtCnpj.getText().replaceAll("[^0-9]+", ""));
            pst.setString(3, inscE.getText().replaceAll("[^0-9]+", ""));
            pst.setString(4, cbCidade.getText());
            pst.setString(5, String.valueOf(estado.getSelectedItem()));
            pst.setString(6, descri.getText());
            pst.setString(7, telefone.getText().replaceAll("[^0-9]+", ""));
            pst.setString(8, email.getText());

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public boolean validacaoField() {
        boolean confirma = true;

        if (edtNome.getText().isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo nome está vazio.");

        } else if (edtCnpj.getText().replaceAll("[^0-9]+", "").isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo cpf está vazio.");

        } else if (inscE.getText().replaceAll("[^0-9]+", "").isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo Inscrição Estadual está vazio.");

        } else if (email.getText().isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo email está vazio.");

        } else if (cbCidade.getText().isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo cidade está vazio");

        } else if (telefone.getText().replaceAll("[^0-9]+", "").isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo telefone está vazio.");

        } else if (descri.getText().isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo descrição está vazio.");

        } else {
            confirma = true;
        }

        return confirma;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        edtCnpj = new javax.swing.JFormattedTextField();
        estado = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbCidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        edtNome = new javax.swing.JFormattedTextField();
        inscE = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        Logo2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Cad = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        descri = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        telefone = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(745, 582));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        try {
            edtCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        edtCnpj.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(edtCnpj, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 220, 35));

        estado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        estado.setMaximumRowCount(4);
        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acre (AC)", "Alagoas (AL)", "Amapá (AP)", "Amazonas (AM)", "Bahia (BA)", "Ceará (CE)", "Distrito Federal (DF)", "Espírito Santo (ES)", "Goiás (GO)", "Maranhão (MA)", "Mato Grosso (MT)", "Mato Grosso do Sul (MS)", "Minas Gerais (MG)", "Pará (PA)", "Paraíba (PB)", "Paraná (PR)", "Pernambuco (PE)", "Piauí (PI)", "Rio de Janeiro (RJ)", "Rio Grande do Norte (RN)", "Rio Grande do Sul (RS)", "Rondônia (RO)", "Roraima (RR)", "Santa Catarina (SC)", "São Paulo (SP)", "Sergipe (SE)", "Tocantins (TO)" }));
        estado.setSelectedIndex(12);
        getContentPane().add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 220, 35));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Inscrição Estadual");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, -1, -1));
        getContentPane().add(cbCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, 220, 35));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Telefone:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 87, -1));

        edtNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(edtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 220, 35));

        try {
            inscE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###.###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inscE.setText("   .   .   .");
        inscE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(inscE, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, 220, 35));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Email:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 220, 35));

        Logo2.setText("jLabel1");
        getContentPane().add(Logo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 0, 278, 112));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Descrição");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, -1, -1));

        Cad.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Cad.setText("Cadastrar");
        Cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadActionPerformed(evt);
            }
        });
        getContentPane().add(Cad, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 458, 145, 41));

        descri.setColumns(20);
        descri.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        descri.setLineWrap(true);
        descri.setRows(5);
        descri.setWrapStyleWord(true);
        jScrollPane1.setViewportView(descri);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 460, 90));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Nome:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 67, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("CNPJ:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 67, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Cidade:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, -1, -1));

        try {
            telefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        telefone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        telefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefoneActionPerformed(evt);
            }
        });
        getContentPane().add(telefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 220, 35));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Estado:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadActionPerformed
        if (validacaoField()) {
            if (valida.validaCNPJ(edtCnpj.getText().replaceAll("[^0-9]+", ""))) {
                inserirFornecedor();

            } else {
                JOptionPane.showMessageDialog(null, "CNPJ invalido", "ERRO", 0);
            }
        }


    }//GEN-LAST:event_CadActionPerformed

    private void telefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefoneActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cad;
    private javax.swing.JLabel Logo2;
    private javax.swing.JTextField cbCidade;
    private javax.swing.JTextArea descri;
    private javax.swing.JFormattedTextField edtCnpj;
    private javax.swing.JFormattedTextField edtNome;
    private javax.swing.JTextField email;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JFormattedTextField inscE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField telefone;
    // End of variables declaration//GEN-END:variables
}
