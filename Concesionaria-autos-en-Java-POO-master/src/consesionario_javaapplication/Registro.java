package consesionario_javaapplication;

public class Registro {
    private int montoDeLaVenta;
    private String vehiculoVendido;
    private String tipoVehiculo;
    private String nombre;
    private String apellido;
    private int documentoComprador;

    public Registro(int montoDeLaVenta, String vehiculoVendido, String tipoVehiculo, String nombre, String apellido, int documentoComprador) {
        this.montoDeLaVenta = montoDeLaVenta;
        this.vehiculoVendido = vehiculoVendido;
        this.tipoVehiculo = tipoVehiculo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documentoComprador = documentoComprador;
    }

    public int getMontoDeLaVenta() {
        return montoDeLaVenta;
    }

    public String getVehiculoVendido() {
        return vehiculoVendido;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDocumentoComprador() {
        return documentoComprador;
    }
}
