package com.github.fssforj.utils;

public class FSSConfig
{
    private int numberOfIterations;
    private double minimumInitialWeight;
    private double maximumInitialWeight;
    private double minimumWeight;
    private double maximumWeight;
    private double initialIndividualStep;
    private double finalIndividualStep;
    private double individualStepDecayRate;

    public FSSConfig(int numberOfIterations, double minimumInitialWeight, double maximumInitialWeight,
	    double minimumWeight, double maximumWeight, double initialIndividualStep, double finalIndividualStep)
    {
	this.numberOfIterations = numberOfIterations;
	this.minimumInitialWeight = minimumInitialWeight;
	this.maximumInitialWeight = maximumInitialWeight;
	this.minimumWeight = minimumWeight;
	this.maximumWeight = maximumWeight;
	this.initialIndividualStep = initialIndividualStep;
	this.finalIndividualStep = finalIndividualStep;
	this.individualStepDecayRate = (this.initialIndividualStep - this.finalIndividualStep)
		/ this.numberOfIterations;
    }

    public int getNumberOfIterations()
    {
	return numberOfIterations;
    }

    public void setNumberOfIterations(int numberOfIterations)
    {
	this.numberOfIterations = numberOfIterations;
    }

    public double getMinimumInitialWeight()
    {
	return minimumInitialWeight;
    }

    public void setMinimumInitialWeight(double minimumInitialWeight)
    {
	this.minimumInitialWeight = minimumInitialWeight;
    }

    public double getMaximumInitialWeight()
    {
	return maximumInitialWeight;
    }

    public void setMaximumInitialWeight(double maximumInitialWeight)
    {
	this.maximumInitialWeight = maximumInitialWeight;
    }

    public double getMinimumWeight()
    {
	return minimumWeight;
    }

    public void setMinimumWeight(double minimumWeight)
    {
	this.minimumWeight = minimumWeight;
    }

    public double getMaximumWeight()
    {
	return maximumWeight;
    }

    public void setMaximumWeight(double maximumWeight)
    {
	this.maximumWeight = maximumWeight;
    }

    public double getInitialIndividualStep()
    {
	return initialIndividualStep;
    }

    public void setInitialIndividualStep(double initialIndividualStep)
    {
	this.initialIndividualStep = initialIndividualStep;
    }

    public double getFinalIndividualStep()
    {
	return finalIndividualStep;
    }

    public void setFinalIndividualStep(double finalIndividualStep)
    {
	this.finalIndividualStep = finalIndividualStep;
    }

    public double getIndividualStepDecayRate()
    {
	return individualStepDecayRate;
    }

    public void setIndividualStepDecayRate(double individualStepDecayRate)
    {
	this.individualStepDecayRate = individualStepDecayRate;
    }
}
