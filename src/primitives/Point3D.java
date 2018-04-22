package primitives;

/**
 * Describe a 3D point in space
 */
public class Point3D extends Point2D {

    protected Coordinate _z;


    // ***************** Constructors ********************** //

    public static final Point3D ORIGIN= new Point3D(0,0,0);


    /**
     * Constructor of a 3D point.
     * @param x The coordinate on the x axe.
     * @param y The coordinate on the y axe.
     * @param z The coordinate on the z axe.
     */
    public Point3D(double x, double y, double z) {
        super(x,y);
        _z = new Coordinate(z);
    }

    /**
     * Constructor of a 3D point.
     * @param x The coordinate on the x axe.
     * @param y The coordinate on the y axe.
     * @param z The coordinate on the z axe.
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        super(x, y);
        _z = new Coordinate(z);
    }

    /**
     * Copy Constructor.
     */
    public Point3D(Point3D point){
        super(point);
        _z = new Coordinate(point._z);
    }


    // ***************** Getters/Setters ********************** //

    /**
     * @return The coordinate on the z axe.
     */
    public Coordinate getZ() {
        return _z;
    }


    // ***************** Administration  ******************** //
    @Override
    public boolean equals(Object obj) {
        if(this==obj)return true;
        if(obj==null)return false;
        if (!(obj instanceof Point3D))
            return false;
        Point3D toCompareObj = (Point3D) obj;
        return super.equals(obj) && _z.equals(toCompareObj._z);
    }


    @Override
    public String toString() {
        return "Point : ("+_x + ", "+ _y + ", "+_z+")";
    }

    /**
     * @param point The point who will calculate the distance.
     * @return The distance between the 2 points.
     */
    public double distance(Point3D point) {
        Coordinate newX = _x.sub(point._x);
        Coordinate newY = _y.sub(point._y);
        Coordinate newZ = _z.sub(point._z);
        double result = Math.sqrt(
                Math.pow(newX.get(), 2.0) + Math.pow(newY.get(), 2.0) + Math.pow(newZ.get(), 2.0)
        );
        return result;
    }

    /**
     * @param vec The vector to add (Operand-2).
     * @return A new point resulted by addition of a vector and another point.
     */
    public Point3D addVector(Vector vec){
        Coordinate x = this._x.add(vec.getHead()._x);
        Coordinate y = this._y.add(vec.getHead()._y);
        Coordinate z = this._z.add(vec.getHead()._z);
        return new Point3D(x,y,z);
    }

    /**
     * @param p The 3D point to subtract (Operand-1).
     * @return A new vector resulted by subtraction of a vector and another point.
     */
    public Vector subVector(Point3D p){
        Coordinate x = this._x.sub(p._x);
        Coordinate y = this._y.sub(p._y);
        Coordinate z = this._z.sub(p._z);
        return new Vector(x,y,z); //Why don't return point !
    }
}
