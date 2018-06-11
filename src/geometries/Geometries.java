package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.*;

public class Geometries extends Geometry {

    /**
     * Array list of all geometries
     */
    private ArrayList<Geometry> geometriesArrayList = new ArrayList<>();

    /**
     * @param g Add a geometry to the composite
     */
    public void addGeometry(Geometry g){
        geometriesArrayList.add(g);
    }

    /**
     * @param g Remove the geometry from the composite
     */
    public void removeGeometry(Geometry g){
        geometriesArrayList.remove(g);
    }

    /**
     * @return null
     */
    @Override
    public Vector getNormal(Point3D p) {
        return null;
    } //TODO

    /**
     * @param myRay Find intersection of all composite
     * @return
     */
    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray myRay) {
        List<Point3D> lst = new ArrayList<>();

        Map<Geometry, List<Point3D>> map = new HashMap<>();

        for (Geometry object: geometriesArrayList) {
            Map<Geometry, List<Point3D>> m1 = object.findIntersections(myRay);

            if(m1==null)
                continue;

            for (Map.Entry<Geometry, List<Point3D>> entry: m1.entrySet()){
                Geometry key = entry.getKey();
                List<Point3D> lst1 = entry.getValue();
                map.put(key,lst1);
            }

            }
            return map;
    }
}
