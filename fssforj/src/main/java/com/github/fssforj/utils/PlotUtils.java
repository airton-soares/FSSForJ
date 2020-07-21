package com.github.fssforj.utils;

import java.awt.Frame;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import com.github.fssforj.exception.InvalidPlotParametersException;

public class PlotUtils
{
    public static void plotXY(double[] X, double[] Y)
    {
        try
        {
            if (X != null && Y != null)
            {
                if (X.length == Y.length)
                {
                    Plot2DPanel plot = new Plot2DPanel();
                    plot.addLinePlot("Plot", X, Y);
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
        catch (InvalidPlotParametersException e)
        {
            e.printStackTrace();
        }
    }

    public static void plotBoxPlot(double[][] XY)
    {
        try
        {
            if (XY != null)
            {
                Plot2DPanel plot = new Plot2DPanel();
                plot.addBoxPlot("BoxPlot", XY);
                JFrame frame = new JFrame("BoxPlot");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setExtendedState(Frame.MAXIMIZED_BOTH);
                frame.setContentPane(plot);
                frame.pack();
                frame.setVisible(true);
            }
            else
            {
                throw new InvalidPlotParametersException("Null arguments");
            }
        }
        catch (InvalidPlotParametersException e)
        {
            e.printStackTrace();
        }
    }
}
