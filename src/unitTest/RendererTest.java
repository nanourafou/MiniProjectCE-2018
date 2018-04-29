package unitTest;

import elements.AmbientLight;
import elements.Camera;
import elements.Color;
import elements.Scene;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Renderer;

public class RendererTest {

    @Test
    public void RenderTest(){
        Scene s = new Scene("sc1",
                new Color(0,0,0),
                new Camera(new Point3D(0,0,0), new Vector(0,0,-1),new Vector(0,-1,0)),
                new AmbientLight(new Color(1,1,1),1),
                100);

        s.addGeometry(new Sphere(new Point3D(0,0,-400),200));
        s.addGeometry(new Triangle(new Point3D(0,-100,-200), new Point3D(100,100,-200),new Point3D(-100,100,-200)));

        ImageWriter imgWrt = new ImageWriter(s.getName(),150,150,3,3);

        Renderer r = new Renderer(s, imgWrt);


        r.printImage(10);
    }
}
