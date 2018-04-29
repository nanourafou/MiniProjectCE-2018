package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

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
        for (Geometry object: geometriesArrayList) {
            return object.getNormal(p); //Maybe not working ??
        }
        return null;
    }

    @Override
    public List<Point3D> findIntersections(Ray myRay) {
        List<Point3D> lst = new ArrayList<>();

        for (Geometry object: geometriesArrayList) {
            List<Point3D> l1 = new ArrayList<>();
            for (Point3D p: l1){
                lst.add(p);
            }

        }
        return lst;
    }
}
