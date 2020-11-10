/*
* @author: V.R.B. Mahanthe - IT17009164

* This class outputs the optimum solution 
* Compares the separate solutions obtained by Genetic algorithm and ILS algorithm
 */
package gap.operations;

import gap.model.Gate;

public class Output implements Runnable {

    public static Gate[] optimumSolution;

    @Override
    public void run() {

        System.out.println("Generating output...");
        System.out.println();

        //Soultion from Genetic Algorithm
        Gate[] gaSolution = new GaEvaluation().runGeneticAlgorithm();
        //Soultion from ILS Algorithm
        Gate[] ilsSolution = new IlsEvaluation().runILSalgorithm();

        System.out.println("gaSolution: " + new GaOperations().displayChromosome(gaSolution));
        System.out.println("Fitness: " + new IlsOperations().calculateFitnessLS(gaSolution));
        System.out.println();

        System.out.println("ilsSolution: " + new GaOperations().displayChromosome(ilsSolution));
        System.out.println("Fitness: " + new IlsOperations().calculateFitnessLS(ilsSolution));
        System.out.println();

        optimumSolution = new IlsOperations().acceptanceCriterion(gaSolution, ilsSolution);
        System.out.println("optimumSolution: " + new GaOperations().displayChromosome(optimumSolution));
        System.out.println("Fitness: " + new IlsOperations().calculateFitnessLS(optimumSolution));
    }
}
