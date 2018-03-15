package geometries;

import primitives.Models;
import primitives.Point3D;

/**
 * Abstract class who describing all radial geometries in spaces.
 */


public abstract class RadialGeometry extends Geometry {

    Point3D _radius; //Vector, Point3D, double ??

    // ***************** Constructors ********************** //
    public RadialGeometry(RadialGeometry x){
        super(x);
        _radius = new Point3D(x._radius);
    }

    public RadialGeometry(Point3D radius){
        super(); //need this ???
        _radius = new Point3D(radius);
    }

    public RadialGeometry(){}

    // ***************** Getters/Setters ********************** //

    /**
     * @return the radius of the radial object
     */
    public Point3D getRadius() {
        return _radius;
    }

    /*
    public double getRadius(){
        return Models.ORIGIN.distance(_radius); //Fixed to center ????
    }
    */


    // ***************** Administration  ******************** //

    @Override
    public String toString() {
        return "Radius "+_radius;
    }

    @Override
    public boolean equals(Object obj) {
        return _radius.equals(obj);
    }
}
