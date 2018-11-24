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
            boolean tmp = listOfItems.containsKey((V) destination);
            if(!tmp)
                throw new NoSuchElementException();
            updatedGraph.add((V) destination);

            listOfItems.put((V) start, updatedGraph);
        }
        catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void clear() {
        listOfItems.clear();
    }

    @Override
    public boolean contains(V label) {
        if (listOfItems.containsKey(label)) {
            return true;
        }
        return false;
    }

    @Override
    public void disconnect(V start, V destination) {
        try {
            LinkedList<V> updatedGraph = listOfItems.get((V) start);
            boolean tmp = updatedGraph.contains((V) destination);
            if(!tmp)
                throw new NoSuchElementException();
            updatedGraph.remove((V) destination);
            listOfItems.put((V) start, updatedGraph);
        }
        catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    private Set<V> checkVisited(List<V> graph, Set<V> visitedItems) {
        Set<V> finalVisitedItems = new HashSet<>(visitedItems);
        for(V item: graph) {
            if (!finalVisitedItems.contains(item)) {
                finalVisitedItems.add(item);
                finalVisitedItems.addAll(checkVisited(listOfItems.get(item),finalVisitedItems));
            }

        }
        return finalVisitedItems;
    }

    @Override
    public boolean isConnected(V start, V destination) {
        List<V> updatedGraph = listOfItems.get((V) start);
        Set<V> visitedItems = new HashSet<>();
        visitedItems = checkVisited(updatedGraph,visitedItems);
        return visitedItems.contains((V) destination);
    }

    @Override
    public Iterable<V> neighbors(V vertexName) {
        try {
            List<V> neighborList = listOfItems.get((V) vertexName);
            boolean tmp = listOfItems.containsKey((V) vertexName);
            if(!tmp)
                throw new NoSuchElementException();
            return neighborList;
        }
        catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove(V vertexName) {
        if(!listOfItems.containsKey(vertexName)) {
            throw new NoSuchElementException();
        }
        listOfItems.remove((V) vertexName);
        for(V key: listOfItems.keySet()) {
            LinkedList<V> newList = listOfItems.get(key);
            if(newList.contains(vertexName)) {
                newList.remove(vertexName);
                listOfItems.put(key, newList);
            }
        }
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
        List<V> updatedGraph = listOfItems.get((V) origin);
        Set<V> visitedItems = new HashSet<>();
        visitedItems.add(origin);
        visitedItems = checkVisited(updatedGraph,visitedItems);
        IGraph<V> directedGraph = new DirectedGraph<>();
        for(V item: visitedItems) {
            System.out.println("Has: " + item.toString());
            directedGraph.add((V) item);
        }
        for(V item: visitedItems) {
            for(V connectedItem: listOfItems.get((V)item)) {
                directedGraph.connect(item, connectedItem);
            }
        }
        return directedGraph;
    }
}
