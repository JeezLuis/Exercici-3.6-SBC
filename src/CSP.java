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

    public boolean backtrack(String current, String to, Graf graf){
        if(completeAssigment(from, to)) return true;
        for (City c: graf.getNodes()) {
        }
        return false;
    }

    public boolean completeAssigment(String from, String to){
        if(from.equals(to)){
            return true;
        }
        return false;
    }


}
