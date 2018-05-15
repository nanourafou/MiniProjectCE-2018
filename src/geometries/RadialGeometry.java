package geometries;

import elements.Color;
import primitives.Material;

/**
 * Abstract class who describing all radial geometries in spaces.
 */


public abstract class RadialGeometry extends Geometry {

    protected double _radius; //Vector, Point3D, double ??

    // ***************** Constructors ********************** //
    public RadialGeometry(RadialGeometry x){
        super(x);
        _radius = x._radius;
    }

    public RadialGeometry(double radius, Color clr, Material m){
        super(clr, m);
        _radius = radius;
    }


    // ***************** Getters/Setters ********************** //

    /**
     * @return the radius of the radial object
     */
    public double getRadius() {
        return _radius;
    }


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
