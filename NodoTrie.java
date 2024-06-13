package aed;
public class NodoTrie{
        NodoTrie[] hijos;
        String significado;

        //para nodos de carrera
        trie materiasAsoc;

        //para nodos de materia
        materia laMateria;
        
        //para nodos de alumno 
        int inscripciones;
        
        NodoTrie(){
            hijos=new NodoTrie[256];
            int i=0;
            while (i<hijos.length){
                hijos[i]=null;
                i++;
            }
            significado=null;
            materiasAsoc=null;
            laMateria=null;
            inscripciones=0;
        }

        public boolean dosHijos(){
            int i=0;
            while (i<this.hijos.length){
                if (this.hijos[i]!=null){
                    i++;
                }
                if (i>1){
                    return true;
                }
            }
            return false;
        }

        public boolean util(){
            if (this.significado!=null || this.dosHijos()){
                return true;
            }
            else{return false;}
        }

        public void inscribir(){
            this.inscripciones++;
        }

        public void desinscribir(){
            this.inscripciones--;
        }
}