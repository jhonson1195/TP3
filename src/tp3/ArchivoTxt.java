/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.io.*;
import static tp3.Configuracion.DireccionTXT;

/**
 *
 * @author carlosr respaldo
 */

public class ArchivoTxt {
      File archivo;
      FileReader fr;
      BufferedReader br;
      BufferedReader br1;
      FileReader fr1;
      BufferedReader br2;
      FileReader fr2;
      BufferedReader br3;
      FileReader fr3;
      BufferedReader br4;
      FileReader fr4;
     // String DireccionTX = "/home/carlos/Escritorio/Archivo.txt";
      static Stacks <Puntos> puntos = new Stacks();
      static Stacks <Mapas> mapas = new Stacks();
      Grafo Mapas;
      
      public void ArchivoTxt(){
          archivo = null;
          fr = null;
          br = null;
          br1 = null;
          fr1 = null;
          br2 = null;
          fr2 = null;
          br3 = null;
          fr3 = null;
          br4 = null;
          fr4 = null;
          
          Mapas = new Grafo();
          
          
          
      } 
      
      public  void abrirArchivo(String Direccion) throws FileNotFoundException{
         
         archivo = new File (DireccionTXT);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         fr1 = new FileReader (archivo);
         br1 = new BufferedReader(fr1);
         fr2 = new FileReader (archivo);
         br2 = new BufferedReader(fr2);
         fr3 = new FileReader (archivo);
         br3 = new BufferedReader(fr3);
         fr4 = new FileReader (archivo);
         br4 = new BufferedReader(fr4);
         System.out.println("Se abrio el archivo");
      }
      
      // Retorna el tamaño del grafo
      public int retornarTam() throws FileNotFoundException, IOException{
          abrirArchivo(DireccionTXT);
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
      
      public int retornarTamMapas() throws FileNotFoundException, IOException{
          abrirArchivo(DireccionTXT);
          String tam3="";
          
          int tam=0;
          String comparacion ="FINAL";
         //while((tam3=br3.readLine()).compareTo(comparacion)!=0){
         while((tam3=br3.readLine())!=null){
             tam++;
            
         }
             

           
              System.out.println(tam);
              return tam;
      }
      
      public Stacks puntosgrafo() throws FileNotFoundException, IOException{
          
          abrirArchivo(DireccionTXT);
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
      
      public Stacks mapaGrafo() throws FileNotFoundException, IOException{
          abrirArchivo(DireccionTXT);
          String linea="";
          String arreglo [];
          int lineas = retornarTamMapas()-retornarTam();
          int cont=-1;
          System.out.println(" linea en la que empieza " + lineas);
          int maximo = retornarTamMapas();
          while((linea=br4.readLine())!=null){
              cont++;
              if(cont>=lineas){
                  String parentesis=")";
              
                  linea=EliminaCaracteres(linea,parentesis);
                  linea=linea.replace('(',' ');
                  arreglo=linea.split(" ");
                  //System.out.println(" arreglo0 " + arreglo[0]+" arreglo1 " + arreglo[1]+ " arreglo2 " + arreglo[2]);
                  int corte=(arreglo.length-1);
                  int count=0;
                  while (count<corte){
                      Mapas objeto = new Mapas();
                      objeto.origen=arreglo[0];
                      objeto.destino=arreglo[count+1];
                      objeto.tiempo=arreglo[count+2];
                      count= count+2;
                      mapas.push(objeto);  
                  }
              
              }
              
              
              
              
          }
          return mapas;
          
          
          
      }
      
      public void RecorrerPilaPuntos() throws IOException{
          int maximo = retornarTam();
          
          for(int i=0;i<maximo;i++){
              
              Puntos referencia = puntos.pop();
          
          System.out.println(" Nombre " + referencia.nombre + " Ciudad " +  referencia.ciudad+ " Direccion " + referencia.direccion);
              
          }
      }
      
           public void RecorrerPilaMapas() throws IOException{
          
          
          while(mapas.top()!=null){
              
              Mapas referencia = mapas.pop();
          
          System.out.println(" Origen "+ referencia.origen +" destino " + referencia.destino+ " tiempo " +referencia.tiempo);
              
          }
      }
      
      public String EliminaCaracteres(String s_cadena, String s_caracteres){
          String nueva_cadena = "";
          Character caracter = null;
          boolean valido = true;
 
          /* Va recorriendo la cadena s_cadena y copia a la cadena que va a regresar,
          sólo los caracteres que no estén en la cadena s_caracteres */
          for (int i=0; i<s_cadena.length(); i++){
                valido = true;
                for (int j=0; j<s_caracteres.length(); j++){
                    caracter = s_caracteres.charAt(j);
                    if (s_cadena.charAt(i) == caracter){
                        valido = false;
                        break;
                    }
                }
                if (valido)
                    nueva_cadena += s_cadena.charAt(i);
                }
          
            
            return nueva_cadena;
        }
      
      public void introducirDatos() throws IOException{
          puntosgrafo();
          Mapas.setTamañoMaximo(retornarTam());
          int tam = retornarTam();
          for(int i=0;i<tam;i++){
              Puntos referencia = puntos.pop();
              int valor1= Integer.parseInt(referencia.nombre);
              Mapas.AgregarNodo(valor1,referencia.ciudad,referencia.direccion);
              System.out.println(valor1+ referencia.ciudad+referencia.direccion);
          }
          
          
      }
}