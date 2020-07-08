package cse222.proje.Graph;

import cse222.proje.Sector;

public class Vertex implements Comparable<Vertex> {

	/**
	 * Value of the vertex
	 */
	private final Sector vertex;

	/**
	 * Boolean visited field for bfs and dfs.
	 */
	private boolean visited;

	/**
	 * Constructor that takes a Sector object
	 * @param vertex Sector object
	 */
	public Vertex(Sector vertex) {
		this.vertex = vertex;
		this.visited = false;
	}

	/**
	 * Accessor method for vertex
	 * @return Sector object
	 */
	public Sector getVertex() {
		return this.vertex;
	}

	/**
	 * Accessor method for visited field
	 * @return visited
	 */
	public boolean isVisited() {
		return this.visited;
	}

	/**
	 * Set visited field to given parameter
	 * @param visited new visited
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Vertex)) {
			return false;
		}

		Vertex vertexObj = (Vertex) obj;
		return vertexObj.vertex == this.vertex;
	}

	@Override
	public int hashCode() {
		return vertex.hashCode();
	}

	/**
	 * compareTo method for Vertex objects
	 * @param o - other Vertex object
	 * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(Vertex o) {
		return Integer.compare(vertex.getSectorID(), o.getVertex().getSectorID());
	}

	/**
	 * Return a String representation of the vertex
	 * @return A String representation of the vertex
	 */
	@Override
	public String toString() {
		return "Vertex{" +
				"vertex=" + vertex +
				'}';
	}
}
