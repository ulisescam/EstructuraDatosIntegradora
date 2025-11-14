package com.example.EstructuraDatosIntegradora.model.estructura;

public class Nodo<T> {
    protected T dato;
    protected Nodo<T> siguiente;

    public Nodo(T valor) {
        this.dato = valor;
        this.siguiente = null;
    }

    public T getValor() {
        return dato;
    }

    public void setValor(T valor) {
        this.dato = valor;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}
