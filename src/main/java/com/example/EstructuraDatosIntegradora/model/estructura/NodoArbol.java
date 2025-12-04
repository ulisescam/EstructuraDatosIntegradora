package com.example.EstructuraDatosIntegradora.model.estructura;

public class NodoArbol<T> {
    private T dato;
    private NodoArbol<T> izquierdo;
    private NodoArbol<T> derecho;

    public NodoArbol(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public T getDato() { return dato; }
    public NodoArbol<T> getIzquierdo() { return izquierdo; }
    public NodoArbol<T> getDerecho() { return derecho; }

    public void setIzquierdo(NodoArbol<T> izquierdo) { this.izquierdo = izquierdo; }
    public void setDerecho(NodoArbol<T> derecho) { this.derecho = derecho; }
}
