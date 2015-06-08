/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;



/**
 *Clase de tipo pila
 * @author jhonson
 */
public class Stacks <T>{
    //Atributos
    private Node<T> top;
    private int size;
    
    public Stacks(){
        top=null;
        size=0;
    }
    

   
    /**
     * Ingresa un elemento  pila
     * @param <T>element
     */
    public void push(T element) {
        top = new Node<T>(element, top);
        size++; 
    }

   
    /**
     * Elimina y devuelve el elemento de la pila
     * @return temp
     */
    public T pop() {
        if (top == null) {
            System.out.println("Pila vacia");
            return null;
        }
        T temp = top.getElement();
        top = top.getNext();
        size--;
        return temp;
    }

 
    /**
     * Devuelve el top de la pila
     * @return elmento 
     */
    public T top() {
        if (top == null) {
            System.out.println("Lista vacia");
            return null;
        }
        return top.getElement();
    }

    
    /**
     * Devuelve el tamaño de la pila
     * @return size
     */
    public int size() {
        return size;
    }


    /**
     * Elimina los elemento de la pila
     */
    public void clear() {
        top = null;
        size = 0;
    }

   
    /**
     * Retona si la lista esta vacia o no
     * @return false o true
     */
    public boolean isEmpty() {
        if(top==null)
            return true;
        else{
            return false;
        }
    }
    /**
     * Devuelve los elemento de la pila
     * @return 
     */
    @Override
    public String toString(){
        String result = "Pila \n";
        Node<T>temp = top;
        while(temp != null){
            result+= temp.getElement() + "\n";
            temp = temp.getNext();
        }
        return result;
    }
    /**
     * Clase que almacena los elementos
     */
    private class Node<T>{
        private T element;
        private Node<T> next;

        // CONSTRUCTORES "Node"
        public Node(){
            this.element = null;
            this.next = null;
        }

        public Node(T element){
            this.element = element;
            this.next = null;
        }

        public Node(T element, Node<T> next){
            this.element = element;
            this.next = next;
        }

        public T getElement(){
            return this.element;
        }

        public void setElement(T element){
            this.element = element;
        }

        public Node<T> getNext(){
            return this.next;
        }

        public void setNext(Node<T> next){
            this.next = next;
        }
    }
    
}
