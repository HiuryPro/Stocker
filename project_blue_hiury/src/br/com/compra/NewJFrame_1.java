/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.compra;

import java.sql.*;
import br.com.dal_connexao.ModuloConexao;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.Vector;
import java.util.Date;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import br.com.compra.ItemCompra;
import java.util.ArrayList;

/**
 *
 * @author samue
 */
public class NewJFrame_1 extends javax.swing.JFrame {

    Connection conexao = null;
    List lista = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    compraData objcompra;
    Fornecedor objfornecedor;
    Date data;
    Integer IdNome;

    private boolean inserir() {
        Fornecedor forne = new Fornecedor();

        try {

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        String sql = "insert into notas_fiscal_entrada(numero_nf, chave_nf, id_fornecedor, valor_frete, "
                + "data_emissao) value(?, ?, ? ,?, ?) ";
        try {

            pst = conexao.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(edtNumNf.getText()));
            pst.setInt(2, Integer.parseInt(edtChaveNf.getText()));
            pst.setInt(3, cbfornecedo.getSelectedIndex());
            pst.setFloat(4, Float.parseFloat(edtValorFre.getText()));
            pst.setString(5, edtDataInicial.getText());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return true;
    }
    
    public void IdProdutoCompra(){
     String sql = "select id from produto_compra";   
    }
    
    public void procurarNome(){
        String sql ="select id from produtos where nome = ?";
        
        try {
          pst = conexao.prepareStatement(sql);
          pst.setString(1, edtNome.getText());
          
          rs = pst.executeQuery();
          
          while(rs.next()){
              
              this.IdNome = rs.getInt("id");
          }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void inserirItem() {
        String sql = "insert into produto_compra(id_nota_fiscal, id_produto, quantidade, unitario, total) value("
                + "?, ?, ?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, 0);
            pst.setInt(2, IdNome);
            pst.setFloat(3, Float.parseFloat(edtQuantidade.getText()));
            pst.setFloat(4, Float.parseFloat(edtValor.getText()));
            pst.setFloat(5, Float.parseFloat(edtTotal.getText()));

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public List<ItemCompra> Listar() {
        String sql = "select id_nota_fiscal, P.nome, quantidade, unitario, total from produto_compra inner join produtos as P on id_produto = P.id where id_nota_fiscal = ?;";
        List<ItemCompra> item = new ArrayList<>();
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, 0);

            rs = pst.executeQuery();

            while (rs.next()) {

                ItemCompra items = new ItemCompra();

                items.setIdNota(rs.getInt("id_nota_fiscal"));
                items.setNomeProduto(rs.getString("nome"));
                items.setQuantidade(rs.getFloat("quantidade"));
                items.setUnitario(rs.getFloat("unitario"));
                items.setTotal(rs.getFloat("total"));

                item.add(items);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return item;
    }

    private void carregarTabela() {
        
        DefaultTableModel modelo = (DefaultTableModel) tbItem.getModel();
        modelo.setNumRows(0);

        try {
            for(ItemCompra p: Listar()) {
                modelo.addRow(new Object[]{
                    p.getNomeProduto(),
                    p.getUnitario(),
                    p.getQuantidade(),
                    p.getTotal()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    Vector<Fornecedor> fornecedor;
    compraData DAO;

    public NewJFrame_1() {
        initComponents();
        ifEntrada.setVisible(false);

        try {

            DAO = new compraData();
            objcompra = new compraData();
            objfornecedor = new Fornecedor();
            fornecedor = DAO.listarFornecedor();
            cbfornecedo.setModel(new DefaultComboBoxModel(fornecedor));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jToolBar1 = new javax.swing.JToolBar();
        btEntrada = new javax.swing.JButton();
        ifEntrada = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        edtNumNf = new javax.swing.JTextField();
        edtChaveNf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        edtDataInicial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        edtDataEntrada = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        edtValorFre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btSalavr = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbItem = new javax.swing.JTable();
        edtQuantidade = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        edtValor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btInserir = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        edtTotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cbfornecedo = new javax.swing.JComboBox();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        edtNome = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(805, 588));

        jToolBar1.setRollover(true);

        btEntrada.setText("Entrada");
        btEntrada.setFocusable(false);
        btEntrada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btEntrada.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEntradaActionPerformed(evt);
            }
        });
        jToolBar1.add(btEntrada);

        ifEntrada.setVisible(true);

        jLabel1.setText("Numero NF");

        edtChaveNf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtChaveNfActionPerformed(evt);
            }
        });

        jLabel2.setText("Chave NF");

        edtDataInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtDataInicialActionPerformed(evt);
            }
        });

        jLabel3.setText("Data De Emissão");

        jLabel4.setText("Data De Entrada");

        jLabel5.setText("Fornecedor");

        edtValorFre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtValorFreActionPerformed(evt);
            }
        });

        jLabel6.setText("Valor Frete");

        btSalavr.setText("Salvar");
        btSalavr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalavrActionPerformed(evt);
            }
        });

        btEditar.setText("Editar");

        tbItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Produto", "Descrição", "QNT.", "Preço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbItem.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tbItem);
        tbItem.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbItem.getColumnModel().getColumnCount() > 0) {
            tbItem.getColumnModel().getColumn(0).setResizable(false);
        }

        edtQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtQuantidadeActionPerformed(evt);
            }
        });

        jLabel9.setText("Quantidade");

        jLabel10.setText("Valor");

        btInserir.setText("Inserir");
        btInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInserirActionPerformed(evt);
            }
        });

        btSair.setText("Sair");

        btExcluir.setText("Excluir");

        edtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtTotalActionPerformed(evt);
            }
        });

        jLabel11.setText("Total");

        cbfornecedo.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                cbfornecedoAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        cbfornecedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbfornecedoActionPerformed(evt);
            }
        });

        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Nome Produto");

        javax.swing.GroupLayout ifEntradaLayout = new javax.swing.GroupLayout(ifEntrada.getContentPane());
        ifEntrada.getContentPane().setLayout(ifEntradaLayout);
        ifEntradaLayout.setHorizontalGroup(
            ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ifEntradaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(ifEntradaLayout.createSequentialGroup()
                            .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(ifEntradaLayout.createSequentialGroup()
                                    .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(edtNumNf))
                                    .addGap(18, 18, 18)
                                    .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(edtChaveNf, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)))
                                .addGroup(ifEntradaLayout.createSequentialGroup()
                                    .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbfornecedo, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(ifEntradaLayout.createSequentialGroup()
                                    .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(edtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(edtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)))
                                .addGroup(ifEntradaLayout.createSequentialGroup()
                                    .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addGroup(ifEntradaLayout.createSequentialGroup()
                                            .addComponent(edtValorFre, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btEditar)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btExcluir)))
                                    .addGap(11, 11, 11))))
                        .addGroup(ifEntradaLayout.createSequentialGroup()
                            .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(ifEntradaLayout.createSequentialGroup()
                                    .addComponent(btSalavr)
                                    .addGap(38, 38, 38)
                                    .addComponent(btSair)))
                            .addGap(11, 11, 11)))
                    .addGroup(ifEntradaLayout.createSequentialGroup()
                        .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(edtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(ifEntradaLayout.createSequentialGroup()
                                .addComponent(edtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ifEntradaLayout.setVerticalGroup(
            ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ifEntradaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtNumNf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtChaveNf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ifEntradaLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbfornecedo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ifEntradaLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtValorFre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ifEntradaLayout.createSequentialGroup()
                        .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btSalavr, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(198, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ifEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ifEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(436, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEntradaActionPerformed
        // TODO add your handling code here:
        ifEntrada.setVisible(true);
        conexao = ModuloConexao.conector();
    }//GEN-LAST:event_btEntradaActionPerformed

    private void edtDataInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtDataInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtDataInicialActionPerformed

    private void edtChaveNfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtChaveNfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtChaveNfActionPerformed

    private void edtQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtQuantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtQuantidadeActionPerformed

    private void edtValorFreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtValorFreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtValorFreActionPerformed

    private void edtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtTotalActionPerformed

    private void cbfornecedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbfornecedoActionPerformed

    }//GEN-LAST:event_cbfornecedoActionPerformed

    private void btSalavrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalavrActionPerformed
        // TODO add your handling code here:

        inserir();
    }//GEN-LAST:event_btSalavrActionPerformed

    private void cbfornecedoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_cbfornecedoAncestorAdded
        // TODO add your handling code here:
        //lista();
    }//GEN-LAST:event_cbfornecedoAncestorAdded

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void btInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInserirActionPerformed
        // TODO add your handling code here:

        procurarNome();
        inserirItem();
        carregarTabela();
    }//GEN-LAST:event_btInserirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame_1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEntrada;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btInserir;
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSalavr;
    private javax.swing.JComboBox cbfornecedo;
    private javax.swing.JTextField edtChaveNf;
    private javax.swing.JTextField edtDataEntrada;
    private javax.swing.JTextField edtDataInicial;
    private javax.swing.JTextField edtNome;
    private javax.swing.JTextField edtNumNf;
    private javax.swing.JTextField edtQuantidade;
    private javax.swing.JTextField edtTotal;
    private javax.swing.JTextField edtValor;
    private javax.swing.JTextField edtValorFre;
    private javax.swing.JInternalFrame ifEntrada;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tbItem;
    // End of variables declaration//GEN-END:variables
}
