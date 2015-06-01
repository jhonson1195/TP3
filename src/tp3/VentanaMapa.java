/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import javax.swing.JFrame;

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