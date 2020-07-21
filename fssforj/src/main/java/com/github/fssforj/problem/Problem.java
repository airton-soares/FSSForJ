package com.github.fssforj.problem;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Problem
{
    private int dimension;
    private double bottomDomainLimit;
    private double topDomainLimit;

    public Problem(int dimension, double bottomDomainLimit, double topDomainLimit)
    {
        this.dimension = dimension;
        this.bottomDomainLimit = bottomDomainLimit;
        this.topDomainLimit = topDomainLimit;
    }

    /**
     * Função avaliadora da aptidão de uma determinada função para uma dada
     * posição no espaço de busca.
     *
     * @param position - Posição a ser avaliada pela função de "fitness"
     * @return Aptidão da posição passada como parâmetro para essa função
     *
     * @author Airton Soares
     */
    public abstract double fitness(double[] position);

    public abstract boolean compareFitness(double fitness1, double fitness2);

    public double getRandonNumberInDomain()
    {
        double randonNumberInDomain = 0;

        randonNumberInDomain = ThreadLocalRandom.current().nextDouble(this.bottomDomainLimit, this.topDomainLimit);

        return randonNumberInDomain;
    }

    public int getDimension()
    {
        return dimension;
    }

    public void setDimension(int dimension)
    {
        this.dimension = dimension;
    }

    public double getBottomDomainLimit()
    {
        return bottomDomainLimit;
    }

    public void setBottomDomainLimit(double bottomDomainLimit)
    {
        this.bottomDomainLimit = bottomDomainLimit;
    }

    public double getTopDomainLimit()
    {
        return topDomainLimit;
    }

    public void setTopDomainLimit(double topDomainLimit)
    {
        this.topDomainLimit = topDomainLimit;
    }
}
