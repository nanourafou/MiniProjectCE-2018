package unitTest;

import elements.Color;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SphereTest {
    Sphere s1= new Sphere(new Point3D(0,0,-2),2,new Color(1,1,1));
    Ray r1=new Ray(new Point3D(0,0,2),new Vector(0,0,1));
    Ray r2=new Ray(new Point3D(0,0,2),new Vector(0,0,-1));
    Ray r3=new Ray(new Point3D(0,-2,0),new Vector(0,1,0));
    Ray r4=new Ray(new Point3D(0,-2,0),new Vector(0,0,-1));
    Sphere s2= new Sphere(new Point3D(200,50,0),180,new Color(1,1,1));
    Ray rTest=new Ray (new Point3D(0,0,0),new Vector(100,80,150));


    @Test
    public void findIntersectionsTest() {
        assertEquals(s1.findIntersections(r1).size(),0);
        assertEquals(s1.findIntersections(r2).size(),2);
        //assertEquals(s1.findIntersections(r3).size(),1);
        //assertEquals(s1.findIntersections(r4).size(),1);




    }


}
