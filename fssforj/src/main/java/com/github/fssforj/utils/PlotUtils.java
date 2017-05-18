package com.github.fssforj.utils;

import java.awt.Frame;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import com.github.fssforj.exception.InvalidPlotParametersException;

public class PlotUtils
{
    public static void plotXY(double[] x, double[] y)
    {
	try
	{
	    if (x != null && y != null)
		{
		    if (x.length == y.length)
		    {
			Plot2DPanel plot = new Plot2DPanel();
			plot.addLinePlot("Plot", x, y);
			JFrame frame = new JFrame("Plot");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setExtendedState(Frame.MAXIMIZED_BOTH);
			frame.setContentPane(plot);
			frame.pack();
			frame.setVisible(true);
		    }
		    else
		    {
			throw new InvalidPlotParametersException("Arguments must have equal length");
		    }
		}
		else
		{
		    throw new InvalidPlotParametersException("Null arguments");
		}
	}
	catch(InvalidPlotParametersException e)
	{
	    e.printStackTrace();
	}
    }
}
