/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.estoque;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.produto.Produtos;
/**
 *
 * @author Hiury
 */
public class TelaEstoque extends javax.swing.JInternalFrame {
    public int id;
    /** Creates new form Teste */
    public TelaEstoque(int i) {
        initComponents();
        id = i;
        try {
            this.setMaximum(true);
            
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaEstoque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        estoque = new javax.swing.JButton();
        telaComprar = new javax.swing.JDesktopPane();
        Produtos = new javax.swing.JButton();

        jScrollPane1.setViewportView(jEditorPane1);

        setClosable(true);
        setMinimumSize(new java.awt.Dimension(651, 485));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        estoque.setBackground(new java.awt.Color(0, 102, 204));
        estoque.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        estoque.setForeground(new java.awt.Color(255, 255, 255));
        estoque.setText("Estoque");
        estoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estoqueActionPerformed(evt);
            }
        });
        getContentPane().add(estoque, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 0, 373, 125));

        javax.swing.GroupLayout telaComprarLayout = new javax.swing.GroupLayout(telaComprar);
        telaComprar.setLayout(telaComprarLayout);
        telaComprarLayout.setHorizontalGroup(
            telaComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 747, Short.MAX_VALUE)
        );
        telaComprarLayout.setVerticalGroup(
            telaComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 582, Short.MAX_VALUE)
        );

        getContentPane().add(telaComprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 124, 747, 582));

        Produtos.setBackground(new java.awt.Color(0, 102, 204));
        Produtos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Produtos.setForeground(new java.awt.Color(255, 255, 255));
        Produtos.setText("Produtos");
        Produtos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProdutosActionPerformed(evt);
            }
        });
        getContentPane().add(Produtos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 374, 125));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProdutosActionPerformed
        Produtos produto = new Produtos(id);
        telaComprar.add(produto);
        produto.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_ProdutosActionPerformed

    private void estoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estoqueActionPerformed
        Estoque est = new Estoque();
        telaComprar.add(est);
        est.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_estoqueActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Produtos;
    private javax.swing.JButton estoque;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JDesktopPane telaComprar;
    // End of variables declaration//GEN-END:variables

}
