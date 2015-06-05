/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.io.*;

/**
 *
 * @author carlosr
 */
public class ArchivoTxt {
    File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      BufferedReader tr = null;
      FileReader fr1 = null;
      String puntos [][];
      int Mapa [][];
      int cont1=0;
      static String Direccion = "C:\\archivo.txt";
      
      public  void abrirArchivo(String Direccion) throws FileNotFoundException{
         archivo = new File (Direccion);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         fr1 = new FileReader (archivo);
         tr = new BufferedReader(fr1);
         System.out.println("Se abrio el archivo");
      }
      
      // Retorna el tamaño del grafo
      public int retornarTam() throws FileNotFoundException, IOException{
          abrirArchivo(Direccion);
          String tam1="";
          
          int tam=0;
         while((tam1=br.readLine())!=null&&(tam1=br.readLine())!="MAPA"){
             tam++;
            
         }
             tam=tam-1; 

            // tamaño del grafo
              System.out.println(tam);
              return tam;
      }
}