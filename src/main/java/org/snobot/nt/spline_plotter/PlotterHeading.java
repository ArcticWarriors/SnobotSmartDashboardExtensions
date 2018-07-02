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
    private final XYSeries mIdealHeading;
    private final XYSeries mRealHeading;

    public PlotterHeading(String aChartTitle)
    {
        setLayout(new BorderLayout());
        mIdealHeading = new XYSeries("Ideal Heading");
        mRealHeading = new XYSeries("Real Heading");

        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(mIdealHeading);
        collection.addSeries(mRealHeading);

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

    public void setPath(List<Double> headings)
    {
        mIdealHeading.clear();
        clearActuals();

        for (int i = 0; i < headings.size(); ++i)
        {
            mIdealHeading.add(i, headings.get(i));
        }
    }

    public void clearActuals()
    {
        mRealHeading.clear();
    }

    public void setPoint(int index, double heading)
    {
        mRealHeading.add(index, heading);
    }
}
