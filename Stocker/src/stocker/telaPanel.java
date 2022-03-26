/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocker;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author hiurylucas
 */
public class telaPanel {
    
  
    public JPanel tela1;
    public JLabel teste1;
    
    public void criaTela1(){
        tela1 = new JPanel();
        tela1.setLayout(new GridLayout(2,2));
        tela1.setSize(600,500);
        tela1.setLocation(150,0);
        tela1.setVisible(true);
        teste1.setText("Suco");
        tela1.add(teste1);
      
    }
    
}
