package geometries;

import primitives.Point3D;
import primitives.Vector;

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

}