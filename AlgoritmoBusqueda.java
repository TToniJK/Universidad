import java.util.Scanner;

public class AlgoritmoBusqueda {

    // Método principal
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el tamaño del arreglo: ");
        int n = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        String[] palabras = new String[n];

        // Llenado del arreglo
        System.out.println("\n--- Ingrese las palabras ---");
        for (int i = 0; i < n; i++) {
            System.out.print("Palabra " + (i + 1) + ": ");
            palabras[i] = sc.nextLine();
        }

        // Menú de ordenamiento
        int opcion;
        do {
            System.out.println("\n--- MENÚ DE ORDENAMIENTO ---");
            System.out.println("1. Selección");
            System.out.println("2. Inserción");
            System.out.println("3. Burbuja");
            System.out.println("4. Mezcla (Merge Sort)");
            System.out.println("5. Rápido (Quick Sort)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            String[] copia = palabras.clone(); // copiar el arreglo original

            switch (opcion) {
                case 1 -> seleccion(copia);
                case 2 -> insercion(copia);
                case 3 -> burbuja(copia);
                case 4 -> mezcla(copia, 0, copia.length - 1);
                case 5 -> rapido(copia, 0, copia.length - 1);
                case 6 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida.");
            }

            if (opcion >= 1 && opcion <= 5) {
                System.out.println("\nArreglo ordenado:");
                mostrarArreglo(copia);

                // Búsqueda binaria
                System.out.print("\nIngrese la palabra a buscar: ");
                String palabraBuscar = sc.nextLine();
                int resultado = busquedaBinaria(copia, palabraBuscar);

                if (resultado != -1) {
                    System.out.println("Palabra encontrada en la posición: " + resultado);
                } else {
                    System.out.println("Palabra no encontrada.");
                }
            }

        } while (opcion != 6);
    }

    // --- MÉTODO AUXILIAR PARA IMPRIMIR EL ARREGLO ---
    public static void mostrarArreglo(String[] arreglo) {
        for (String s : arreglo) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    // --- SELECCIÓN ---
    public static void seleccion(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareToIgnoreCase(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            String temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    // --- INSERCIÓN ---
    public static void insercion(String[] arr) {
        for (int i = 1; i < arr.length; i++) {
            String key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].compareToIgnoreCase(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // --- BURBUJA ---
    public static void burbuja(String[] arr) {
        boolean swapped;
        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareToIgnoreCase(arr[j + 1]) > 0) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // --- MEZCLA (MERGE SORT) ---
    public static void mezcla(String[] arr, int izq, int der) {
        if (izq < der) {
            int medio = (izq + der) / 2;
            mezcla(arr, izq, medio);
            mezcla(arr, medio + 1, der);
            combinar(arr, izq, medio, der);
        }
    }

    public static void combinar(String[] arr, int izq, int medio, int der) {
        int n1 = medio - izq + 1;
        int n2 = der - medio;

        String[] L = new String[n1];
        String[] R = new String[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[izq + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[medio + 1 + j];

        int i = 0, j = 0, k = izq;

        while (i < n1 && j < n2) {
            if (L[i].compareToIgnoreCase(R[j]) <= 0) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // --- RÁPIDO (QUICK SORT) ---
    public static void rapido(String[] arr, int izq, int der) {
        if (izq < der) {
            int pi = particion(arr, izq, der);
            rapido(arr, izq, pi - 1);
            rapido(arr, pi + 1, der);
        }
    }

    public static int particion(String[] arr, int izq, int der) {
        String pivote = arr[der];
        int i = izq - 1;

        for (int j = izq; j < der; j++) {
            if (arr[j].compareToIgnoreCase(pivote) <= 0) {
                i++;
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String temp = arr[i + 1];
        arr[i + 1] = arr[der];
        arr[der] = temp;

        return i + 1;
    }

    // --- BÚSQUEDA BINARIA ---
    public static int busquedaBinaria(String[] arr, String palabra) {
        int inicio = 0;
        int fin = arr.length - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            int comp = palabra.compareToIgnoreCase(arr[medio]);

            if (comp == 0) return medio;
            else if (comp < 0) fin = medio - 1;
            else inicio = medio + 1;
        }
        return -1;
    }
}
