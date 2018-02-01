package org.snobot.nt.path_plotter;

public class PathSetpoint
{

    public enum TrapezoidSegment
    {
        Acceleration, ConstantVelocity, Deceleration
    }

    /** The segment of the profile this segment is in. */
    public TrapezoidSegment mSegment;

    /** The desired position for this setpoint. */
    public double mPosition;

    /** The desired velocity for this setpoint. */
    public double mVelocity;

    /** The desired acceleration for this setpoint. */
    public double mAcceleration;

    /**
     * Constructor.
     */
    public PathSetpoint()
    {
        this(TrapezoidSegment.Acceleration, .02, 0, 0, 0);
    }

    /**
     * Constructor.
     * 
     * @param aSegment
     *            The individual segment for an instance in time
     * @param aDt
     *            The expected time
     * @param aPosition
     *            The position, in inches
     * @param aVelocity
     *            The velocity, in in/sec
     * @param aAccel
     *            The acceleration
     */
    public PathSetpoint(TrapezoidSegment aSegment, double aDt, double aPosition, double aVelocity, double aAccel)
    {
        mSegment = aSegment;
        mPosition = aPosition;
        mVelocity = aVelocity;
        mAcceleration = aAccel;
    }

}
