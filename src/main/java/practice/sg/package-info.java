/**
 * <p>
 * Contains Java implementations of some graph algorithms. Many are based on the excellent book,
 * Algorithmic Graph Theory, by Joyner, Nguyen, and Phillips.
 * </p>
 * <p>
 * I decided to use this page also as a place to record historical notes (counter chronological). Versions refer to SHA
 * of this repo. Graphs are tricky!
 * <ol>
 *     <li> I should switch to <a href="http://cs.anu.edu.au/~bdm/data/formats.txt">graph6</a> format.</li>
 *     <li> In <code>v 6e00da1</code> I realized that {@linkplain practice.sg.EdgeSpec} is too edge-centric. For graphs having nodes
 *     without any edges, EdgeSpec instances are clearly not enough. Also realized that an explicit vertex set is
 *     needed because from adjacency list, one can not always derive the set of vertices.</li>
 *     <li> Removed the explicit vertex set from the Graph Representation {@linkplain practice.sg.EdgeListGraph}.
 *     Apoorv had argued why you need an explicit vertex set if you have {@linkplain practice.sg.EdgeListGraph#adjList}</li>
 *     <li> Started with basic API (<code>v a50de1b</code>)</li>
 * </ol>
 * </p>
 */
package practice.sg;
