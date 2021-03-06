package mma.legacy.interval;

/**
 * Checks for Open/Closed intervals.
 */
class Interval {

    // the highest interval value.
    private double maximum;
    // the lowest interval value.
    private double minimum;
    // refers to the interval opening type (may be open/close)
    private Opening opening;


    /**
     * Constructor.
     *
     * @param minimum
     *            the lowest interval value.
     * @param maximum
     *            the highest interval value.
     * @param opening
     *            refers to the interval opening type (may be open/close)
     */
    Interval(double minimum, double maximum, Opening opening) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.opening = opening;
    }


    @Override
    public boolean equals(Object obj) {
        boolean result = true;
        if (this == obj) {
            result = true;
        }
        if (obj == null) {
            result = false;
        }
        if (this.getClass() != obj.getClass()) {
            result = false;
        }
        Interval other = (Interval) obj;
        if (Double.doubleToLongBits(this.maximum) != Double.doubleToLongBits(other.maximum)) {
            result = false;
        }
        if (Double.doubleToLongBits(this.minimum) != Double.doubleToLongBits(other.minimum)) {
            result = false;
        }
        if (this.opening != other.opening) {
            result = false;
        }
        return result;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(this.maximum);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.minimum);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((this.opening == null) ? 0 : this.opening.hashCode());
        return result;
    }


    /**
     * Este método mira si un número está dentro de un determiando intervalo
     */
    public boolean includes(double value) {
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
     * @param interval
     * @return
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
                        return false;
                }
            default :
                return false;
        }
    }


    /**
     * Este método calcula el ancho de un intervalo
     *
     * @param interval
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
                    return false;
            }
        }
        return this.includes(interval.minimum) || this.includes(interval.maximum);
    }


    /**
     * This method calculates the mean of maximun and minimum.
     *
     * @return
     */
    public double midPoint() {
        return (this.maximum + this.minimum) / 2;
    }


    @Override
    public String toString() {
        return "Interval [maximum=" + this.maximum + ", minimum=" + this.minimum + ", opening=" + this.opening + "]";
    }
}
