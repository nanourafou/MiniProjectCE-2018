package primitives;

/**
 * Describe a vector in space.
 */
public class Vector {

    private Point3D _head;

    // ***************** Constructors ********************** //

    /**
     * Construct a vector with 3 doubles for coordinates.
     * @param x Coordinate x of head point.
     * @param y Coordinate y of head point.
     * @param z Coordinate of head point.
     */
    public Vector(double x, double y, double z){
        _head = new Point3D(x,y,z);
    }

    /**
     * Construct a vector with 3 coordinates.
     * @param x Coordinate x of head point.
     * @param y Coordinate y of head point.
     * @param z Coordinate z of head point.
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z){
        _head = new Point3D(x,y,z);
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
        return "Vector : ("+_head._x + ", "+ _head._y + ", "+_head._z+")";
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)return true;
        if(obj==null)return false;
        if(!(obj instanceof Vector))
            return false;
        return ((Vector)obj)._head.equals(this._head);
    }

    // ***************** Operations ******************** //

    /**
     * @param v1 The first vector to add.
     * @param v2 The second vector to add.
     * @return Vector addition v1+v2.
     */
    public static Vector add(Vector v1, Vector v2){
        Coordinate x = Coordinate.add(v1._head._x,v2._head._x);
        Coordinate y = Coordinate.add(v1._head._y,v2._head._y);
        Coordinate z = Coordinate.add(v1._head._z,v2._head._z);
        return new Vector(new Point3D(x, y, z));
    }

    /**
     * @param v1 The first vector to subtract.
     * @param v2 The second vector to subtract.
     * @return Vector subtraction v1-v2.
     */
    public static  Vector sub(Vector v1, Vector v2) {
        return Vector.add(v1, v2.getReverseVector());
    }

    /**
     * @param v The vector to multiply.
     * @param scalar The scalar for the multiplication
     * @return Vector multiplied by scalar
     */
    public static Vector mult(Vector v, double scalar){
        Coordinate x = new Coordinate(scalar*v._head._x.get());
        Coordinate y = new Coordinate(scalar*v._head._y.get());
        Coordinate z = new Coordinate(scalar*v._head._z.get());
        return new Vector(new Point3D(x,y,z));
    }

    /**
     * @param v Operand vector.
     * @return The dot product u.v (u scalar v).
     */
    public double dotProduct(Vector v){
        double x = _head._x.get()*v._head._x.get();
        double y = _head._y.get()*v._head._y.get();
        double z = _head._z.get()*v._head._z.get();
        return x+y+z;
    }


    /**
     * @param u Vector operand 1.
     * @param v Vector operand 2.
     * @return UxV (Cross Product between vectors).
     */
    public static Vector crossProdcut(Vector u, Vector v){
        Coordinate x = Coordinate.sub(
                Coordinate.mult(u._head._y,v._head._z), Coordinate.mult(u._head._z,v._head._y)
        );

        Coordinate y = Coordinate.sub(
                Coordinate.mult(u._head._z,v._head._x), Coordinate.mult(u._head._x,v._head._z)
        );

        Coordinate z = Coordinate.sub(
                Coordinate.mult(u._head._x,v._head._y), Coordinate.mult(u._head._y,v._head._x)
        );

        return new Vector(new Point3D(x,y,z));
    }

    /**
     * @return The size of the vector.
     */
    public double size() {
        return Math.sqrt(
                this.dotProduct(this)
        );
    }

    /**
     * @return The normal vector of the object.
     */
    public Vector getNormalVector(){

        Coordinate x = new Coordinate(0);
        Coordinate y = new Coordinate(-1*_head._z.get());
        Coordinate z = new Coordinate(_head._y);
        return new Vector(new Point3D(x,y,z));//Chelli pas daccord !!
    }

    /***
     * @param v The vector who with test the colinearity
     * @return True if they are colineare
     */
    public boolean isColinear(Vector v){
        // If the cross product of two vector is null there are colinear.
        if(crossProdcut(this, v).size()==0)
            return true;
        return false;
    }


    /**
     * @return The reverse vector -v.
     */
    public Vector getReverseVector() {
        return mult(this,-1);
    }


}
