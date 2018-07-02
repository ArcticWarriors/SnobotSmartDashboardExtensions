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
 * profiling
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

    public void setPath(List<PathSetpoint> path_points)
    {
        mIdealPosition.clear();
        mIdealVelocity.clear();
        clearActuals();

        for (int i = 0; i < path_points.size(); ++i)
        {
            mIdealPosition.add(i, path_points.get(i).mPosition);
            mIdealVelocity.add(i, path_points.get(i).mVelocity);
        }
    }

    public void clearActuals()
    {
        mRealPosition.clear();
        mRealVelocity.clear();
    }

    public void setPoint(int index, PathSetpoint aPoint)
    {
        mRealPosition.add(index, aPoint.mPosition);
        mRealVelocity.add(index, aPoint.mVelocity);
    }

}
