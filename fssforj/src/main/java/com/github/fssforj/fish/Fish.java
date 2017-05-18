package com.github.fssforj.fish;

public class Fish
{
    private double currentWeight;
    private double previusWeight;
    private double currentFitness;
    private double previusFitness;
    private double[] currentPosition;
    private double[] previousPosition;
    private double[] individualStep;

    public Fish(double weight, double[] position, double[] individualStep)
    {
	this.currentWeight = weight;
	this.currentPosition = position;
	this.previousPosition = position;
	this.individualStep = individualStep;
    }

    public double getCurrentWeight()
    {
	return currentWeight;
    }

    public void setCurrentWeight(double currentWeight)
    {
	this.currentWeight = currentWeight;
    }

    public double getPreviusWeight()
    {
	return previusWeight;
    }

    public void setPreviusWeight(double previusWeight)
    {
	this.previusWeight = previusWeight;
    }

    public double getCurrentFitness()
    {
	return currentFitness;
    }

    public void setCurrentFitness(double currentFitness)
    {
	this.currentFitness = currentFitness;
    }

    public double getPreviusFitness()
    {
	return previusFitness;
    }

    public void setPreviusFitness(double previusFitness)
    {
	this.previusFitness = previusFitness;
    }

    public double[] getCurrentPosition()
    {
	return currentPosition;
    }

    public void setCurrentPosition(double[] currentPosition)
    {
	this.currentPosition = currentPosition;
    }

    public double[] getPreviousPosition()
    {
	return previousPosition;
    }

    public void setPreviousPosition(double[] previousPosition)
    {
	this.previousPosition = previousPosition;
    }

    public double[] getIndividualStep()
    {
	return individualStep;
    }

    public void setIndividualStep(double[] individualStep)
    {
	this.individualStep = individualStep;
    }

}
