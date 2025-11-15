package com.example.EstructuraDatosIntegradora.model.estructura;

import java.util.ArrayList;
import java.util.List;

public class ListaSimple<T> {

    private Nodo<T> cabeza;
    private int tamanio;

    public ListaSimple() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Agrega un elemento al final de la lista.
     */
    public void agregar(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            Nodo<T> aux = cabeza;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
        tamanio++;
    }

    /**
     * Busca un elemento en la lista usando equals().
     *
     * @param elemento elemento a buscar
     * @return el elemento encontrado o null si no existe
     */
    public T buscar(T elemento) {
        Nodo<T> aux = cabeza;
        while (aux != null) {
            if (aux.getDato().equals(elemento)) {
                return aux.getDato();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    /**
     * Elimina la primera aparición del elemento en la lista.
     *
     * @param elemento elemento a eliminar
     * @return true si se eliminó, false si no se encontró
     */
    public boolean eliminar(T elemento) {
        if (estaVacia()) {
            return false;
        }

        // Caso: la cabeza es la que se elimina
        if (cabeza.getDato().equals(elemento)) {
            cabeza = cabeza.getSiguiente();
            tamanio--;
            return true;
        }

        Nodo<T> aux = cabeza;
        while (aux.getSiguiente() != null &&
                !aux.getSiguiente().getDato().equals(elemento)) {
            aux = aux.getSiguiente();
        }

        if (aux.getSiguiente() == null) {
            return false; // no se encontró
        }

        aux.setSiguiente(aux.getSiguiente().getSiguiente());
        tamanio--;
        return true;
    }

    /**
     * Devuelve todos los elementos de la lista en un List de Java.
     */
    public List<T> obtenerTodos() {
        List<T> elementos = new ArrayList<>();
        Nodo<T> aux = cabeza;
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
