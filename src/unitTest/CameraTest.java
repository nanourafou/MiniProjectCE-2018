package unitTest;

import elements.Camera;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CameraTest {

    @Test
    public void createCameraTest() throws Exception{
        assertThrows(Exception.class,()->{Camera z = new Camera(new Point3D(2,4,5), new Vector(2,5,4), new Vector(4,10,8));}); // Colinear Vector test

        Camera c = new Camera(new Point3D(2,4,5), new Vector(2,5,4), new Vector(4,5,8));
        assertNotNull(c);
        System.out.println("Camera Created :\n "+ c);
    }

    @Test
    public void constructRayThoughPixelTest() throws Exception{
        Camera c = new Camera(new Point3D(2,4,5), new Vector(2,5,4), new Vector(4,5,8));
        System.out.println(c.constructRayThoughPixel(3,3,3,3,100,150,150));
    }
}
