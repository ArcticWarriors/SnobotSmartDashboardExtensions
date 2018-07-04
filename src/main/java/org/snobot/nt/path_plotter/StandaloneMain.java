package org.snobot.nt.path_plotter;

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
     * @param aArg
     *            optional arguments
     */
    public static void main(String[] aArg)
    {

        final PathPlotterPanel panel = new PathPlotterPanel();

        final List<PathSetpoint> path_points = new ArrayList<PathSetpoint>();

        PathSetpoint testPoint;

        testPoint = new PathSetpoint();
        testPoint.mVelocity = 0;
        testPoint.mPosition = 0;
        path_points.add(testPoint);

        for (int i = 1; i < 10; ++i)
        {
            testPoint = new PathSetpoint();
            testPoint.mVelocity = 0 + i * .7;
            testPoint.mPosition = path_points.get(path_points.size() - 1).mPosition + testPoint.mVelocity * .02;
            path_points.add(testPoint);
        }
        for (int i = 0; i < 20; ++i)
        {
            testPoint = new PathSetpoint();
            testPoint.mVelocity = 7.0;
            testPoint.mPosition = path_points.get(path_points.size() - 1).mPosition + testPoint.mVelocity * .02;
            path_points.add(testPoint);
        }
        for (int i = 0; i < 10; ++i)
        {
            testPoint = new PathSetpoint();
            testPoint.mVelocity = 7 - i * .7;
            testPoint.mPosition = path_points.get(path_points.size() - 1).mPosition + testPoint.mVelocity * .02;
            path_points.add(testPoint);
        }

        testPoint = new PathSetpoint();
        testPoint.mVelocity = 0;
        testPoint.mPosition = path_points.get(path_points.size() - 1).mPosition + testPoint.mVelocity * .02;
        path_points.add(testPoint);

        panel.setPath(path_points);

        JFrame frame = new JFrame();
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);

        Thread threa = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                List<PathSetpoint> actuals = new ArrayList<PathSetpoint>();

                for (int i = 0; i < path_points.size(); ++i)
                {
                    PathSetpoint setpoint = path_points.get(i);
                    setpoint.mVelocity *= .9;
                    setpoint.mPosition = 0;

                    if (i != 0)
                    {
                        setpoint.mPosition = actuals.get(i - 1).mPosition + setpoint.mVelocity * .02;
                    }

                    actuals.add(setpoint);
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
        threa.start();
    }
}
