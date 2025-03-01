package consesionario_javaapplication;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Consesionario_JavaApplication {
    private static ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
    private static ArrayList<Registro> listaVentas = new ArrayList<>();

    public static void main(String[] args) {
        cargarVehiculosDesdeArchivo();
        cargarVentasDesdeArchivo();
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Registrar vehículo");
            System.out.println("2. Registrar venta de vehículo");
            System.out.println("3. Mostrar ventas registradas");
            System.out.println("4. Mostrar vehículos registrados");
            System.out.println("0. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarVehiculo(scanner);
                    break;
                case 2:
                    registrarVenta(scanner);
                    break;
                case 3:
                    listarVentas();
                    break;
                case 4:
                    listarVehiculos();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        }
        scanner.close();
    }

    public static void registrarVehiculo(Scanner scanner) {
        System.out.println("Ingrese el código del vehículo:");
        String codigo = scanner.nextLine();
        System.out.println("Ingrese la marca del vehículo:");
        String marca = scanner.nextLine();

        String tipo = "";
        int tipoOpcion = 0;
        while (tipoOpcion < 1 || tipoOpcion > 4) {
            System.out.println("Seleccione el tipo de vehículo:");
            System.out.println("1. Auto");
            System.out.println("2. Camioneta");
            System.out.println("3. Motocicleta");
            System.out.println("4. Otro");
            tipoOpcion = scanner.nextInt();
            scanner.nextLine();

            switch (tipoOpcion) {
                case 1:
                    tipo = "Auto";
                    break;
                case 2:
                    tipo = "Camioneta";
                    break;
                case 3:
                    tipo = "Motocicleta";
                    break;
                case 4:
                    System.out.println("Ingrese el tipo de vehículo:");
                    tipo = scanner.nextLine();
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        }

        System.out.println("Ingrese el precio del vehículo:");
        int precio = scanner.nextInt();
        System.out.println("Ingrese el modelo del vehículo:");
        int modelo = scanner.nextInt();
        System.out.println("Ingrese el kilometraje del vehículo:");
        int kilometraje = scanner.nextInt();
        scanner.nextLine();

        Vehiculo nuevoVehiculo = new Vehiculo(codigo, marca, tipo, precio, modelo, kilometraje);
        listaVehiculos.add(nuevoVehiculo);
        guardarVehiculoEnArchivo(nuevoVehiculo);

        System.out.println("Vehículo registrado exitosamente.");
    }

    public static void registrarVenta(Scanner scanner) {
        System.out.println("Ingrese el código del vehículo vendido:");
        String codigo = scanner.nextLine();

        Vehiculo vehiculoVendido = null;
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.getCodigo().equals(codigo)) {
                vehiculoVendido = vehiculo;
                break;
            }
        }

        if (vehiculoVendido == null) {
            System.out.println("Error: No se encontró un vehículo con ese código.");
            return;
        }

        System.out.println("Ingrese el monto de la venta:");
        int monto = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el nombre del comprador:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del comprador:");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese el documento del comprador:");
        int documento = scanner.nextInt();
        scanner.nextLine();

        Registro nuevaVenta = new Registro(monto, codigo, vehiculoVendido.getTipo(), nombre, apellido, documento);
        listaVentas.add(nuevaVenta);
        guardarVentaEnArchivo(nuevaVenta);

        System.out.println("Venta registrada exitosamente.");
    }

    public static void listarVentas() {
        if (listaVentas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            System.out.println("\n***** REGISTRO DE VENTAS *****");
            for (Registro venta : listaVentas) {
                System.out.println("Vehículo vendido: " + venta.getVehiculoVendido());
                System.out.println("Tipo de vehículo: " + venta.getTipoVehiculo());
                System.out.println("Monto de la venta: " + venta.getMontoDeLaVenta());
                System.out.println("Comprador: " + venta.getNombre() + " " + venta.getApellido());
                System.out.println("Documento del comprador: " + venta.getDocumentoComprador());
                System.out.println("-------------------------------------");
            }
        }
    }

    public static void listarVehiculos() {
        if (listaVehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            System.out.println("\n***** LISTA DE VEHÍCULOS *****");
            for (Vehiculo vehiculo : listaVehiculos) {
                System.out.println("Código: " + vehiculo.getCodigo());
                System.out.println("Marca: " + vehiculo.getMarca());
                System.out.println("Tipo: " + vehiculo.getTipo());
                System.out.println("Precio: " + vehiculo.getPrecio());
                System.out.println("Modelo: " + vehiculo.getModelo());
                System.out.println("Kilometraje: " + vehiculo.getKilometraje());
                System.out.println("-------------------------------------");
            }
        }
    }

    public static void guardarVehiculoEnArchivo(Vehiculo vehiculo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehiculos.txt", true))) {
            writer.write(vehiculo.getCodigo() + "," + vehiculo.getMarca() + "," + vehiculo.getTipo() + "," +
                    vehiculo.getPrecio() + "," + vehiculo.getModelo() + "," + vehiculo.getKilometraje());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el vehículo en el archivo.");
        }
    }

    public static void guardarVentaEnArchivo(Registro venta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ventas.txt", true))) {
            writer.write(venta.getMontoDeLaVenta() + "," + venta.getVehiculoVendido() + "," + venta.getTipoVehiculo() + "," +
                    venta.getNombre() + "," + venta.getApellido() + "," + venta.getDocumentoComprador());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar la venta en el archivo.");
        }
    }

    public static void cargarVehiculosDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("vehiculos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                String codigo = datos[0];
                String marca = datos[1];
                String tipo = datos[2];
                int precio = Integer.parseInt(datos[3]);
                int modelo = Integer.parseInt(datos[4]);
                int kilometraje = Integer.parseInt(datos[5]);

                Vehiculo vehiculo = new Vehiculo(codigo, marca, tipo, precio, modelo, kilometraje);
                listaVehiculos.add(vehiculo);
            }
        } catch (IOException e) {
            System.out.println("No se encontraron vehículos guardados.");
        }
    }

    public static void cargarVentasDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("ventas.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                int monto = Integer.parseInt(datos[0]);
                String vehiculoVendido = datos[1];
                String tipoVehiculo = datos[2];
                String nombre = datos[3];
                String apellido = datos[4];
                int documento = Integer.parseInt(datos[5]);

                Registro venta = new Registro(monto, vehiculoVendido, tipoVehiculo, nombre, apellido, documento);
                listaVentas.add(venta);
            }
        } catch (IOException e) {
            System.out.println("No se encontraron ventas guardadas.");
        }
    }
}
