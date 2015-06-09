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
    
    public void setTamañoMaximo(int TamañoMax){
        this.TamañoMax=TamañoMax;
        Lista=new Nodo [TamañoMax];
        Matriz= new Integer [TamañoMax][TamañoMax]; 
        tamañoact=0;
        
    }
    
    /**
     *
     * @return
     */
    public int getTamañoMax(){
        return TamañoMax;
    }
    public Nodo [] getLista(){
        return Lista;
    }
    public Integer [][] getAyacencia(){
        return Matriz;
    }
    public int getDuracionP_P(int Punto1, int Punto2){
        return Matriz[BuscarIndice(Punto1)][BuscarIndice(Punto2)];
        
    }
    
    public void AgregarNodo(int Punto, String Ciudad,String Dirrecion){
        if(tamañoact==TamañoMax){
            System.out.println("Lista llena");
            return;}
        
        Nodo Nodo = new Nodo(Punto, Ciudad, Dirrecion);
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
        for(j=0;j<TamañoMax;j++){
            if(Lista[j].getPunto()==punto){break;}
        }
        return j;
    }
    
    public int DuracionRuta(int [] Ruta){
        int suma=0;
        for(int i=0;i<Ruta.length-1;i++){
            suma+=Matriz[BuscarIndice(Ruta[i])][BuscarIndice(Ruta[i+1])];
        }
        return suma;
    }
    
    public int [] RutaCorta(int Inicial, int Final){
        NodoRuta [] Dijktra= new NodoRuta[TamañoMax];
        int j=BuscarIndice(Inicial);
        int anterior=0;
        
        for(int i=0; i<TamañoMax;i++){
            NodoRuta temp= new NodoRuta(Lista[i].getPunto());
            Dijktra[i]=temp;
        }
        int Distancia=0;
        for(int con=0; con<TamañoMax;con++){
            Dijktra[j].setRutaMenor();
            if(Distancia==0){
               Dijktra[j].setRutaPunto_Distancia(Lista[j].getPunto(), Distancia);
            }
            else{
                Dijktra[j].setRutaPunto_Distancia(anterior, Distancia);
            }
            
            for(int indice=0; indice<TamañoMax;indice++){
                if(Matriz[j][indice]!=null){
                    if(!Dijktra[indice].getRutaMenor()){
                        int suma;
                        if(Dijktra[indice].Distancia()==null){
                            suma = Distancia + Matriz[j][indice];
                            Dijktra[indice].setRutaPunto_Distancia(Lista[j].getPunto(), suma);
                        }else{
                            suma= Distancia + Matriz[j][indice];
                            if(suma<Dijktra[indice].Distancia()){
                                Dijktra[indice].setRutaPunto_Distancia(Lista[j].getPunto(), suma);
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
            
            //System.out.println(j);
            j=BuscarIndice(n1.getPunto());
            Distancia=Dijktra[j].Distancia();
            anterior=Dijktra[j].getRutaPunto();
        }
        for(NodoRuta n3:Dijktra){
            //System.out.println(n3.getPunto()+" "+n3.getRutaPunto()+" "+n3.Distancia());
        }
        j=BuscarIndice(Final);
        DoublyLinkedList<Integer> Camino = new DoublyLinkedList<>();
        Camino.append(Final);
        while(Dijktra[j].getRutaPunto()!=Inicial){
            Camino.append(Dijktra[j].getRutaPunto());
            j=BuscarIndice(Dijktra[j].getRutaPunto());
        }
        Camino.append(Inicial);
        int [] TEmp = new int [Camino.size()];
        int ls=0;
        for(int i=Camino.size()-1; i>=0; i--){
            TEmp[i]= Camino.get(ls);
            ls++;
        }
        return TEmp;
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
        private String Direccion, Ciudad;
        
        public Nodo(int Punto, String Ciudad, String Dirrecion){
            this.Punto=Punto;
            this.Direccion=Dirrecion;
            this.Ciudad=Ciudad;
        }
        public  String getCiudad(){
            return Ciudad;
        }
        
        public  int getPunto(){
            return Punto;
        }
        
        public String getDireccion(){
            return Direccion;
        }
    }
    
}
