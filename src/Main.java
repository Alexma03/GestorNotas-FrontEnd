import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("¿Que fichero quieres usar?");
        Scanner sc = new Scanner(System.in);
        String nombreFichero = sc.nextLine();
        Scanner num = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("¿Que quieres hacer?");
            System.out.println("1. Escribir una nota:");
            System.out.println("2. Leer una nota");
            System.out.println("3. Modificar una nota");
            System.out.println("4. Eliminar una nota");
            System.out.println("5. Generar 100 ids y notas aleatorias");
            System.out.println("6. Leer todas las notas");
            System.out.println("0. Salir");
            try {
                opcion = num.nextInt();
            } catch (Exception e) {
                System.out.println("Introduce un numero");
                opcion = 0;
            }
            Scanner doubleNum = new Scanner(System.in);
            //lanza Gestor.jar y en los argumentos le pasa la opcion el nombre del fichero el id y la nota
            switch (opcion) {
                case 1 -> nuevaNota(num, doubleNum, nombreFichero);
                case 2 -> leerNota(nombreFichero, num);
                case 3 -> modificarNota(num, doubleNum, nombreFichero);
                case 4 -> eliminarNota(nombreFichero, num);
                case 5 -> crear100notas(nombreFichero);
                case 6 -> leerTodas(nombreFichero);
            }

        } while (opcion != 0);
    }

    /**
     * Lee todas las notas
     *
     * @param nombreFichero Nombre del fichero
     * @throws IOException Si no se puede leer el fichero
     * @throws InterruptedException Si el proceso se interrumpe
     * @throws IllegalArgumentException Si el id ya existe
     * @throws NumberFormatException Si el id no es un numero
     */
    private static void leerTodas(String nombreFichero) {
        String[] argumentos = {"r", nombreFichero, "-1"};
        try {
            Process p = Runtime.getRuntime().exec("java -jar Gestor.jar " + argumentos[0] + " " + argumentos[1] + " " + argumentos[2]);
            flujoDatos(p);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea 100 notas aleatorias
     *
     * @param nombreFichero Nombre del fichero
     * @throws IOException              Si no se puede leer el fichero
     * @throws NumberFormatException    Si el id no es un numero
     * @throws InterruptedException     Si el proceso se interrumpe
     * @throws IllegalArgumentException Si el id ya existe
     */
    private static void crear100notas(String nombreFichero) {
        for (int i = 0; i < 100; i++) {
            int id = (int) (Math.random() * 100 + 1);
            double nota = (double) Math.round(Math.random() * 1000) / 100;
            String[] argumentos = {"w", nombreFichero, String.valueOf(id), String.valueOf(nota)};
            try {
                Process p = Runtime.getRuntime().exec("java -jar Gestor.jar w " + argumentos[1] + " " + argumentos[2] + " " + argumentos[3]);
                flujoDatos(p);
            } catch (IOException | IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Elimina una nota
     *
     * @param nombreFichero Nombre del fichero
     * @param num           Scanner para leer el id
     * @throws IOException           Si no se puede leer el fichero
     * @throws NumberFormatException Si el id no es un numero
     * @throws InterruptedException  Si el proceso se interrumpe
     */
    private static void eliminarNota(String nombreFichero, Scanner num) {
        System.out.println("Introduce el id:");
        try {
            int id = num.nextInt();
            String[] argumentos = {"e", nombreFichero, String.valueOf(id)};
            try {
                Process p = Runtime.getRuntime().exec("java -jar Gestor.jar " + argumentos[0] + " " + argumentos[1] + " " + argumentos[2]);
                flujoDatos(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Introduce un numero");
        }
    }

    /**
     * Modifica una nota
     *
     * @param num           Scanner para leer el id
     * @param doubleNum     Scanner para leer la nota
     * @param nombreFichero Nombre del fichero
     * @throws IOException                    Si no se puede leer el fichero
     * @throws NumberFormatException          Si el id o la nota no son numeros
     * @throws ArrayIndexOutOfBoundsException Si el id o la nota no son numeros
     */
    private static void modificarNota(Scanner num, Scanner doubleNum, String nombreFichero) {
        try {
            System.out.println("Introduce el id:");
            int id = num.nextInt();
            System.out.println("Introduce la nota:");
            double nota = Double.parseDouble(doubleNum.next());
            String[] argumentos = {"m", nombreFichero, String.valueOf(id), String.valueOf(nota)};
            try {
                Process p = Runtime.getRuntime().exec("java -jar Gestor.jar " + argumentos[0] + " " + argumentos[1] + " " + argumentos[2] + " " + argumentos[3]);
                flujoDatos(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Introduce un numero");
        }
    }

    /**
     * Lee una nota
     *
     * @param nombreFichero Nombre del fichero
     * @param num           Scanner para leer el id
     * @throws IOException                    Si no se puede leer el fichero
     * @throws NumberFormatException          Si el id no es un numero
     * @throws ArrayIndexOutOfBoundsException Si el id no es un numero
     */
    private static void leerNota(String nombreFichero, Scanner num) {
        try {
            System.out.println("Introduce el id:");
            int id = num.nextInt();
            String[] argumentos = {"r", nombreFichero, String.valueOf(id)};
            try {
                Process p = Runtime.getRuntime().exec("java -jar Gestor.jar " + argumentos[0] + " " + argumentos[1] + " " + argumentos[2]);
                flujoDatos(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Introduce un numero");
        }
    }

    /**
     * Escribir una nota de un estudiante concreto indicando su id y su nota.
     *
     * @param num           Scanner para leer el id.
     * @param doubleNum     Scanner para leer la nota.
     * @param nombreFichero Nombre del fichero.
     * @throws NumberFormatException          Si el id o la nota no son numeros.
     * @throws ArrayIndexOutOfBoundsException Si el id o la nota no son numeros.
     * @throws IOException                    Si no se puede escribir en el fichero.
     */
    private static void nuevaNota(Scanner num, Scanner doubleNum, String nombreFichero) {
        try {
            System.out.println("Introduce el id:");
            int id = num.nextInt();
            System.out.println("Introduce la nota:");
            double nota = Double.parseDouble(doubleNum.next());
            String[] argumentos = {"w", nombreFichero, String.valueOf(id), String.valueOf(nota)};
            try {
                Process p = Runtime.getRuntime().exec("java -jar Gestor.jar " + argumentos[0] + " " + argumentos[1] + " " + argumentos[2] + " " + argumentos[3]);
                flujoDatos(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Introduce un numero");
        }
    }

    /**
     * Lee el flujo de datos del proceso.
     *
     * @param p Proceso.
     * @throws IOException Si no se puede leer el flujo de datos.
     */
    private static void flujoDatos(Process p) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
}
