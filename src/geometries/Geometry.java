package geometries;

import elements.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Map;

/**
 * Abstract class who define all geometries in space.
 */
public abstract class Geometry {

    private Color _emission;

    private Material _material;
    // ***************** Constructors ********************** //

    /**
     * Constructor
     *
     * @param emission
     */
    public Geometry(Color emission, Material m) {
        this._emission = new Color(emission);
        this._material = m;
    }

    /**
     * Copy Costructors
     *
     * @param geoObj
     */
    public Geometry(Geometry geoObj) {
        this._emission = new Color(geoObj._emission);
        this._material = new Material(geoObj._material);
    }


    public Geometry() { }


    // ***************** Operations ******************** //

    /**
     * @param p the point of/in the object
     * @return the normal vector of the object
     */
    public abstract Vector getNormal(Point3D p);

    /**
     * Find the intersection between ray and the geometry
     *
     * @param myRay The ray
     * @return A Map with the geometry and a list of points
     */
    public abstract Map<Geometry, List<Point3D>> findIntersections(Ray myRay);

    /**
     * @return Color Emission ofn the geometry
     */
    public Color getEmission() {
        return _emission;
    }

    /**
     * @return The Material
     */
    public Material getMaterial() {
        return _material;
    }
}

