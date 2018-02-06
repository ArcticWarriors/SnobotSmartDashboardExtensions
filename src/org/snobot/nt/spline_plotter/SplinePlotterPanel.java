package org.snobot.nt.spline_plotter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * Container for the three individual plotting panels (right, left, heading).
 * 
 * @author PJ
 *
 */
public class SplinePlotterPanel extends JPanel
{
    private final PlotterWheel mLeftWheelPlotter;
    private final PlotterWheel mRightWheelPlotter;
    private final PlotterHeading mHeadingPlotter;
    private final PlotterXY mXYPlotter;

    /**
     * Constructor.
     */
    public SplinePlotterPanel()
    {
        setLayout(new GridBagLayout());
        
        mLeftWheelPlotter = new PlotterWheel("Left Wheel");
        mRightWheelPlotter = new PlotterWheel("Right Wheel");
        mHeadingPlotter = new PlotterHeading("Heading");
        mXYPlotter = new PlotterXY("XY");
        
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(mLeftWheelPlotter, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(mRightWheelPlotter, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(mHeadingPlotter, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(mXYPlotter, gbc);

    }

    /**
     * Sets the spline segments.
     * 
     * @param aPathPoints
     *            The segments
     */
    public void setPath(List<SplineSegment> aPathPoints)
    {
        List<Double> leftPos = new ArrayList<Double>();
        List<Double> leftVel = new ArrayList<Double>();
        List<Double> rightPos = new ArrayList<Double>();
        List<Double> rightVel = new ArrayList<Double>();
        List<Double> heading = new ArrayList<Double>();
        List<PlotterXY.XYPoint> xyList = new ArrayList<PlotterXY.XYPoint>();

        for (int i = 0; i < aPathPoints.size(); ++i)
        {
            leftPos.add(aPathPoints.get(i).mLeftSidePosition);
            leftVel.add(aPathPoints.get(i).mLeftSideVelocity);
            rightPos.add(aPathPoints.get(i).mRightSidePosition);
            rightVel.add(aPathPoints.get(i).mRightSideVelocity);
            heading.add(aPathPoints.get(i).mRobotHeading);

            PlotterXY.XYPoint xyPoint = new PlotterXY.XYPoint();
            xyPoint.mX = aPathPoints.get(i).mAverageX;
            xyPoint.mY = aPathPoints.get(i).mAverageY;
            xyList.add(xyPoint);
        }

        mLeftWheelPlotter.setPath(leftPos, leftVel);
        mRightWheelPlotter.setPath(rightPos, rightVel);
        mHeadingPlotter.setPath(heading);
        mXYPlotter.setPath(xyList);
    }

    /**
     * Clears the measured values.
     */
    public void clearActuals()
    {
        mLeftWheelPlotter.clearActuals();
        mRightWheelPlotter.clearActuals();
        mHeadingPlotter.clearActuals();
        mXYPlotter.clearActuals();
    }

    /**
     * Sets a measured point.
     * 
     * @param aIndex
     *            The index of this measurement
     * @param aSplineSegment
     *            The measurement
     */
    public void setPoint(int aIndex, SplineSegment aSplineSegment)
    {
        mLeftWheelPlotter.setPoint(aIndex, aSplineSegment.mLeftSidePosition, aSplineSegment.mLeftSideVelocity);
        mRightWheelPlotter.setPoint(aIndex, aSplineSegment.mRightSidePosition, aSplineSegment.mRightSideVelocity);
        mHeadingPlotter.setPoint(aIndex, aSplineSegment.mRobotHeading);
        mXYPlotter.setPoint(aSplineSegment.mAverageX, aSplineSegment.mAverageY);
    }

}
