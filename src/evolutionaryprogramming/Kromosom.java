/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutionaryprogramming;

import java.util.Random;

/**
 *
 * @author Ersa
 */
public class Kromosom {

    private double[] variable = new double[2];
    private double[] stepSize = new double[2];
    private double fitness;
    private int win;

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public Kromosom(double x1, double x2, double y1, double y2) {
        this.variable[0] = x1;
        this.variable[1] = x2;
        this.stepSize[0] = y1;
        this.stepSize[0] = y2;
        setFitness();
        this.win = 0;
    }

    public Kromosom() {
        for (int i = 0; i < variable.length; i++) {
            this.variable[i] = randomizeWeight();
        }
        setFitness();
        this.win = 0;
    }

    private double randomizeWeight() {
        Random r = new Random();
        return ((r.nextDouble() * 10.24) - 5.12);
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness() {
        this.fitness = 1 / ((100 * Math.pow(variable[0] * variable[0] - variable[1], 2) + Math.pow(1 - variable[0], 2)) + 0.0001);
    }

    public double getVariable(int index) {
        return variable[index];
    }

    public void setVariable(double x, int index) {
        this.variable[index] = x;
    }

    public double getMutationStep(int index) {
        return variable[index];
    }

    public void setMutationStep(double x, int index) {
        this.variable[index] = x;
    }

    public int getVariableLenght() {
        return variable.length;
    }

}
