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
    private XYSeriesCollection mCollection;
    private XYSeries mIdealPosition;
    private XYSeries mIdealVelocity;
    private XYSeries mRealPosition;
    private XYSeries mRealVelocity;

    private JPanel mChartPanel;

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

        mCollection = new XYSeriesCollection();
        mCollection.addSeries(mIdealPosition);
        mCollection.addSeries(mIdealVelocity);
        mCollection.addSeries(mRealVelocity);
        mCollection.addSeries(mRealPosition);

        final JFreeChart chart = ChartFactory.createXYLineChart("Motion Profile", "Time (sec)",
                "Data",
                mCollection,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        chart.setBackgroundPaint(Color.white);

        mChartPanel = new ChartPanel(chart);
        mChartPanel.setPreferredSize(new Dimension(400, 300));
        mChartPanel.setBackground(getBackground());

        add(mChartPanel, BorderLayout.CENTER);
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
            mIdealPosition.add(i, aPathpoints.get(i).mPosition);
            mIdealVelocity.add(i, aPathpoints.get(i).mVelocity);
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
        mRealPosition.add(aIndex, aPoint.mPosition);
        mRealVelocity.add(aIndex, aPoint.mVelocity);
    }

}
