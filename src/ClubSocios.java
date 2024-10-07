import java.util.ArrayList;
import java.util.Scanner;

// Clase Socio con sus atributos
class Socio {
    String nombre;
    int edad;
    String cargo;

    public Socio(String nombre, int edad, String cargo) {
        this.nombre = nombre;
        this.edad = edad;
        this.cargo = cargo;
    }

    // Sobrescribimos equals para comparar si dos socios son iguales
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Socio socio = (Socio) obj;
        return edad == socio.edad &&
               nombre.equals(socio.nombre) &&
               cargo.equals(socio.cargo);
    }
}

// Clase principal que maneja el club
public class ClubSocios {
    private static final int LIMITE_SOCIOS = 7;
    private ArrayList<Socio> socios = new ArrayList<>();

    // Método para agregar un nuevo socio
    public void agregarSocio(String nombre, int edad, String cargo) throws Exception {
        Socio nuevoSocio = new Socio(nombre, edad, cargo);

        // Excepción si el club ya tiene 7 socios
        if (socios.size() >= LIMITE_SOCIOS) {
            throw new Exception("Error: Se ha excedido el límite de socios permitidos (máximo 7).");
        }

        // Excepción si el socio ya existe
        if (socios.contains(nuevoSocio)) {
            throw new Exception("Error: El socio con ese nombre, edad y cargo ya está afiliado al club.");
        }

        // Agregar el nuevo socio a la lista
        socios.add(nuevoSocio);
        System.out.println("Socio agregado exitosamente.");
    }

    // Método para mostrar la cantidad actual de socios
    public void mostrarSocios() {
        System.out.println("Cantidad de socios actuales: " + socios.size());
        for (Socio socio : socios) {
            System.out.println(socio.nombre + " - " + socio.edad + " años - " + socio.cargo);
        }
    }

    public static void main(String[] args) {
        ClubSocios club = new ClubSocios();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        // Menú para gestionar el club
        while (continuar) {
            System.out.println("\n--- Club de Socios ---");
            System.out.println("1. Agregar nuevo socio");
            System.out.println("2. Mostrar socios actuales");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del socio: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la edad del socio: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    System.out.print("Ingrese el cargo del socio: ");
                    String cargo = scanner.nextLine();
                    try {
                        club.agregarSocio(nombre, edad, cargo);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    club.mostrarSocios();
                    break;

                case 3:
                    continuar = false;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }
}
