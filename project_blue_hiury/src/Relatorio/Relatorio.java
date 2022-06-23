/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Relatorio;

import br.com.dal_connexao.ModuloConexao;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Hiury
 */
public class Relatorio {

    Connection con, con1, con2, conexao;
    PreparedStatement pst;
    ResultSet rs = null;
    ResultSet rs1 = null;
    Statement st;

    public String data;
    public String data2;
    public int qtdTotal;
    public float valorTotal;

    public Relatorio() {

        conexao = ModuloConexao.conector();
        resetaRelatorio();
    }

    public String caminho() {
        Path path = Paths.get("");
        String directoryName = path.toAbsolutePath().normalize().toString();
        System.out.println("Current Working Directory is = " + directoryName);
        return directoryName;
    }

    public void criaRelatorio(JTextField deData, JTextField ateData) {
        try {
            data = deData.getText();
            data2 = ateData.getText();

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocker", "root", "");

            JasperDesign jDesign = JRXmlLoader.load(caminho() + "\\src\\Relatorio\\report1.jrxml");
            String sql = "SELECT * FROM produto_compra where (data_entrada BETWEEN STR_TO_DATE('" + deData.getText() + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + ateData.getText() + " ', \"%d/%m/%Y\"))ORDER BY data_entrada"; // or  (data BETWEEN STR_TO_DATE('" + deData.getText() + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + ateData.getText() + " ', \"%d/%m/%Y\"))";
            resetaRelatorio();
            distinguiR(1);

            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(sql);
            jDesign.setQuery(updateQuery);

            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            HashMap<String, Object> hm = new HashMap<>();

            hm.put("total1", String.valueOf(qtdTotal));
            hm.put("total2", String.valueOf(valorTotal));
            hm.put("image", caminho() + "\\src\\Relatorio\\Stocker_blue_transp.png");
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, hm, con);
            JasperViewer.viewReport(jPrint, false);

// TODO add your handling code here:
        } catch (JRException ex) {
            Logger.getLogger(Relat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Relat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Relat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void criaRelatorio2(JTextField deData, JTextField ateData) {
        try {
            data = deData.getText();
            data2 = ateData.getText();

            Class.forName("com.mysql.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocker", "root", "");

            JasperDesign jDesign = JRXmlLoader.load(caminho() + "\\src\\Relatorio\\relatorio.jrxml");
            String sql = "SELECT * FROM produto_venda where (data_saida BETWEEN STR_TO_DATE('" + deData.getText() + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + ateData.getText() + " ', \"%d/%m/%Y\")) ORDER BY data_saida"; // or  (data BETWEEN STR_TO_DATE('" + deData.getText() + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + ateData.getText() + " ', \"%d/%m/%Y\"))";
            resetaRelatorio();
            distinguiR(0);

            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(sql);
            jDesign.setQuery(updateQuery);

            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            HashMap<String, Object> hm = new HashMap<>();

            hm.put("total1", String.valueOf(qtdTotal));
            hm.put("total2", String.valueOf(valorTotal));
            hm.put("image", caminho() + "\\src\\Relatorio\\Stocker_blue_transp.png");

            JasperPrint jPrint = JasperFillManager.fillReport(jReport, hm, con1);
            JasperViewer.viewReport(jPrint, false);

// TODO add your handling code here:
        } catch (JRException ex) {
            Logger.getLogger(Relat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Relat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Relat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void criaRelatorio3(JTextField deData, JTextField ateData) {
        try {
            data = deData.getText();
            data2 = ateData.getText();

            Class.forName("com.mysql.jdbc.Driver");
            con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocker", "root", "");

            JasperDesign jDesign = JRXmlLoader.load(caminho() + "\\src\\Relatorio\\report2.jrxml");
            String sql = "SELECT *,date_format(data, '%d') as teste FROM varia_estoque where (data BETWEEN STR_TO_DATE('" + deData.getText() + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + ateData.getText() + " ', \"%d/%m/%Y\")) ORDER BY data"; // or  (data BETWEEN STR_TO_DATE('" + deData.getText() + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + ateData.getText() + " ', \"%d/%m/%Y\"))";

            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(sql);
            jDesign.setQuery(updateQuery);

            JasperReport jReport = JasperCompileManager.compileReport(jDesign);

            String para = deData.getText();
            String para2 = ateData.getText();
            HashMap<String, Object> hm = new HashMap<>();
            java.util.Date utilDate, utilDate2;

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                utilDate = format.parse(deData.getText());
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                utilDate2 = format.parse(ateData.getText());
                java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());

                hm.put("query", data);
                hm.put("query2", data2);
                hm.put("image", caminho() + "\\src\\Relatorio\\Stocker_blue_transp.png");
                JasperPrint jPrint = JasperFillManager.fillReport(jReport, hm, con2);
                JasperViewer.viewReport(jPrint, false);

            } catch (ParseException ex) {
                Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
            }

// TODO add your handling code here:
        } catch (JRException ex) {
            Logger.getLogger(Relat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Relat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Relat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void distinguiR(int id) {

        String sql = "SELECT nome_produto FROM produto_venda";
        try {

            st = conexao.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                pegaRel(id, rs.getString("nome_produto"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pegaRel(int id, String nomeP) {

        if (id == 0) {

            String sql = "SELECT SUM(quantidade), SUM(total), nome_produto FROM produto_venda where (data_saida BETWEEN STR_TO_DATE('" + data + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + data2 + " ', \"%d/%m/%Y\")) and nome_produto = '" + nomeP + "'";
            try {

                st = conexao.createStatement();
                rs1 = st.executeQuery(sql);

                while (rs1.next()) {
                    relatoriototal(rs1.getString("nome_produto"), rs1.getInt("SUM(quantidade)"), rs1.getFloat("SUM(total)"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
            }

            sql = "SELECT SUM(quantidade), SUM(total) FROM produto_venda where (data_saida BETWEEN STR_TO_DATE('" + data + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + data2 + " ', \"%d/%m/%Y\"))";
            try {

                st = conexao.createStatement();
                rs1 = st.executeQuery(sql);

                while (rs1.next()) {
                    qtdTotal = rs1.getInt("SUM(quantidade)");
                    valorTotal = rs1.getFloat("SUM(total)");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (id == 1) {

            String sql = "SELECT SUM(quantidadeC), SUM(totalC), produtoC FROM produto_compra where (data_entrada BETWEEN STR_TO_DATE('" + data + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + data2 + " ', \"%d/%m/%Y\")) and produtoC = '" + nomeP + "'";
            try {

                st = conexao.createStatement();
                rs1 = st.executeQuery(sql);

                while (rs1.next()) {
                    relatoriototal(rs1.getString("produtoC"), rs1.getInt("SUM(quantidadeC)"), rs1.getFloat("SUM(totalC)"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
            }

            sql = "SELECT SUM(quantidadeC) as teste1, SUM(totalC) as teste2 FROM produto_compra where (data_entrada BETWEEN STR_TO_DATE('" + data + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + data2 + " ', \"%d/%m/%Y\"))";
            try {

                st = conexao.createStatement();
                rs1 = st.executeQuery(sql);

                while (rs1.next()) {
                    qtdTotal = rs1.getInt("teste1");
                    valorTotal = rs1.getFloat("teste2");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void resetaRelatorio() {
        String sql = "UPDATE relatoriototal SET qtd_total = ? , preco_total = ?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, 0);
            pst.setFloat(2, 0);

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void relatoriototal(String nomeP, int qtdTotal, float valorTotal) {

        int count, qtd2;

        String sql = "UPDATE relatoriototal SET qtd_total = ? , preco_total = ? WHERE nome_produto = '" + nomeP + "'";
        try {

            pst = conexao.prepareStatement(sql);
            pst.setInt(1, qtdTotal);
            pst.setFloat(2, valorTotal);

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
}
