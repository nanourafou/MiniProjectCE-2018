package unitTest;

import elements.*;
import geometries.Plane;
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
    public void shadowSphereTest() {

        Scene scene = new Scene("shadowSphereTest");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0.0, 0.0, -1.0), new Vector(1.0, 0.0, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setCameraDistance(200);

        Sphere sphere = new Sphere(new Point3D(0, 0, -1000), 600, new Color(0, 0, 100), new Material(1, 90, 201, 0.2, 0.2));

        Sphere s2 = new Sphere(new Point3D(100, 100, -400), 150, new Color(0, 100, 0), new Material(1, 90, 201, 0.1, 0));

        Plane p = new Plane(new Vector(0, 1, 0), new Point3D(0, 0, 0), new Color(java.awt.Color.black), new Material(1, 20, 200, 0.5, 0.5));
        scene.addGeometry(s2);
        scene.addGeometry(sphere);
        //scene.addGeometry(p);


        scene.getLights().add(new PointLight(new Color(250, 150, 150), new Point3D(200, 200, -100), 1, 0.0, 0.0000005));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 800, 800, 800, 800);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void shadowPlusTest() {
        Scene scene = new Scene("shadowPlusTest");
        scene.setCamera(new Camera(new Point3D(0, 0, 200), new Vector(0.0, 0.0, -1.0), new Vector(1.0, 0.0, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setCameraDistance(200);
        Sphere s = new Sphere(new Point3D(0, 0, -150), 80, new Color(0, 0, 0), new Material(1, 90, 89, 0.25, 0.2));
        Plane p = new Plane(new Vector(1, 1, 0), new Point3D(0, -100, 0), new Color(100, 50, 200), new Material(1, 1, 19, 0.2, 0.8));
        Triangle tr = new Triangle(new Point3D(145, -150, 100), new Point3D(170, -180, -200), new Point3D(250, -100, 50), new Color(java.awt.Color.green), new Material(1, 120, 19, 0.25, 0.2));

        scene.addGeometry(s);
        scene.addGeometry(p);
        scene.addGeometry(tr);

        scene.getLights().add(new PointLight(new Color(250, 150, 150), new Point3D(0, 200, 0), 1, 0.0, 0.0000005));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 800, 800, 800, 800);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();
        render.writeToImage();

    }

}
