package cse222.proje.Graph;

import java.util.Objects;

public class Edge {

	/**
	 * Source vertex
	 */
	private final Vertex source;

	/**
	 * Destination vertex
	 */
	private final Vertex dest;

	/**
	 * The weight
	 */
	private final double weight;

	/**
	 * Constructs a weighted edge with a source of from and a destination of to.
	 * @param source - The source vertex
	 * @param dest * The destination vertex
	 */
	public Edge(Vertex source, Vertex dest) {
		this.source = source;
		this.dest = dest;
		this.weight = 1.0;
	}

	/**
	 * Constructs a weighted edge with a source of from and a destination of to.
	 * Sets the weight to w.
	 * @param source - The source vertex
	 * @param dest - The destination vertex
	 * @param weight - The weight
	 */
	public Edge(Vertex source, Vertex dest, double weight) {
		this.source = source;
		this.dest = dest;
		this.weight = weight;
	}

	/**
	 * Get the source
	 * @return The source vertex
	 */
	public Vertex getSource() {
		return source;
	}

	/**
	 * Get the destination
	 * @return The destination vertex
	 */
	public Vertex getDest() {
		return dest;
	}

	/**
	 * Get the weight
	 * @return the value of weight
	 */
	public double getWeight() {
		return weight;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Edge edge = (Edge) o;
		return Objects.equals(source, edge.source) &&
				Objects.equals(dest, edge.dest);
	}

	@Override
	public int hashCode() {
		return Objects.hash(source, dest);
	}

	/**
	 * Returns a String representation of the edge
	 * @return A String representation of the edge
	 */
	@Override
	public String toString() {
		return "Edge{" +
				"source=" + source +
				", dest=" + dest +
				", weight=" + weight +
				'}';
	}
}
