package practice.sg;

import java.util.List;

import static practice.sg.Graphs.EdgeType.DIRECTED;
import static practice.sg.Graphs.EdgeType.UNDIRECTED;

/**
 * Some simple and small graph definitions as instances of {@linkplain EdgeListGraph}.
 */
public final class Graphs {

    public enum EdgeType {DIRECTED, UNDIRECTED}

    public static final EdgeListGraph ButterflyGraphUndirected = EdgeListGraph.of(
        List.of(
            EdgeSpec.of(1, 2),
            EdgeSpec.of(1, 3),
            EdgeSpec.of(1, 4),
            EdgeSpec.of(1, 5),
            EdgeSpec.of(2, 3),
            EdgeSpec.of(4, 5)
        ), UNDIRECTED
    );
    public static final EdgeListGraph AGraphDirected = EdgeListGraph.of(
        List.of(EdgeSpec.of(1, 4), EdgeSpec.of(1, 5), EdgeSpec.of(1, 6),
            EdgeSpec.of(2, 5), EdgeSpec.of(2, 6),
            EdgeSpec.of(3, 6),
            EdgeSpec.of(4, 1),
            EdgeSpec.of(5, 1), EdgeSpec.of(5, 2),
            EdgeSpec.of(6, 1), EdgeSpec.of(6, 2), EdgeSpec.of(6, 3)), DIRECTED
    );
}
