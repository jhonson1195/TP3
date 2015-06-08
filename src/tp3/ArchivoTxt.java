/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.io.*;
import java.math.BigInteger;
import static tp3.Configuracion.DireccionTXT;
import tp3.Puntos;

/**
 *
 * @author carlosr
 */
public class ArchivoTxt {
      File archivo;
      FileReader fr;
      BufferedReader br;
      BufferedReader br1;
      FileReader fr1;
      BufferedReader br2;
      FileReader fr2;
      String DireccionTX = "/home/carlos/Escritorio/Archivo.txt";
      static Stacks <Puntos> puntos = new Stacks();
      
      public void ArchivoTxt(){
          archivo = null;
          fr = null;
          br = null;
          br1 = null;
          fr1 = null;
          br2 = null;
          fr2 = null;
          DireccionTX = "/home/carlos/Escritorio/Archivo.txt";
          
          
          
      } 
      
      public  void abrirArchivo(String Direccion) throws FileNotFoundException{
         
         archivo = new File (DireccionTX);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         fr1 = new FileReader (archivo);
         br1 = new BufferedReader(fr1);
         fr2 = new FileReader (archivo);
         br2 = new BufferedReader(fr2);
         System.out.println("Se abrio el archivo");
      }
      
      // Retorna el tamaño del grafo
      public int retornarTam() throws FileNotFoundException, IOException{
          abrirArchivo(DireccionTX);
          String tam1="";
          
          int tam=0;
          String comparacion ="MAPA";
         while((tam1=br.readLine()).compareTo(comparacion)!=0){
             tam++;
            
         }
             tam=tam-1; 

            // tamaño del grafo
              System.out.println(tam);
              return tam;
      }
      
      public Stacks puntosgrafo() throws FileNotFoundException, IOException{
          
          abrirArchivo(DireccionTX);
          String linea="";
          String arreglo [];
          
          int lineas=0;
          int maximo=retornarTam();
           while(lineas<=maximo){
              lineas++;
              if((linea=br2.readLine()).equals("PUNTOS")){
                  
                  System.out.println("leyo la linea de puntos");
              }
              else{
                  arreglo=linea.split(" ");
                  //Integer nombres= Integer.parseInt(arreglo[0]);
                  //Nombre deberia ser tipo Integer
                  Puntos objecto = new Puntos();
                  objecto.nombre=arreglo[0];
                  objecto.ciudad=arreglo[1];
                  objecto.direccion = arreglo[2];
                  for(int i=3;i<arreglo.length;i++){
                      String direccion= arreglo[i];
                      objecto.direccion= objecto.direccion + direccion;
                  }
                  //System.out.println(" Nombre " + objecto.nombre + " Ciudad " + objecto.ciudad + " dirección " + objecto.direccion  );
                  puntos.push(objecto);
                  //System.out.println("Se agrego el elemento");
                  
              }
              
          }
          
          
        
        return puntos;
          
      }
      
      public void RecorrerCola() throws IOException{
          int maximo = retornarTam();
          
          for(int i=0;i<maximo;i++){
              
              Puntos referencia = puntos.pop();
          
          System.out.println(referencia.nombre + referencia.ciudad+referencia.direccion);
              
          }
      }
      
      public static void main(String [] arg) throws IOException{
        ArchivoTxt pruebas = new ArchivoTxt();
        pruebas.puntosgrafo();
        pruebas.RecorrerCola();
       
      }
      
}