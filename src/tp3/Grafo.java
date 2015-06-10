package tp3;

/**
 *
 * Aqui se crea la estructura de grafos y se hace la manipulacion de la misma
 * 
 * Se utilizan las clases:
 * * 
 */
public class Grafo {
    private Nodo [] Lista;
    private Integer [][] Matriz;
    private int TamañoMax;
    private int tamañoact;
    NodoRuta n1;
    
    //Se ingresa o se cambia el tamaño maximo del grafo
    public void setTamañoMaximo(int TamañoMax){
        this.TamañoMax=TamañoMax;
        Lista=new Nodo [TamañoMax];
        Matriz= new Integer [TamañoMax][TamañoMax]; 
        tamañoact=0;
        
    }
    
    // Retorna el tamaño maximo
    public int getTamañoMax(){
        return TamañoMax;
    }
    
    // Retorna la lista de Nodos(Puntos)
    public Nodo [] getLista(){
        return Lista;
    }
    
    // Retorna la matriz de adyacencia
    public Integer [][] getAyacencia(){
        return Matriz;
    }
    
    // Retorna la duracion entre punto y punto
    public int getDuracionP_P(int Punto1, int Punto2){
        return Matriz[BuscarIndice(Punto1)][BuscarIndice(Punto2)];
        
    }
    
    // Se agrega un Punto a la lista
    public void AgregarNodo(int Punto, String Ciudad,String Dirrecion){
        if(tamañoact==TamañoMax){
            System.out.println("Lista llena");
            return;}
        
        Nodo Nodo = new Nodo(Punto, Ciudad, Dirrecion);
        Lista[tamañoact]= Nodo;
        tamañoact++;  
    }
    
    // Se hacen las relaciones(conexiones) entre un punto y otro
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
    
    //Busca el indice o posicion en la que se encuentra un punto dentro de la lista
    public int BuscarIndice(int punto){
        int j;
        for(j=0;j<TamañoMax;j++){
            if(Lista[j].getPunto()==punto){break;}
        }
        return j;
    }
    
    // Retorna la duracion de una ruta
    public int DuracionRuta(int [] Ruta){
        int suma=0;
        for(int i=0;i<Ruta.length-1;i++){
            suma+=Matriz[BuscarIndice(Ruta[i])][BuscarIndice(Ruta[i+1])];
        }
        return suma;
    }
    
    /**
     * 
     * Va a buscar cual es la ruta mas corta para llegar de un punto
     * a otro y va a retornar una lista con los ID´s de dicha ruta
     * 
     * Se utiliza el metodo de Dijktra para buscar la ruta mas corta
     */
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
            
            j=BuscarIndice(n1.getPunto());
            Distancia=Dijktra[j].Distancia();
            anterior=Dijktra[j].getRutaPunto();
        }
        for(NodoRuta n3:Dijktra){
            
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
        
    
    // Clase que va ir creando una ruta entre un punto y otro
    class NodoRuta{
        private boolean RutaMenor;
        private Integer Punto=null;
        private Integer RutaPunto= null;
        private Integer Distancia=null;
        
        NodoRuta(Integer Punto){
           this.Punto=Punto; 
           RutaMenor=false;
        }
        
        // Retorna la ruta mas rapida
        public boolean getRutaMenor(){
            return RutaMenor;
        }
        
        // Se avala que la ruta es la mas corta
        public void setRutaMenor(){
            RutaMenor=true;
        }
        
        // Retorna el ID de un punto
        public Integer getPunto(){
            return Punto;
        }
        
        // Retorna el ID de la ruta
        public Integer getRutaPunto(){
            return RutaPunto;
        }
        
        // Retorna la distacia entre un punto y otro
        public Integer Distancia(){
            return Distancia;
        }
        
        // Se ingresan la distancia entre un punto y otro; ademas de la ruta.
        public void setRutaPunto_Distancia(Integer RutaPunto,Integer Distancia){
            this.RutaPunto=RutaPunto;
            this.Distancia=Distancia;
        }
    }
    
    // Clase Nodo, la cual se va a encargar de ir guardando la informacion de un punto
    class Nodo{
        private int Punto;
        private String Direccion, Ciudad;
        
        public Nodo(int Punto, String Ciudad, String Dirrecion){
            this.Punto=Punto;
            this.Direccion=Dirrecion;
            this.Ciudad=Ciudad;
        }
        
        // Retorna la ciudad que corresponde al punto
        public  String getCiudad(){
            return Ciudad;
        }
        
        // Retorna el ID del punto
        public  int getPunto(){
            return Punto;
        }
        
        // Retorna Direccion del punto
        public String getDireccion(){
            return Direccion;
        }
    }
    
}
