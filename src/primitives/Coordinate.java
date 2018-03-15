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
        _coordinate = coordinate;
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
        if(!(obj instanceof Coordinate))
            return false;
        return Math.abs(_coordinate-((Coordinate)obj)._coordinate)<Models.DELTA_ERROR;
    }

    @Override
    public String toString() {
        return ""+_coordinate;
    }

    // ***************** Operations ******************** //

    /***
     * Function for addition of two Coordinates.
     * @param obj1 The coordinate to add (Operand-1).
     * @param obj The coordinate to add (Operand-2).
     * @return The coordinate resulted by the addition.
     */
    public static Coordinate add(Coordinate obj1, Coordinate obj){
        double newCoordinate = obj1._coordinate + obj._coordinate;
        return new Coordinate(newCoordinate);
    }

    /**
     * Function for subtraction of two Coordinates.
     * @param obj1 The coordinate to subtract (Operand-1).
     * @param obj The coordinate to subtract (Operand-2).
     * @return The coordinate resulted by the subtraction (obj1-obj).
     */
    public static Coordinate sub(Coordinate obj1, Coordinate obj){
        double newCoordinate = obj1._coordinate - obj._coordinate;
        return new Coordinate(newCoordinate);
    }


    /**
     * @param c1 The coordinate to multiply (Operand-1).
     * @param c2 The coordinate to multiply (Operand-2).
     * @return The coordinate resulted by the multiplication.
     */
    public static Coordinate mult(Coordinate c1, Coordinate c2){
        return new Coordinate(c1._coordinate * c2._coordinate);
    }
}
