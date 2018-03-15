package geometries;

import primitives.Models;
import primitives.Point3D;

/**
 * Abstract class who describing all radial geometries in spaces.
 */


public abstract class RadialGeometry extends Geometry {

    private double _radius; //Vector, Point3D, double ??

    // ***************** Constructors ********************** //
    public RadialGeometry(RadialGeometry x){
        super(x);
        _radius = x._radius;
    }

    public RadialGeometry(double radius){
        super(); //need this ???
        _radius = radius;
    }

    public RadialGeometry(){}

    // ***************** Getters/Setters ********************** //

    /**
     * @return the radius of the radial object
     */
    public double getRadius() {
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
        if (this == obj) return true;
        if (obj == null) return false;
        return _radius==((RadialGeometry)obj)._radius;
    }
}
