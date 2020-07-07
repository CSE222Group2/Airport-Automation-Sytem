package cse222.proje.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DijkstrasAlgorithm {

	public static void main(String[] args) {
		Sector s1 = new Sector(1, Sector.CleanningStates.DIRTY, Sector.SecurityStates.SECURE);
		Sector s2 = new Sector(2, Sector.CleanningStates.DIRTY, Sector.SecurityStates.SECURE);
		Sector s3 = new Sector(3, Sector.CleanningStates.CLEAN, Sector.SecurityStates.INSECURE);
		Sector s4 = new Sector(4, Sector.CleanningStates.DIRTY, Sector.SecurityStates.INSECURE);
		Sector s5 = new Sector(5, Sector.CleanningStates.DIRTY, Sector.SecurityStates.SECURE);
		Sector s6 = new Sector(6, Sector.CleanningStates.DIRTY, Sector.SecurityStates.INSECURE);
		Sector s7 = new Sector(7, Sector.CleanningStates.DIRTY, Sector.SecurityStates.SECURE);
		Sector s8 = new Sector(8, Sector.CleanningStates.DIRTY, Sector.SecurityStates.SECURE);
		Sector s9 = new Sector(9, Sector.CleanningStates.CLEAN, Sector.SecurityStates.INSECURE);
		Sector s10 = new Sector(10, Sector.CleanningStates.DIRTY, Sector.SecurityStates.INSECURE);
		Sector s11 = new Sector(11, Sector.CleanningStates.DIRTY, Sector.SecurityStates.SECURE);
		Sector s12 = new Sector(12, Sector.CleanningStates.DIRTY, Sector.SecurityStates.INSECURE);

		Graph graph = new ListGraph(12, true);
		Vertex a = new Vertex(s1);
		Vertex b = new Vertex(s2);
		Vertex c = new Vertex(s3);
		Vertex d = new Vertex(s4);
		Vertex e = new Vertex(s5);
		Vertex f = new Vertex(s6);
		Vertex g = new Vertex(s7);
		Vertex h = new Vertex(s8);
		Vertex i = new Vertex(s9);
		Vertex j = new Vertex(s10);
		Vertex k = new Vertex(s11);
		Vertex l = new Vertex(s12);

		graph.insertVertex(a);
		graph.insertVertex(b);
		graph.insertVertex(c);
		graph.insertVertex(d);
		graph.insertVertex(e);
		graph.insertVertex(f);
		graph.insertVertex(g);
		graph.insertVertex(h);
		graph.insertVertex(i);
		graph.insertVertex(j);
		graph.insertVertex(k);
		graph.insertVertex(l);

		graph.insertEdge(new Edge(a, b, 2.0));
		graph.insertEdge(new Edge(a, d, 2.0));
		graph.insertEdge(new Edge(b, a, 2.0));
		graph.insertEdge(new Edge(b, c, 5.0));
		graph.insertEdge(new Edge(b, e, 1.0));
		graph.insertEdge(new Edge(c, f, 2.0));
		graph.insertEdge(new Edge(d, e, 5.0));
		graph.insertEdge(new Edge(d, g, 3.0));
		graph.insertEdge(new Edge(e, h, 6.0));
		graph.insertEdge(new Edge(e, b, 1.0));
		graph.insertEdge(new Edge(f, e, 4.0));
		graph.insertEdge(new Edge(f, i, 1.0));
		graph.insertEdge(new Edge(g, h, 8.0));
		graph.insertEdge(new Edge(g, j, 7.0));
		graph.insertEdge(new Edge(h, i, 1.0));
		graph.insertEdge(new Edge(i, f, 1.0));
		graph.insertEdge(new Edge(i, l, 3.0));
		graph.insertEdge(new Edge(i, h, 1.0));
		graph.insertEdge(new Edge(j, k, 2.0));
		graph.insertEdge(new Edge(k, h, 4.0));
		graph.insertEdge(new Edge(l, k, 5.0));
		graph.insertEdge(new Edge(l, i, 3.0));

		shortestPath(graph, a, i);
		System.out.println();
		System.out.println();
		shortestPath(graph, b, k);
		System.out.println();
		System.out.println();
		shortestPath(graph, c, h);
		System.out.println();
		System.out.println();
		shortestPath(graph, l, e);
	}

	public static void shortestPath(Graph graph, Vertex start, Vertex dest) {
		Map<Vertex, Double> dist = new HashMap<>();
		Map<Vertex, Vertex> prev = new HashMap<>();
		dijkstrasAlgorithm(graph, start, dist, prev);

		for (Map.Entry<Vertex, Double> entry : dist.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		System.out.println("Shortest path from " + start.getVertex().getSectorID() +
							" to " + dest.getVertex().getSectorID() + ": ");
		printPath(start, dest, prev);
	}

	public static void printPath(Vertex start, Vertex dest, Map<Vertex, Vertex> prev) {
		if (dest.equals(start)) {
			System.out.print(start.getVertex().getSectorID() + " ");
		} else {
			printPath(start, prev.get(dest), prev);
			System.out.print(dest.getVertex().getSectorID() + " ");
		}
	}

	public static void dijkstrasAlgorithm(Graph graph, Vertex start,
										  Map<Vertex, Double> dist,
										  Map<Vertex, Vertex> prev) {
		int numV = graph.getNumV();
		HashSet<Vertex> vMinusS = new HashSet<>(numV);

		Set<Vertex> vertexSet = graph.getVertices();

		for (Vertex v : vertexSet) {
			if (!v.equals(start)) {
				vMinusS.add(v);
			}
		}

		for (Vertex v : vMinusS) {
			if (graph.isEdge(start, v)) {
				prev.put(v, start);
				double weight = graph.getEdge(start, v).getWeight();
				dist.put(v, weight);
			} else {
				dist.put(v, Double.POSITIVE_INFINITY);
			}
		}

		while (vMinusS.size() != 0) {
			double minDist = Double.POSITIVE_INFINITY;
			Vertex u = null;
			for (Vertex v : vMinusS) {
				if (dist.get(v) <= minDist) {
					minDist = dist.get(v);
					u = v;
				}
			}

			vMinusS.remove(u);

			for (Vertex v : vMinusS) {
				if (graph.isEdge(u, v)) {
					double weight = graph.getEdge(u, v).getWeight();
					if (dist.get(u) + weight <= dist.get(v)) {
						dist.put(v, dist.get(u) + weight);
						prev.put(v, u);
					}
				}
			}
		}
	}
}

