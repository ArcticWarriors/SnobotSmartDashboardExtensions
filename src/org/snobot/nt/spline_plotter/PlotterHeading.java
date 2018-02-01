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
 * This panel plots the heading v. time
 * 
 * @author PJ
 *
 */
public class PlotterHeading extends JPanel
{
    private XYSeriesCollection mCollection;
    private XYSeries mIdealHeading;
    private XYSeries mRealHeading;

    private JPanel mChartPanel;

    /**
     * Constructor.
     * 
     * @param aChartTitle
     *            The title
     */
    public PlotterHeading(String aChartTitle)
    {
        setLayout(new BorderLayout());
        mIdealHeading = new XYSeries("Ideal Heading");
        mRealHeading = new XYSeries("Real Heading");

        mCollection = new XYSeriesCollection();
        mCollection.addSeries(mIdealHeading);
        mCollection.addSeries(mRealHeading);

        final JFreeChart chart = ChartFactory.createXYLineChart(
                aChartTitle,
                "Time (sec)",
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
     * Sets the ideal heading.
     * 
     * @param aHeadings
     *            The heading
     */
    public void setPath(List<Double> aHeadings)
    {
        mIdealHeading.clear();
        clearActuals();

        for (int i = 0; i < aHeadings.size(); ++i)
        {
            mIdealHeading.add(i, aHeadings.get(i));
        }
    }

    /**
     * Clears the measured results.
     */
    public void clearActuals()
    {
        mRealHeading.clear();
    }

    /**
     * Sets a measured point.
     * 
     * @param aIndex
     *            The index of the point
     * @param aHeading
     *            The measured heading
     */
    public void setPoint(int aIndex, double aHeading)
    {
        mRealHeading.add(aIndex, aHeading);
    }
}
