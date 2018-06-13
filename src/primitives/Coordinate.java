package primitives;

/***
 * Class describing coordinate in space.
 */
public class Coordinate {

    private double _coordinate;

    public static Coordinate ZERO = new Coordinate(0.0);

    private static final int ACCURACY = -20; // Accuracy for i-dyoukim

    // ***************** Constructors ********************** //

    /***
     * Constructor of Coordinate.
     * @param coordinate : The coordinate of the point.
     */
    public Coordinate(double coordinate) {
        _coordinate = (getExp(coordinate) < ACCURACY) ? 0.0 : coordinate;
    }

    /**
     * Copy Constructor.
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
    private int getExp(double num) {
        return (int) ((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
    }


}
