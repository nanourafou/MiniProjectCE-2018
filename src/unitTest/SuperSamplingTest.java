package unitTest;

import elements.*;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Renderer;

public class SuperSamplingTest {

    @Test
    public void ssTest(){

        Scene scene = new Scene("ssTest");
        scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(0.0, 0.0, -1.0),new Vector(1.0, 0.0, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.1));
        scene.setBackground(new Color(java.awt.Color.black));
        scene.setCameraDistance(200);
        Sphere sphere = new Sphere(new Point3D(0, 0, -1000), 600, new Color(120,90,76), new Material(1, 1, 40, 0, 0));
        scene.addGeometry(sphere);


        //scene.getLights().add(new PointLight(new Color(250, 150, 150), new Point3D(-200, -200, -100), 1, 0.00000006, 0.0000005));


        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);

        Renderer render = new Renderer(scene,imageWriter);


        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void notssTest(){

        Scene scene = new Scene("notssTest");
        scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(0.0, 0.0, -1.0),new Vector(1.0, 0.0, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.1));
        scene.setBackground(new Color(java.awt.Color.black));
        scene.setCameraDistance(200);
        Sphere sphere = new Sphere(new Point3D(0, 0, -1000), 600, new Color(120,90,76), new Material(1, 1, 40, 0, 0));
        scene.addGeometry(sphere);


        //scene.getLights().add(new PointLight(new Color(250, 150, 150), new Point3D(-200, -200, -100), 1, 0.00000006, 0.0000005));


        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);

        Renderer render = new Renderer(scene,imageWriter);


        render.renderImageNotSS();
        render.writeToImage();
    }
}
