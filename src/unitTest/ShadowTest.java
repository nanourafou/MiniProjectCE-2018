package unitTest;

import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.GeoPoint;
import renderer.ImageWriter;
import renderer.Renderer;

public class ShadowTest {

    @Test
    public void shadowSphereTest(){

        Scene scene = new Scene("shadowSphereTest");
        scene.setCamera(new Camera(new Point3D(0, 0, 0 ),new Vector(0.0, 0.0, 1.0),new Vector(1.0, 0.0, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setCameraDistance(150);

        Sphere sphere = new Sphere(new Point3D(0, 0, 1000),600, new Color(0, 0, 100), new Material(1, 90, 201));

        Sphere s2 = new Sphere(new Point3D(100, -10, 400),150, new Color(0, 100, 0), new Material(1, 90, 201));

        scene.addGeometry(s2);
        scene.addGeometry(sphere);




        scene.getLights().add(new PointLight(new Color(250, 150, 150), new Point3D(200, 200, 100), 1, 0.0, 0.0000005));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 800, 800, 800, 800);

        Renderer render = new Renderer(scene,imageWriter);

        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void test(){



    }
}
