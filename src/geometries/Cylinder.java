package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * The class describing a Cylinder in space.
 */
public class Cylinder  extends RadialGeometry{

    private Point3D _originPoint;
    private Ray _directionRay;
    private double _height;


    // ***************** Constructors ********************** //
    /**
     * Constructor for a Cylinder.
     * @param directionRay Ray to define the direction and inclinaison.
     * @param origin Original position for the cylinder.
     * @param height The height of the cylinder.
     */
    public Cylinder(Ray directionRay, double radius, Point3D origin, double height){
        super(radius);
        this._directionRay = new Ray(directionRay);
        this._height = height;
        this._originPoint = new Point3D(origin);
    }


    /**
     * Copy Constructor
     */
    public Cylinder(Cylinder cyl){
        super(cyl);
        this._originPoint = new Point3D(cyl._originPoint);
        this._height = cyl._height;
        this._directionRay = new Ray(cyl._directionRay);
    }

    // ***************** Administration  ******************** //
    @Override
    public String toString() {
        return "Cylinder : \n "+ super.toString() +" \n Ray: "+_directionRay+"\n "+_originPoint+"\n "+"Height: "+_height;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if(!(obj instanceof Cylinder))
            return false;
        Cylinder toCompare = (Cylinder)obj;
        return _originPoint.equals(toCompare._originPoint)&&_directionRay.equals(toCompare._directionRay)&&(_height==toCompare._height)&&super.equals(toCompare);
    }

    // ***************** Getters/Setters ********************** //

    /**
     * @return The original position for the cylinder.
     */
    public Point3D getOriginPoint() {
        return _originPoint;
    }

    /**
     * @return The height of the cylinder.
     */
    public double getHeight() {
        return _height;
    }

    /**
     * @return The the direction and inclinaison of the cylinder.
     */
    public Ray getDirectionRay() {
        return _directionRay;
    }

    // ***************** Operations ******************** //

    @Override
    public Vector getNormal(Point3D p) {
        //Vector u = Vector.sub(p,_directionRay.getDirection());
       // Point3D proj = Vector.mult(_directionRay.getDirection(),u.dotProduct(_directionRay.getDirection()))
        //         / (_directionRay.getDirection().dotProduct(_directionRay.getDirection())
        return null;
    }


}
