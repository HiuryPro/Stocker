/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.compra;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hiury
 */
public class telaVenda extends javax.swing.JInternalFrame {

    /**
     * Creates new form telaVenda
     */
    public int id;

    public telaVenda(int i) {
       
            initComponents();
            id = i;
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vender = new javax.swing.JButton();
        comprar = new javax.swing.JButton();
        telaComprar = new javax.swing.JDesktopPane();

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        vender.setBackground(new java.awt.Color(0, 102, 204));
        vender.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        vender.setForeground(new java.awt.Color(255, 255, 255));
        vender.setText("Vender");
        vender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venderActionPerformed(evt);
            }
        });
        getContentPane().add(vender, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 380, 125));

        comprar.setBackground(new java.awt.Color(0, 102, 204));
        comprar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        comprar.setForeground(new java.awt.Color(255, 255, 255));
        comprar.setText("Comprar");
        comprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprarActionPerformed(evt);
            }
        });
        getContentPane().add(comprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 125));

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

        getContentPane().add(telaComprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 125, 747, 582));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void venderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venderActionPerformed
        Venda venda = new Venda(id);
        telaComprar.add(venda);
        venda.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_venderActionPerformed

    private void comprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comprarActionPerformed
        Compra compra = new Compra(id);
        telaComprar.add(compra);
        compra.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_comprarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton comprar;
    private javax.swing.JDesktopPane telaComprar;
    private javax.swing.JButton vender;
    // End of variables declaration//GEN-END:variables
}
