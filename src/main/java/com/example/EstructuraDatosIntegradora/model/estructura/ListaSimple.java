package com.example.EstructuraDatosIntegradora.model.estructura;

public class ListaSimple<T extends Comparable<T>> {

    private Nodo<T> cabeza;

    public ListaSimple() {
        cabeza = null;
    }

    // Agregar al inicio
    public void agregarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
    }

    // Agregar al final
    public void agregarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }
        Nodo<T> aux = cabeza;
        while (aux.getSiguiente() != null) {
            aux = aux.getSiguiente();
        }
        aux.setSiguiente(nuevo);
    }

    // Buscar por valor
    public Nodo<T> buscar(T valor) {
        Nodo<T> aux = cabeza;
        while (aux != null) {
            if (aux.getValor().equals(valor)) {
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    // Insertar antes de un valor
    public void insertarAntes(T referencia, T nuevoDato) {
        if (cabeza == null) return;

        if (cabeza.getValor().equals(referencia)) {
            agregarInicio(nuevoDato);
            return;
        }

        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null &&
                !actual.getSiguiente().getValor().equals(referencia)) {
            actual = actual.getSiguiente();
        }

        if (actual.getSiguiente() != null) {
            Nodo<T> nuevo = new Nodo<>(nuevoDato);
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
        }
    }

    // Insertar después de un valor
    public void insertarDespues(T referencia, T nuevoDato) {
        Nodo<T> nodoRef = buscar(referencia);
        if (nodoRef != null) {
            Nodo<T> nuevo = new Nodo<>(nuevoDato);
            nuevo.setSiguiente(nodoRef.getSiguiente());
            nodoRef.setSiguiente(nuevo);
        }
    }

    // Eliminar un nodo
    public void eliminar(T valor) {
        if (cabeza == null) return;

        if (cabeza.getValor().equals(valor)) {
            cabeza = cabeza.getSiguiente();
            return;
        }

        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null &&
                !actual.getSiguiente().getValor().equals(valor)) {
            actual = actual.getSiguiente();
        }

        if (actual.getSiguiente() != null) {
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
        }
    }

    // Ordenar (burbuja)
    public void ordenar() {
        if (cabeza == null) return;

        Nodo<T> actual = cabeza;
        Nodo<T> siguiente;
        T temp;

        while (actual != null) {
            siguiente = actual.getSiguiente();
            while (siguiente != null) {
                if (actual.getValor().compareTo(siguiente.getValor()) > 0) {
                    temp = actual.getValor();
                    actual.setValor(siguiente.getValor());
                    siguiente.setValor(temp);
                }
                siguiente = siguiente.getSiguiente();
            }
            actual = actual.getSiguiente();
        }
    }

    // Convertir la lista a un arreglo → útil para Thymeleaf
    public T[] toArray() {
        int size = tamaño();
        T[] arr = (T[]) new Comparable[size];
        Nodo<T> aux = cabeza;
        int index = 0;

        while (aux != null) {
            arr[index++] = aux.getValor();
            aux = aux.getSiguiente();
        }
        return arr;
    }

    // Tamaño de la lista
    public int tamaño() {
        int contador = 0;
        Nodo<T> aux = cabeza;
        while (aux != null) {
            contador++;
            aux = aux.getSiguiente();
        }
        return contador;
    }

    // Mostrar por consola (para pruebas)
    public void mostrar() {
        Nodo<T> aux = cabeza;
        while (aux != null) {
            System.out.print(aux.getValor() + " -> ");
            aux = aux.getSiguiente();
        }
        System.out.println("null");
    }

    // Obtener cabeza (si se necesita recorrer)
    public Nodo<T> getCabeza() {
        return cabeza;
    }
}
