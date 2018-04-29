package unitTest;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class PlaneTest {

    @Test
    public void findIntersectionsTest(){
        //Just 1 intersection Point
        Ray r = new Ray(new Point3D(0,0,0),new Vector(-6,-2,0));
        Plane pl = new Plane(new Vector(2,3,4),new Point3D(0,0,-3)); //2x+3x+4x+18
        List<Point3D> x = pl.findIntersections(r);

        //0 intersection point
        Ray r1 = new Ray(new Point3D(0,0,0),new Vector(-1,-1,0));
        x = pl.findIntersections(r);

        //Inclus dans le plans

        //Colineaire au plan

        //Secante au plan

    }
}
