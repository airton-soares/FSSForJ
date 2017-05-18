package com.github.fssforj;

import com.github.fssforj.function.Problem;
import com.github.fssforj.function.RosenbrockFunction;
import com.github.fssforj.school.School;
import com.github.fssforj.utils.FSSConfig;
import com.github.fssforj.utils.PlotUtils;

public class Main
{
    /**
     * @param args [0] - Número de simulações
     * @param args [1] - Número de iterações
     * @param args [2] - Dimensão do problema
     * @param args [3] - Tamanho do cardume
     * @param args [4] - Peso inicial mínimo
     * @param args [5] - Peso inicial máximo
     * @param args [6] - Peso mínimo
     * @param args [7] - Peso máximo
     * @param args [8] - Passo individual inicial
     * @param args [9] - Passo individual final
     * @param args [10] - Limite inferior do domínio
     * @param args [11] - Limite superior do domínio
     */
    public static void main(String[] args)
    {
	if (args.length == 12)
	{
	    int numberOfSimulations = Integer.parseInt(args[0]);
	    int numberOfIterations = Integer.parseInt(args[1]);
	    int problemDimension = Integer.parseInt(args[2]);
	    int schoolSize = Integer.parseInt(args[3]);
	    double minimumInitialWeight = Double.parseDouble(args[4]);
	    double maximumInitialWeight = Double.parseDouble(args[5]);
	    double minimumWeight = Double.parseDouble(args[6]);
	    double maximumWeight = Double.parseDouble(args[7]);
	    double initialIndividualStep = Double.parseDouble(args[8]);
	    double finalIndividualStep = Double.parseDouble(args[9]);
	    double bottomLimitDomain = Double.parseDouble(args[10]);
	    double topLimitDomain = Double.parseDouble(args[11]);

	    Problem problem = new RosenbrockFunction(problemDimension, bottomLimitDomain, topLimitDomain);
	    FSSConfig fssConfig = new FSSConfig(numberOfIterations, minimumInitialWeight, maximumInitialWeight,
		    minimumWeight, maximumWeight, initialIndividualStep, finalIndividualStep);
	    School school = new School(schoolSize);
	    FSS fss = new FSS(school, problem, fssConfig);
	    
	    double[] simulationsResults = new double[numberOfSimulations];
	    double[] simulationIndices = new double[numberOfSimulations];
	    for(int i = 0; i < numberOfSimulations; i++)
	    {
		simulationIndices[i] = i;
		simulationsResults[i] = fss.optmize();
	    }
	    
	    PlotUtils.plotXY(simulationIndices, simulationsResults);
	}
	else
	{
	    System.err.println("INVALID NUMBER OF ARGUMENTS.");
	}
    }
}
