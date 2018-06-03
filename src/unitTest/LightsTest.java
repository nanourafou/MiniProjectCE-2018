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

        geometries.addGeometry(new Sphere( new Point3D(0, 0, 150),50,  new Color(java.awt.Color.blue), new Material(0,1,1)));

        scene.getLights().add(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100), 1, 0.00001, 0,
                new Vector(-10, -10, -3)));

        ImageWriter imageWriter = new ImageWriter("LightTest", 500, 500, 500, 500);
        Renderer render = new Renderer(scene,imageWriter);


        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void pointLightTestTSphere() {

        Scene scene = new Scene("pointLightTestTSphere");
        scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(0.0, 0.0, -1.0),new Vector(1.0, 0.0, 0.0)));
        scene.setBackground(new Color(0,0,0));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setCameraDistance(200);

        Sphere sphere = new Sphere(new Point3D(0, 0, -1000),600, new Color(0, 0, 100), new Material(1, 0.7, 80));
        scene.addGeometry(sphere);

        scene.getLights().add(new PointLight(new Color(150, 250, 150), new Point3D(-200, -200, -100), 1, 0.0000009, 0.000008));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);

        Renderer render = new Renderer(scene,imageWriter);


        render.renderImage();
        render.writeToImage();
    }


    /*@Test
    public void spotLightTest1(){
        Scene scene = new Scene("spotLightTestTriangle");
        scene.setCamera(new Camera(new Point3D(0, 0, 10),  new Vector(0, 0, -1),new Vector(1, 0, 0)));
        scene.setCameraDistance(400);
        scene.setBackground(new Color(0,0,0));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.getLights().add(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100), 1, 0.00001, 0,
                new Vector(-10, -10, -3)));

        Triangle triangle1 = new Triangle(new Point3D(3000, 3000, -2000),
                new Point3D(-3000, -3000, -1000),
                new Point3D(3000, -3000, -2000),
                new Color(0, 0, 0),
                new Material(1, 1, 50));

        Triangle triangle2 = new Triangle(new Point3D(3000, 3000, -2000),
                new Point3D(-3000, 3000, -1000),
                new Point3D(-3000, -3000, -1000),
                new Color(0, 0, 0),
                new Material(1, 1, 50));

        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);
        Renderer render = new Renderer(scene,imageWriter);


        render.renderImage();
        render.writeToImage();
    }*/


    /*@Test
    public void spotLightTestSphere(){

        Scene scene = new Scene("spotLightTestSphere");
        scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(0.0, 0.0, -1.0),new Vector(1.0, 0.0, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setBackground(new Color(0,0,0));
        scene.setCameraDistance(200);
        Sphere sphere = new Sphere( new Point3D(0, 0, -1000),600, new Color(0, 0, 100), new Material(1, 0.7, 80));
        scene.addGeometry(sphere);

        scene.getLights().add(
                new SpotLight(new Color(250, 150, 150), new Point3D(-200, -200, -100), 0, 0.0018, 0,
                        new Vector(0,3,-2)));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);

        Renderer render = new Renderer(scene,imageWriter);


        render.renderImage();
        render.writeToImage();
    }*/



}
