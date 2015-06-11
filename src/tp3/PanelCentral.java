package tp3;

import java.awt.Component;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * Esta va a ser la ventana principal
 * 
 * Utiliza las clases:
 * * Grafo
 * * DoublyLinkedList
 * * RecibirMail
 * * EnviarCorreo
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
    Integer [] CronometroRepartidores;
    int [] Hora2 =new int [2];
    int SumaDuracion, numeroPedido, RepartidoresEnRuta, CuentaRegresiva;
    boolean RutaGenerada=true;
    int ultimaEntrega;
    

    /**
     * Creates new form PanelCentral
     * 
     */
    public PanelCentral(Grafo Mapa, int [] Cantidades) {
        initComponents();
        ColaPedidos= new DoublyLinkedList<>();
        this.Mapa=Mapa;
        CronometroRepartidores= new Integer [Cantidades[0]];
        this.Cantidades=Cantidades;
        temporizador = new Thread(this);
        temporizador.start();
        temporizador2 = new Thread(this);
        temporizador2.start();
        numeroPedido=0;
        RepartidoresEnRuta=0;
        CuentaRegresiva=Cantidades[2];
        jTextArea1.setEditable(false);
        jTextArea2.setEditable(false);
        
        
    }
    

    // Informacion del JFrame
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Panel Central");

        jLabel2.setText("jLabel2");

        jButton1.setText("Aceptar Ruta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setText("Repatidores Dispo:");

        jLabel4.setText("Pedidos MAX:");

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel7.setText("Notificaciones");

        jLabel8.setText("Cuenta Regresiva:");

        jLabel9.setText("Repartidores en Ruta:");

        jLabel10.setText("jLabel10");

        jLabel11.setText("Cuenta Regreiva Pedidos:");

        jLabel12.setText("jLabel12");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        jLabel13.setText("Tiempo de llegada");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(276, 276, 276)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12)
                            .addComponent(jLabel5))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addComponent(jLabel1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel2))
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Boton en donde se va a aprobar una ruta generada 
     * y asi generar la informacion correspondiente a dicha ruta
     * Como la hora de entrega del pedido y la duracion del mismo
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(!RutaGenerada){
            Cantidades[0]--;
            RepartidoresEnRuta++;
            CuentaRegresiva=Cantidades[2];
            jTextArea1.setText("");
            jTextArea2.setText("Un repartidor acaba de salir");
            for(int i=0;i<ColaPedidos.size();i++){
                if(ColaPedidos.get(0).getEntregaBoolean()){
                    EnviarCorreo Envio= new EnviarCorreo();
                    try {
                        Envio.Enviador(ColaPedidos.get(i).getcliente(), "Hora de entrega del pedido", "Entrega aproximada en: "+SumaDuracion+" minutos");
                    } catch (MessagingException ex) {
                        Logger.getLogger(PanelCentral.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ColaPedidos.remove(0);
                }
            }
            for(int i=0;i<CronometroRepartidores.length;i++){
                if(CronometroRepartidores[i]==null){
                    CronometroRepartidores[i]=SumaDuracion;
                    break;
                }
            }
            RutaGenerada=true;
            
        }else{
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"No se a generado una ruta");
            return;
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
    
    // Aqui se llevara el tiempo correspondiente a cada repartidor
    public void CronometroRepartidores(){
        DefaultListModel modelojlist = new DefaultListModel();
        for(int i=0;i<CronometroRepartidores.length;i++){
            if(CronometroRepartidores[i]!=null){
               CronometroRepartidores[i]--;
               if(CronometroRepartidores[i]==0){
                  RepartidoresEnRuta--;
                  Cantidades[0]++;
                  CronometroRepartidores[i]=null;
                  jTextArea2.setText("Un repartidor acaba de llegar");
               }
            }
            modelojlist.addElement(CronometroRepartidores[i]);
        }
        jList1.setModel(modelojlist);
    }
    
    /**
     * Cada 5 segundos se comprueba si hay un correo nuevo
     */
    @Override
    public void run() {
        run2();
        Thread ct = Thread.currentThread();
        while(ct == temporizador) {  
           RecibirMail CorreoR = new RecibirMail();
           CorreoR.conectar();
           if(CorreoR.getEstado()){
               Pedido P = new Pedido(CorreoR.getDatos()[0] ,CorreoR.getDatos()[1],convetir(CorreoR.getDatos()[2]),convetir(CorreoR.getDatos()[3]), CorreoR.getCorreoCliente(), numeroPedido);
               ColaPedidos.append(P);
               String R="Nuevo pedido: "+"#"+numeroPedido +"\n";
               R+="Nombre: "+P.getNombre()+"\n";
               R+="Cuerpo: "+P.getCuerpo()+"\n";
               R+="Recolecta "+P.getRecolectar()+"\n";
               R+="Entrega: "+P.getEntregar()+"\n";
               jTextArea2.setText(R);
               numeroPedido++;
               CuentaRegresiva--;
               
               try {
                   CorreoR.enviarCorreo();
               } catch (MessagingException ex) {
                   Logger.getLogger(PanelCentral.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
            try {
                Thread.sleep(1000*5);
                }catch(InterruptedException e) {}
        }

        
    }
    
    /**
     * Se va a manipular todo lo relacionado a pedidos, tiempos de espera,
     * Cantidad de repartidores disponibles y en ruta, cantidad de pedidos,
     * la ruta que se va a realizar, los correos recibidos, duraciones de 
     * los repartidores, entre otras cosas
     * 
     */
    public void run2() {
        int segundos=59;
        int minutos=Cantidades[1]-1;
        
        Thread ct = Thread.currentThread();
        while(ct == temporizador2) {
            CronometroRepartidores();
            jLabel5.setText(String.valueOf(Cantidades[0]));
            jLabel6.setText(String.valueOf(Cantidades[2]));
            jLabel10.setText(String.valueOf(RepartidoresEnRuta));
            jLabel12.setText(String.valueOf(CuentaRegresiva));
            if(segundos==0 && RutaGenerada){
                segundos=59;
                minutos--;
            }
            jLabel2.setText(minutos+":"+segundos);
            
            if(Cantidades[0]!=0){
                
                if(RutaGenerada){
                    segundos--;
                }
                
                if((segundos==0 & 0==minutos) | Cantidades[2]==ColaPedidos.size()){
                    if(RutaGenerada){
                       RutaGenerada=false;
                       generarRuta(); 
                    }
                    segundos=59;
                    minutos=Cantidades[1]-1;
                    
                    
                }
            }else{jTextArea2.setText("No hay repartidores disponibles");}
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {}
        } 
    }
    
    /**
     * Se va a crear la mejor ruta para realizar las entregas de los pedidos
     * Buscando que la ruta sea la mas ideal
     */
    public void generarRuta(){
        if(ColaPedidos.isEmpty()){
            return;
        }
        
        Hora2=hora();
        int punto_actual=1;
        int [] Ruta;
        String Resultado="Punto: 1 Salida: "+Hora2[0]+":"+Hora2[1]+"\n";
        SumaDuracion=0;
       
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
                               Resultado+="Punto: " +ColaPedidos.get(j).getRecolectar()+" Se recolecta pedido #"+ColaPedidos.get(j).getNumeroPedido()+" LLegada: "+Hora2[0]+":"+Hora2[1];
                               sumahora(1);
                               Resultado+=" Salida: "+Hora2[0]+":"+Hora2[1]+"\n";
                               Bandera=true;
                          }
                          
                      }
                        if(ColaPedidos.get(j).getRecolectaBoolean()){
                            if(!ColaPedidos.get(j).getEntregaBoolean()){
                                if (ColaPedidos.get(j).getEntregar()==P){
                                    ColaPedidos.get(j).setEntregaBoolean();
                                    Resultado+="Punto: " +ColaPedidos.get(j).getEntregar()+" Se entrega pedido #"+ColaPedidos.get(j).getNumeroPedido()+" LLegada: "+Hora2[0]+":"+Hora2[1];
                                    sumahora(1);
                                    Resultado+=" Salida: "+Hora2[0]+":"+Hora2[1]+"\n";
                                    Bandera=true;
                                }
                                
                            }
                        } 
                      
                   }
                   
                   if(!Bandera && count!=0 && count!= Ruta.length-1){
                       Resultado+="Punto: "+ P+" LLegada: "+Hora2[0]+":"+Hora2[1]+ " Salida: "+Hora2[0]+":"+Hora2[1]+"\n";
                   }
                   if(count!=Ruta.length-1){
                    sumahora(Mapa.getDuracionP_P(Ruta[count], Ruta[count+1]));
                   }
                   
                   count++;
                }
                
                Resultado+="Punto: " +ColaPedidos.get(i).getRecolectar()+" Se recolecta pedido # "+ColaPedidos.get(i).getNumeroPedido()+" LLegada: "+Hora2[0]+":"+Hora2[1];
                sumahora(1);
                Resultado+=" Salida: "+Hora2[0]+":"+Hora2[1]+"\n";
                
                
            }
                
                if(!ColaPedidos.get(i).getEntregaBoolean()){
                    ultimaEntrega=ColaPedidos.get(i).getEntregar();
                    ColaPedidos.get(i).setEntregaBoolean();
                    Ruta=Mapa.RutaCorta(ColaPedidos.get(i).getRecolectar(),ColaPedidos.get(i).getEntregar());
                    boolean Bandera=false;
                    int count=0;
                    for(int P:Ruta){
                    
                        for(int j=0;j<ColaPedidos.size();j++){
                            if(!ColaPedidos.get(j).getRecolectaBoolean()){
                                if (ColaPedidos.get(j).getRecolectar()==P){
                                    ColaPedidos.get(j).setRecolectaBoolean();
                                    Resultado+="Punto: " +ColaPedidos.get(j).getRecolectar()+" Se recolecta pedido #"+ColaPedidos.get(j).getNumeroPedido()+" Recolecta "+" LLegada: "+Hora2[0]+":"+Hora2[1];
                                    sumahora(1);
                                    Resultado+=" Salida: "+Hora2[0]+":"+Hora2[1]+"\n";
                                    Bandera=true;
                                }
                            }
                            if(ColaPedidos.get(j).getRecolectaBoolean()){
                                if(!ColaPedidos.get(j).getEntregaBoolean()){
                                    if (ColaPedidos.get(j).getEntregar()==P){
                                        ColaPedidos.get(j).setEntregaBoolean();
                                        Resultado+="Punto: " +ColaPedidos.get(j).getEntregar()+" Se entrega pedido #"+ColaPedidos.get(j).getNumeroPedido()+" LLegada: "+Hora2[0]+":"+Hora2[1];
                                        sumahora(1);
                                        Resultado+=" Salida: "+Hora2[0]+":"+Hora2[1]+"\n";
                                        Bandera=true;
                                    }
                                
                                }
                            } 
                      
                   }
                   
                   if(!Bandera && count!=0 && count!= Ruta.length-1){
                       Resultado+="Punto: "+ P+" LLegada: "+Hora2[0]+":"+Hora2[1]+" Salida: "+Hora2[0]+":"+Hora2[1]+"\n";
                   }
                   if(count!=Ruta.length-1){
                    sumahora(Mapa.getDuracionP_P(Ruta[count], Ruta[count+1]));
                   }
                   count++;

                   
                }
                    Resultado+="Punto: " +ColaPedidos.get(i).getEntregar()+" Se entrega pedido #"+ColaPedidos.get(i).getNumeroPedido()+" Llegada: "+Hora2[0]+":"+Hora2[1];
                    sumahora(1);
                    Resultado+=" Salida: "+Hora2[0]+":"+Hora2[1]+"\n";
                }
           punto_actual=ColaPedidos.get(i).getEntregar();
           
        } 
        Ruta=Mapa.RutaCorta(ultimaEntrega, 1);
        for(int i =0; i<Ruta.length-1;i++){
            
            if(Ruta[i+1]==1){
                sumahora(Mapa.getDuracionP_P(Ruta[i], Ruta[i+1]));
                Resultado+="Punto: "+ Ruta[i+1]+" LLegada: "+Hora2[0]+":"+Hora2[1]+"\n";
                break;
                
            }
            sumahora(Mapa.getDuracionP_P(Ruta[i], Ruta[i+1]));
            Resultado+="Punto: "+ Ruta[i+1]+" LLegada: "+Hora2[0]+":"+Hora2[1]+ " Salida: "+Hora2[0]+":"+Hora2[1]+"\n";
        }
        Resultado+="Tiempo total de la ruta: "+ SumaDuracion +" minutos"+"\n";
        jTextArea1.setText(Resultado);
    }
    
    // Se va a manipular la hora del sistema sumandole mas minutos
    public void sumahora(int minutos){
        SumaDuracion+= minutos;
        int suma= Hora2[1]+minutos;
        int div = suma/59;
        if(div>=2){
            Hora2[0]=Hora2[0]+div;
            Hora2[1]=suma -59*div;
            return;
        }
        if(suma>59){
            Hora2[0]++;
            Hora2[1]=suma -59;
        }
        else{
            Hora2[1]=suma;
        }
    }
    
    // Se va a crear un nuevo arreglo de menor tamaño
    public int [] PartiArreglo(int [] Arreglo, int tamaño){
        int [] temp = new int [tamaño];
        for(int i=0; i<tamaño;i++){
            temp [i]=Arreglo[i];
        }
        return temp;
    }
    
    // Para convertir datos tipos string en Integers
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
    
    // Para obtener la fecha y hora del sistema
    public int [] hora(){
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();
        calendario.setTime(fechaHoraActual);
        int [] Hora={calendario.get(Calendar.HOUR_OF_DAY),calendario.get(Calendar.MINUTE)};
        return Hora;
    } 
}
