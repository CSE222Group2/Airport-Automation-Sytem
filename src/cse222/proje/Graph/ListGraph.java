package cse222.proje.Graph;

import java.util.*;

/**
 * A ListGraph is an extension of the AbstractGraph abstract class
 * that uses an array of lists to represent the edges.
 */
public class ListGraph extends AbstractGraph {

	/**
	 * Holds vertices of the graph.
	 */
	private Set<Vertex> vertices;

	/**
	 * A map of linked list that holds the edges that originate with the source of each vertex */
	private Map<Vertex, LinkedList<Edge>> edges;

	/**
	 * Construct a graph with the specified number of vertices and the directed flag.
	 * If the directed flag is true, this is a directed graph.
	 *
	 * @param numV     The number of vertices
	 * @param directed The directed flag
	 */
	public ListGraph(int numV, boolean directed) {
		super(numV, directed);
		vertices = new HashSet<>();
		edges = new HashMap<>();
	}


	/**
	 * Inserts the given vertex into the graph.
	 * @param vertex The vertex to be inserted
	 * @return true if vertex is inserted, false if it already exists in the graph
	 */
	@Override
	public boolean insertVertex(Vertex vertex) {
		if (!vertices.add(new Vertex(vertex.getVertex()))) {
			return false;
		}
		edges.put(vertex, new LinkedList<>());
		setNumV(getNumV() + 1);
		return true;
	}

	/**
	 * Removes the given vertex from the graph.
	 * @param vertex The vertex to be removed
	 * @throws NoSuchElementException if there is no such vertex to remove
	 */
	@Override
	public void removeVertex(Vertex vertex) throws NoSuchElementException
	{
		if (!vertices.contains(vertex)) {
			throw new NoSuchElementException("No such vertex to remove.");
		}
		LinkedList<Edge> list = edges.get(vertex);
		list.clear();
		vertices.remove(vertex);
		edges.remove(vertex);
		setNumV(getNumV() - 1);
	}

	@Override
	public void insertEdge(Edge edge) {
		Vertex source = edge.getSource();
		Vertex dest = edge.getDest();

		if (isEdge(source, dest)) {
			throw new UnsupportedOperationException("Edge already exists in the graph.");
		}

		if (edges.containsKey(source)) {
			edges.get(source).add(new Edge(edge.getSource(), edge.getDest(), edge.getWeight()));
		} else throw new NoSuchElementException("Invalid source vertex.");

		if (!isDirected()) {
			if (edges.containsKey(dest)) {
				edges.get(source).add(new Edge(edge.getSource(), edge.getDest(), edge.getWeight()));
			} else throw new NoSuchElementException("Invalid destination vertex.");
		}
	}

	/**
	 * Removes the specified edge from the graph.
	 * @param edge The edge to remove
	 * @return true if edge is removed, false if there is no such edge to remove.
	 */
	@Override
	public boolean removeEdge(Edge edge)
	{
		if (!isEdge(edge.getSource(), edge.getDest())) {
			return false;
		}
		LinkedList<Edge> list = edges.get(edge.getSource());
		list.remove(edge);

		if (!isDirected()) {
			Edge tmp = new Edge(edge.getDest(), edge.getSource());
			list.remove(tmp);
		}
		return true;
	}

	@Override
	public boolean isEdge(Vertex source, Vertex dest) {
		if (edges.containsKey(source)) {
			Edge edge = new Edge(source, dest);
			LinkedList<Edge> list = edges.get(source);
			return list.contains(edge);
		}
		return false;
	}

	@Override
	public Edge getEdge(Vertex source, Vertex dest) {
		if (edges.containsKey(source)) {
			Edge tmp = new Edge(source, dest);
			LinkedList<Edge> list = edges.get(source);
			for (Edge edge : list) {
				if (edge.equals(tmp)) {
					return edge;
				}
			}
		}
		return null;
	}

	@Override
	public Iterator<Edge> edgeIterator(Vertex source) {
		return edges.get(source).iterator();
	}

	@Override
	public String toString() {
		return "ListGraph{" +
				"edges=" + edges +
				"} ";
	}
}
