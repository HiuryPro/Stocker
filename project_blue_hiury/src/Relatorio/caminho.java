/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Relatorio;

import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 *
 * @author Hiury
 */
public class caminho {

   public static void main(String[] args) {
		Path path = FileSystems.getDefault().getPath("");
		String directoryName = path.toAbsolutePath().toString();
		System.out.println("Current Working Directory is = " +directoryName);
	}
}
