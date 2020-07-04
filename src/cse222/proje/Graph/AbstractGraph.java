package cse222.proje.Graph;

public abstract class AbstractGraph implements Graph {

	/**
	 * The number of vertices
	 * */
	private int numV;

	/**
	 * Flag to indicate whether this is a directed graph
	 * */
	private boolean directed;

	/**
	 * Construct a graph with the specified number of vertices and the directed flag.
	 * If the directed flag is true, this is a directed graph.
	 * @param numV The number of vertices
	 * @param directed The directed flag
	 */
	public AbstractGraph(int numV, boolean directed) {
		this.numV = numV;
		this.directed = directed;
	}

	/**
	 * Return the number of vertices.
	 * @return The number of vertices
	 */
	public int getNumV() {
		return numV;
	}

	/**
	 * Set numV to the given parameter.
	 * @param numV new numV
	 */
	public void setNumV(int numV)
	{
		this.numV = numV;
	}

	/**
	 * Return whether this is a directed graph.
	 * @return true if this is a directed graph
	 */
	public boolean isDirected() {
		return directed;
	}
}
