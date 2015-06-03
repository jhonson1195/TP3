/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

/**
 *
 * @author jhonson
 */
public class Pedido {
    String Cuerpo, Nombre; 
    int Recolectar, Entregar;
    
    public Pedido(String Cuerpo, String Nombre, int Recolectar, int Entregar){
        this.Cuerpo=Cuerpo;
        this.Nombre=Nombre;
        this.Recolectar=Recolectar;
        this.Entregar=Entregar;
    } 
    
    public String getNombre(){
        return Nombre;
    }
    public String getCuerpo(){
        return Cuerpo;
    }
    public int getRecolectar(){
        return Recolectar;
    }
    public int getEntregar(){
        return Entregar;
    }
    
}
