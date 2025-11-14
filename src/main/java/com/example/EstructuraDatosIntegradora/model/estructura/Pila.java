package com.example.EstructuraDatosIntegradora.model.estructura;

public class Pila<T> {
    private Nodo<T> cima;
    private int size;

    public Pila() {
        this.cima = null;
        this.size = 0;
    }

    public void insertar(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        size++;
    }

    public boolean pilaVacia() {
        return cima == null;
    }

    public T quitar() { // pop
        if (pilaVacia()) {
            System.out.println("La pila está vacía");
            return null;
        }
        T aux = cima.getValor();
        cima = cima.getSiguiente();
        size--;
        return aux;
    }

    public T verCima() { // peek
        if (pilaVacia()) {
            System.out.println("Pila vacía");
            return null;
        }
        return cima.getValor();
    }

    public void limpiarPila() {
        cima = null;
        size = 0;
        System.out.println("La pila fue limpiada");
    }

    public int tamanioPila() {
        return size;
    }

    public void mostrarPila() {
        if (pilaVacia()) {
            System.out.println("La pila está vacía");
            return;
        }
        Nodo<T> aux = cima;
        while (aux != null) {
            System.out.println(aux.getValor() + " ");
            aux = aux.getSiguiente();
        }
    }

    public String[] toArray() {
        String[] arr = new String[size];
        Nodo<T> aux = cima;
        int index = 0;

        while (aux != null) {
            arr[index++] = aux.getValor().toString();
            aux = aux.getSiguiente();
        }
        return arr;
    }
}
