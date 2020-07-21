package com.github.fssforj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.github.fssforj.fish.Fish;
import com.github.fssforj.problem.Problem;
import com.github.fssforj.school.School;
import com.github.fssforj.utils.FSSConfig;
import com.github.fssforj.utils.MathUtils;

public class FSS
{
    private School school;
    private Problem problem;
    private FSSConfig fssConfig;

    public FSS(School school, Problem problem, FSSConfig fssConfig)
    {
        this.school = school;
        this.problem = problem;
        this.fssConfig = fssConfig;
    }

    public double[] optmize()
    {
        double[] results = new double[this.fssConfig.getNumberOfIterations()];

        this.initializeSchool();

        for (int i = 0; i < this.fssConfig.getNumberOfIterations(); i++)
        {
            List<Double> fitnessVariations = doIndividualFishesMovement();
            double maximumVariation = Collections.max(fitnessVariations);
            feedFishes(maximumVariation);

            double[] instinctiveMovementArray = getInstinctiveMovementArray();
            doInstinctiveMovement(instinctiveMovementArray);

            double[] barycenter = getBaryCenter();

            doVolitionalMovement(barycenter);

            updateIndividualStepArray();

            results[i] = getBestSchoolFitness();
            System.out.println("[INFO] Fitness = " + results[i]);
        }

        return results;
    }

    private List<Double> doIndividualFishesMovement()
    {
        List<Double> fitnessVariations = new ArrayList<Double>();

        for (int i = 0; i < this.school.getFishes().length; i++)
        {
            Fish currentFish = this.school.getFishes()[i];
            double[] newPosition = new double[this.problem.getDimension()];

            for (int j = 0; j < this.problem.getDimension(); j++)
            {
                newPosition[j] = currentFish.getCurrentPosition()[j]
                                 + currentFish.getIndividualStep()[j] * ThreadLocalRandom.current().nextDouble(-1, 1);
            }

            double currentPositionFitness = this.problem.fitness(currentFish.getCurrentPosition());
            double newPositionFitness = this.problem.fitness(newPosition);

            if (this.problem.compareFitness(newPositionFitness, currentPositionFitness))
            {
                currentFish.setPreviousPosition(currentFish.getCurrentPosition());
                currentFish.setCurrentPosition(newPosition);
                currentFish.setCurrentFitness(newPositionFitness);
                currentFish.setPreviusFitness(currentPositionFitness);
            }
            else
            {
                currentFish.setCurrentFitness(currentPositionFitness);
                currentFish.setPreviusFitness(currentPositionFitness);
            }

            fitnessVariations.add(Math.abs(newPositionFitness - currentPositionFitness));

        }

        return fitnessVariations;

    }

    private void feedFishes(double maximumVariation)
    {
        for (int i = 0; i < this.school.getFishes().length; i++)
        {
            Fish currentFish = this.school.getFishes()[i];

            currentFish.setPreviusWeight(currentFish.getCurrentWeight());

            double fitnessVariation = Math.abs(currentFish.getCurrentFitness() - currentFish.getPreviusFitness());

            double newWeight = currentFish.getCurrentWeight();

            if (maximumVariation != 0)
            {
                newWeight = currentFish.getCurrentWeight() + fitnessVariation / maximumVariation;

                if(newWeight > this.fssConfig.getMaximumWeight())
                {
                    newWeight = this.fssConfig.getMaximumWeight();
                }
            }

            currentFish.setCurrentWeight(newWeight);
        }

    }

    private double[] getInstinctiveMovementArray()
    {
        double[] instinctiveMovementArray = new double[this.problem.getDimension()];
        double[] distanceVariationTimesFitnessVariationSum = new double[this.problem.getDimension()];
        double fitnessVariationSum = 0;

        for (int i = 0; i < this.problem.getDimension(); i++)
        {
            distanceVariationTimesFitnessVariationSum[i] = 0;
        }

        for (int i = 0; i < this.school.getFishes().length; i++)
        {
            Fish currentFish = this.school.getFishes()[i];
            double fitnessVariation = Math.abs(currentFish.getCurrentFitness() - currentFish.getPreviusFitness());
            fitnessVariationSum += fitnessVariation;

            for (int j = 0; j < this.problem.getDimension(); j++)
            {
                double distanceVariation = Math
                                           .abs(currentFish.getCurrentPosition()[j] - currentFish.getPreviousPosition()[j]);
                distanceVariationTimesFitnessVariationSum[j] += distanceVariation * fitnessVariation;
            }
        }

        for (int i = 0; i < this.problem.getDimension(); i++)
        {
            if (fitnessVariationSum != 0)
            {
                instinctiveMovementArray[i] = distanceVariationTimesFitnessVariationSum[i] / fitnessVariationSum;
            }
            else
            {
                instinctiveMovementArray[i] = 0;
            }
        }

        return instinctiveMovementArray;
    }

