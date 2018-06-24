package unitTest;

import elements.Camera;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class CameraTest  {


    Point3D p1=new Point3D(0,0,0);
    Point3D p2=new Point3D(-3,3,-3);

    Ray r11=new Ray(p1,new Vector(1/Math.sqrt(6),1/Math.sqrt(6),-Math.sqrt(2)/Math.sqrt(3)));
    Ray r12=new Ray(p1,new Vector(-1/Math.sqrt(6),-1/Math.sqrt(6),-Math.sqrt(2)/Math.sqrt(3)));
    Ray r13=new Ray(p1,new Vector(-1/Math.sqrt(6),1/Math.sqrt(6),-Math.sqrt(2)/Math.sqrt(3)));
    Ray r14=new Ray(p1,new Vector(1/Math.sqrt(6),-1/Math.sqrt(6),-Math.sqrt(2)/Math.sqrt(3)));
    Ray r15=new Ray(p1,new Vector(0,0,-1));


    @Test
    public void constructRayThoughPixelTest(){
        Camera c1 = new Camera(new Point3D(0,0,0),new Vector(0,0,-1),new Vector(0,-1,0)); //Vous ne savez pas calculer un signe
        assertEquals(c1.constructRayThoughPixel(3, 3, 3, 3, 100, 150, 150),r11);
        assertEquals(c1.constructRayThoughPixel(3, 3, 1, 1, 100, 150, 150),r12);
        assertEquals(c1.constructRayThoughPixel(3, 3, 1, 3, 100, 150, 150),r13);
        assertEquals(c1.constructRayThoughPixel(3, 3, 3, 1, 100, 150, 150),r14);
        assertEquals(c1.constructRayThoughPixel(3, 3, 2, 2, 100, 150, 150),r15);
    }

    @Test
    public void cameraTest(){

        Camera c = new Camera(new Point3D(0,0,0),new Vector(0,0,-1),new Vector(0,-1,0));
        Ray t = c.constructRayThoughPixel(3,3,3,3,100,150,150);
        Ray r = new Ray(new Point3D(0,0,0),(new Vector(50,50,-100)).normalize());
        assertEquals(r,t);
    }

}
