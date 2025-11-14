package com.example.EstructuraDatosIntegradora.model.estructura;

public class Cola<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int size;

    // Constructor: crear cola vacía
    public Cola() {
        inicio = null;
        fin = null;
        size = 0;
    }

    // Verificar si la cola está vacía
    public boolean isVacia() {
        return inicio == null;
    }

    // Agregar elementos al final
    public void agregarElementos(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);

        if (isVacia()) {
            inicio = nuevo;
        } else {
            fin.setSiguiente(nuevo);
        }

        fin = nuevo;
        size++;
    }

    // Quitar elemento: elimina y retorna el dato del inicio
    public T quitarElemento() {
        if (isVacia()) {
            System.out.println("No hay elementos");
            return null;
        }

        T aux = inicio.getValor();
        inicio = inicio.getSiguiente();
        size--;

        // Si la cola queda vacía, reiniciar fin también
        if (inicio == null) {
            fin = null;
        }

        return aux;
    }

    // Ver inicio sin eliminar
    public T verInicio() {
        if (isVacia()) {
            System.out.println("No hay elementos");
            return null;
        }
        return inicio.getValor();
    }

    // Mostrar todos los elementos
    public void mostrarElementos() {
        if (isVacia()) {
            System.out.println("NO hay elementos");
            return;
        }

        Nodo<T> actual = inicio;
        System.out.print("Elementos: ");
        while (actual != null) {
            System.out.print(actual.getValor() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println();
    }

    // Borrar todos
    public void borrarElementos() {
        inicio = null;
        fin = null;
        size = 0;
    }

    // Metodo tamaño
    public int tamanoCola() {
        return size;
    }

    // 👇 Importante para mostrar en Thymeleaf
    public String[] toArray() {
        String[] arr = new String[size];
        Nodo<T> actual = inicio;
        int index = 0;

        while (actual != null) {
            arr[index++] = actual.getValor().toString();
            actual = actual.getSiguiente();
        }

        return arr;
    }
}
