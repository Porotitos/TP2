package aed;

import java.util.Vector;

public class trie{
    private NodoTrie raiz;
    private int tamaño;

    public trie(){
        raiz=null;
    }

    public int tamaño(){
        return tamaño;
    }

    public static void main(String[] args){
        trie a = new trie();
        a.agregarEstudiante("a");
        a.agregarEstudiante("aa");

        System.out.println(a.inOrder().toString());
}
    public boolean buscar(String s){
        NodoTrie actual=raiz;
        int i=0;
        if (actual==null){
            return false;
        }
        while (i<s.length()){
            if (actual.hijos[(int) s.charAt(i)]==null){
                return false;
            }
            else{
                actual=actual.hijos[(int) s.charAt(i)];
                i++;
            }
        }
        if (actual.significado!=null){ return true;}
        else {return false;}
    }

    public NodoTrie buscarYDev(String s){
        NodoTrie actual=raiz;
        int i=0;
        while (i<s.length()){
            actual=actual.hijos[(int) s.charAt(i)];
            i++;
            }
        return actual;
        }

    public void agregarEstudiante(String s){
        if (raiz==null){
            raiz=new NodoTrie();
        }
        NodoTrie actual=raiz;
        int i=0;
        while (i<s.length()){
            if (actual.hijos[(int) s.charAt(i)]==null){
                actual.hijos[(int) s.charAt(i)]=new NodoTrie();
            }
            actual=actual.hijos[(int) s.charAt(i)];
            i++;
        }
        actual.significado=s;
        tamaño++;
    }

    public void agregarCarrera(String s){
        if (raiz==null){
            raiz=new NodoTrie();
        }
        NodoTrie actual=raiz;
        int i=0;
        while (i<s.length()){
            if (actual.hijos[(int) s.charAt(i)]==null){
                actual.hijos[(int) s.charAt(i)]=new NodoTrie();
            }
            actual=actual.hijos[(int) s.charAt(i)];
            i++;
        }
        actual.significado=s;
        actual.materiasAsoc=new trie();
        tamaño++;
    }
    
    public void agregarMateria(String s, materia mat){
        if (raiz==null){
            raiz=new NodoTrie();
        }
        NodoTrie actual=raiz;
        int i=0;
        while (i<s.length()){
            if (actual.hijos[(int) s.charAt(i)]==null){
                actual.hijos[(int) s.charAt(i)]=new NodoTrie();
            }
            actual=actual.hijos[(int) s.charAt(i)];
            i++;
        }
        actual.significado=s;
        actual.laMateria=mat;
        tamaño++;
        
        //cambio 1 para que ande sacar
        mat.agregarPadre(actual);
    }

/* 
    public void sacar(String s){
        NodoTrie actual=raiz;
        int i=0;
        while(i<s.length()-1){
            actual=actual.hijos[(int) s.charAt(i)];
        }
        i++;
        actual=actual.hijos[(int) s.charAt(i)];
        int c=actual.laMateria.losPadres().longitud();
        for (int j=0; j<c; j++){
            NodoTrie a = (actual.laMateria.losPadres()).sacarPrimero();
            a.significado=null;
        }
    }
*/
   

/* 
    public void eliminar(String s){
        NodoTrie actual=raiz;
        NodoTrie ultUtil=raiz;
        int i=0;
        int ultIndice=(int) s.charAt(i);
        while(i<s.length()-1){
            actual=actual.hijos[(int) s.charAt(i)];
            if (actual.hijos[(int) s.charAt(i)].util()){
                ultUtil=actual.hijos[(int) s.charAt(i)];
                ultIndice=(int) s.charAt(i);
            }
            i++;
        }
        actual=actual.hijos[(int) s.charAt(i)];
        actual.significado=null;
        ultUtil.hijos[ultIndice]=null;
        this.tamaño--;
    }
*/
    
//ver
    public Vector<String> inOrderNodo (NodoTrie n){
        Vector<String> res=new Vector<String>();
        if (n!=null){
            if (n.significado!=null){
                res.add(n.significado);}
            for (int i=0; i<256; i++){
                    inOrderNodo(n.hijos[i]);
            }
        }
        return res;
    }

    public Vector<String> inOrder (){
        return inOrderNodo(this.raiz);
    }
}




