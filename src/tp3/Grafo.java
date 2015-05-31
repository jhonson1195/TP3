/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

/**
 *
 * @author jhonson
 */
public class Grafo {
    private Nodo [] Lista;
    private Integer [][] Matriz;
    private int TamañoMax;
    private int tamañoact;
    NodoRuta n1;
    
    public Grafo(int TamañoMax){
        this.TamañoMax=TamañoMax;
        Lista=new Nodo [TamañoMax];
        Matriz= new Integer [TamañoMax][TamañoMax]; 
        tamañoact=0;
    }
    
    public void AgregarNodo(int Punto, String Dirrecion){
        if(tamañoact==TamañoMax){
            System.out.println("Lista llena");
            return;}
        
        Nodo Nodo = new Nodo(Punto, Dirrecion);
        Lista[tamañoact]= Nodo;
        tamañoact++;  
    }
    /**
     * Punto uno nodo principal para relacionarlo con el punto2, arista es la distancia
     * @param punto
     * @param punto2
     * @param Arista 
     */
    public void RelacionarNodo(int punto, int punto2, int Arista){
        Matriz[BuscarIndice(punto)][BuscarIndice(punto2)]=Arista;
    } 
    
    /**
     * Imprime toda la matriz
     */
    public void Imprimir(){
        String r="";
        for(int i=0; i<TamañoMax;i++){
            r+=Lista[i].getPunto()+" ";
            for(int j=0; j<TamañoMax;j++){
                r+=" "+Matriz[i][j];
            }
            r+="\n"; 
        }
        System.out.print(r);  
    }
    
    public int BuscarIndice(int punto){
        int j;
        for(j=0;j<tamañoact;j++){
            if(Lista[j].getPunto()==punto){break;}
        }
        return j;
    }
    
    public void RutaCorta(int Inicial, int Final){
        NodoRuta [] Dijktra= new NodoRuta[TamañoMax];
        int j=BuscarIndice(Inicial);
        
        for(int i=0; i<TamañoMax;i++){
            NodoRuta temp= new NodoRuta(Lista[i].getPunto());
            Dijktra[i]=temp;
        }
        int Distancia=0;
        for(int con=0; con<TamañoMax;con++){
            Dijktra[j].setRutaMenor();
            Dijktra[j].setRutaPunto_Distancia(Lista[j].getPunto(), Distancia);
            for(int indice=0; indice<TamañoMax;indice++){
                if(Matriz[j][indice]!=null){
                    if(!Dijktra[indice].getRutaMenor()){
                        int suma;
                        if(Dijktra[indice].Distancia()==null){
                            Dijktra[indice].setRutaPunto_Distancia(Lista[indice].getPunto(), Distancia);
                        }else{
                            suma= Dijktra[j].Distancia() + Matriz[j][indice];
                            if(suma<Dijktra[indice].Distancia()){
                                Dijktra[indice].setRutaPunto_Distancia(Lista[indice].getPunto(), suma);
                            }
                        }   
                            
                        
                    }
                }
            }
            
            
            
            for(NodoRuta n:Dijktra){
                if(!n.getRutaMenor()){
                    if(n.Distancia()!=null){
                        n1=n;
                        //System.out.println(n.Distancia());
                        break;
                    }
                }
            }
         
            
            for(NodoRuta n2:Dijktra){
                if(!n2.getRutaMenor()){
                    if(n2.Distancia()!=null){
                        if(n2.Distancia()<n2.Distancia()){
                            n1=n2;
                        }
                    }
                }
            }
            
            System.out.println(j);
            
            j=BuscarIndice(n1.getPunto());
            Distancia=n1.Distancia();
        }
        System.out.println(Dijktra[2].Distancia());
    }
        
    
    
    class NodoRuta{
        private boolean RutaMenor;
        private Integer Punto=null;
        private Integer RutaPunto= null;
        private Integer Distancia=null;
        
        NodoRuta(Integer Punto){
           this.Punto=Punto; 
           RutaMenor=false;
        }
        
        public boolean getRutaMenor(){
            return RutaMenor;
        }
        public void setRutaMenor(){
            RutaMenor=true;
        }
        public Integer getPunto(){
            return Punto;
        }
        public Integer getRutaPunto(){
            return RutaPunto;
        }
        public Integer Distancia(){
            return Distancia;
        }
        public void setRutaPunto_Distancia(Integer RutaPunto,Integer Distancia){
            this.RutaPunto=RutaPunto;
            this.Distancia=Distancia;
        }
        
    }
    
    class Nodo{
        private int Punto;
        private String Direccion;
        
        public Nodo(int Punto, String Dirrecion){
            this.Punto=Punto;
            this.Direccion=Dirrecion;
        }
        
        public  int getPunto(){
            return Punto;
        }
        
        public String getDireccion(){
            return Direccion;
        }
    }
    
}
