package com.example.EstructuraDatosIntegradora.model.estructura;

import java.util.ArrayList;
import java.util.List;

public class ArbolBinario<T> {

    private NodoArbol<T> raiz;
    private final PrioridadExtractor<T> extractor;

    public interface PrioridadExtractor<T> {
        int obtenerClave(T elemento);
    }

    public ArbolBinario(PrioridadExtractor<T> extractor) {
        this.extractor = extractor;
        this.raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public void insertar(T elemento) {
        raiz = insertarRec(raiz, elemento);
    }

    private NodoArbol<T> insertarRec(NodoArbol<T> nodo, T elemento) {
        if (nodo == null) {
            return new NodoArbol<>(elemento);
        }

        int claveElemento = extractor.obtenerClave(elemento);
        int claveNodo = extractor.obtenerClave(nodo.getDato());

        if (claveElemento < claveNodo) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), elemento));
        } else {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), elemento));
        }

        return nodo;
    }

    public List<T> recorridoInorden() {
        List<T> resultado = new ArrayList<>();
        recorridoInordenRec(raiz, resultado);
        return resultado;
    }

    private void recorridoInordenRec(NodoArbol<T> nodo, List<T> lista) {
        if (nodo != null) {
            recorridoInordenRec(nodo.getIzquierdo(), lista);
            lista.add(nodo.getDato());
            recorridoInordenRec(nodo.getDerecho(), lista);
        }
    }
}
