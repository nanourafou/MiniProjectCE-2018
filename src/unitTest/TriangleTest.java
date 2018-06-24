package unitTest;

import elements.Color;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {


    Triangle t2=new Triangle(new Point3D(4,-4,4),new Point3D(-3,3,5),new Point3D(5,2,5), new Color(1,1,1), null);
    @Test
    public void findIntrsectionTest(){
        Triangle t = new Triangle(new Point3D(4,-4,4),new Point3D(-3,3,5),new Point3D(5,2,5),new Color(0,240,05),new Material(0,10,11,0.2,0.1));
        Ray r = new Ray(new Point3D(0,0,0), new Vector(0.9,1.9,4.9));

        assertEquals((t.findIntersections(r)).size(),1);
    }


}
