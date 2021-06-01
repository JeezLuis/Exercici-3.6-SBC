import Model.City;
import Model.Graf;

/**
 * Set of variables X = {Ciutats}
 * Set of domains D = {CiutatA, CiutatB}foreach X^i that belongs to X
 * Set of constraints C = {CiutatA || CiutatB == Destination && Shortest Path && Can't go back}
 */

/**
 * Pseudocodi:
 * function BacktrackingSearch(csp)
 *  return Backtrack({},csp)
 *
 * function Backtrack(assignment, csp)
 *  if assignment is complete return assignment
 *  u_var=SelectUnassignedVariable(csp)
 *  for each value in OrderDomainValues(u_var, assignment, csp) do
 *      if isConsistent(value, assignment)
 *          add{u_var=value} to assignment
 *          inferences = Inference(csp, u_var, value)
 *          if inferences != failure
 *              add inferences to assignment
 *              result = Backtrack(assignment, csp)
 *              if result != failure then
 *                  return result
 *      remove {u_var=value} and inferences from assignment
 *  return failure
 *
 *  https://www.youtube.com/watch?v=lCrHYT_EhDs
 */
public class CSP {

    private Graf graf;
    private String from;
    private String current;
    private String to;
    private City route;

    public CSP(Graf graf, String from, String to) {
        this.graf = graf;
        this.from = from;
        this.to = to;
    }

    public void backtrackingSearch(Graf graf){
        current = from;
        //return backtrack(current,to,graf);
    }
    /**
    private void dijkstra(final NavigableSet<Node> q) {
        Node u, v;
        while (!q.isEmpty()) {
            // vertex with shortest distance (first iteration will return source)
            u = q.pollFirst();
            if (u.getDistance() == Integer.MAX_VALUE)
                break; // we can ignore u (and any other remaining vertices) since they are unreachable

            // look at distances to each neighbour
            for (Map.Entry<Node, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey(); // the neighbour in this iteration

                final int alternateDist = u.getDistance() + a.getValue();
                if (alternateDist < v.getDistance()) { // shorter path to neighbour found
                    q.remove(v);
                    v.setDistance(alternateDist);
                    v.setPreviousNode(u);
                    q.add(v);
                }
            }
        }
    }

    public void printPath(String endName) {
        if (!graph.containsKey(endName)) {
            System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
            return;
        }

        graph.get(endName).printPath();
        System.out.println();
    }**/

    /**
     * Prints the path from the source to every vertex (output order is not guaranteed)
     */
    /**
    public void printAllPaths() {
        for (Node v : graph.values()) {
            v.printPath();
            System.out.println();
        }
    }**/

}
