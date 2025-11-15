package com.example.EstructuraDatosIntegradora.model.estructura;

import java.util.ArrayList;
import java.util.List;

public class Pila<T> {

    private Nodo<T> tope;
    private int tamanio;

    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    /**
     * Apila un elemento en el tope de la pila.
     */
    public void apilar(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        nuevo.setSiguiente(tope);
        tope = nuevo;
        tamanio++;
    }

    /**
     * Desapila el elemento del tope y lo devuelve.
     */
    public T desapilar() {
        if (estaVacia()) {
            System.out.println("La pila está vacía.");
            return null;
        }
        T dato = tope.getDato();
        tope = tope.getSiguiente();
        tamanio--;
        return dato;
    }

    /**
     * Devuelve el elemento del tope sin eliminarlo.
     */
    public T verTope() {
        if (estaVacia()) {
            return null;
        }
        return tope.getDato();
    }

    /**
     * Devuelve todos los elementos de la pila en una lista.
     * El primer elemento de la lista corresponde al tope de la pila.
     */
    public List<T> obtenerElementos() {
        List<T> elementos = new ArrayList<>();
        Nodo<T> aux = tope;
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
