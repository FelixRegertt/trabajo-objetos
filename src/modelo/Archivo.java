package modelo;

import java.time.LocalDate;

public class Archivo extends Elemento {

    private Usuario propietario;

    public Archivo(String nombre, String tipo, Integer tamanio, LocalDate fechaModificacion, LocalDate fechaCreacion, Catedra catedra, Usuario propietario) {
        super(nombre, tipo, tamanio, fechaModificacion,  fechaCreacion,  catedra);
        this.propietario = propietario;
    }

    public Usuario getPropietario(){
        return propietario;
    }
    
    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

}
