/*
* @author: V.R.B. Mahanthe - IT17009164

* This class outputs the optimum solution  by the Iterated Local Search (ILS) approach
 */
package gap.operations;

import gap.model.Gate;

public class IlsEvaluation {

    IlsOperations ilsOp = new IlsOperations();
    GaOperations gaOp = new GaOperations();

    /*runs the ILS algorithm*/
    public Gate[] runILSalgorithm() {
        //Number of iterations
        int iteration = 10;

        /*generate initial solution*/
        Gate[] initialSolution = ilsOp.getInitialSolution();
        Gate[] candidateSolution = new Gate[initialSolution.length];
        Gate[] modifiedSolution = new Gate[initialSolution.length];

        /*can localsearch the initial solution here but at this stage it is optional*/
        for (int i = 0; i < iteration; i++) {
            Gate[] newInitSol = new Gate[initialSolution.length];
            System.arraycopy(initialSolution, 0, newInitSol, 0, initialSolution.length);
            /*Pertubation*/
            candidateSolution = ilsOp.pertubation(newInitSol);

            Gate[] newCandSol = new Gate[candidateSolution.length];
            System.arraycopy(candidateSolution, 0, newCandSol, 0, candidateSolution.length);
            /*run Local Search algorithm for the pertubed solution*/
            modifiedSolution = ilsOp.localSearch(newCandSol);

            /*select the best solution*/
            initialSolution = ilsOp.acceptanceCriterion(initialSolution, modifiedSolution);
        }

//        System.out.println("Optimum set of gates: " + gaOp.displayChromosome(optimumSolution));
//        System.out.println("Fitness: " + ilsOp.calculateFitnessLS(optimumSolution));

        /*return the optimum solution*/
        return initialSolution;
    }
}
