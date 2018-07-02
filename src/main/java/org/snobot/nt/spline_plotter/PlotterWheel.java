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
 * Plots the path of an individual side of the robot
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

    public PlotterWheel(String chartTitle)
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
                chartTitle,
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

    public void setPath(List<Double> position, List<Double> velocity)
    {
        mIdealPosition.clear();
        mIdealVelocity.clear();
        clearActuals();

        for (int i = 0; i < position.size(); ++i)
        {
            mIdealPosition.add(i, position.get(i));
            mIdealVelocity.add(i, velocity.get(i));
        }
    }

    public void clearActuals()
    {
        mRealPosition.clear();
        mRealVelocity.clear();
    }

    public void setPoint(int index, double position, double velocity)
    {
        mRealPosition.add(index, position);
        mRealVelocity.add(index, velocity);
    }

}
