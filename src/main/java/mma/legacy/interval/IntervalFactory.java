package mma.legacy.interval;

/**
 * Clase que implementa el patrón de diseño Factory de Interval.
 *
 * @author Giedre & Iker
 */
public class IntervalFactory {

    public static Interval getInterval(double minimum, double maximum, Opening opening) {
        return new Interval(minimum, maximum, opening);
    }
}
