package unitTest;

import elements.*;
import geometries.Geometries;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Renderer;

public class LightsTest {

    @Test
    public void sphereTest(){
        Scene scene = new Scene();
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1),new Vector(0, -1, 0)));
        scene.setCameraDistance(400);
        scene.setBackground(new Color(0,0,0));
        scene.setAmbientLight(new AmbientLight( java.awt.Color.white,0.1));
        Geometries geometries = scene.getGeometriesManager();
        geometries.addGeometry(new Sphere( new Point3D(0, 0, 150),50,  new Color(java.awt.Color.blue), new Material(1,1,1)));

        scene.getLights().add((
                new SpotLight(new Color(255, 199, 135),
                        new Point3D(5,5,5)
                        ,0,0.000001,0.00001,
                        new Vector(-2,-2,-3))));


        scene.getLights().add(new DirectionalLight(new Color(java.awt.Color.white),new Vector(0,0,1)));
        ImageWriter imageWriter = new ImageWriter("LightTest", 500, 500, 500, 500);
        Renderer render = new Renderer(scene,imageWriter);


        render.renderImage();
        render.writeToImage();
    }
}
