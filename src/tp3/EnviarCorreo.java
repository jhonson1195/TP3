package tp3;

import java.util.Properties; 
import javax.mail.*; 
import javax.mail.internet.InternetAddress; 
import javax.mail.internet.MimeMessage;

/**
*
* Esta es la clase para enviar correos
* Se utilizan las librerias externas correspondientes a JavaMail
*/
public class EnviarCorreo {
    final String Cuenta; 
    final String Contraseña; 
    final String servidorSMTP; 
    final String puertoEnvio; 
    
    public EnviarCorreo(){
        this.Cuenta = "pruebaprogramacion0405@gmail.com"; 
        this.Contraseña = "tallertec"; 
        this.servidorSMTP = "smtp.gmail.com"; 
        this.puertoEnvio = "465";
    }
        

    public  void Enviador(String Cliente, String asunto, String Correo) throws MessagingException { 


        Properties propiedades = new Properties(); 
        propiedades.put("mail.smtp.user", Cuenta); 
        propiedades.put("mail.smtp.host", servidorSMTP); 
        propiedades.put("mail.smtp.port", puertoEnvio); 
        propiedades.put("mail.smtp.starttls.enable", "true"); 
        propiedades.put("mail.smtp.auth", "true"); 
        propiedades.put("mail.smtp.socketFactory.port", puertoEnvio); 
        propiedades.put("mail.smtp.socketFactory.class", 
                "javax.net.ssl.SSLSocketFactory"); 
        propiedades.put("mail.smtp.socketFactory.fallback", "false"); 

        SecurityManager seguridad = System.getSecurityManager(); 

     
            Authenticator autorizar = new EnviarCorreo.autentificadorSMTP(); 
            Session sesion = Session.getInstance(propiedades, autorizar); 
            //Session.setDebug(true); 

            MimeMessage msg = new MimeMessage(sesion); 
            msg.setText(Correo); 
            msg.setSubject(asunto); 
            msg.setFrom(new InternetAddress(Cuenta)); 
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(Cliente)); 
            Transport.send(msg); 
       

    } 

    private class autentificadorSMTP extends javax.mail.Authenticator { 
        @Override
        public PasswordAuthentication getPasswordAuthentication() { 
            return new PasswordAuthentication(Cuenta, Contraseña); 
        } 
    }
}

