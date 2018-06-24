package unitTest;

import elements.Color;
import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaneTest {
    Plane p1 = new Plane(new Vector(0,1,0),new Point3D(0,0,0), new Color(0,0,0),null);
    Ray r1= new Ray(new Point3D(0,-1,0),new Vector(0,1,0));
    @Test
    public void findIntersectionsTest(){
        assertEquals(p1.findIntersections(r1).size(),1);


    }

}
