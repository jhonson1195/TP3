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
import java.util.*;

 
 class LeeFichero {
    public static void main(String [] arg) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        BufferedReader tr = null;
        FileReader fr1 = null;
        String puntos [][] = new String[15][3];
        String Mapas [][] = new String[15][15];
        int cont1=0;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File ("/home/jhonson/Escritorio/TP3/Archivo.txt");
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

            //tamaño del grafo
            //System.out.println(tam);
            //puntos = new String [tam][tam];
            //Mapa = new int[tam][tam];
            int punto=0;
            int mapa=0;
            boolean infoMapa=false;
            while((linea=tr.readLine())!=null){
            //while((linea=tr.readLine())!="MAPA"){
                int sw=0;
                String cadena="";
                if("PUNTOS".equals(linea)){
                    linea=tr.readLine();
                }
                if("Mapa".equals(linea)){
                    infoMapa=true;
                    linea=tr.readLine();
                }
                for(int i=0; i<linea.length(); i++){
                    if(!infoMapa){
                        if ((" ".equals(linea.substring(i, i+1)))&&(sw<2)){
                            puntos[punto][sw]=cadena;
                            //System.out.println(puntos[punto][sw]);
                            sw++;
                            i++;
                            cadena="";
                        }
                        if (sw==0){
                            cadena=cadena+linea.substring(i,i+1);
                        }
                        else if (sw==1){
                            cadena=cadena+linea.substring(i,i+1);
                        } 
                        else{
                            cadena=cadena+linea.substring(i,i+1);
                        }
                        if(i+1==linea.length()){
                            puntos[punto][sw]=cadena;
                            //System.out.println(puntos[punto][sw]);
                        }
                    }
                    else{
                        if ((" ".equals(linea.substring(i, i+1)))){
                            Mapas[mapa][sw]=cadena;
                            //System.out.println(Mapas[mapa][sw]);
                            sw++;
                            i++;
                            cadena="";
                        }
                        if (sw==0){
                            cadena=cadena+linea.substring(i,i+1);
                        }
                        else if (sw==1){
                            cadena=cadena+linea.substring(i,i+1);
                        } 
                        else{
                            cadena=cadena+linea.substring(i,i+1);
                        }
                        if(i+1==linea.length()){
                            Mapas[mapa][sw]=cadena;
                            //System.out.println(Mapas[mapa][sw]);
                        }
                    }
                }
                if(!infoMapa){ 
                    punto++;
                }
                else{
                    mapa++;
                }
                arreglo = linea.split(" ");
                //puntos[cont1][0]=linea;
                //for(int a=0; a < arreglo.length;a++ ){
                    //puntos[cont1][a]=arreglo[a];
                //}
                
                //cont1++;
                for(int a=0; a < arreglo.length;a++ ){
                //while
                    //System.out.println(arreglo[a]);
                }
            }
            
            // arreglo = linea.split(" ");   
        }catch(Exception e){
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
        for(int i=0; i<puntos.length; i++){
            for(int j=0; j<puntos[i].length;j++){
                if(puntos[i][j]!=null){
                    System.out.println("Puntos("+i+","+j+")= "+puntos[i][j]);
                }
            }
        }
        for(int i=0; i<Mapas.length; i++){
            for(int j=0; j<Mapas[i].length;j++){
                if(Mapas[i][j]!=null){
                    System.out.println("Mapas("+i+","+j+")= "+Mapas[i][j]);
                }
            }
        }
    }
}