package org.snobot.nt.spline_plotter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public final class StandaloneMain
{
    private StandaloneMain()
    {

    }

    /**
     * Main Runner.
     * 
     * @param aArgs
     *            optional arguments
     */
    public static void main(String[] aArgs)
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final SplinePlotterPanel panel = new SplinePlotterPanel();

        final List<SplineSegment> path_points = new ArrayList<SplineSegment>();

        SplineSegment splineSegment;

        splineSegment = new SplineSegment();
        path_points.add(splineSegment);

        double radius = 1.7;
        double angleMult = .1;

        for (int i = 1; i < 10; ++i)
        {
            splineSegment = new SplineSegment();
            splineSegment.mLeftSideVelocity = 0 + i * .7;
            splineSegment.mLeftSidePosition = path_points.get(path_points.size() - 1).mLeftSidePosition + splineSegment.mLeftSideVelocity * .02;
            splineSegment.mRightSideVelocity = splineSegment.mLeftSideVelocity;
            splineSegment.mRightSidePosition = splineSegment.mLeftSidePosition;
            splineSegment.mRobotHeading = i;
            splineSegment.mAverageX = (radius * i) * Math.sin(angleMult * i);
            splineSegment.mAverageY = (radius * i) * Math.cos(angleMult * i);

            path_points.add(splineSegment);
        }
        for (int i = 0; i < 20; ++i)
        {
            splineSegment = new SplineSegment();
            splineSegment.mLeftSideVelocity = 7.0;
            splineSegment.mLeftSidePosition = path_points.get(path_points.size() - 1).mLeftSidePosition + splineSegment.mLeftSideVelocity * .02;
            splineSegment.mRightSideVelocity = splineSegment.mLeftSideVelocity;
            splineSegment.mRightSidePosition = splineSegment.mLeftSidePosition;
            splineSegment.mRobotHeading = i;
            splineSegment.mAverageX = (radius * i) * Math.sin(angleMult * i);
            splineSegment.mAverageY = (radius * i) * Math.cos(angleMult * i);
            path_points.add(splineSegment);
        }
        for (int i = 0; i < 10; ++i)
        {
            splineSegment = new SplineSegment();
            splineSegment.mLeftSideVelocity = 7 - i * .7;
            splineSegment.mLeftSidePosition = path_points.get(path_points.size() - 1).mLeftSidePosition + splineSegment.mLeftSideVelocity * .02;
            splineSegment.mRightSideVelocity = splineSegment.mLeftSideVelocity;
            splineSegment.mRightSidePosition = splineSegment.mLeftSidePosition;
            splineSegment.mRobotHeading = i;
            splineSegment.mAverageX = (radius * i) * Math.sin(angleMult * i);
            splineSegment.mAverageY = (radius * i) * Math.cos(angleMult * i);
            path_points.add(splineSegment);
        }

        splineSegment = new SplineSegment();
        splineSegment.mLeftSideVelocity = 0;
        splineSegment.mLeftSidePosition = path_points.get(path_points.size() - 1).mLeftSidePosition + splineSegment.mLeftSideVelocity * .02;
        splineSegment.mRightSideVelocity = splineSegment.mLeftSideVelocity;
        splineSegment.mRightSidePosition = splineSegment.mLeftSidePosition;
        splineSegment.mRobotHeading = 0;
        path_points.add(splineSegment);

        panel.setPath(path_points);

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);

        Thread thread = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                List<SplineSegment> actuals = new ArrayList<SplineSegment>();

                for (int i = 0; i < path_points.size(); ++i)
                {
                    SplineSegment splineSegment = path_points.get(i);
                    splineSegment.mLeftSideVelocity *= .9;
                    splineSegment.mLeftSidePosition = 0;
                    splineSegment.mRightSideVelocity = splineSegment.mLeftSideVelocity * .5;
                    splineSegment.mRightSidePosition = 0;
                    splineSegment.mRobotHeading = 0;

                    if (i != 0)
                    {
                        splineSegment.mLeftSidePosition = actuals.get(i - 1).mLeftSidePosition + splineSegment.mLeftSideVelocity * .02;
                        splineSegment.mRightSidePosition = splineSegment.mLeftSidePosition * .5;
                        splineSegment.mRobotHeading = i * .8;
                        splineSegment.mAverageX = splineSegment.mAverageX * .8;
                        splineSegment.mAverageY = splineSegment.mAverageY * .8;
                    }

                    actuals.add(splineSegment);
                }

                for (int i = 0; i < actuals.size(); ++i)
                {
                    panel.setPoint(i, actuals.get(i));

                    try
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException ex)
                    {
                        ex.printStackTrace(); // NOPMD
                    }
                }
            }
        });
        thread.start();
    }
}
