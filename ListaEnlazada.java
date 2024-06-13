package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int longitud;



    private class Nodo<T> {
        T valor;
        Nodo<T> sig;
        Nodo<T> ant;

        Nodo (T elem){valor=elem;}
    }

    public ListaEnlazada() {
         primero = null;
         ultimo = null;
         longitud = 0;      
    }

    public int longitud() {
        return longitud;
    }

    public void agregarAdelante(T elem) {
        Nodo<T> nuevo = new Nodo<T>(elem);
        if (primero==null){
           primero=nuevo;
           ultimo=nuevo;
        }
        else {
            primero.ant=nuevo;
            nuevo.sig=primero;
            primero=nuevo;
        }

        longitud++;
    }

    public void agregarAtras(T elem) {
        Nodo<T> nuevo = new Nodo<T> (elem);
        if (primero==null){
            ultimo=nuevo;
            primero=nuevo;
        }
        else{
            ultimo.sig=nuevo;
            nuevo.ant=ultimo;
            ultimo=nuevo;
        }
        
        longitud++;
    }

    public T obtener(int i) {     
        Nodo <T> actual = primero;
        int a = 0;
        while (a<i){
            actual=actual.sig;
            a++;    
        }   
        
        return actual.valor;
    }

    public T sacarPrimero(){
        T res=this.primero.valor;
        primero=primero.sig;
        longitud--;
        return res;
    }

    public void eliminar(int i) {
        Nodo<T> actual = primero;
        Nodo<T> prev = primero;
        for (int j = 0; j < i; j++) {
            prev = actual;
            actual = actual.sig;
        }
        if (i == 0) {
            primero = actual.sig;   
        } else {
            prev.sig = actual.sig;
            //ver
            actual.sig.ant=prev;
        }
        longitud--;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = primero;
        for (int j = 0; j < indice; j++) {
            actual = actual.sig;
        }
            actual.valor=elem;
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> copia = new ListaEnlazada();
        Nodo<T> actual = primero;
        for (int j = 0; j < longitud; j++){
            copia.agregarAtras(actual.valor);
            actual=actual.sig;        
        }
        return copia;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        Nodo<T> actual = lista.primero;
        for (int j=0; j<lista.longitud; j++){
            this.agregarAtras(actual.valor);
            actual=actual.sig;
        }
    }
    
    @Override
    public String toString() {
        StringBuffer sbuffer = new StringBuffer();
        sbuffer.append ("[");
        for (int j=0; j<longitud-1;j++){
            sbuffer.append (obtener(j));
            sbuffer.append (", ");
        }
        sbuffer.append(ultimo.valor);
        sbuffer.append("]");
        return sbuffer.toString();
    }

    private class ListaIterador implements Iterador<T> {
    	private int marcador;
        private Nodo<T> actual;

        ListaIterador(){
            marcador=0;
            actual=primero;
        }

        public boolean haySiguiente() {
	        return marcador<longitud;
        }
        
        public boolean hayAnterior() {
	        return marcador>0;
        }

        public T siguiente() {
	        Nodo <T> res= actual;
            actual=actual.sig;
            marcador=marcador+1;
            return res.valor;
        }
        

        public T anterior() {
            if(marcador==longitud){
                actual=ultimo;
                return ultimo.valor;
            }
            else {
            actual=actual.ant;
            marcador=marcador-1;
            }
            return actual.valor;
        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }

}