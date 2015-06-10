package tp3;

import java.awt.Component;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import static tp3.ArchivoTxt.mapas;
import static tp3.ArchivoTxt.puntos;
//import static tp3.ArchivoTxt.mapas;
//import tp3.Puntos;
//import tp3.Mapas;

/**
 *
 * Clase en la cual el usuario ingresa:
 * * Cantidad de repartidores
 * * Maximo de minutos que debe esperar un repartidor para salir
 * * Maximo de pedidos que se puede tener en espera
 * 
 * La configuracion solo se aplica al inicio del proyecto
 * Se hace uso de las clases
 * * ArchivoTxt
 * * Grafo
 * * PanelCentral
 * * VentanaMapa
 * 
 */
public class Configuracion extends javax.swing.JFrame {
    static String DireccionTXT=null;//Se usa static porque la direccion 
                                    //va a ser la misma en todo el proyecto
    Grafo Mapa;
    int [] Cantidades;
    ArchivoTxt manejo;
    
    //Inicia la configuracion
    public Configuracion() {
        initComponents();
        Mapa = new Grafo();
        Cantidades =new int [3];
        manejo = new ArchivoTxt();
    }

    // Informacion de la JFrame
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Configuracíon");

        jLabel2.setText("Repartidores:");

        jLabel3.setText("Máximo  de  minutos:");

        jLabel4.setText("Máximo  de  pedidos:");

        jButton1.setText("Examinar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Cargar mapa");

        jButton2.setText("Iniciar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(jLabel5)))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jButton2))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel1)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Boton para buscar el "Archivo.txt" donde se encontrara la informacion
     * de los puntos del mapa y sus rutas 
     */ 
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser buscador = new JFileChooser(); //Libreria para manejo de archivos
        buscador.setCurrentDirectory(new java.io.File("."));
        FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("TXT","txt");
        buscador.setFileFilter(filtroImagen);
        buscador.setDialogTitle("Seleccione el mapa");
        if (buscador.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            DireccionTXT= buscador.getSelectedFile().toString();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Boton que va a cargar los datos ingresados como configuracion,
     * incluyendo el archivo, dentro de un grafo y el diseño del mismo
     * 
     * Los datos ingresados se meteran dentro un arreglo, 
     * los mismos deben ser de tipo Integer
     * 
     * Todos los datos son necesarios por lo cual sino se ingresa algo
     * la configuracion no va a avanzar
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Component frame = null;
        if("".equals(jTextField1.getText())|"".equals(jTextField2.getText()) | "".equals(jTextField3.getText())){
            JOptionPane.showMessageDialog(frame,"Por favor ingrese los datos solicitados");
            return;
        }
        try{
            Cantidades[0]=Integer.parseInt(jTextField1.getText());
            Cantidades[2]=Integer.parseInt(jTextField2.getText());
            Cantidades[1]=Integer.parseInt(jTextField3.getText());
        }catch(java.lang.NumberFormatException e){
            JOptionPane.showMessageDialog(frame,"Por favor ingrese un numero entero");
            return;
        }
        if(DireccionTXT==null){
            JOptionPane.showMessageDialog(frame,"Por favor ingrese un mapa");
            return;
        }
        
        try {
            CargarMapaGrafo();
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        PanelCentral Panel = new PanelCentral(Mapa, Cantidades);
        Panel.setVisible(true);
        VentanaMapa M =new VentanaMapa(Mapa);
        M.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    //Metodo para cargar los datos en el Mapa del Grafo
    public void CargarMapaGrafo() throws IOException{
        int tam= manejo.retornarTam(); //Se Toman la cantidad de puntos
        manejo.puntosgrafo(); // Ingresa la informacion de los puntos en una pila "Puntos"
        manejo.mapaGrafo(); // Ingresa la informacion del mapa y las rutas en una pila "Mapas"

        Mapa.setTamañoMaximo(tam); 
        //Se empieza a crear el Grafo
        for(int i=0;i<tam;i++){
            Puntos referencia = (puntos.pop());
            int valor1= Integer.parseInt(referencia.nombre);
            Mapa.AgregarNodo(valor1,referencia.ciudad,referencia.direccion);        
        }
       
        while(mapas.top()!=null){
            Mapas referencia = (mapas.pop());
            int valor2 = Integer.parseInt(referencia.origen);
            int valor3 = Integer.parseInt(referencia.destino);
            int valor4 =Integer.parseInt(referencia.tiempo);
            Mapa.RelacionarNodo(valor2, valor3, valor4);
        }  
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
