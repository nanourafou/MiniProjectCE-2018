package primitives;

/***
 * Class describing coordinate in space.
 */
public class Coordinate {

    private double _coordinate;

    private static final int ACCURACY = -20; // Accuracy for i-dyoukim

    public static boolean isZero(double number) {
        return getExp(number) < ACCURACY;
    }

    // ***************** Constructors ********************** //

    /***
     * This function is the Constructor of Coordinate.
     * @param coordinate : The coordinate of the point.
     */
    public Coordinate(double coordinate) {
        _coordinate = (getExp(coordinate) < ACCURACY) ? 0.0 : coordinate;
    }

    /**
     * This function is the Copy Constructor.
     *
     * @param c
     */
    public Coordinate(Coordinate c) {
        _coordinate = c._coordinate;
    }
    // ***************** Getters/Setters ********************** //

    /***
     *
     * @return The coordinate.
     */
    public double get() {
        return _coordinate;
    }

    // ***************** Administration  ******************** //
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Coordinate))
            return false;
        return sub((Coordinate) obj)._coordinate == 0.0;
    }

    @Override
    public String toString() {
        return "" + _coordinate;
    }

    // ***************** Operations ******************** //

    /**
     * This function is the difference between two coordinates.
     * It take attention to the no precise coordinate.
     * @param other The other Coordinate
     * @return The substraction between Coordinates
     */
    public Coordinate sub(Coordinate other) {
        int otherExp = getExp(other._coordinate); // ???
        int thisExp = getExp(this._coordinate);


        if (otherExp - thisExp < ACCURACY)
            return new Coordinate(this._coordinate);

        if (thisExp - otherExp < ACCURACY)
            return new Coordinate(-other._coordinate);

        double result = this._coordinate - other._coordinate;

        int resultExp = getExp(result);
        return resultExp < ACCURACY ? new Coordinate(0) : new Coordinate(result);
    }


    /**
     * This function is the addition between two coordinates
     * It take attention to the no precise coordinate.
     * @param other The other Coordinate
     * @return The addition between Coordinates
     */
    public Coordinate add(Coordinate other) {
        int otherExp = getExp(other._coordinate);
        int thisExp = getExp(this._coordinate);


        if (otherExp - thisExp < ACCURACY)
            return new Coordinate(this._coordinate);

        if (thisExp - otherExp < ACCURACY)
            return new Coordinate(other._coordinate);

        double result = this._coordinate + other._coordinate;

        int resultExp = getExp(result);
        return resultExp < ACCURACY ? new Coordinate(0.0) : new Coordinate(result);
    }

    /**
     * @param c2 The other Coordinate
     * @return New Coordinate multed
     */
    public Coordinate mult(Coordinate c2) {
        return new Coordinate(this._coordinate * c2._coordinate);
    }

    /**
     * @param num The Coordinate
     * @return Get the exp filed of a bytes number
     */
    private static int getExp(double num) {
        return (int) ((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
    }


}
