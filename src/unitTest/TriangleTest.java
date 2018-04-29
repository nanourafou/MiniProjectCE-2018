package unitTest;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

    @Test
    public void findIntrsectionTest(){
        Triangle t = new Triangle(new Point3D(4,-4,4),new Point3D(-3,3,5),new Point3D(5,2,5));
        Ray r = new Ray(new Point3D(0,0,0), new Vector(0.9,1.9,4.9));

        List<Point3D> lst =t.findIntersections(r);
        assertEquals(lst.size(),1);


    }


}
