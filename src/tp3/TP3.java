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
public class TP3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grafo g = new Grafo(8);
        g.AgregarNodo(1, "Sanjose");
        g.AgregarNodo(301, "hola");
        g.AgregarNodo(302, "hola");
        g.AgregarNodo(303, "hola");
        g.AgregarNodo(304, "hola");
        g.AgregarNodo(101, "hola");
        g.AgregarNodo(102, "hola");
        g.AgregarNodo(103, "hola");
        g.RelacionarNodo(301, 302, 10);
        g.RelacionarNodo(301, 303, 10);
        g.RelacionarNodo(301, 304, 10);
        g.RelacionarNodo(301, 102, 40);
        g.RelacionarNodo(303, 302, 1);
        g.RelacionarNodo(303, 301, 11);
        //g.Imprimir();
        g.RutaCorta(301,302);
    }
    
}
