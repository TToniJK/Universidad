
import java.util.PriorityQueue;

public class Farmacia {
    PriorityQueue<String> clientes;
}

class Cliente {
    private String nombreCompleto;
    private String cedulaCliente;
    private String preferencial;

}

abstract class Servicio implements Comparable<Servicio> {
    protected int id;
    protected String nombre;
    protected double precio;
    public Servicio(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
    //Setters y Getters
    public String getNombre() {return nombre;}
    public Double getPrecio() {return precio;} 
    public int getID() {return id;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setPrecio(Double precio) {this.precio = precio;}
    @Override
    public int compareTo(Servicio otro) {
        return Integer.compare(this.id, otro.id);
    }
    @Override
    public String toString() {
        return """
        \nID: """ + id +
        "Nombre: " + nombre +
        "Precio: " + precio +
        "\n"; 
    }

}

class Tratamiento extends Servicio {
    private String tratamiento;
    public Tratamiento (String nombre, double precio, int id, String tratamiento) {
        super(id, nombre, precio);
        this.tratamiento = tratamiento;
    }

    public String getTratamiento() {return tratamiento;}
    public void setTratamiento() {this.tratamiento = tratamiento;}

    @Override
    public String toString() {
        return super.toString() + String.format("Tratamiento: " , tratamiento);   

    }

}

class Medicamento extends Servicio {
    private String indicacion;
    private int cantidad;
    public Medicamento (String nombre, double precio, int id, String indicacion, int cantidad) {
        super(id, nombre, precio);
        this.indicacion = indicacion;
        this.cantidad = cantidad;
    }
    public String getIndicacion() { return indicacion; }
    public int getCantidad() { return cantidad; }
    public void setIndicacion(String indicacion) { this.indicacion = indicacion; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public boolean ConteoInventario(int reducirCantidad) {

        if (reducirCantidad <= cantidad) {
            System.out.println("Producto disponible");
            cantidad -= reducirCantidad;
            return true;
        } 
        System.out.println("Producto no disponible");
        return  false;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Indicación: " , indicacion);
    }


} 