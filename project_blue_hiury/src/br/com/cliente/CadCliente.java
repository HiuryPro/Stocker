/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cliente;

import br.com.dal_connexao.ModuloConexao;
import br.com.fornecedor.Validacao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author Hiury
 */
public class CadCliente extends javax.swing.JInternalFrame {

    /**
     * Creates new form CadCliente
     */
    Cliente cliente = new Cliente();
    private String sql;
    private PreparedStatement pst;
    private ResultSet rs;
    Connection conexao = null;
    Integer id = null;
    Validacao val = new Validacao();

    public CadCliente() {
        initComponents();
        conexao = ModuloConexao.conector();

    }

    public String caminho() {
        Path path = Paths.get("");
        String directoryName = path.toAbsolutePath().normalize().toString();
        System.out.println("Current Working Directory is = " + directoryName);
        return directoryName;
    }

    public void cidades() {
        File arquivo = new File(caminho() + "\\src\\br\\com\\cliente\\" + String.valueOf(cbEstado.getSelectedItem()) + ".txt");

        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha = br.readLine();

            while (linha != null) {
                // cbCidade.addItem(linha);
                linha = br.readLine();
            }
        } catch (IOException e) {
            System.err.printf("Erro na leitura do Arquivo: %s.\n",
                    e.getMessage());
        }
    }

    public void inserir() {

        String sql = null;
        sql = "insert into cliente(nome, cnpj, descricao, categoria, faixaR, estado, cidade, telefone, email, endereco) value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, edtNome.getText());
            pst.setString(2, edtCpf.getText().replaceAll("[^0-9]+", ""));
            pst.setString(3, descricao.getText());
            pst.setString(4, edtCategoria.getText());
            pst.setFloat(5, Float.parseFloat(edtFaixa.getText()));
            pst.setString(6, String.valueOf(cbEstado.getSelectedItem()));
            pst.setString(7, cbCidade.getText());
            pst.setString(8, telefone.getText().replaceAll("[^0-9]+", ""));
            pst.setString(9, email.getText());
            pst.setString(10, endereco.getText());

            pst.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    public void Confirma() {
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja Cadastrar o Cliente", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            if (validacaoField()) {
                if (val.isCPF(edtCpf.getText().replaceAll("[^0-9]+", ""))) {
                    inserir();
                    JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso.");
                    cbCidade.setText(null);
                    descricao.setText(null);
                    edtCategoria.setText(null);
                    edtCpf.setText(null);
                    edtFaixa.setText(null);
                    edtNome.setText(null);
                    email.setText(null);
                    endereco.setText(null);
                    telefone.setText(null);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Cpf invalido");
                }
            }
        } else {

        }
    }

    public boolean validacaoField() {
        boolean confirma = true;

        if (edtNome.getText().isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo nome está vazio.");

        } else if (edtCpf.getText().replaceAll("[^0-9]+", "").isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo cpf está vazio.");

        } else if (descricao.getText().isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo descrição está vazio.");

        } else if (edtCategoria.getText().isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo categoria está vazio.");

        } else if (edtFaixa.getText().isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo faixa de renda esrá vazio.");

        } else if (cbCidade.getText().isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo cidade está vazio");

        } else if (telefone.getText().replaceAll("[^0-9]+", "").isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo telefone está vazio.");

        } else if (email.getText().isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo email está vazio.");

        } else if (endereco.getText().isEmpty()) {
            confirma = false;
            JOptionPane.showMessageDialog(null, "Campo endereço está vazio.");

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

        jLabel1 = new javax.swing.JLabel();
        CPF = new javax.swing.JLabel();
        edtFaixa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        edtCategoria = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox<>();
        edtNome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricao = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        edtCpf = new javax.swing.JFormattedTextField();
        Cad = new javax.swing.JButton();
        email = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        telefone = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        cbCidade = new javax.swing.JTextField();
        endereco = new javax.swing.JTextField();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Nome");

        CPF.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        CPF.setText("CPF");

        edtFaixa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Faixa De Renda");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Descrição");

        edtCategoria.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Categoria");

        cbEstado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acre ", "Alagoas ", "Amapá ", "Amazonas ", "Bahia ", "Ceará ", "Distrito Federal", "Espírito Santo ", "Goiás ", "Maranhão ", "Mato Grosso ", "Mato Grosso do Sul ", "Minas Gerais", "Pará ", "Paraíba ", "Paraná ", "Pernambuco ", "Piauí ", "Rio de Janeiro ", "Rio Grande do Norte ", "Rio Grande do Sul ", "Rondônia ", "Roraima ", "Santa Catarina ", "São Paulo ", "Sergipe ", "Tocantins ", " " }));
        cbEstado.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                cbEstadoAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        cbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoActionPerformed(evt);
            }
        });

        edtNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        descricao.setColumns(20);
        descricao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        descricao.setRows(5);
        jScrollPane1.setViewportView(descricao);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Cidade");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Estado");

        try {
            edtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        edtCpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Cad.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Cad.setText("Cadastrar");
        Cad.setToolTipText("Salva no banco de dados os dados da tabela acima");
        Cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadActionPerformed(evt);
            }
        });

        email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Email");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Telefone");

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

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Endereço:");

        cbCidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        endereco.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        endereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enderecoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(34, 34, 34))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addGap(247, 247, 247)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(CPF)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3)
                                            .addGap(75, 75, 75))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(101, 101, 101))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(edtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(58, 58, 58)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(edtFaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(endereco, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel10)))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(edtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(67, 67, 67))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jLabel5)
                                                    .addGap(184, 184, 184)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel8)
                                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(230, 230, 230)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(Cad, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(CPF)
                            .addComponent(jLabel3))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtFaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(endereco, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(Cad, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbEstadoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_cbEstadoAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoAncestorAdded

    private void cbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoActionPerformed
        // TODO add your handling code here:
        cliente.estado(cbEstado.getSelectedItem().toString());
        // cidades();

    }//GEN-LAST:event_cbEstadoActionPerformed

    private void CadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadActionPerformed
        Confirma();
    }//GEN-LAST:event_CadActionPerformed

    private void telefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefoneActionPerformed

    private void enderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enderecoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CPF;
    private javax.swing.JButton Cad;
    private javax.swing.JTextField cbCidade;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JTextArea descricao;
    private javax.swing.JTextField edtCategoria;
    private javax.swing.JFormattedTextField edtCpf;
    private javax.swing.JTextField edtFaixa;
    private javax.swing.JTextField edtNome;
    private javax.swing.JTextField email;
    private javax.swing.JTextField endereco;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField telefone;
    // End of variables declaration//GEN-END:variables
}
