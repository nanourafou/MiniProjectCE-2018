package unitTest;

import elements.Color;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {

    Triangle t1=new Triangle(new Point3D(0,0,4),new Point3D(0,0,0),new Point3D(4,0,0), new Color(1,1,1),null);
    Ray r1=new Ray(new Point3D(0,-2,0),new Vector(1,2,1));
    Ray r2=new Ray(new Point3D(0,-2,0),new Vector(-1,1,1));
    Ray r3=new Ray(new Point3D(0,-2,0),new Vector(-1,-1,1));
    Ray r4=new Ray(new Point3D(0,-2,0),new Vector(2,1,1));

    Triangle t2=new Triangle(new Point3D(4,-4,4),new Point3D(-3,3,5),new Point3D(5,2,5), new Color(1,1,1), null);
    @Test
    public void findIntrsectionTest(){
       // Triangle t = new Triangle(new Point3D(4,-4,4),new Point3D(-3,3,5),new Point3D(5,2,5));
        //Ray r = new Ray(new Point3D(0,0,0), new Vector(0.9,1.9,4.9));

        //List<Point3D> lst =t.findIntersections(r);
        //assertEquals(lst.size(),1);

        assertEquals(t1.findIntersections(r1).size(),1);
        //assertEquals(t1.findIntersections(r2).size(),0);
       // assertEquals(t1.findIntersections(r3).size(),0);
        //assertEquals(t1.findIntersections(r4).size(),0);

    }


}
