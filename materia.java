package aed;

public class materia{
    private int[] docentes;
    private ListaEnlazada<String> inscrips;
    private ListaEnlazada<NodoTrie> padres;

    public materia(){
        docentes=new int[]{0,0,0,0};
        inscrips= new ListaEnlazada();
        padres= new ListaEnlazada();
    }

    //O(1)
    public ListaEnlazada<NodoTrie> losPadres(){
        return padres;
    }

    //agregar adelante, como es una lista enlazada, cuesta O(1)
    public void agregarPadre(NodoTrie padre){
        this.padres.agregarAdelante(padre);
    }

    //agregar adelante, como es una lista enlazada, cuesta O(1)
    public void agregarInscripto(String alumno){
        this.inscrips.agregarAdelante(alumno);
    }

    //crear una nueva lista enlazada es hacer simplemente tres asignaciones, esto es O(1)
    public void cambiarInscriptos(){
        this.inscrips=new ListaEnlazada();
    }

    // longitud de lista enlazada cuesta O(1), luego esto es O(1)
    public int inscriptos(){
        return this.inscrips.longitud();
    }

    //O(1)
    public ListaEnlazada<String> alumnos(){
        return this.inscrips;
    }
    
    //en el peor caso hace tres comparaciones y una asignacion, todas operaciones O(1), luego esto es O(1)
    public void cambiarPlantel(int i){
        if (i==0){this.docentes[3]++;}
        else if (i==1){this.docentes[2]++;}
        else if (i==2){this.docentes[1]++;}
        else{this.docentes[0]++;}
    }

    //O(1)
    public int[] plantel(){
        return this.docentes;
    }


// como son returns y comparaciones una vez es O(1)
    public int maximo(int a, int b){
        if (a>b){
            return a;
        }
        else{return b;}
    }

// cuatro asignaciones y llamar tres veces a una funcion O(1) es O(1)
    public int cupo(){
        int a=this.docentes[0]*250;
        int b=this.docentes[1]*100;
        int c=this.docentes[2]*20;
        int d=this.docentes[3]*30;
        return maximo(a,maximo(b,maximo(c,d)));
    }

//lo hace una vez por cada carrera que tenga a esta materia O(Nm) siendo Nm los nombres de la materia, sacar primero es O(1)
    public void sacar(){
        while (padres.longitud()>0){
            NodoTrie a = padres.sacarPrimero();
            a.significado=null;
        }
    }



//para chequear cosas
    public static void main(String[] args){
        materia N=new materia();
        N.agregarInscripto("marcelo");
        N.agregarInscripto("juan");
        N.agregarInscripto("pia");
        NodoTrie w=new NodoTrie();
        NodoTrie g=new NodoTrie();
        NodoTrie h=new NodoTrie();
        N.agregarPadre(h);
        N.agregarPadre(w);
        N.agregarPadre(h);
        System.out.println(N.padres.longitud());
        Iterador<String> it = N.alumnos().iterador();

        for (int i=0; i<N.inscriptos();i++){
            String a=it.siguiente();
            System.out.println(a);
        }
    }




}


