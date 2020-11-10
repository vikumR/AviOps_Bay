/*
* @author: V.R.B. Mahanthe - IT17009164

* This class outputs the optimum solution  by the genetic algorithm
 */
package gap.operations;

import gap.model.Gate;

public class GaEvaluation {

    GaOperations gaOp = new GaOperations();

    /*runs the genetic algorithm*/
    public Gate[] runGeneticAlgorithm() {
        /*Number of generations*/
        int nGen = 50;

        /*initialize population; three chrmosomes are initialized 
        through three separate initializing methods;
        "firstParent" chromosome is randomly initialized,
        "secondParent" chromsome is initialized in the ascending order of gate occupancy rate, and
        "offspring" chromsome is initialized in the descending order of gate occupancy rate*/
        Gate[] firstParent = gaOp.randomInitialize();
        Gate[] secondParent = gaOp.ascendOccupancyInitialize();
        Gate[] offspring = gaOp.descendOccupancyInitialize();

        for (int i = 0; i < nGen; i++) {
            /*Selection process; The fittest parents are selected*/
            Object[] parents = gaOp.select(firstParent, secondParent, offspring);
            firstParent = (Gate[]) parents[0];
            secondParent = (Gate[]) parents[1];

            Gate[] newFirstParent = new Gate[firstParent.length];
            System.arraycopy(firstParent, 0, newFirstParent, 0, firstParent.length);
            Gate[] newSecondParent = new Gate[secondParent.length];
            System.arraycopy(secondParent, 0, newSecondParent, 0, secondParent.length);

            /*Crossover*/
            offspring = gaOp.mate(newFirstParent, newSecondParent);
            /*Mutation*/
            offspring = gaOp.mutate(offspring);
        }
        /*return the optimum chromosome*/
        return offspring;
    }
}
