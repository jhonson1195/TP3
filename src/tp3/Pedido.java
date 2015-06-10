package tp3;

/**
 *
 * Clase que va a tener la informacion correspondiente a un pedido
 */
public class Pedido {
    String Cuerpo, Nombre, cliente; 
    int Recolectar, Entregar, numeroPedido;
    boolean Recolecta= false, Entrega=false;
    
    // Se crea un pedido
    public Pedido(String Cuerpo, String Nombre, int Recolectar, int Entregar, String cliente, int numeroPedido){
        this.cliente=cliente;
        this.Cuerpo=Cuerpo;
        this.Nombre=Nombre;
        this.Recolectar=Recolectar;
        this.Entregar=Entregar;
        this.numeroPedido=numeroPedido;
    }
    
    // Retorna el numero del pedido
    public int getNumeroPedido(){
        return numeroPedido;
    }
    
    // Retorna el nombre del cliente
    public String getcliente(){
        return cliente;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    // Retorna el cuerpo del correo recibido
    public String getCuerpo(){
        return Cuerpo;
    }
    
    // Retorna el ID del lugar donde debe recolectarse
    public int getRecolectar(){
        return Recolectar;
    }
    
    // Retorna el ID del lugar donde debe entregarse
    public int getEntregar(){
        return Entregar;
    }
    
    // Retorna si ya fue recolectado 
    public boolean getRecolectaBoolean(){
        return Recolecta;
    }
    
    // Se cambia el estado de recoleccion
    public void setRecolectaBoolean(){
        Recolecta=true;
    }
    
    // Retorna si ya fue entregado
    public boolean getEntregaBoolean(){
        return Entrega;
    }
    
    // Se cambia el estado de entrega
    public void setEntregaBoolean(){
        Entrega=true;
    }
}
