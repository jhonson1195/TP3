/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author jhonson
 */
public class GraficarGrafo extends JPanel{
    Grafo Mapa;
    
    public GraficarGrafo(Grafo Mapa){
        this.Mapa=Mapa;
    }
    
    protected void paintCompo(Graphics g){
        Graphics2D gr= (Graphics2D)g;
        gr.setColor(Color.red);
        gr.fillRect(0, 0, this.getWidth(), this.getHeight());
        gr.setColor(Color.WHITE);
        int x,y;
        Random b = new Random();
        NodoXY [] Ubica = new NodoXY[Mapa.getTamañoMax()];
        for(int i=0;i<Mapa.getTamañoMax();i++){
            x=b.nextInt(900);
            y=b.nextInt(700);
            NodoXY xy = new NodoXY(x,y);
            Ubica[i]=xy;
            gr.fillOval(x,y, 20, 20);
            gr.drawString(String.valueOf(Mapa.getLista()[i].getPunto()), x+30, y+30);
            gr.drawString(String.valueOf(Mapa.getLista()[i].getDireccion()), x, y+50);
        }
        Integer [][] CMatriz=Mapa.getAyacencia();
        for(int j=0;j<Mapa.getTamañoMax();j++){
            for(int f=0;f<Mapa.getTamañoMax();f++){
                if(CMatriz[j][f]!=null){
                    gr.drawLine(Ubica[j].getX()+5, Ubica[j].getY()+5, Ubica[f].getX()+5, Ubica[f].getY()+5);
                }
            }
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintCompo(g);
    }
    
    class NodoXY{
        int x, y;
        
        public NodoXY(int x, int y){
            this.x=x;
            this.y=y;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
    }
    
    
}
