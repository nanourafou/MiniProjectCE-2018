package unitTest;

import elements.Camera;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class CameraTest  {


    Point3D p1=new Point3D(0,0,0);
    Point3D p2=new Point3D(-3,3,-3);
    Vector v1=new Vector(1/Math.sqrt(6),1/Math.sqrt(6),-Math.sqrt(2)/Math.sqrt(3));
    Ray r11=new Ray(p1,v1);
    Ray r12=new Ray(p1,new Vector(-1/Math.sqrt(6),-1/Math.sqrt(6),-Math.sqrt(2)/Math.sqrt(3)));
    Ray r13=new Ray(p1,new Vector(-1/Math.sqrt(6),1/Math.sqrt(6),-Math.sqrt(2)/Math.sqrt(3)));
    Ray r14=new Ray(p1,new Vector(1/Math.sqrt(6),-1/Math.sqrt(6),-Math.sqrt(2)/Math.sqrt(3)));
    Ray r15=new Ray(p1,new Vector(0,0,-1));
    Camera c1;
    Camera c2;

    //Init Camera Ctor
    private void initCamera() throws Exception{
        c1=new Camera(p1,new Vector(0,-1,0),new Vector(0,0,-1));
        c2=new Camera(p2,new Vector(1,1,1),new Vector(-1,2,-1));
    }


    @Test
    public void createCameraTest() throws Exception{
        assertThrows(Exception.class,()->{Camera z = new Camera(new Point3D(2,4,5), new Vector(2,5,4), new Vector(4,10,8));}); // Colinear Vector test

        Camera c = new Camera(new Point3D(2,4,5), new Vector(2,5,4), new Vector(4,5,8));
        assertNotNull(c);
        System.out.println("Camera Created :\n "+ c);
    }


    @Test
    public void constructRayThoughPixelTest() throws Exception {
        initCamera();
        assertEquals(c1.constructRayThoughPixel(3, 3, 3, 3, 100, 150, 150),r11);
        assertEquals(c1.constructRayThoughPixel(3, 3, 1, 1, 100, 150, 150),r12);
        assertEquals(c1.constructRayThoughPixel(3, 3, 1, 3, 100, 150, 150),r13);
        assertEquals(c1.constructRayThoughPixel(3, 3, 3, 1, 100, 150, 150),r14);
        assertEquals(c1.constructRayThoughPixel(3, 3, 2, 2, 100, 150, 150),r15);
    }
}
