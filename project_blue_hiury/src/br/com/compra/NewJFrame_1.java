/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.compra;

import br.com.compra.Fornecedor;
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
import br.com.compra.ItemCompra;
import br.com.compra.compraData;
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
    Boolean copia = true;

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

    public String validacaoField() {
        setarLable();
        if (edtNumNf.getText().isEmpty()) {
            lbNumeroNf.setText("Digite o nome da empresa");
            lbNumeroNf.setVisible(true);
            return "invalido";
        } else if (edtChaveNf.getText().isEmpty()) {
            lbChaveNf.setText("Digite o cnpj da empresa");
            lbChaveNf.setVisible(true);
            return "invalido";
        } else if (edtDataInicial.getText().isEmpty()) {
            lbData.setText("Digite o email da empresa");
            lbData.setVisible(true);
            return "invalido";
        } else if (edtNome.getText().isEmpty()) {
            lbNomeProduto.setText("Digite o nome da Cidade");
            lbNomeProduto.setVisible(true);
            return "invalido";
        } else if (edtValorFre.getText().isEmpty()) {
            lbValorFrete.setText("Digite o faturamento da empresa");
            lbValorFrete.setVisible(true);
            return "invalido";
        } else if (edtQuantidade.getText().isEmpty()) {
            lbQuantidade.setText("Digite o nome da Cidade");
            lbQuantidade.setVisible(true);
            return "invalido";
        } else if (edtValor.getText().isEmpty()) {
            lbValor.setText("Digite o nome da Cidade");
            lbValor.setVisible(true);
            return "invalido";
        } else if (edtTotal.getText().isEmpty()) {
            lbTotal.setText("Digite o nome da Cidade");
            lbTotal.setVisible(true);
            return "invalido";
        } else {
            return "valido";
        }
    }

    public void setarLable() {

        lbNumeroNf.setVisible(false);

        lbChaveNf.setVisible(false);

        lbData.setVisible(false);

        lbNomeProduto.setVisible(false);

        lbValorFrete.setVisible(false);

        lbQuantidade.setVisible(false);

        lbValor.setVisible(false);

        lbTotal.setVisible(false);
    }

    public void verificarCopia() {
        String sql = "select * from produto_compra where chave_nf = ?";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, edtNumNf.getText());

            rs = pst.executeQuery();

            if (rs.next()) {
                copia = true;
            } else {
                copia = false;
            }
        } catch (Exception e) {


        }
    }

    public void procurarNome() {
        String sql = "select id from produtos where nome = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, edtNome.getText());

            rs = pst.executeQuery();

            while (rs.next()) {

                this.IdNome = rs.getInt("id");
            }
        } catch (Exception e) {
            
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
            for (ItemCompra p : Listar()) {
                modelo.addRow(new Object[]{
                    p.getNomeProduto(),
                    p.getUnitario(),
                    p.getQuantidade(),
                    p.getTotal()
                });
            }
        } catch (Exception e) {
            
        }

    }

    Vector<Fornecedor> fornecedor;
    compraData DAO;

    public NewJFrame_1() {
        initComponents();
        ifEntrada.setVisible(false);
        setarLable();
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
        edtNome = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lbNumeroNf = new javax.swing.JLabel();
        lbChaveNf = new javax.swing.JLabel();
        lbData = new javax.swing.JLabel();
        lbNomeProduto = new javax.swing.JLabel();
        lbValorFrete = new javax.swing.JLabel();
        lbQuantidade = new javax.swing.JLabel();
        lbValor = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();

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

        jLabel7.setText("Nome Produto");

        lbNumeroNf.setText("jLabel8");

        lbChaveNf.setText("jLabel8");

        lbData.setText("jLabel8");

        lbNomeProduto.setText("jLabel4");

        lbValorFrete.setText("jLabel4");

        lbQuantidade.setText("jLabel4");

        lbValor.setText("jLabel4");

        lbTotal.setText("jLabel4");

        javax.swing.GroupLayout ifEntradaLayout = new javax.swing.GroupLayout(ifEntrada.getContentPane());
        ifEntrada.getContentPane().setLayout(ifEntradaLayout);
        ifEntradaLayout.setHorizontalGroup(
            ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ifEntradaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ifEntradaLayout.createSequentialGroup()
                        .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNumeroNf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(edtNumNf, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(edtChaveNf, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(lbChaveNf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ifEntradaLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ifEntradaLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ifEntradaLayout.createSequentialGroup()
                                        .addComponent(edtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbfornecedo, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(ifEntradaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbData, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ifEntradaLayout.createSequentialGroup()
                            .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lbQuantidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(edtQuantidade, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGap(18, 18, 18)
                            .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(edtValor, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addComponent(lbValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbTotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(edtTotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)))
                            .addGap(30, 30, 30)
                            .addComponent(btSalavr)
                            .addGap(18, 18, 18)
                            .addComponent(btSair)))
                    .addGroup(ifEntradaLayout.createSequentialGroup()
                        .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNomeProduto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ifEntradaLayout.createSequentialGroup()
                                .addComponent(edtValorFre, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btExcluir))
                            .addComponent(lbValorFrete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        ifEntradaLayout.setVerticalGroup(
            ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ifEntradaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtNumNf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtChaveNf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbfornecedo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNumeroNf)
                    .addComponent(lbChaveNf)
                    .addComponent(lbData))
                .addGap(4, 4, 4)
                .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ifEntradaLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ifEntradaLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtValorFre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNomeProduto)
                    .addComponent(lbValorFrete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(edtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btSalavr, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ifEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbQuantidade)
                    .addComponent(lbValor)
                    .addComponent(lbTotal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ifEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(ifEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(551, Short.MAX_VALUE))
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
        verificarCopia();
        if((copia == false) || (validacaoField() == "valido")){
            inserir();
        }else{
            JOptionPane.showMessageDialog(null, "Nota já cadastrada, Ou Campos vazios por favor revise os dados informados");
        }

    }//GEN-LAST:event_btSalavrActionPerformed

    private void cbfornecedoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_cbfornecedoAncestorAdded
        // TODO add your handling code here:
        //lista();
    }//GEN-LAST:event_cbfornecedoAncestorAdded

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
        //</editor-fold>
        //</editor-fold>
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
    private javax.swing.JTextField edtDataInicial;
    private javax.swing.JTextField edtNome;
    private javax.swing.JTextField edtNumNf;
    private javax.swing.JTextField edtQuantidade;
    private javax.swing.JTextField edtTotal;
    private javax.swing.JTextField edtValor;
    private javax.swing.JTextField edtValorFre;
    private javax.swing.JInternalFrame ifEntrada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbChaveNf;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lbNomeProduto;
    private javax.swing.JLabel lbNumeroNf;
    private javax.swing.JLabel lbQuantidade;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JLabel lbValor;
    private javax.swing.JLabel lbValorFrete;
    private javax.swing.JTable tbItem;
    // End of variables declaration//GEN-END:variables
}
