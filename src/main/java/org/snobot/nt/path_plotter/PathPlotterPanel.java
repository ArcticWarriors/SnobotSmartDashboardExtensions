package org.snobot.nt.path_plotter;

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
 * Panel used to plot the ideal and real paths that the robot uses for motion
 * profiling.
 * 
 * @author PJ
 *
 */
public class PathPlotterPanel extends JPanel
{
    private final XYSeries mIdealPosition;
    private final XYSeries mIdealVelocity;
    private final XYSeries mRealPosition;
    private final XYSeries mRealVelocity;

    /**
     * Constructor.
     */
    public PathPlotterPanel()
    {
        setLayout(new BorderLayout());
        mIdealPosition = new XYSeries("Ideal Position");
        mIdealVelocity = new XYSeries("Ideal Velocity");
        mRealVelocity = new XYSeries("Real Velocity");
        mRealPosition = new XYSeries("Real Position");

        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(mIdealPosition);
        collection.addSeries(mIdealVelocity);
        collection.addSeries(mRealVelocity);
        collection.addSeries(mRealPosition);

        final JFreeChart chart = ChartFactory.createXYLineChart("Motion Profile", "Time (sec)",
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
     * Sets the expected path for the robot to drive.
     * 
     * @param aPathpoints
     *            The ideal path points
     */
    public void setPath(List<PathSetpoint> aPathpoints)
    {
        mIdealPosition.clear();
        mIdealVelocity.clear();
        clearActuals();

        for (int i = 0; i < aPathpoints.size(); ++i)
        {
            mIdealPosition.add(i, aPathpoints.get(i).getPosition());
            mIdealVelocity.add(i, aPathpoints.get(i).getVelocity());
        }
    }

    /**
     * Clears the measured results from the graph.
     */
    public void clearActuals()
    {
        mRealPosition.clear();
        mRealVelocity.clear();
    }

    /**
     * Sets a measured point.
     * 
     * @param aIndex
     *            The index of the point
     * @param aPoint
     *            The measured point
     */
    public void setPoint(int aIndex, PathSetpoint aPoint)
    {
        mRealPosition.add(aIndex, aPoint.getPosition());
        mRealVelocity.add(aIndex, aPoint.getVelocity());
    }

}
