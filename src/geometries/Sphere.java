package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * The class describing a Sphere in space.
 */
public class Sphere extends RadialGeometry implements IGeometry{
    /***
     * Fields
     */
    private Point3D _center;

    // ***************** Constructors ********************** //

    /***
     * Constructor of the class.
     */
    public Sphere(Point3D center, double radius){
        super(radius);
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
      return   Vector.normalize(p.subVector(p));
    }

    @Override
    public List<Point3D> findIntersections(Ray myRay) {
        Vector l = _center.subVector(myRay.getOrigin());
        double tm = l.dotProduct(myRay.getDirection());
        double d = Math.sqrt(Math.pow(l.size(), 2) - Math.pow(tm, 2));

        if (d > this._radius)
            return null;

        double th = Math.sqrt(Math.pow(_radius, 2) - Math.pow(d, 2));

        double t1 = tm - th;
        double t2 = tm + th;

        java.util.Vector<Point3D> lst = new java.util.Vector<Point3D>();

        if (t1 > 0) {
            Vector v1 = Vector.mult(myRay.getDirection(), t1);
            Point3D p1 = Point3D.addVector(myRay.getOrigin(), v1);
            lst.add(p1);
        }

        if (t2 > 0) {
            Vector v2 = Vector.mult(myRay.getDirection(), t2);
            Point3D p2 = Point3D.addVector(myRay.getOrigin(), v2);
            lst.add(p2);
        }
        return lst;
    }

}