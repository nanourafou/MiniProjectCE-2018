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
        for (Geometry object: geometriesArrayList) {
            return object.findIntersections(myRay); //Maybe not working ??
        }
        return null;
    }
}
