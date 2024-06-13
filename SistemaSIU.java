package aed;

public class SistemaSIU {
    // Completar atributos privados
    private trie estudiantes;
    private trie carreras;

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] materiasEnCarreras, String[] libretasUniversitarias){
        carreras=new trie();
        estudiantes = new trie();
        for (int i=0; i<materiasEnCarreras.length; i++){
            materia fuente=new materia();
            for (int j=0; j<materiasEnCarreras[i].carreras.length; j++){
                if (carreras.tamaño()==0 || !carreras.buscar(materiasEnCarreras[i].carreras[j])){
                    carreras.agregarCarrera(materiasEnCarreras[i].carreras[j]);
                }
                NodoTrie actual=carreras.buscarYDev(materiasEnCarreras[i].carreras[j]);

                actual.materiasAsoc.agregarMateria(materiasEnCarreras[i].nombresEnCarreras[j], fuente);
            }
        }
        for (int i=0; i<libretasUniversitarias.length; i++){
            estudiantes.agregarEstudiante(libretasUniversitarias[i]);
        }
    }
    //revisar que onda si materia cerrada 
    public void inscribir(String estudiante, String carrera, String materia){
        
        //aumenta la cantidad de inscriptos a una materia
        NodoTrie actual=carreras.buscarYDev(carrera);
        actual=actual.materiasAsoc.buscarYDev(materia);
        actual.laMateria.agregarInscripto(estudiante);
        
        //aumenta la cant de materias a las que está inscripto el alumno
        actual=estudiantes.buscarYDev(estudiante);
        actual.inscribir();
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        NodoTrie actual=carreras.buscarYDev(carrera);
        actual=actual.materiasAsoc.buscarYDev(materia);
        actual.laMateria.cambiarPlantel(cargo.ordinal());

    }

    public int[] plantelDocente(String materia, String carrera){
        NodoTrie actual=carreras.buscarYDev(carrera);
        actual=actual.materiasAsoc.buscarYDev(materia);
        return actual.laMateria.plantel();
    }

    public void cerrarMateria(String materia, String carrera){
        NodoTrie actual=carreras.buscarYDev(carrera);
        actual=actual.materiasAsoc.buscarYDev(materia);
        

        //desinscribo de la materia a cada alumno, como itero sobre una lista enlazada cada operacion cuesta O(1) y se hace Em veces 
        //quiza me sirva mas pensarlo como en sacar materias así borro la list ad e alumnos
        Iterador<String> it = actual.laMateria.alumnos().iterador();

        for (int i=0; i<actual.laMateria.inscriptos();i++){
            String a=it.siguiente();
            NodoTrie alumne=estudiantes.buscarYDev(a);
            alumne.desinscribir();
        }
        actual.laMateria.cambiarInscriptos();
        actual.significado=null;
        actual.laMateria.sacar();
        //carreras.eliminar(materia);
    }

    public int inscriptos(String materia, String carrera){
        NodoTrie actual=carreras.buscarYDev(carrera);
        actual=actual.materiasAsoc.buscarYDev(materia);
        return actual.laMateria.inscriptos();
    }

    public boolean excedeCupo(String materia, String carrera){
        NodoTrie actual=carreras.buscarYDev(carrera);
        actual=actual.materiasAsoc.buscarYDev(materia);
        if (actual.laMateria.inscriptos()>actual.laMateria.cupo()){
            return true;
        }
        else{return false;}
    }

    public String[] carreras(){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public int materiasInscriptas(String estudiante){
        NodoTrie actual=estudiantes.buscarYDev(estudiante);
        return actual.inscripciones;
    }
}
