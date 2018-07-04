package org.snobot.nt.spline_plotter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Plots the path of an individual side of the robot.
 * 
 * @author PJ
 *
 */
public class PlotterWheel extends JPanel
{
    private final XYSeries mIdealPosition;
    private final XYSeries mIdealVelocity;

    private final XYSeries mRealPosition;
    private final XYSeries mRealVelocity;

    /**
     * Constructor.
     * 
     * @param aChartTitle
     *            The title to put on the chart
     */
    public PlotterWheel(String aChartTitle)
    {
        setLayout(new BorderLayout());
        mIdealPosition = new XYSeries("Ideal  Position");
        mIdealVelocity = new XYSeries("Ideal  Velocity");

        mRealPosition = new XYSeries("Real  Position");
        mRealVelocity = new XYSeries("Real  Velocity");

        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(mIdealPosition);
        collection.addSeries(mIdealVelocity);
        collection.addSeries(mRealPosition);
        collection.addSeries(mRealVelocity);

        final JFreeChart chart = ChartFactory.createXYLineChart(
                aChartTitle,
                "Time (sec)",
                "Data",
                collection,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        chart.setBackgroundPaint(Color.white);

        JPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBackground(getBackground());

        add(chartPanel, BorderLayout.CENTER);
    }

    /**
     * Sets the spline center path, and the velocity.
     * 
     * @param aPosition
     *            The ideal position, in inches
     * @param aVelocity
     *            The ideal velocity, in in/sec
     */
    public void setPath(List<Double> aPosition, List<Double> aVelocity)
    {
        mIdealPosition.clear();
        mIdealVelocity.clear();
        clearActuals();

        for (int i = 0; i < aPosition.size(); ++i)
        {
            mIdealPosition.add(i, aPosition.get(i));
            mIdealVelocity.add(i, aVelocity.get(i));
        }
    }

    /**
     * Clears the "real" values.
     */
    public void clearActuals()
    {
        mRealPosition.clear();
        mRealVelocity.clear();
    }

    /**
     * Sets a measured point from the robot.
     * 
     * @param aIndex
     *            The index of this piont
     * @param aPosition
     *            The position, in inches
     * @param aVelocity
     *            The velocity, in in/sec
     */
    public void setPoint(int aIndex, double aPosition, double aVelocity)
    {
        mRealPosition.add(aIndex, aPosition);
        mRealVelocity.add(aIndex, aVelocity);
    }

}
