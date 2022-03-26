/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package stocker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hiurylucas
 */
public class ArquivoEmJava {

    /**
     * @param args the command line arguments
     */
    public String nome, idade, email, cnpj, telefone;

    public ArrayList<String> nomes = new ArrayList();
    public ArrayList<String> idades = new ArrayList();
    public ArrayList<String> cnpjs = new ArrayList();
    public ArrayList<String> emails = new ArrayList();
    public ArrayList<String> enderecos = new ArrayList();
    public ArrayList<String> telefones = new ArrayList();

    public void CriaArquivo() {
        try {
            File myObj = new File("src/arquivo/nome.txt");
            File myObj2 = new File("src/arquivo/idade.txt");
            File myObj3 = new File("src/arquivo/email.txt");
            File myObj4 = new File("src/arquivo/telefone.txt");
            File myObj5 = new File("src/arquivo/cnpj.txt");
            if (myObj.createNewFile()) {
                escreveArquivo(nome, idade, email, telefone, cnpj, 0);
            } else {

                escreveArquivo(nome, idade, email, telefone, cnpj, 1);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void escreveArquivo(String nome, String idade, String email, String telefone, String cnpj, int c) {
        int i;
        i = 0;
        try {

            FileWriter myWriter = new FileWriter("src/arquivo/nome.txt", true);
            FileWriter myWriter2 = new FileWriter("src/arquivo/idade.txt", true);
            FileWriter myWriter3 = new FileWriter("src/arquivo/email.txt", true);
            FileWriter myWriter4 = new FileWriter("src/arquivo/telefone.txt", true);
            FileWriter myWriter5 = new FileWriter("src/arquivo/cnpj.txt", true);

            if (c == 0) {
                myWriter.write(nome);
                myWriter2.write(idade);
                myWriter3.write(email);
                myWriter4.write(telefone);
                myWriter5.write(cnpj);
            } else {
                myWriter.write("\n" + nome);
                myWriter2.write("\n" + idade);
                myWriter3.write("\n" + email);
                myWriter4.write("\n" + telefone);
                myWriter5.write("\n" + cnpj);

            }

            myWriter.close();
            myWriter2.close();
            myWriter3.close();
            myWriter4.close();
            myWriter5.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void escreveArquivo2() {
        int i;
        i = 0;

        LeArquivo();
        try {
            FileWriter myWriter = new FileWriter("src/arquivo/teste.txt");
            myWriter.write("Files in Java might be tricky, but it is fun enough !\n");

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void LeArquivo() {
        try {
            File myObj = new File("src/arquivo/nome.txt");
            File myObj2 = new File("src/arquivo/idade.txt");
            File myObj3 = new File("src/arquivo/email.txt");
            File myObj4 = new File("src/arquivo/telefone.txt");
            File myObj5 = new File("src/arquivo/cnpj.txt");

            Scanner myReader = new Scanner(myObj);
            Scanner myReader2 = new Scanner(myObj2);
            Scanner myReader3 = new Scanner(myObj3);
            Scanner myReader4 = new Scanner(myObj4);
            Scanner myReader5 = new Scanner(myObj5);
            while (myReader.hasNextLine()) {
                nomes.add(myReader.nextLine());
                idades.add(myReader2.nextLine());
                emails.add(myReader3.nextLine());
                telefones.add(myReader4.nextLine());
                cnpjs.add(myReader5.nextLine());

            }

            myReader.close();
            myReader2.close();
            myReader3.close();
            myReader4.close();
            myReader5.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
