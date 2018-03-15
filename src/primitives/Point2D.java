package primitives;

/**
 * Describe a 2D point in space.
 */
public class Point2D {

    protected Coordinate _x;
    protected Coordinate _y;

    // ***************** Constructors ********************** //
    /***
     * Construct a 2D point.
     * @param x The coordinate on the x axe.
     * @param y The coordinate on the y axe.
     */
    public Point2D(double x, double y) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
    }

    /***
     * Construct a 2D point.
     * @param x The coordinate on the x axe.
     * @param y The coordinate on the y axe.
     */
    public Point2D(Coordinate x, Coordinate y) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
    }

    /**
     * Copy Constructor.
     */
    public Point2D(Point2D obj) {
        this._x = new Coordinate(obj._x);
        this._y = new Coordinate(obj._y);
    }

    // ***************** Getters/Setters ********************** //
    /***
     * @return _x The x coordinate.
     */
    public Coordinate getX() {
        return _x;
    }

    /**
     * @return _x The x coordinate.
     */
    public Coordinate getY() {
        return _y;
    }


    // ***************** Administration  ******************** //
    /***
     * The function check if the object are equals.
     * @param obj The 2D point to compare.
     */
    @Override
    public boolean equals(Object obj) {
        if(this==obj)return true;
        if(obj==null)return false;
        if (!(obj instanceof Point2D))
            return false;
        Point2D toCompareObj = (Point2D) obj;
        return _x.equals(toCompareObj._x) && _y.equals(toCompareObj._y);
    }

    @Override
    public String toString() {
        return "Point : ("+_x + ", "+ _y +")";
    }
}


