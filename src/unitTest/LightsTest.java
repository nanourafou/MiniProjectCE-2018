package unitTest;

import elements.*;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Renderer;

public class LightsTest {


    @Test
    public void pointLightTestTSphere() {

        Scene scene = new Scene("pointLightTestTSphere");
        scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(0.0, 0.0, -1.0),new Vector(1.0, 0.0, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setCameraDistance(200);

        Sphere sphere = new Sphere(new Point3D(0, 0, -1000),600, new Color(0, 0, 100), new Material(1, 1 , 19));
        scene.addGeometry(sphere);


        scene.getLights().add(new PointLight(new Color(250, 150, 150), new Point3D(-200, -200, -100), 1, 0.00000006, 0.0000005));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);

        Renderer render = new Renderer(scene,imageWriter);

        render.renderImage();
        render.writeToImage();
    }




    @Test
    public void spotLightTestSphere(){

        Scene scene = new Scene("spotLightTestSphere");
        scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(0.0, 0.0, -1.0),new Vector(1.0, 0.0, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setBackground(new Color(java.awt.Color.black));
        scene.setCameraDistance(200);
        Sphere sphere = new Sphere( new Point3D(0, 0, -1000),600, new Color(0, 0, 100), new Material(1, 1, 40));
        scene.addGeometry(sphere);

        scene.getLights().add(
                new SpotLight(new Color(250, 150, 150), new Point3D(-200, -200, -200), 1 , 0.0, 0.00000000005,
                        new Vector(-100,200,-200)));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);

        Renderer render = new Renderer(scene,imageWriter);


        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void directionalLightTest(){

        Scene scene = new Scene("directionalLightTest");
        scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(0.0, 0.0, -1.0),new Vector(1.0, 0.0, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setBackground(new Color(java.awt.Color.black));
        scene.setCameraDistance(200);
        Sphere sphere = new Sphere( new Point3D(0, 0, -1000),600, new Color(0, 0, 100), new Material(1, 90, 40));
        scene.addGeometry(sphere);



        scene.getLights().add(new DirectionalLight(new Color(java.awt.Color.white),new Vector(-50,-100,-10)));
        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);

        Renderer render = new Renderer(scene,imageWriter);


        render.renderImage();
        render.writeToImage();
    }


}
