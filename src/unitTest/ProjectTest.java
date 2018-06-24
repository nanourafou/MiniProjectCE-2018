package unitTest;

import elements.*;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Renderer;

import java.util.concurrent.ThreadLocalRandom;

public class ProjectTest {


    @Test
    public void projectTest1() {
        Scene scene = new Scene("project1");
        scene.setCamera(new Camera(new Point3D(0, 1000, 2500), new Vector(0.0, 0.0, -1.0), new Vector(0.0, 1.0, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setCameraDistance(300);

        Material mSphere = new Material(1, 90, 89, 0.20, 0.2);

        Sphere s = new Sphere(new Point3D(0, 0, -150), 80, new Color(233, 67, 71), mSphere);
        int t=0, j=0,o=0;
        for (t=0, j=0,o=0;t<10;t++,j+=50,o+=200){

            Sphere s2 = new Sphere(new Point3D(0 + o, 0, -150 + j), 80, Color.random(10, 240), mSphere);
            scene.addGeometry(s2);

        }
        for (t=0;t<11;t++,j+=50,o-=200){

            Sphere s2 = new Sphere(new Point3D(0 + o, 0, -150 + j), 80, Color.random(10, 240), mSphere);
            scene.addGeometry(s2);

        }

        for (t=0, j=0,o=0;t<10;t++,j+=50,o-=200){

            Sphere s2 = new Sphere(new Point3D(0 + o, 0, -150 + j), 80, Color.random(10, 240), mSphere);
            scene.addGeometry(s2);

        }

        for (t=0;t<11;t++,j+=50,o+=200){

            Color c = Color.random(10,240);
            Sphere s2 = new Sphere(new Point3D(0 + o, 0, -150 + j), 80, Color.random(10, 240), mSphere);
            scene.addGeometry(s2);
        }




        Plane p = new Plane(new Vector(0, 1, 0), new Point3D(0, -100, 0), Color.random(5,80), new Material(1, 1, 19, 0.2, 0.8));
        Triangle tr = new Triangle(new Point3D(-250,0,425),new Point3D(250,0,425), new Point3D(0,50,-100),new Color(java.awt.Color.green), new Material(1, 90, 89, 0.20, 0.2) );
        scene.addGeometry(s);
        scene.addGeometry(tr);
        scene.addGeometry(p);


        scene.getLights().add(new PointLight(new Color(java.awt.Color.red), new Point3D(-100, -800, 2500), 1, 0.0, 0.0000005));
        scene.getLights().add(new SpotLight(Color.random(30,255), new Point3D(400, 500, 1000), 1, 0.0, 0.0000005, new Vector(0,0,-1)));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 800, 800, 800, 800);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();
        render.writeToImage();

    }


    @Test
    public void snake() {

        Scene scene = new Scene("snake");
        scene.setCamera(new Camera(new Point3D(-800, 1500, 2500), new Vector(0.0, 0.0, -1.0), new Vector(0.0, 1.0, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setCameraDistance(150);

        Material mSphere = new Material(1, 90, 89, 0.20, 0.2);
        int t=0, j=0,o=0;

        for (int z = 0; z < 4; z++) {
            for (t = 0; t < 3; t++, j -= 300, o -= 300) {


                Sphere s2 = new Sphere(new Point3D(-400 + j, 0, 1000 + o), 200, Color.random(20, 170), mSphere);
                scene.addGeometry(s2);

            }
            j += 1000;

            for (t = 0; t < 3; t++, j += 300, o -= 300) {


                Sphere s2 = new Sphere(new Point3D(-400 + j, 0, 1000 + o), 200, Color.random(20, 170), mSphere);
                scene.addGeometry(s2);

            }
        }


        Sphere head1 = new Sphere(new Point3D(-60, 0, 1000), 300, new Color(java.awt.Color.green), mSphere);
        Sphere head2 = new Sphere(new Point3D(-100, 0, 1000), 300, new Color(java.awt.Color.green), mSphere);
        Sphere head = new Sphere(new Point3D(150, 0, 1000), 300, new Color(java.awt.Color.green), mSphere);
        Sphere eye = new Sphere(new Point3D(220, 100, 1300), 100, new Color(java.awt.Color.white), mSphere);
        Sphere eyeIn = new Sphere(new Point3D(200, 90, 1400), 50, new Color(java.awt.Color.black), mSphere);
        Triangle langue = new Triangle(new Point3D(240, 0, 1300), new Point3D(240, -110, 1300), new Point3D(600, 0, 1400), new Color(java.awt.Color.red), mSphere);


        Plane p = new Plane(new Vector(0, 1, 0), new Point3D(0, -300, 0), new Color(44, 51, 57), new Material(1, 1, 19, 0.2, 0.8));
        scene.addGeometry(p);

        scene.addGeometry(head);
        scene.addGeometry(eye);
        scene.addGeometry(eyeIn);
        scene.addGeometry(langue);

        scene.getLights().add(new DirectionalLight(Color.random(0,255), new Vector(0,1,0)));
        scene.getLights().add(new SpotLight(new Color(250, 150, 150), new Point3D(400, 500, 1000), 1, 0.0, 0.0000005, new Vector(0,0,-1)));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 800, 800, 800, 800);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();
        render.writeToImage();

    }
}
