package com.github.fssforj.utils;

public class MathUtils
{
    public static double euclidianDistance(double[] a, double[] b)
    {
        double sum = 0;

        for (int i = 0; i < a.length; i++)
        {
            sum += Math.pow(a[i] - b[i], 2.0);
        }

        return Math.sqrt(sum);
    }
}
