/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutionaryprogramming;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Ersa
 */
public class EP {

    static double alpha = 0.6;
    static int selectivePressure = 25   ;

    public static Kromosom Mutation(Kromosom kromosom) {
        Random r = new Random();
        double a = r.nextGaussian();
        double[] newMutationStep = new double[2];
        for (int i = 0; i < kromosom.getVariableLenght(); i++) {
            newMutationStep[i] = kromosom.getMutationStep(i) * (1 + (alpha * a));
            if (newMutationStep[i] > 2){
                newMutationStep[i] = 2;
            }
        }
        double[] newVariable = new double[2];
        for (int i = 0; i < kromosom.getVariableLenght(); i++) {
            double b = r.nextGaussian();
            newVariable[i] = kromosom.getVariable(i) + newMutationStep[i] * b;
        }
        Kromosom newKromosom = new Kromosom(newVariable[0], newVariable[1], newMutationStep[0], newMutationStep[1]);
        return newKromosom;
    }

    public static Populasi SurvivorSelection(Populasi pop) {
        pop.clearWin();
        for (int i = 0; i < pop.getNumbOfPop(); i++) {
            Kromosom y = pop.getKromosom(i);
            Random r = new Random();
            Set<Integer> generated = new LinkedHashSet<Integer>();
            while (generated.size() < selectivePressure) {
                Integer next = r.nextInt(pop.getNumbOfPop());
                if (next != i) {
                    generated.add(next);
                }
            }
            Iterator<Integer> iterator = generated.iterator();
            for (int j = 0; j < selectivePressure; j++) {
                Kromosom x = pop.getKromosom(iterator.next());
                if (y.getFitness() > x.getFitness()) {
                    y.setWin(y.getWin() + 1);
                }
            }
        }
        pop.sortByWin();
        Populasi newPopulation = new Populasi(pop.getNumbOfPop() / 2, false);
        for (int i = 0; i < newPopulation.getNumbOfPop(); i++) {
            newPopulation.setKromosom(i, pop.getKromosom(i));
        }
        return newPopulation;
    }

    public static Populasi Evolution(Populasi pop) {
        Populasi newPopulation = new Populasi(pop.getNumbOfPop()*2, false);
        for (int i = 0;i<newPopulation.getNumbOfPop();i++){
            int j = 0;
            Kromosom x;
            if(i<pop.getNumbOfPop()){
                x = pop.getKromosom(i);
                newPopulation.setKromosom(i, x);
            }
            else
            {
                x = Mutation(pop.getKromosom(j));
                newPopulation.setKromosom(i, x);
            }
        }
        newPopulation = SurvivorSelection(newPopulation);
        return newPopulation;
    }
}
