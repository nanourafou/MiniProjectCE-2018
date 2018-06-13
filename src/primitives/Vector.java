package primitives;

/**
 * Describe a vector in space.
 */
public class Vector {

    private Point3D _head;

    // ***************** Constructors ********************** //

    /**
     * Construct a vector with 3 doubles for coordinates.
     *
     * @param x Coordinate x of head point.
     * @param y Coordinate y of head point.
     * @param z Coordinate of head point.
     */
    public Vector(double x, double y, double z) {
        _head = new Point3D(x, y, z);
    }

    /**
     * Construct a vector with 3 coordinates.
     *
     * @param x Coordinate x of head point.
     * @param y Coordinate y of head point.
     * @param z Coordinate z of head point.
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        _head = new Point3D(x, y, z);
    }

    public Vector(Point3D p1, Point3D p2) {

        this(p2.getX().get() - p1.getX().get(),
                p2.getY().get() - p1.getY().get(),
                p2.getZ().get() - p1.getZ().get());
    }

    /***
     * Construct a vector with a 3D point.
     * @param point 3D point for the head of vector.
     */
    public Vector(Point3D point) {
        _head = new Point3D(point);
    }

    /***
     * Copy Constructor.
     */
    public Vector(Vector myVector) {
        _head = new Point3D(myVector._head);
    }

    // ***************** Getters/Setters ********************** //

    /**
     * @return The head of the vector.
     */
    public Point3D getHead() {
        return _head;
    }

    // ***************** Administration  ******************** //
    @Override
    public String toString() {
        return "Vector : (" + _head._x + ", " + _head._y + ", " + _head._z + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector))
            return false;
        return ((Vector) obj)._head.equals(this._head);
    }

    // ***************** Operations ******************** //

    /**
     * @param v2 The vector to add.
     * @return Vector addition v1+v2.
     */
    public Vector add(Vector v2) {
        Coordinate x = this.getHead()._x.add(v2._head._x);
        Coordinate y = this.getHead()._y.add(v2._head._y);
        Coordinate z = this.getHead()._z.add(v2._head._z);
        return new Vector(x, y, z);
    }

    /**
     * @param v2 The vector to subtract.
     * @return Vector subtraction v1-v2.
     */
    public Vector sub(Vector v2) {
        return this.add(v2.getReverseVector());
    }

    /**
     * @param scalar The scalar for the multiplication
     * @return Vector multiplied by scalar
     */
    public Vector mult(double scalar) {
        Coordinate x = new Coordinate(scalar * this._head._x.get());
        Coordinate y = new Coordinate(scalar * this._head._y.get());
        Coordinate z = new Coordinate(scalar * this._head._z.get());
        return new Vector(x, y, z);
    }

    /**
     * @param v Operand vector.
     * @return The dot product u.v (u scalar v).
     */
    public double dotProduct(Vector v) {
        double x = _head._x.get() * v._head._x.get();
        double y = _head._y.get() * v._head._y.get();
        double z = _head._z.get() * v._head._z.get();
        return x + y + z;
    }


    /**
     * @param v Vector operand.
     * @return UxV (Cross Product between vectors).
     */
    public Vector crossProdcut(Vector v) {
        /*
        //Alias
        Point3D p = this.getHead();
        Point3D pv = v.getHead();


        Coordinate x = (p._y.mult(pv._z)).sub((pv._y.mult(p._z)));
        Coordinate y = (p._z.mult(pv._x)).sub((pv._z.mult(p._x)));
        Coordinate z = (p._x.mult(pv._y)).sub((pv._x.mult(p._y)));
        return new Vector(x,y,z);
        */

        double u1 = this.getHead().getX().get();
        double u2 = this.getHead().getY().get();
        double u3 = this.getHead().getZ().get();

        double v1 = v.getHead().getX().get();
        double v2 = v.getHead().getY().get();
        double v3 = v.getHead().getZ().get();

        return new Vector(u2 * v3 - u3 * v2, u3 * v1 - u1 * v3, u1 * v2 - u2 * v1);

    }

    /**
     * @return The size of the vector.
     */
    public double size() {
        return Math.sqrt(this.dotProduct(this));
    }


    /***
     * @param v The vector who with test the colinearity
     * @return True if they are colineare
     */
    public boolean isColinear(Vector v) {
        // If the cross product of two vector is null there are colinear.
        return (this.crossProdcut(v).size() == 0); // A revoir
    }


    /**
     * @return A new Vector normalised
     */
    public Vector normalize() {
        double size = size();
        if (size == 0)
            throw new ArithmeticException("Can't divide by zero");
        return new Vector(_head).mult(1 / size);
    }

    /**
     * @return The reverse vector -v.
     */
    private Vector getReverseVector() {
        return this.mult(-1);
    }


}
