/*
* @author: V.R.B. Mahanthe - IT17009164

* This class outputs the optimum solution by the Iterated Local Search (ILS) algorithm
 */
package gap.operations;

import gap.instances.FlightInstances;
import gap.model.Flight;
import gap.model.Gate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IlsOperations {

    //flights array
    private final Flight[] flightsArray = new FlightInstances().getFlightsArray();

    //returns the initial solution needed to initialize the algorithm
    public Gate[] getInitialSolution() {
        Gate[] initialSolution = new GaOperations().randomInitialize();
        Gate[] shuffledSolution = shuffleSolution(initialSolution);
        return shuffledSolution;
    }

    /*
    * Fitness calculator
    * Metrics considered:
        {Gate Type, Airline preffered gate type, Gate Size} 
     */
    public int calculateFitnessLS(Gate[] solution) {
        int fitness = 0;
        for (int i = 0; i < solution.length; i++) {
            try {
                //increment fitness for gate type: pier bay
                if (solution[i].getGateType().equals("PB")) {
                    fitness++;
                }

                //increment fitness by comparing gate type and requested gate type by flight
                if (solution[i].getGateType().equals(flightsArray[i].getReqGate())) {
                    fitness++;
                }

                //increment fitness by comparing gate size and aircraft size
                int x = solution[i].getGateSize();
                int y = flightsArray[i].getAircraftType();
                if (x == y) {
                    fitness += 2;
                } else if (x > y) {
                    fitness++;
                }
            } catch (NullPointerException ignored) {
            }
        }
        return fitness;
    }

    //Local Search Algorithm
    public Gate[] localSearch(Gate[] solution) {
        //generate neighborhood
        List<Gate[]> solList = generateNeighborhood(solution);
        //evaluate neighborhood
        return evaluateNeighborhood(solList);
    }

    //evaluate neighborhood; 
    //the solution with highest fitness from the neighborhood is chosen as the candidate solution
    private Gate[] evaluateNeighborhood(List<Gate[]> solList) {
        int maxFit = 0;
        Gate[] candidateSol = {};
        for (Gate[] solution : solList) {
            int solutionFitness = calculateFitnessLS(solution);
            if (maxFit < solutionFitness) {
                maxFit = solutionFitness;
                candidateSol = new Gate[solution.length];
                System.arraycopy(solution, 0, candidateSol, 0, solution.length);
            }
        }
        return candidateSol;
    }

    //generate neighborhood needed for a local search
    private List<Gate[]> generateNeighborhood(Gate[] solution) {
        List<Gate[]> neighborhood = new ArrayList<>();
//        int iter = (int) Math.pow(getNeighborhoodSize(solution.length), 2);
        /*
        * The actual Neighborhood size is too large 
        * For Ex. a solution with 10 solution components need 3628800 iterations 
        * Every iteration does not guarantee a candidate solution as a random shuffle procedure is followed
        * It needs more than 3628800 iterations to generate the neighborhood theoritically 
        * Then the algorithm takes forever to output a solution
        * So a reasonable amount of iterations are selected (10000) such that the fitness of the solutions no longer increases
        * If the fitness of solutions no longer increases there is no point in generating solutions further.s
         */
        int iter = 10000;
        for (int i = 0; i < iter; i++) {
            Gate[] newSol = new Gate[solution.length];
            System.arraycopy(solution, 0, newSol, 0, solution.length);
            Gate[] shuffledSol = shuffleSolution(newSol);
            boolean contains = neighborhood.stream().anyMatch(sol -> Arrays.equals(sol, shuffledSol));
            if (!contains) {
                neighborhood.add(shuffledSol);
            }
        }

        return neighborhood;
    }

    //Pertubation: Flips the solution components in the solution to maintain variation
    public Gate[] pertubation(Gate[] solution) {
        int i, j;
        for (i = 0, j = solution.length - 1; i < solution.length / 2; i++, j--) {
            Gate temp;
            temp = solution[i];
            solution[i] = solution[j];
            solution[j] = temp;
        }
        return solution;
    }

    //determines the next candidate solution; the fittest solution is chosen
    public Gate[] acceptanceCriterion(Gate[] firstSolution, Gate[] secondSolution) {
        ArrayList<Gate[]> solList = new ArrayList<>();
        solList.add(firstSolution);
        solList.add(secondSolution);
        return evaluateNeighborhood(solList);
    }

    //shuffles the solution components in given solution
    public Gate[] shuffleSolution(Gate[] solution) {

        for (int i = 0; i < solution.length; i++) {
            int randNum = i + (int) (Math.random() * (solution.length - i));

            Gate temp = solution[randNum];
            solution[randNum] = solution[i];
            solution[i] = temp;
        }
        return solution;
    }

    /*
    * returns the size of the neighborhood for a local search
    * theoritically, that is the factorial of the number of solution components.
    * since NpR = N!/(N-R)!
    * here, R = N
    * therefore, Neighborhood Size = N!
     */
    public int getNeighborhoodSize(int num) {
        int i, nSize = 1;
        for (i = 1; i <= num; i++) {
            nSize = nSize * i;
        }
        return nSize;
    }
}
