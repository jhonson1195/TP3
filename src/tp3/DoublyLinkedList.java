package tp3;


/**
 * 
 *Lista doblemente enlazada implementada con la interfaz LinearList generica
 * 
 */
public class DoublyLinkedList <T>{
    //Definicon de atributos
    private Node<T> head, current, tail;
    private int position;
    private int size;
    /**
     * contructor que inicializa las variables
     */
    public DoublyLinkedList(){
        head=new Node<>();
        current = head;
        tail = head;
        size = 0;
        position = -1;
    }

    
    /**
     * Inserta un elemento en la lista antes del nodo actual.
     * @param <T> variable generica
     */
    public void insert(T element) {
        Node<T> newnode = new Node<>(element, current, current.getLast());
        Node<T> temp=current.getLast();
        temp.setNext(newnode);
        current.setLast(newnode);
        
        current=current.getLast();
        
        size++;
        
    }
    /**
     * Actuliza el valor del nodo en tal posicion
     * @param pos
     * @param Date 
     */
    public void setElement(int pos,T Date){
        goToPos(pos);
        current.setDate(Date);
    }
    
    /**
     * obtiene el dato de la posicion tal
     * @param pos
     * @return 
     */
    public T get(int pos){
        goToPos(pos);
        return getElement(); 
    }

    
    /**
     * Agrega un nodo despues de la cola actual 
     * @param <T>
     */
    public void append(T Date) {
        Node<T> newnode = new Node<>(Date);
        tail.setNext(newnode);
        newnode.setLast(tail);
        tail=newnode;
        size++;
        if(current==head){
            current=current.getNext();
            position++;
        }
     
    }

    
    /**
     * Elimina el nodo actual nodo
     */
    public void remove(int pos) {
        goToPos(pos);
        if(current==head && head==tail){
            System.out.println("Lista vacia");
            return;
        }
        if(current==tail){
            current.getLast().setNext(null);
            tail=current=current.getLast();
            position--;
            size--;
            return;
        }
        
        Node<T> temp = current.getLast();
        Node<T> temp2 = current.getNext();
        temp.setNext(current.getNext());
        temp2.setLast(current.getLast());
        if(current==tail){
            current=tail=temp;
        }
        else{
            current=current.getNext();
        }
        size--;
    }

    
    /**
     * Devuelve el elemento contenido en el nodo actual
     * @return <T>
     */
    public T getElement() {
        return current.getDate(); 
    }

    
    /**
     * Devuelve true o false para avanzar el nodo de posicion actual
     * @return boolean
     */
    public boolean next() {
        if(current==tail){
            System.out.println("La lista esta vacia, no se puede avanzar");
            return false;
        }else{
            current=current.getNext();
            position++;
            return true;
        }
        
    }
    
    
     /**
     * Devuelve true o false para retroceder el nodo de posicion actual
     * @return boolean
     */
    public boolean previous() {
        if(current.getLast()==head){
            System.out.println("no se puede retroceder");
            return false;
        }
        current=current.getLast();
        position--;
        return true;
    }

    
    /**
     * Retorna la posicion actual del nodo
     * @return int
     */
    public int getPosition() {
        return position;
    }

    
    /**
     * El nodo actual se convierte al primer nodo de la lista
     */
    public void goToStart() {
        current=head.getNext();
        position=0;
    }

    
    /**
     * El nodo actual se convierte al ultimo nodo de la lista
     */
    public void goToEnd() {
        current=tail;
        position=size-1;
    }

    
    /**
     * Mueve el nodo actual a la posicion indicada
     * @param pos
     */
    public void goToPos(int pos) throws IndexOutOfBoundsException {
        
        if(pos>=0 && pos<size){
            if(pos>position){
                while(pos>position){
                    if(next()){}
                    else{break;}
                }
            }
            if(pos<position) {
                while(pos<position){
                    if(previous()){}
                    else{break;}
                }   
            }  
            position=pos;
        }
        else{//System.out.println("posicion invalida")
            ;}
    }

    
    /**
     * Busca el elemento en la lista y devuelve su indice
     * @param <T>
     */
    public int getPositionOfElement(T element) {
        Node<T>temp=head.getNext();
        int cont=0;
        while(temp.getDate()!=element){
            temp=temp.getNext();
            cont++;
        }
        if(temp.getDate()==element){
            return cont;
        }
        else{
            return -1;
        }
        
    }

 
    /**
     * Devuelve el tamaño de la lista
     * @return size
     */
    public int size() {
        return size;
    }

    
    /**
     * Borra todos los nodos de la lista y crea una nueva
     */
    public void clear() {
        head=new Node<>();
        current = head;
        tail = head;
        size = 0;
        position = -1;  
    }

    
    /**
     * Comprueba si la lista esta vacia
     * @return boolean
     */
    public boolean isEmpty() {
         if(size>0){
            return false;
        }
        else{
            return true;
        }
    }
    /**
     * Imprime los elementos de la lista
     * @return 
     */
    @Override
    public String toString(){
        if(size==0){
            
            return "Lista vacia";
        }
        String result= "Lista doblemente enlazada \n";
        Node<T> temp =head.getNext();
        while (temp.getNext()!=null){
            result+=temp.getDate()+ ", ";
            temp=temp.getNext();
            
        }
        return result+=temp.getDate();
            
    }
    /**
     * Clase para crear una variable de tipo Nodo
     * @param <T>
     */
    public class Node <T>{
        private Node<T> next;
        private Node<T> last;
        private T Date;
        
        /**
         * Contrutor que inicializa las variables
         */
        public Node(){
            next=null;
            last=null;
            Date=null;
        }
         /**
         * Contrutor que inicializa las variables
         * @param Date
         */
        public Node(T Date){
            next=null;
            last=null;
            this.Date=Date;
        }
         /**
         * Contrutor que inicializa las variables
         * @param Date
         * @param next
         * @param last
         */
        public Node(T Date, Node<T> next,Node<T> last){
            this.Date= Date;
            this.next=next;
            this.last=last; 
        }
         /**
         * Devuelve el elemento del nodo
         * @return Date
         */
        public T getDate(){
            return Date;
        }
         /**
         * Define la varialbe del nodo
         * @param Date
         */
        public void setDate(T Date){
            this.Date= Date;
        }
         /**
         * Devuelve el Nodo de la variable next
         * @return next
         */
        public Node<T> getNext(){
            return next;
        }
         /**
         * Define el noso siguiente
         * @param next
         */
        public void setNext(Node<T> next){
            this.next=next;
        }
         /**
         * Devuelve el nodo anterior
         * @return last
         */
        public Node<T> getLast(){
            return last;
        }
         /**
         * Define el nodo anterior
         * @param last
         */
        public void setLast(Node<T> last){
            this.last=last;
        }
        
    }
}
