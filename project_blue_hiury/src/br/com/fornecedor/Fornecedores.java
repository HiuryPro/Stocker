/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fornecedor;

import java.sql.*;
import br.com.dal_connexao.ModuloConexao;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Hiury
 */
public class Fornecedores extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs2 = null;
    ResultSet rs = null;
    ResultSet rs3 = null;
    Statement st2;
    Statement st3;
    Statement st;
    public String nomes;
    Validacao altera = new Validacao();
    public int id;

    /**
     * Creates new form Fornecedores
     */

    public Fornecedores(int i1) {
        int i;
        id = i1;

      
        initComponents();
        cad.setText("<html>Cadastrar<br>Fornecedor</html>");
        cadP.setText("<html>Cadastrar<br>Fornecedor-Produto</html>");
        fornecedor.setText("<html>Dados<br>Fornecedor</html>");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        cadP = new javax.swing.JButton();
        cad = new javax.swing.JButton();
        fornecedor = new javax.swing.JButton();
        telaF = new javax.swing.JDesktopPane();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cadP.setBackground(new java.awt.Color(0, 102, 204));
        cadP.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cadP.setForeground(new java.awt.Color(255, 255, 255));
        cadP.setText("Fornecedor-Produto");
        cadP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadPActionPerformed(evt);
            }
        });
        getContentPane().add(cadP, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 0, 248, 125));

        cad.setBackground(new java.awt.Color(0, 102, 204));
        cad.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cad.setForeground(new java.awt.Color(255, 255, 255));
        cad.setText("Cadastrar Fornecedor");
        cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadActionPerformed(evt);
            }
        });
        getContentPane().add(cad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 248, 125));

        fornecedor.setBackground(new java.awt.Color(0, 102, 204));
        fornecedor.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        fornecedor.setForeground(new java.awt.Color(255, 255, 255));
        fornecedor.setText("Fornecedor");
        fornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fornecedorActionPerformed(evt);
            }
        });
        getContentPane().add(fornecedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 0, 248, 125));

        javax.swing.GroupLayout telaFLayout = new javax.swing.GroupLayout(telaF);
        telaF.setLayout(telaFLayout);
        telaFLayout.setHorizontalGroup(
            telaFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 745, Short.MAX_VALUE)
        );
        telaFLayout.setVerticalGroup(
            telaFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        getContentPane().add(telaF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 125, 745, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cadPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadPActionPerformed


    }//GEN-LAST:event_cadPActionPerformed

    private void cadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadActionPerformed
         CadastroForn cad = new CadastroForn();
        telaF.add(cad);
        cad.setVisible(true);
    }//GEN-LAST:event_cadActionPerformed

    private void fornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fornecedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cad;
    private javax.swing.JButton cadP;
    private javax.swing.JButton fornecedor;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDesktopPane telaF;
    // End of variables declaration//GEN-END:variables
}
