/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Relatorio;

import br.com.dal_connexao.ModuloConexao;
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

    Connection con, con1, con2;
    PreparedStatement pst;

    public void criaRelatorio(JTextField deData, JTextField ateData) {
        try {

            String url = "jdbc:mysql://localhost:3306/stocker";
            String user = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocker", "root", "");

            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\Hiury\\Documents\\Git-Projeto\\Stocker\\project_blue_hiury\\src\\Relatorio\\report1.jrxml");
            String sql = "SELECT * FROM produto_compra where (data_entrada BETWEEN STR_TO_DATE('" + deData.getText() + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + ateData.getText() + " ', \"%d/%m/%Y\"))"; // or  (data BETWEEN STR_TO_DATE('" + deData.getText() + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + ateData.getText() + " ', \"%d/%m/%Y\"))";

            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(sql);
            jDesign.setQuery(updateQuery);

            JasperReport jReport = JasperCompileManager.compileReport(jDesign);

            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, con);
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

            String url = "jdbc:mysql://localhost:3306/stocker";
            String user = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocker", "root", "");

            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\Hiury\\Documents\\Git-Projeto\\Stocker\\project_blue_hiury\\src\\Relatorio\\relatorio.jrxml");
            String sql = "SELECT * FROM produto_venda where (data_saida BETWEEN STR_TO_DATE('" + deData.getText() + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + ateData.getText() + " ', \"%d/%m/%Y\"))"; // or  (data BETWEEN STR_TO_DATE('" + deData.getText() + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + ateData.getText() + " ', \"%d/%m/%Y\"))";

            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(sql);
            jDesign.setQuery(updateQuery);

            JasperReport jReport = JasperCompileManager.compileReport(jDesign);

            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, con1);
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

            String url = "jdbc:mysql://localhost:3306/stocker";
            String user = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocker", "root", "");

            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\Hiury\\Documents\\Git-Projeto\\Stocker\\project_blue_hiury\\src\\Relatorio\\report2.jrxml");
            String sql = "SELECT * FROM varia_estoque where (data BETWEEN STR_TO_DATE('" + deData.getText() + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + ateData.getText() + " ', \"%d/%m/%Y\"))"; // or  (data BETWEEN STR_TO_DATE('" + deData.getText() + " ', \"%d/%m/%Y\") AND STR_TO_DATE('" + ateData.getText() + " ', \"%d/%m/%Y\"))";

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
                
                
                hm.put("query", sqlDate);
                hm.put("query2", sqlDate2);
                
                
                JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, con2);
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

}
