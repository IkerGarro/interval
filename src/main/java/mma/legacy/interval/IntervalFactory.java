package mma.legacy.interval;

/**
 * Factory class for Interval.
 */
public class IntervalFactory {

    /**
     * A new interval is created according to the next parameters.
     * 
     * @param minimum
     *            the lowest interval value.
     * @param maximum
     *            the highest interval value.
     * @param opening
     *            refers to the interval opening type (may be open/close)
     * @return Interval instance created based on the above parameters.
     */
    static Interval getInterval(double minimum, double maximum, Opening opening) {
        return new Interval(minimum, maximum, opening);
    }
}
