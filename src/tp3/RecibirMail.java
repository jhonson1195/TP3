
package tp3;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

public class RecibirMail{
    
    EnviarCorreo Envia;
    String cliente;
    String [] Datos;
    boolean Bandera;
    
    RecibirMail(){
        Envia = new EnviarCorreo();
        Bandera=false;
    }
   
    public String [] getDatos(){
        return Datos;
    }
    
    public boolean getEstado(){
        return Bandera;
    }
    public String getCorreoCliente(){
        return cliente;
    }
    
    public void enviarCorreo() throws MessagingException{
        String Correo=accederCorreo(cliente);
        Envia.Enviador(Correo, "Respueta del Pedido", "Acuse de recibido y que su pedido será procesado en los próximos minutos. ");
    }
    
    
    public void conectar(){
        // Se hace contacto con el correo
        Properties prop = new Properties();
        prop.setProperty("mail.pop3.starttls.enable", "false");
        prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
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
                    // Se visualiza el contenido de cada mensaje
                    analizaParteDeMensaje(mensajes[i]);
                }
            }
            folder.close(false);
            store.close();
        }catch (Exception e){
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
                    String str2=(String)unaParte.getContent();
                    str2=quitarHTML(str2);
                    str2=str2.replace("Cuerpo: ", "");
                    str2=str2.replace("Nombre: ", "");
                    str2=str2.replace("Recolectar: ", "");
                    str2=str2.replace("Entregar: ", "");
                    Datos = str2.split(System.getProperty("line.separator"));
                    Bandera=true;
                }
            }
            
        }catch (MessagingException | IOException e){
            System.out.println("Error al leer el correo");
        }
    }
    
    //Se quita el codigo HTML de los correos
    private String quitarHTML(String htmlString){
        String noHTMLString = htmlString.replaceAll("\\<.*?\\>\n", "");
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
}