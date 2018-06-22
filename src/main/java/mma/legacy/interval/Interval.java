package mma.legacy.interval;

/**
 * Clase que controla operaciones sobre intervalos que pudeen ser abiertos o cerrados.
 *
 * @author Giedre & Iker
 */
public class Interval {

    private double maximum;
    private double minimum;
    private Opening opening;


    /**
     * Constructor de la clase.
     *
     * @param minimum
     *            se refiere al mínimo del intervalo.
     * @param maximum
     *            se refiere al mínimo del intervalo.
     * @param opening
     *            define el tipo de apertura posible de un intervalo.
     */
    public Interval(double minimum, double maximum, Opening opening) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.opening = opening;
        System.out.println("Objeto creado");
    }


    @Override
    public boolean equals(Object object) {
        // TODO
        return false;
    }


    /**
     * Comprueba si un valor concreto esta dentro o no de la apertura definida en opening.
     *
     * @param value
     *            indica que se desea verificar si esta o no incluído dentro del intervalo.
     * @return valor booleano indicando si esta o no dentro del intervalo definido en opening.
     */
    public boolean includes(double value) {
        System.out.print("Entro en el método");
        switch (this.opening) {
            case BOTH_OPENED :
                return this.minimum < value && value < this.maximum;
            case LEFT_OPENED :
                return this.minimum < value && value <= this.maximum;
            case RIGHT_OPENED :
                return this.minimum <= value && value < this.maximum;
            case UNOPENED :
                return this.minimum <= value && value <= this.maximum;
            default :
                return false;
        }
    }


    /**
     * Este método calcula si un número está dentro de un intervalo
     *
     * @param interval,
     *            intervalo sobre el que se calculara si el número está dentro o no.
     * @return valor booleano indicando si esta o no dentro del intervalo definido en opening.
     */
    public boolean includes(Interval interval) {
        boolean minimumIncluded = this.includes(interval.minimum);
        boolean maximumIncluded = this.includes(interval.maximum);
        switch (this.opening) {
            case BOTH_OPENED :
                switch (interval.opening) {
                    case BOTH_OPENED :
                        return (minimumIncluded || this.minimum == interval.minimum)
                                && (maximumIncluded || this.maximum == interval.maximum);
                    case LEFT_OPENED :
                        return (minimumIncluded || this.minimum == interval.minimum) && (maximumIncluded);
                    case RIGHT_OPENED :
                        return (minimumIncluded) && (maximumIncluded || this.maximum == interval.maximum);
                    case UNOPENED :
                        return (minimumIncluded) && (maximumIncluded);
                    default :
                        assert false;
                        return false;
                }
            case LEFT_OPENED :
                switch (interval.opening) {
                    case BOTH_OPENED :
                        return (minimumIncluded || this.minimum == interval.minimum)
                                && (maximumIncluded || this.maximum == interval.maximum);
                    case LEFT_OPENED :
                        return (minimumIncluded || this.minimum == interval.minimum)
                                && (maximumIncluded || this.maximum == interval.maximum);
                    case RIGHT_OPENED :
                        return (minimumIncluded) && (maximumIncluded || this.maximum == interval.maximum);
                    case UNOPENED :
                        return (minimumIncluded) && (maximumIncluded || this.maximum == interval.maximum);
                    default :
                        assert false;
                        return false;
                }
            case RIGHT_OPENED :
                switch (interval.opening) {
                    case BOTH_OPENED :
                        return (minimumIncluded || this.minimum == interval.minimum)
                                && (maximumIncluded || this.maximum == interval.maximum);
                    case LEFT_OPENED :
                        return (minimumIncluded || this.minimum == interval.minimum) && (maximumIncluded);
                    case RIGHT_OPENED :
                        return (minimumIncluded || this.minimum == interval.minimum)
                                && (maximumIncluded || this.maximum == interval.maximum);
                    case UNOPENED :
                        return (minimumIncluded || this.minimum == interval.minimum) && (maximumIncluded);
                    default :
                        assert false;
                        return false;
                }
            case UNOPENED :
                switch (interval.opening) {
                    case BOTH_OPENED :
                        return (minimumIncluded || this.minimum == interval.minimum)
                                && (maximumIncluded || this.maximum == interval.maximum);
                    case LEFT_OPENED :
                        return (minimumIncluded || this.minimum == interval.minimum)
                                && (maximumIncluded || this.maximum == interval.maximum);
                    case RIGHT_OPENED :
                        return (minimumIncluded || this.minimum == interval.minimum)
                                && (maximumIncluded || this.maximum == interval.maximum);
                    case UNOPENED :
                        return (minimumIncluded || this.minimum == interval.minimum)
                                && (maximumIncluded || this.maximum == interval.maximum);
                    default :
                        assert false;
                        return false;
                }
            default :
                assert false;
                return false;
        }
    }


    /**
     * Este método calcula el ancho de un intervalo.
     *
     * @param interval,
     *            intervalo sobre el que se calculara si es el ancho de un intervalo.
     * @return
     */
    public boolean intersectsWith(Interval interval) {
        if (this.minimum == interval.maximum) {
            switch (this.opening) {
                case BOTH_OPENED :
                case LEFT_OPENED :
                    return false;
                case RIGHT_OPENED :
                case UNOPENED :
                    return interval.opening == Opening.LEFT_OPENED || interval.opening == Opening.UNOPENED;
                default :
                    assert false;
                    return false;
            }
        }
        if (this.maximum == interval.minimum) {
            switch (this.opening) {
                case BOTH_OPENED :
                case RIGHT_OPENED :
                    return false;
                case LEFT_OPENED :
                case UNOPENED :
                    return interval.opening == Opening.RIGHT_OPENED || interval.opening == Opening.UNOPENED;
                default :
                    assert false;
                    return false;
            }
        }
        return this.includes(interval.minimum) || this.includes(interval.maximum);
    }


    /**
     * Este método calcula el punto medio de un intervalo.
     *
     * @return devuelve el punto medio.
     */
    public double midPoint() {
        return (this.maximum + this.minimum) / 2;
    }


    @Override
    public String toString() {
        // TODO
        return null;
    }
}
