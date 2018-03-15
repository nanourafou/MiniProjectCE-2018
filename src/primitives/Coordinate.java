package primitives;

/***
 * Class describing coordinate in space.
 */
public class Coordinate {

    private double _coordinate;

    // ***************** Constructors ********************** //
    /***
     * Constructor of Coordinate.
     * @param coordinate : The coordinate of the point.
     */
    public Coordinate(double coordinate){
        _coordinate = (getExp(coordinate) < ACCURACY) ? 0.0 : coordinate;
    }

    /**
     * Copy Constructor.
     * @param c
     */
    public Coordinate(Coordinate c){
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
        if(this==obj)return true;
        if(obj==null)return false;
        if(!(obj instanceof Coordinate))
            return false;
        return Coordinate.sub(this,(Coordinate)obj)._coordinate== 0.0;
    }

    @Override
    public String toString() {
        return ""+_coordinate;
    }

    // ***************** Operations ******************** //



    public static Coordinate sub(Coordinate me, Coordinate other) {
        int otherExp = getExp(other._coordinate);
        int thisExp = getExp(me._coordinate);



        if (otherExp - thisExp < ACCURACY)
            return new Coordinate(me._coordinate);

        if (thisExp - otherExp < ACCURACY)
            return new Coordinate(-other._coordinate);

        double result = me._coordinate - other._coordinate;

        int resultExp = getExp(result);
        return resultExp < ACCURACY ? new Coordinate(0) : new Coordinate(result);
    }


    public static Coordinate add(Coordinate me, Coordinate other) {
        int otherExp = getExp(other._coordinate);
        int thisExp = getExp(me._coordinate);


        if (otherExp - thisExp < ACCURACY)
            return new Coordinate(me._coordinate);

        if (thisExp - otherExp < ACCURACY)
            return new Coordinate(other._coordinate);

        double result = me._coordinate + other._coordinate;

        int resultExp = getExp(result);
        return resultExp < ACCURACY ? new Coordinate(0.0) : new Coordinate(result);
    }

    /**
     * @param c1 The coordinate to multiply (Operand-1).
     * @param c2 The coordinate to multiply (Operand-2).
     * @return The coordinate resulted by the multiplication.
     */
    public static Coordinate mult(Coordinate c1, Coordinate c2){
        return new Coordinate(c1._coordinate * c2._coordinate);
    }


    private static final int ACCURACY = -20;

    public static int getExp(double num) {
        return (int) ((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
    }


}
