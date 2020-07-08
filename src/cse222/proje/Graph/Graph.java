package cse222.proje.Graph;

import java.util.Set;
import java.util.Iterator;
import java.util.NoSuchElementException;

public interface Graph {

	/**
	 * Return the number of vertices.
	 * @return The number of vertices
	 */
	int getNumV();

	/**
	 * Determine whether this is a directed graph.
	 * @return true if this is a directed graph
	 */
	boolean isDirected();

	/**
	 * Inserts the given vertex into the graph.
	 * @param vertex The vertex to be inserted
	 * @return true if vertex is inserted, false if it already exists in the graph
	 */
	public boolean insertVertex(Vertex vertex);

	/**
	 * Removes the given vertex from the graph.
	 * @param vertex The vertex to be removed
	 * @throws NoSuchElementException if there is no such vertex to remove
	 */
	public void removeVertex(Vertex vertex);

	/**
	 * Insert a new edge into the graph.
	 * @param edge The new edge
	 */
	void insertEdge(Edge edge);

	/**
	 * Removes the specified edge from the graph.
	 * @param edge The edge to remove
	 * @return true if edge is removed, false if there is no such edge to remove.
	 */
	public boolean removeEdge(Edge edge);

	/**
	 * Determine whether an edge exists.
	 * @param source The source vertex
	 * @param dest The destination vertex
	 * @return true if there is an edge from source to dest
	 */
	boolean isEdge(Vertex source, Vertex dest);

	/**
	 * Get the edge between two vertices.
	 * @param source The source vertex
	 * @param dest The destination vertex
	 * @return The Edge between these two vertices or an Edge with a weight of
	 * Double.POSITIVE_INFINITY if there is no edge
	 */
	Edge getEdge(Vertex source, Vertex dest);

	/**
	 * Get the set of vertices.
	 * @return set of vertices.
	 */
	Set<Vertex> getVertices();

	/** Return an iterator to the edges connected to a given vertex.
	 * @param source The source vertex
	 * @return An Iterator to the vertices connected to source
	 */
	Iterator<Edge> edgeIterator(Vertex source);
}
