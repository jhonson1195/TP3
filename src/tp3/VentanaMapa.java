package tp3;

import javax.swing.JFrame;

/**
 * 
 * En esta clase se crea una ventana, 
 * la cual se va a encargar de tener 
 * la representacion de grafo
 * 
 * Utiliza la clase:
 * * Grafo
 */
public class VentanaMapa extends javax.swing.JFrame {
    Grafo Mapa;
    public VentanaMapa(Grafo Mapa) {
        this.Mapa=Mapa;
        initUI();
    }

    private void initUI() {
        add(new GraficarGrafo(Mapa));
        setTitle("Mapa");
        setSize(100, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}