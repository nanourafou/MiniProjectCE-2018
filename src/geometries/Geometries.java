package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.*;

public class Geometries extends Geometry {

    private ArrayList<Geometry> geometriesArrayList = new ArrayList<>();

    public void addGeometry(Geometry g){
        geometriesArrayList.add(g);
    }

    public void removeGeometry(Geometry g){
        geometriesArrayList.remove(g);
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }

    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray myRay) {
        List<Point3D> lst = new ArrayList<>();

        /*for (Geometry object: geometriesArrayList) {
            List<Point3D> l1 = object.findIntersections(myRay);
            if(l1==null)
                continue;
            for (Point3D p: l1){
                lst.add(p);
            }

        }
        return lst;
        */

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
