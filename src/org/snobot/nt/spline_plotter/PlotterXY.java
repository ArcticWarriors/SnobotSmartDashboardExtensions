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

public class PlotterXY extends JPanel
{
    public static class XYPoint
    {
        public double mX;
        public double mY;
    }

    private XYSeriesCollection mCollection;
    private XYSeries mIdeal;
    private XYSeries mReal;

    private JPanel mChartPanel;

    /**
     * Constructor.
     * 
     * @param aChartTitle
     *            The title of the chart
     */
    public PlotterXY(String aChartTitle)
    {
        setLayout(new BorderLayout());
        mIdeal = new XYSeries("Ideal  Position");
        mReal = new XYSeries("Ideal  Velocity");

        mCollection = new XYSeriesCollection();
        mCollection.addSeries(mIdeal);
        mCollection.addSeries(mReal);

        final JFreeChart chart = ChartFactory.createXYLineChart(
                aChartTitle, 
                "X (Inches)", 
                "Y (Inches)", 
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
     * Sets the ideal path to follow.
     * 
     * @param aXyList
     *            The ideal (x, y) position, in inches
     */
    public void setPath(List<XYPoint> aXyList)
    {
        mIdeal.clear();
        clearActuals();

        for (int i = 0; i < aXyList.size(); ++i)
        {
            mIdeal.add(aXyList.get(i).mX, aXyList.get(i).mY);
        }
    }

    /**
     * Clears the measured values.
     */
    public void clearActuals()
    {
        mReal.clear();
    }

    /**
     * Sets the measured point.
     * 
     * @param aX
     *            The X position, in inches
     * @param aY
     *            The Y position, in inches
     */
    public void setPoint(double aX, double aY)
    {
        mReal.add(aX, aY);
    }
}
