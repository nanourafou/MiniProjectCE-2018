package unitTest;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SphereTest {
    @Test
    public void intersectionsTest() {
        Point3D p1 = new Point3D(0,0,-4);
        Point3D p2 = new Point3D(0,0,-2);
        Point3D pCenter = new Point3D(0,0,-3);
        Ray r= new Ray(new Point3D(0,0,0),new Vector(0,0,-1));

        //2Intersection Point
        Sphere s = new Sphere(pCenter,1);
        List<Point3D> point3DList = s.findIntersections(r);
        assertEquals(p2,point3DList.get(0));
        assertEquals(p1,point3DList.get(1));

        //Not in the sphere
        Ray r1= new Ray(new Point3D(0,0,0),new Vector(0,0,1));
        point3DList = s.findIntersections(r1);
        assertEquals(point3DList.size(),0);

        //Just 1 Point intersection
        Ray r2= new Ray(new Point3D(0,0,0),new Vector(0,0,2));
        point3DList = s.findIntersections(r2);


    }

}
