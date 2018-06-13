package geometries;

import elements.Color;
import primitives.*;
import primitives.Vector;

import java.util.*;

/**
 * The class describing a Sphere in space.
 */
public class Sphere extends RadialGeometry {
    /***
     * Fields
     */
    private Point3D _center;

    // ***************** Constructors ********************** //

    /***
     * Constructor of the class.
     */
    public Sphere(Point3D center, double radius, Color clr, Material m) {
        super(radius, clr, m);
        _center = new Point3D(center);
    }

    /***
     *copy ctor of this class.
     */
    public Sphere(Sphere obj) {
        super(obj);
        _center = new Point3D(obj._center);
    }
    // ***************** Getters/Setters ********************** //

    /***
     * @return The center of the sphere.
     */
    public Point3D getCenter() {
        return _center;
    }

    // ***************** Administration  ******************** //


    @Override
    public String toString() {
        return "Sphere: \n " + super.toString() + "\n Center " + _center;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Sphere))
            return false;
        return super.equals(obj) && this._center.equals(((Sphere) obj)._center);

    }

    /***
     * @param p The point of the sphere.
     * @return The vector normal.
     */
    public Vector getNormal(Point3D p) {
        return (p.subVector(this._center)).normalize();
    }

    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray myRay) {
        Map<Geometry, List<Point3D>> intersec = new HashMap<Geometry, List<Point3D>>();
        Point3D p0 = myRay.getOrigin();
        Vector v = myRay.getDirection();
        List<Point3D> list = new ArrayList<Point3D>();
        Vector u = _center.subVector(p0);
        double tm = v.dotProduct(u);
        double d = Math.sqrt(u.dotProduct(u) - tm * tm);
        if (d > _radius)
            return intersec;
        double th = Math.sqrt(_radius * _radius - d * d);

        if (Coordinate.ZERO.equals(new Coordinate(th))) {
            if (tm >= 0)
                list.add(p0.addVector(v.mult(tm)));
        } else {
            double t1 = tm - th;
            if (t1 >= 0)
                list.add(p0.addVector(v.mult(t1)));
            double t2 = th + tm;
            if (t2 >= 0)
                list.add(p0.addVector(v.mult(t2)));
        }
        if (!list.isEmpty())
            intersec.put(this, list);
        return intersec;
    }
}