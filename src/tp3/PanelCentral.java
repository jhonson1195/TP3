/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author jhonson
 */
public class PanelCentral extends javax.swing.JFrame implements Runnable {
    Grafo Mapa;
    DoublyLinkedList<Pedido> ColaPedidos;
    int [] Cantidades;
    Thread temporizador;
    Thread temporizador2;
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    int [] Hora;
    int [] Hora2 =new int [2];
    

    /**
     * Creates new form PanelCentral
     * @param Mapa
     * @param Cantidades
     */
    public PanelCentral(Grafo Mapa, int [] Cantidades) {
        initComponents();
        ColaPedidos= new DoublyLinkedList<>();
        this.Mapa=Mapa;
        this.Cantidades=Cantidades;
        temporizador = new Thread(this);
        temporizador.start();
        temporizador2 = new Thread(this);
        temporizador2.start();
        Pedido o = new Pedido("ddd" ,"nobre",301,307, "correo");
        ColaPedidos.append(o);
        Pedido k = new Pedido("ddd" ,"nobre",307,308, "correo");
        ColaPedidos.append(k);
        Pedido l = new Pedido("ddd" ,"nobre",303,308, "correo");
        ColaPedidos.append(l);
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Panel Central");

        jLabel2.setText("jLabel2");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(88, 88, 88))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(172, 172, 172))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2)))
                .addGap(115, 115, 115)
                .addComponent(jButton1)
                .addContainerGap(329, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        generarRuta();
        System.out.println(Arrays.toString(hora()));
        int [] g ={301,303,307,308};
        
        System.out.println(Mapa.DuracionRuta(g));
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
    /**
     * Cada 15 segundos se comprueba si hay un correo nuevo
     */
    @Override
    public void run() {
        run2();
        Thread ct = Thread.currentThread();
        while(ct == temporizador) {  
           RecibirMail CorreoR = new RecibirMail();
           CorreoR.conectar();
           if(CorreoR.getEstado()){
               Pedido P = new Pedido(CorreoR.getDatos()[0] ,CorreoR.getDatos()[1],convetir(CorreoR.getDatos()[2]),convetir(CorreoR.getDatos()[2]), CorreoR.getCorreoCliente());
               ColaPedidos.append(P);
               try {
                   CorreoR.enviarCorreo();
               } catch (MessagingException ex) {
                   Logger.getLogger(PanelCentral.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
            try {
                Thread.sleep(1000*15);
                }catch(InterruptedException e) {}
        }

        
    }
    
    public void run2() {
        int segundos=59;
        int minutos=Cantidades[1]-1;
        
        Thread ct = Thread.currentThread();
        while(ct == temporizador2) {  
            if(segundos==0){
                segundos=59;
                minutos--;
            }
            jLabel2.setText(minutos+":"+segundos);
            
            if(Cantidades[0]!=0){
                segundos--;
                if((segundos==0 & 0==minutos) | Cantidades[2]==ColaPedidos.size()){
                    //generarRuta();
                    segundos=59;
                    minutos=Cantidades[1]-1;
                }
            }
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {}
        } 
    }
    
    public void generarRuta(){
        Hora2=hora();
        int punto_actual=1;
        int [] Ruta;
       
        for(int i=0;i<ColaPedidos.size();i++){
            if(!ColaPedidos.get(i).getRecolectaBoolean()){
                Ruta=Mapa.RutaCorta(punto_actual,ColaPedidos.get(i).getRecolectar());
                ColaPedidos.get(i).setRecolectaBoolean();
                boolean Bandera=false;
                int count=0;
            
                
                for(int P:Ruta){
                   
                   
                   for(int j=0;j<ColaPedidos.size();j++){
                      if(!ColaPedidos.get(j).getRecolectaBoolean()){
                          if (ColaPedidos.get(j).getRecolectar()==P){
                               ColaPedidos.get(j).setRecolectaBoolean();
                               System.out.println("Recolecta "+ColaPedidos.get(j).getRecolectar()+" LLegada: "+Hora2[0]+":"+Hora2[1]);
                               Bandera=true;
                          }
                          
                      }
                        if(ColaPedidos.get(j).getRecolectaBoolean()){
                            if(!ColaPedidos.get(j).getEntregaBoolean()){
                                if (ColaPedidos.get(j).getEntregar()==P){
                                    ColaPedidos.get(j).setEntregaBoolean();
                                    System.out.println("Entrega "+ColaPedidos.get(j).getEntregar()+" LLegada: "+Hora2[0]+":"+Hora2[1]);
                                    Bandera=true;
                                }
                                
                            }
                        } 
                      
                   }
                   
                   if(!Bandera && count!=0 && count!= Ruta.length-1){
                       System.out.println("Puto: "+ P+" LLegada: "+Hora2[0]+":"+Hora2[1]);
                   }
                   if(count!=Ruta.length-1){
                    sumahora(Mapa.getDuracionP_P(Ruta[count], Ruta[count+1]));
                    System.out.println(Mapa.getDuracionP_P(Ruta[count], Ruta[count+1]));
                   }
                   
                   count++;
                }
                
                System.out.println("Recolecta "+ ColaPedidos.get(i).getRecolectar()+" LLegada: "+Hora2[0]+":"+Hora2[1]);
                
            }
                
                if(!ColaPedidos.get(i).getEntregaBoolean()){
                    ColaPedidos.get(i).setEntregaBoolean();
                    Ruta=Mapa.RutaCorta(ColaPedidos.get(i).getRecolectar(),ColaPedidos.get(i).getEntregar());
                    boolean Bandera=false;
                    int count=0;
                    for(int P:Ruta){
                    
                        for(int j=0;j<ColaPedidos.size();j++){
                            if(!ColaPedidos.get(j).getRecolectaBoolean()){
                                if (ColaPedidos.get(j).getRecolectar()==P){
                                    ColaPedidos.get(j).setRecolectaBoolean();
                                    System.out.println("Recolecta "+ColaPedidos.get(j).getRecolectar()+" LLegada: "+Hora2[0]+":"+Hora2[1]);
                                    Bandera=true;
                                }
                            }
                            if(ColaPedidos.get(j).getRecolectaBoolean()){
                                if(!ColaPedidos.get(j).getEntregaBoolean()){
                                    if (ColaPedidos.get(j).getEntregar()==P){
                                        ColaPedidos.get(j).setEntregaBoolean();
                                        System.out.println("Entrega "+ColaPedidos.get(j).getEntregar()+" LLegada: "+Hora2[0]+":"+Hora2[1]);
                                        Bandera=true;
                                    }
                                
                                }
                            } 
                      
                   }
                   
                   if(!Bandera && count!=0 && count!= Ruta.length-1){
                       System.out.println("Puto: "+ P+" LLegada: "+Hora2[0]+":"+Hora2[1]);
                   }
                   if(count!=Ruta.length-1){
                    sumahora(Mapa.getDuracionP_P(Ruta[count], Ruta[count+1]));
                    System.out.println(Mapa.getDuracionP_P(Ruta[count], Ruta[count+1]));
                   }
                   count++;

                   
                }
                    System.out.println("Entrega "+ColaPedidos.get(i).getEntregar()+"Llegada: "+Hora2[0]+":"+Hora2[1]);
                }
           punto_actual=ColaPedidos.get(i).getEntregar();
           
        }  
    }
    public void sumahora(int minutos){
        int suma= Hora2[1]+minutos;
        int div = suma/59;
        /*if(div>=2){
            Hora2[0]=Hora2[0]+div;
            Hora2[1]=suma -59*div;
            return Hora2;
        }*/
        if(suma>59){
            Hora2[0]++;
            Hora2[1]=suma -59;
        }
        else{
            Hora2[1]=suma;
        }

    }
    
    public int [] PartiArreglo(int [] Arreglo, int tamaño){
        int [] temp = new int [tamaño];
        for(int i=0; i<tamaño;i++){
            temp [i]=Arreglo[i];
        }
        return temp;
    }
    
    public int convetir(String re){
        char [] ch = re.toCharArray();
        String Resul="";
        for(char i:ch){
            try{
            int n =Integer.parseInt(String.valueOf(i));
            Resul+=n;
            }
            catch(NumberFormatException e){}
        }
        return Integer.parseInt(Resul);
    }
    
    public int [] hora(){
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();
        calendario.setTime(fechaHoraActual);
        int [] Hora={calendario.get(Calendar.HOUR_OF_DAY),calendario.get(Calendar.MINUTE)};
        return Hora;
    } 
}
