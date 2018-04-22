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
     * @param v2 The vector to add.
     * @return Vector addition v1+v2.
     */
    public  Vector add(Vector v2){
        Coordinate x = this.getHead()._x.add(v2._head._x);
        Coordinate y = this.getHead()._y.add(v2._head._y);
        Coordinate z = this.getHead()._x.add(v2._head._z);
        return new Vector(x, y, z);
    }

    /**
     * @param v2 The vector to subtract.
     * @return Vector subtraction v1-v2.
     */
    public  Vector sub(Vector v2) {
        return this.add(v2.getReverseVector());
    }

    /**

     * @param scalar The scalar for the multiplication
     * @return Vector multiplied by scalar
     */
    public Vector mult(double scalar){
        Coordinate x = new Coordinate(scalar*this._head._x.get());
        Coordinate y = new Coordinate(scalar*this._head._y.get());
        Coordinate z = new Coordinate(scalar*this._head._z.get());
        return new Vector(x,y,z);
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
     * @param v Vector operand.
     * @return UxV (Cross Product between vectors).
     */
    public Vector crossProdcut(Vector v){

        //Alias
        Point3D p = this.getHead();
        Point3D pv = v.getHead();

        Coordinate x = (p._y.mult(pv._z)).sub((pv._y.mult(p._z)));
        Coordinate y = (p._z.mult(pv._x)).sub((pv._z.mult(p._x)));
        Coordinate z = (p._x.mult(pv._y)).sub((pv._x.mult(p._y)));

        return new Vector(x,y,z);
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
    public boolean isColinear(Vector v){
        // If the cross product of two vector is null there are colinear.
        return (this.crossProdcut(this).size()==0); // A revoir
    }


    /**
     * @return A new Vector normalised
     */
    public Vector normalize(){
        double size = size();
        double x = getHead().getX().get() / size;
        double y = getHead().getY().get() / size;
        double z = getHead().getZ().get() / size;

        return new Vector(x,y,z);
    }

    /**
     * @return The reverse vector -v.
     */
    public Vector getReverseVector() {
        return this.mult(-1);
    }


}
