package stocker;

import java.awt.Dimension;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author hiurylucas
 */
public class PegaImagem {

    public JFrame telaImg = new JFrame();

    public PegaImagem() {
        telaImg.setSize(600, 400);
        telaImg.setTitle("Arquivo");
        telaImg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaImg.setLayout(null);

        telaImg.setVisible(true);
        telaImg.setResizable(false);
        telaImg.setPreferredSize(new Dimension(730, 500));
        telaImg.add(escolheArquivo());
        

    }

    public JFileChooser escolheArquivo() {
        JFileChooser teste = new JFileChooser();
        teste.setLocation(0,0);
        teste.setSize(500,300);
        return teste;
    }
}
