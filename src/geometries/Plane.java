package geometries;

import elements.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.*;

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
    public Plane(Vector normalVector, Point3D point, Color clr, Material m){
        super(clr, m);
        this._normal = new Vector(normalVector).normalize();
        this._p = new Point3D(point);
    }


    /**
     * @param x The Coordinate x
     * @param y The Coordinate y
     * @param z The Coordinate y
     */
    public Plane(Point3D x, Point3D y, Point3D z, Color clr, Material m){ // A revoir
        super(clr, m);
        Vector v1 = new Vector(x).normalize();
        Vector v2 = new Vector(y).normalize();

        if(v1.isColinear(v2)) {
            v2 = new Vector(z).normalize();

            if (v1.isColinear(v2)){
                throw new IllegalArgumentException("Can't define a plan with two colinears vectors");
            }

            this._p = new Point3D(y);
        }
        else {
            this._p = new Point3D(z);
        }

        _normal = v1.crossProdcut(v2);

    }

    /**
     * Copy Constructor
     */
    public Plane(Plane plane){
        super(plane);
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
        return _normal.normalize();
    }

    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray myRay) {

        Map<Geometry, List<Point3D>> m = new HashMap<>();


        double denom = _normal.dotProduct(myRay.getDirection());

        if(denom==0)
            return null; //prevent divide by zero

        Vector d = this._p.subVector(myRay.getOrigin());

        double t = _normal.dotProduct(d) / denom;

        Vector v = myRay.getDirection().mult(t);

        Point3D p = myRay.getOrigin().addVector(v);

        List<Point3D> lst = new ArrayList<>();

        lst.add(p);

        m.put(this,lst);
        return m;

    }
}
