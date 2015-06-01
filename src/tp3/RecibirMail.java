import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

import javax.imageio.ImageIO;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RecibirMail{
    EnviarCorreo Envia = new EnviarCorreo();
    String cliente;
    
    public RecibirMail(){
        conectar();
    }
    
    public void conectar(){
        // Se hace contacto con el correo
        Properties prop = new Properties();
        prop.setProperty("mail.pop3.starttls.enable", "false");
        prop.setProperty(
            "mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.pop3.socketFactory.fallback", "false");
        prop.setProperty("mail.pop3.port", "995");
        prop.setProperty("mail.pop3.socketFactory.port", "995");
        Session sesion = Session.getInstance(prop);
        // sesion.setDebug(true);

        try{
            // Se obtiene la bandeja de entrada, para poder leer el correo.
            Store store = sesion.getStore("pop3");
            store.connect("pop.gmail.com", "pruebaprogramacion0405@gmail.com", "tallertec");
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            // Se obtienen los mensajes.
            Message[] mensajes = folder.getMessages();

            // Se escribe from y subject de cada mensaje
            for (int i = 0; i < mensajes.length; i++){
                //Se valida que el asunto del correo sea un pedido
                if("Pedido".equals(mensajes[i].getSubject())){
                    this.cliente= mensajes[i].getFrom()[0].toString();
                    System.out.println("De:" + mensajes[i].getFrom()[0].toString());
                    System.out.println("Asunto:" + mensajes[i].getSubject());

                    // Se visualiza el contenido de cada mensaje
                    analizaParteDeMensaje(mensajes[i]);
                }
            }
            folder.close(false);
            store.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo recursivo.
     * Si la parte que se pasa es compuesta, se extrae cada una de las subpartes y
     * el metodo se llama a si mismo con cada una de ellas.
     * Si la parte es un text, se escribe en pantalla.
     * Si la parte es una image, se guarda en un fichero y se visualiza en un JFrame.
     * En cualquier otro caso, simplemente se escribe el tipo recibido, pero se
     * ignora el mensaje.
     */
    private void analizaParteDeMensaje(Part unaParte){
        try{
            // Si tiene varias partes, se analiza cada una de sus partes recursivamente.
            if (unaParte.isMimeType("multipart/*")){
                Multipart multi;
                multi = (Multipart) unaParte.getContent();
                //for (int j = 0; j < multi.getCount(); j++)
                //{
                //En este caso solo se va a utilizar la primera parte que seria el Texto
                    analizaParteDeMensaje(multi.getBodyPart(0));
                //}
            }
            else{
                // Si es texto, se escribe el texto.
                if (unaParte.isMimeType("text/*")){
                    //String str1="Texto " + unaParte.getContentType();
                    //str1=quitarHTML(str1);
                    //System.out.println(str1);
                    System.out.println("Cuerpo:");
                    String str2=(String)unaParte.getContent();
                    str2=quitarHTML(str2);
                    System.out.println(str2);
                    String Correo=accederCorreo(cliente);
                    Envia.Enviador(Correo, "Pedido", "Acuse de recibido y que su pedido será procesado en los próximos minutos. ");
                    System.out.println("---------------------------------   OTRO CORREO   ---------------------------------");
                }
                
                else{
                    // Si es imagen, se guarda en fichero y se visualiza en JFrame
                    if (unaParte.isMimeType("image/*")){
                        //String str1="Imagen " + unaParte.getContentType();
                        //str1=quitarHTML(str1);
                        //System.out.println(str1);
                        System.out.println("Cuerpo:");
                        String str2=(String)"Fichero=" + unaParte.getFileName();
                        str2=quitarHTML(str2);
                        String Correo=accederCorreo(cliente);
                        Envia.Enviador(Correo, "Pedido", "Acuse de recibido y que su pedido será procesado en los próximos minutos. ");
                        System.out.println("---------------------------------   OTRO CORREO   ---------------------------------");

                        //No se Ocupa extraer las imagenes en el correo
                        //salvaImagenEnFichero(unaParte);
                        //visualizaImagenEnJFrame(unaParte);
                    }
                    
                    //else{
                        // Si no es ninguna de las anteriores, se escribe en pantalla el tipo.
                        //System.out.println(
                        //    "Recibido " + unaParte.getContentType());
                        //System.out.println("---------------------------------");
                    //}
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Se visualiza la imagen extraida del correo
     * Recoge la imagen y la visualiza en un JFrame
     */
    private void visualizaImagenEnJFrame(Part unaParte) throws IOException, MessagingException{
        JFrame v = new JFrame();
        ImageIcon icono = new ImageIcon(ImageIO.read(unaParte.getInputStream()));
        JLabel l = new JLabel(icono);
        v.getContentPane().add(l);
        v.pack();
        v.setVisible(true);
    }

    /**
     * Se guarda en el computador la imagen extraida del correo
     * Salva la imagen en C:/Users/Esteban/Documents/Recibir Correos/.... (UBICACION SELECCIONADA)
     */
    private void salvaImagenEnFichero(Part unaParte) throws FileNotFoundException, MessagingException, IOException {
        FileOutputStream fichero = new FileOutputStream("C:/Users/Esteban/Documents/Recibir Correos/" + unaParte.getFileName());
        InputStream imagen = unaParte.getInputStream();
        byte[] bytes = new byte[1000];
        int leidos = 0;

        while ((leidos = imagen.read(bytes)) > 0){
            fichero.write(bytes, 0, leidos);
        }
    }
    
    //Se quita el codigo HTML de los correos
    private String quitarHTML(String htmlString){
        String noHTMLString = htmlString.replaceAll("\\<.*?\\>", "");
        return noHTMLString;
    }
    
    //Se utiliza pra nada mas extraer la informacion del correo
    private String accederCorreo(String htmlString){
        boolean estado=false;
        String Correo="";
        for(int i=0; i<htmlString.length();i++){
            if("<".equals(htmlString.substring(i,i+1))){
                i++;
                estado=true;
            }
            if(">".equals(htmlString.substring(i,i+1))){
                estado=false;
                i++;
            }
            if(estado){
                Correo=Correo+htmlString.substring(i,i+1);
            }
        }
        return Correo;
    }
    
    public static void main(String[] args){
        RecibirMail proceso = new RecibirMail();
    }
}