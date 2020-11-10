/*
* @author: V.R.B. Mahanthe - IT17009164

* This class defines the operators and the procedures involved in the genetic algorithm approach
 */
package gap.operations;

import gap.instances.FlightInstances;
import gap.instances.GateInstances;
import gap.model.Flight;
import gap.model.Gate;
import java.util.Arrays;
import java.util.Random;

public class GaOperations {

    //flights array
    private final Flight[] flightsArray = new FlightInstances().getFlightsArray();

    //gates array
    private final GateInstances gateInstance = new GateInstances();

    //chromosome size
    int chromosomeSize = getChromosomeSize(gateInstance.getGatesArray().length, flightsArray.length);

    //return the size of the chromosome needed
    public int getChromosomeSize(int gatesArrSize, int flightsArrSize) {
        return Math.min(gatesArrSize, flightsArrSize);
    }

    //random initialization
    public Gate[] randomInitialize() {
        Gate[] initialSolution = occupancyInitialize("random");
        return new IlsOperations().shuffleSolution(initialSolution);
    }

    //Initialize according to ascending order of gate occupancy rate
    public Gate[] ascendOccupancyInitialize() {
        return occupancyInitialize("ascend");
    }

    //Initialize according to descending order of gate occupancy rate
    public Gate[] descendOccupancyInitialize() {
        return occupancyInitialize("descend");
    }

    //Creates a chromosome
    private Gate[] occupancyInitialize(String type) {
        Gate[] gatesArray = gateInstance.getGatesArray();
        Gate[] chromosome = new Gate[chromosomeSize];

        switch (type) {
            case "ascend":
                for (int i = 0; i < gatesArray.length; i++) {
                    for (int j = i + 1; j < gatesArray.length; j++) {
                        if (gatesArray[i].getOccupancyRate() > gatesArray[j].getOccupancyRate()) {
                            Gate temp = gatesArray[i];
                            gatesArray[i] = gatesArray[j];
                            gatesArray[j] = temp;
                        }
                    }
                    chromosome[i] = gatesArray[i];

                    //check for the last element of chromosome
                    if (Arrays.asList(chromosome).indexOf(chromosome[i]) == chromosome.length - 1) {
                        break;
                    }
                }
                break;
            case "descend":
                for (int i = 0; i < gatesArray.length; i++) {
                    for (int j = i + 1; j < gatesArray.length; j++) {
                        if (gatesArray[i].getOccupancyRate() < gatesArray[j].getOccupancyRate()) {
                            Gate temp = gatesArray[i];
                            gatesArray[i] = gatesArray[j];
                            gatesArray[j] = temp;
                        }
                    }
                    chromosome[i] = gatesArray[i];

                    //check for the last element of chromosome
                    if (Arrays.asList(chromosome).indexOf(chromosome[i]) == chromosome.length - 1) {
                        break;
                    }
                }
                break;
            case "random":
                for (int i = 0; i < gatesArray.length; i++) {
                    chromosome[i] = gatesArray[i];

                    //check for the last element of chromosome
                    if (Arrays.asList(chromosome).indexOf(chromosome[i]) == chromosome.length - 1) {
                        break;
                    }
                }
                break;
        }
        return chromosome;
    }

    //display chromosome
    public String displayChromosome(Gate[] chromosome) {
        int[] showChromosome = new int[chromosome.length];
        for (int i = 0; i < chromosome.length; i++) {
            try {
                showChromosome[i] = chromosome[i].getGateNumber();
            } catch (NullPointerException e) {
                showChromosome[i] = 0;
//                e.printStackTrace();
            }
        }
        return Arrays.toString(showChromosome);
    }

    //fitness calculator
    public int calculateFitness(Gate[] chromosome) {
        return new IlsOperations().calculateFitnessLS(chromosome);
    }

    //Crossover: offspring is created by splitting the top two fittest chromosomes
    public Gate[] mate(Gate[] firstChromosome, Gate[] secondChromosome) {
        Gate[] offspring = new Gate[chromosomeSize];
        System.arraycopy(secondChromosome, 0, offspring, 0, secondChromosome.length / 2);
        Gate[] duplicateChecker = new Gate[chromosomeSize - secondChromosome.length / 2];

        //check for duplicate genes
        int j = 0;
        for (Gate gate : firstChromosome) {
            boolean contains = Arrays.asList(offspring).contains(gate);
            if (!contains) {
                duplicateChecker[j] = gate;
                if (Arrays.asList(duplicateChecker).indexOf(duplicateChecker[j]) == duplicateChecker.length - 1) {
                    break;
                }
                j++;
            }
        }
        System.arraycopy(duplicateChecker, 0, offspring, secondChromosome.length / 2, duplicateChecker.length);
        return offspring;
    }

    //Mutation: flip offspring
    public Gate[] mutate(Gate[] offspring) {
        int i, j;
        for (i = 0, j = offspring.length - 1; i < offspring.length / 2; i++, j--) {
            Gate temp;
            temp = offspring[i];
            offspring[i] = offspring[j];
            offspring[j] = temp;
        }

        //introduce a new random gate
        int x, y;
        Gate[] gatesArray = gateInstance.getGatesArray();
        if (offspring.length < gatesArray.length) {
            x = new Random().nextInt(gatesArray.length);
            boolean contains = Arrays.asList(offspring).contains(gatesArray[x]);
            while (contains) {
                x = new Random().nextInt(gatesArray.length);
                contains = Arrays.asList(offspring).contains(gatesArray[x]);
            }
            y = new Random().nextInt(offspring.length);
            offspring[y] = gatesArray[x];
        }
        return offspring;
    }

    //Selection: The two fittest chromsomes are selected as the parents for the next generation.
    public Object[] select(Gate[] firstChromosome, Gate[] secondChromosome, Gate[] thirdChromosome) {
        final int fitness_1 = calculateFitness(firstChromosome);
        final int fitness_2 = calculateFitness(secondChromosome);
        final int fitness_3 = calculateFitness(thirdChromosome);

        int fitnessMin = fitness_1;
        if (fitness_2 < fitnessMin) {
            fitnessMin = fitness_2;
        } else if (fitness_3 < fitnessMin) {
            fitnessMin = fitness_3;
        }

        if (fitness_1 == fitnessMin) {
            return new Object[]{secondChromosome, thirdChromosome};
        } else if (fitness_2 == fitnessMin) {
            return new Object[]{firstChromosome, thirdChromosome};
        } else {
            return new Object[]{firstChromosome, secondChromosome};
        }
    }
}
