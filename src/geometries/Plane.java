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
    private Vector _normal;

    // ***************** Constructors ********************** //

    /**
     * Constructor for a plane.
     *
     * @param normalVector The vector director (vector normal deduced).
     * @param point        The point who will be on the plane.
     */
    public Plane(Vector normalVector, Point3D point, Color clr, Material m) {
        super(clr, m);
        this._normal = new Vector(normalVector).normalize();
        this._p = new Point3D(point);
    }


    /**
     * @param x The Coordinate x
     * @param y The Coordinate y
     * @param z The Coordinate y
     */
    public Plane(Point3D x, Point3D y, Point3D z, Color clr, Material m) { // A revoir
        super(clr, m);
        Vector v1 = new Vector(x).normalize();
        Vector v2 = new Vector(y).normalize();

        if (v1.isColinear(v2)) {
            v2 = new Vector(z).normalize();

            if (v1.isColinear(v2)) {
                throw new IllegalArgumentException("Can't define a plan with two colinears vectors");
            }

            this._p = new Point3D(y);
        } else {
            this._p = new Point3D(z);
        }

        _normal = v1.crossProdcut(v2);

    }

    /**
     * Copy Constructor
     */
    public Plane(Plane plane) {
        super(plane);
        this._normal = new Vector(plane._normal);
        this._p = new Point3D(plane._p);
    }


    // ***************** Administration  ******************** //
    @Override
    public String toString() {
        return "P = " + _normal.getHead().getX() + "*" + _p.getX() + "+" + _normal.getHead().getY() + "*" + _p.getY() + "+" + _normal.getHead().getZ() + "*" + _p.getZ();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (!(obj instanceof Plane))
            return false;
        Plane toCompare = (Plane) obj;

        return toCompare._normal.equals(this._normal) && toCompare._p.equals(this._p);

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


    /**
     * The FindIntersection function
     * @param ray
     * @return
     */
    public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
        Map<Geometry, List<Point3D>> intersec = new HashMap<Geometry, List<Point3D>>();
        double nv = _normal.dotProduct(ray.getDirection());
        if (nv == 0)
            return intersec;
        double Nqp = _normal.dotProduct(_p.subVector(ray.getOrigin()));
        double t = Nqp / nv;
        if (t > 0) {
            List<Point3D> list = new ArrayList<Point3D>();
            list.add(ray.getOrigin().addVector(ray.getDirection().mult(t)));
            intersec.put(this, list);
        }
        return intersec;
    }
}
