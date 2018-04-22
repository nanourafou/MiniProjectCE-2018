package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * The class describing a Plane in space.
 */
public class Plane extends Geometry {

    private Point3D _p;
    private Vector  _normal;

    // ***************** Constructors ********************** //

    /**
     * Constructor for a plane.
     * @param normalVector The vector director (vector normal deduced).
     * @param point The point who will be on the plane.
     */
    public Plane(Vector normalVector, Point3D point){
        this._normal = new Vector(normalVector);
        this._p = new Point3D(point);
    }

    /**
     * Constructor for a plane.
     * @param v1 The vector director (vector normal deduced).
     * @param v2 The vector director (need to not be colinear with v1).
     * @param p The point who will be on the plane.
     */
    public Plane(Vector v1, Vector v2, Point3D p) throws Exception{

        if(Vector.crossProdcut(v1,v2).size()==0)
            throw new Exception("Can't define a plan with two colinears vectors");

        if(!v1.isColinear(v2)) // if n
        {
            _normal = Vector.crossProdcut(v1,v2);
            _p = new Point3D(p);
        }
    }

    public Plane(Point3D x, Point3D y, Point3D z) throws Exception{
        Vector v1 = new Vector(x);
        Vector v2 = new Vector(y);

        if(v1.isColinear(v2)) {
            v2 = new Vector(z);

            if (v1.isColinear(v2)){
                throw new Exception("Can't define a plan with two colinears vectors");
            }

            this._p = new Point3D(y);
        }
        else {
            this._p = new Point3D(z);
        }

        _normal = Vector.crossProdcut(v1,v2);

    }

    /**
     * Copy Constructor
     */
    public Plane(Plane plane){
        this._normal = new Vector(plane._normal);
        this._p = new Point3D(plane._p);
    }


    // ***************** Administration  ******************** //
    @Override
    public String toString() {
        return "P = "+_normal.getHead().getX()+"*"+_p.getX()+"+"+_normal.getHead().getY()+"*"+_p.getY()+"+"+_normal.getHead().getZ()+"*"+_p.getZ();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if(!(obj instanceof Plane))
            return false;
        Plane toCompare = (Plane)obj;

        return toCompare._normal.equals(this._normal)&&toCompare._p.equals(this._p);

    }

    // ***************** Getters/Setters ********************** //


    /**
     * @return The point on the plane.
     */
    public Point3D getPoint() {
        return _p;
    }

    // ***************** Operations ******************** //

    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }
}
