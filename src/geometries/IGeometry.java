package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

public interface IGeometry {

     List<Point3D> findIntersections(Ray myRay);

}
