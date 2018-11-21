package edu.sdsu.cs.datastructures;

import java.util.*;

public class DirectedGraph<V> implements IGraph<V> {

    Map<V, LinkedList<V>> listOfItems = new TreeMap();

    public DirectedGraph() {

    }

    @Override
    public void add(V vertexName) {
        listOfItems.put((V) vertexName, new LinkedList<V>());
    }

    @Override
    public void connect(V start, V destination) {
        try {
            LinkedList<V> updatedGraph = listOfItems.get((V) start);
            updatedGraph.add((V) destination);
            listOfItems.put((V) start, updatedGraph);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        listOfItems.clear();
    }

    @Override
    public boolean contains(V label) {
        for(V item: listOfItems.keySet()) {
            if (listOfItems.get(item).contains(label)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void disconnect(V start, V destination) throws NoSuchElementException{
        try {
            LinkedList<V> updatedGraph = listOfItems.get((V) start);
            updatedGraph.remove((V) destination);
            listOfItems.put((V) start, updatedGraph);
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isConnected(V start, V destination) throws NoSuchElementException {
        List<V> updatedGraph = listOfItems.get((V) start);
        return updatedGraph.contains((V) destination);
    }

    @Override
    public Iterable<V> neighbors(V vertexName) throws NoSuchElementException {
        List<V> neighborList = listOfItems.get((V) vertexName);
        return neighborList;
    }

    @Override
    public void remove(V vertexName) throws NoSuchElementException {
        listOfItems.remove((V) vertexName);
    }

    @Override
    public List shortestPath(V start, V destination) {
        return new LinkedList();
    }

    @Override
    public int size() {
        return listOfItems.keySet().size();
    }

    @Override
    public Iterable<V> vertices() {
        List<V> verticesList = new LinkedList<>();
        for(V item: listOfItems.keySet()) {
            verticesList.add(item);
        }
        return verticesList;
    }

    @Override
    public IGraph<V> connectedGraph(V origin) {
        IGraph<V> directedGraph = new DirectedGraph<>();

        return directedGraph;
    }
}
