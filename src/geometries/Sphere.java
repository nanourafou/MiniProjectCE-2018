package geometries;

import elements.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
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
    public Sphere(Point3D center, double radius, Color clr, Material m){
        super(radius, clr, m);
        _center=new Point3D(center);
    }

    /***
     *copy ctor of this class.
     */
    public Sphere(Sphere obj){
        super(obj);
        _center=new Point3D(obj._center);
    }
    // ***************** Getters/Setters ********************** //
    /***
     * @return The center of the sphere.
     */
    public Point3D getCenter(){
        return _center;
    }

    // ***************** Administration  ******************** //


    @Override
    public String toString() {
        return "Sphere: \n "+super.toString()+"\n Center "+_center;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if(!(obj instanceof Sphere))
            return false;
        return super.equals(obj)&&this._center.equals(((Sphere)obj)._center);

    }

    /***
     * @param p The point of the sphere.
     * @return The vector normal.
     */
    public Vector getNormal(Point3D p){
      return   p.subVector(p).normalize();
    }

    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray myRay) {
        Vector l = _center.subVector(myRay.getOrigin());
        double tm = l.dotProduct(myRay.getDirection());
        double d = Math.sqrt(Math.pow(l.size(), 2) - Math.pow(tm, 2));

        if (d > this._radius)
            return null;

        double th = Math.sqrt(Math.pow(_radius, 2) - Math.pow(d, 2));

        double t1 = tm - th;
        double t2 = tm + th;

        List<Point3D> lst = new ArrayList<Point3D>();

        if (t1 > 0) {
            Vector v1 = myRay.getDirection().mult(t1);
            Point3D p1 = myRay.getOrigin().addVector(v1);
            lst.add(p1);
        }

        if (t2 > 0) {
            Vector v2 = myRay.getDirection().mult(t2);
            Point3D p2 = myRay.getOrigin().addVector(v2);
            lst.add(p2);
        }
        Map<Geometry, List<Point3D>> m = new HashMap<>();
        m.put(this,lst);
        return m;
    }

}