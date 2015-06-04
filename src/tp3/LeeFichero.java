/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

/**
 *
 * @author carlosr
 */
import java.io.*;

 
 class LeeFichero {
   public static void main(String [] arg) {
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      BufferedReader tr = null;
      FileReader fr1 = null;
      String puntos [][];
      int Mapa [][];
      int cont1=0;
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("C:\\archivo.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         fr1 = new FileReader (archivo);
         tr = new BufferedReader(fr1);
         
         // Lectura del fichero
         //String linea;
         String linea="";
         String tam1="";
         String [] arreglo = null;
         
         
         // Se obtiene el tamaño del grafo
         int tam=0;
         while((tam1=br.readLine())!=null&&(tam1=br.readLine())!="Mapa"){
             tam++;
            
         }
             tam=tam-1; 

            // tamaño del grafo
              System.out.println(tam);
         //    puntos = new String [tam][tam];
         //    Mapa = new int[tam][tam];
         while((linea=tr.readLine())!=null){
          //  while((linea=tr.readLine())!="MAPA"){
               
                arreglo = linea.split(" ");
            //    puntos[cont1][0]=linea;
              //  for(int a=0; a < arreglo.length;a++ ){
            //        puntos[cont1][a]=arreglo[a];
                   // }
              //  cont1++;
                 for(int a=0; a < arreglo.length;a++ ){
                //while
                
                System.out.println(arreglo[a]);
               
            }
            }
            
           // arreglo = linea.split(" ");
            
         
           
         //}
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
   }
}