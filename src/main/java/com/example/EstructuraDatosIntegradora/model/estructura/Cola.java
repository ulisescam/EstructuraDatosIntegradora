package com.example.EstructuraDatosIntegradora.model.estructura;

import java.util.ArrayList;
import java.util.List;


public class Cola<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int tamanio;

    public Cola() {
        this.inicio = null;
        this.fin = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    /**
     * Encola un elemento al final de la cola.
     */
    public void encolar(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (estaVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        tamanio++;
    }

    /**
     * Desencola el elemento del inicio de la cola.
     */
    public T desencolar() {
        if (estaVacia()) {
            System.out.println("La cola está vacía.");
            return null;
        }
        T dato = inicio.getDato();
        inicio = inicio.getSiguiente();
        if (inicio == null) {
            fin = null;
        }
        tamanio--;
        return dato;
    }

    /**
     * Devuelve el elemento del frente sin desencolarlo.
     */
    public T verFrente() {
        if (estaVacia()) {
            return null;
        }
        return inicio.getDato();
    }

    /**
     * Devuelve todos los elementos de la cola en una lista.
     * El primer elemento de la lista corresponde al inicio de la cola.
     */
    public List<T> obtenerElementos() {
        List<T> elementos = new ArrayList<>();
        Nodo<T> aux = inicio;
        while (aux != null) {
            elementos.add(aux.getDato());
            aux = aux.getSiguiente();
        }
        return elementos;
    }

    public int getTamanio() {
        return tamanio;
    }
}