    private void doInstinctiveMovement(double[] instinctiveMovementArray)
    {
        for (int i = 0; i < this.school.getFishes().length; i++)
        {
            Fish currentFish = this.school.getFishes()[i];

            for (int j = 0; j < this.problem.getDimension(); j++)
            {
                currentFish.getCurrentPosition()[j] += instinctiveMovementArray[j];
            }
        }

    }

    private double[] getBaryCenter()
    {
        double[] baryCenter = new double[this.problem.getDimension()];
        double weightSum = 0;
        double weightSumTimesCurrentPosition = 0;

        for (int i = 0; i < this.school.getFishes().length; i++)
        {
            weightSum += this.school.getFishes()[i].getCurrentWeight();
        }

        for (int i = 0; i < this.school.getFishes().length; i++)
        {
            Fish currentFish = this.school.getFishes()[i];

            for (int j = 0; j < this.problem.getDimension(); j++)
            {
                weightSumTimesCurrentPosition += currentFish.getCurrentPosition()[j] * currentFish.getCurrentWeight();
            }
        }

        for (int i = 0; i < this.problem.getDimension(); i++)
        {
            baryCenter[i] = weightSumTimesCurrentPosition / weightSum;
        }

        return baryCenter;
    }

    private void doVolitionalMovement(double[] baryCenter)
    {
        for (int i = 0; i < this.school.getFishes().length; i++)
        {
            Fish currentFish = this.school.getFishes()[i];
            double distanceToBaryCenter = MathUtils.euclidianDistance(currentFish.getCurrentPosition(), baryCenter);
            double[] volitionalArray = new double[this.problem.getDimension()];
            double[] differenceToBarycenter = new double[this.problem.getDimension()];
            double weightVariationSum = 0;

            for (int j = 0; j < this.problem.getDimension(); j++)
            {
                volitionalArray[j] = 2 * currentFish.getCurrentPosition()[j];
                differenceToBarycenter[j] = currentFish.getCurrentPosition()[j] - baryCenter[j];
                weightVariationSum += Math.abs(currentFish.getCurrentWeight() - currentFish.getPreviusWeight());
            }

            for (int j = 0; j < this.problem.getDimension(); j++)
            {
                currentFish.getCurrentPosition()[j] += -Math.signum(weightVariationSum) * volitionalArray[j]
                                                       * Math.random() * differenceToBarycenter[j] / distanceToBaryCenter;
            }
        }

    }

    private void updateIndividualStepArray()
    {
        for (int i = 0; i < this.school.getFishes().length; i++)
        {
            Fish currentFish = this.school.getFishes()[i];

            for(int j = 0; j < this.problem.getDimension(); j++)
            {
                currentFish.getCurrentPosition()[j] -= this.fssConfig.getIndividualStepDecayRate();
            }
        }

    }

    private double getBestSchoolFitness()
    {
        double bestFitness = 0;

        for (int i = 0; i < this.school.getFishes().length; i++)
        {
            Fish currentFish = this.school.getFishes()[i];

            if(i == 0)
            {
                bestFitness = this.problem.fitness(currentFish.getCurrentPosition());
            }
            else
            {
                double currentPositionFitness = this.problem.fitness(currentFish.getCurrentPosition());

                if(this.problem.compareFitness(currentPositionFitness, bestFitness))
                {
                    bestFitness = currentPositionFitness;
                }
            }

        }

        return bestFitness;
    }

    private void initializeSchool()
    {
        for (int i = 0; i < this.school.getFishes().length; i++)
        {
            this.school.getFishes()[i] = initializeFish();
        }
    }

    private Fish initializeFish()
    {
        Fish fish = null;

        double weight = ThreadLocalRandom.current().nextDouble(this.fssConfig.getMinimumInitialWeight(),
                        this.fssConfig.getMaximumInitialWeight());
        double[] currentPosition = new double[this.problem.getDimension()];
        double[] individualStep = new double[this.problem.getDimension()];

        for (int i = 0; i < this.problem.getDimension(); i++)
        {
            currentPosition[i] = this.problem.getRandonNumberInDomain();
            individualStep[i] = this.fssConfig.getInitialIndividualStep();
        }

        fish = new Fish(weight, currentPosition, individualStep);

        double fitness = this.problem.fitness(currentPosition);
        fish.setCurrentFitness(fitness);

        return fish;
    }

    public School getSchool()
    {
        return school;
    }

    public void setSchool(School school)
    {
        this.school = school;
    }

    public Problem getProblem()
    {
        return problem;
    }

    public void setProblem(Problem problem)
    {
        this.problem = problem;
    }

    public FSSConfig getFssConfig()
    {
        return fssConfig;
    }

    public void setFssConfig(FSSConfig fssConfig)
    {
        this.fssConfig = fssConfig;
    }

}
