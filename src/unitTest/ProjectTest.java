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

public class ProjectTest {


    @Test
    public void projectTest1() {

        Scene scene = new Scene("project1");
        scene.setCamera(new Camera(new Point3D(0, 800, 2500), new Vector(0.0, 0.0, -1.0), new Vector(0.0, 1.0, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setCameraDistance(300);

        Sphere s = new Sphere(new Point3D(0, 0, -150), 80, new Color(233,67,71), new Material(1, 90, 89, 0.20, 0.2));
        int t=0, j=0,o=0;
        for (t=0, j=0,o=0;t<10;t++,j+=50,o+=200){

            Sphere s2 = new Sphere(new Point3D(0+o, 0, -150+j), 80, Color.random(10,240), new Material(1, 90, 89, 0.20, 0.2));
            scene.addGeometry(s2);
            //Sphere s1 = new Sphere(new Point3D(200, 0, -120), 80, new Color(212,60,217), new Material(1, 90, 89, 0.20, 0.2));

        }
        for (t=0;t<11;t++,j+=50,o-=200){

            Sphere s2 = new Sphere(new Point3D(0+o, 0, -150+j), 80, Color.random(10,240), new Material(1, 90, 89, 0.20, 0.2));
            scene.addGeometry(s2);
            //Sphere s1 = new Sphere(new Point3D(200, 0, -120), 80, new Color(212,60,217), new Material(1, 90, 89, 0.20, 0.2));

        }

        for (t=0, j=0,o=0;t<10;t++,j+=50,o-=200){

            Sphere s2 = new Sphere(new Point3D(0+o, 0, -150+j), 80, Color.random(10,210), new Material(1, 90, 89, 0.20, 0.2));
            scene.addGeometry(s2);
            //Sphere s1 = new Sphere(new Point3D(200, 0, -120), 80, new Color(212,60,217), new Material(1, 90, 89, 0.20, 0.2));

        }

        for (t=0;t<11;t++,j+=50,o+=200){

            Color c = Color.random(10,240);
            Sphere s2 = new Sphere(new Point3D(0+o, 0, -150+j), 80, Color.random(10,200), new Material(1, 90, 89, 0.20, 0.2));
            scene.addGeometry(s2);
            //Sphere s1 = new Sphere(new Point3D(200, 0, -120), 80, new Color(212,60,217), new Material(1, 90, 89, 0.20, 0.2));

        }




        Plane p = new Plane(new Vector(0, 1, 0), new Point3D(0, -100, 0), new Color(44,51,57), new Material(1, 1, 19, 0.2, 0.8));
        Plane p1 = new Plane(new Vector(0, 1, 0), new Point3D(0, 300, 0), new Color(java.awt.Color.blue), new Material(1, 1, 19, 0.2, 0.8));

        Triangle tr = new Triangle(new Point3D(-250,0,425),new Point3D(250,0,425), new Point3D(0,50,-100),new Color(java.awt.Color.green), new Material(1, 90, 89, 0.20, 0.2) );
        scene.addGeometry(s);
        scene.addGeometry(tr);
        scene.addGeometry(p);
        //scene.addGeometry(p1);


        scene.getLights().add(new PointLight(new Color(java.awt.Color.red), new Point3D(-100, -800, 2500), 1, 0.0, 0.0000005));
        scene.getLights().add(new SpotLight(new Color(250, 150, 150), new Point3D(400, 500, 1000), 1, 0.0, 0.0000005, new Vector(0,0,-1)));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 800, 800, 800, 800);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImageNotSS();
        render.writeToImage();

    }


    @Test
    public void snake() {

        Scene scene = new Scene("snake");
        scene.setCamera(new Camera(new Point3D(0, 1000, 2500), new Vector(0.0, 0.0, -1.0), new Vector(0.0, 1.0, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setCameraDistance(300);

        Sphere s = new Sphere(new Point3D(0, 0, -150), 80, new Color(233,67,71), new Material(1, 90, 89, 0.20, 0.2));
        int t=0, j=0,o=0;
        for (t=0, j=0,o=0;t<10;t++,j+=50,o+=200){

            Sphere s2 = new Sphere(new Point3D(0+o, 0, -150+j), 80, Color.random(10,240), new Material(1, 90, 89, 0.20, 0.2));
            scene.addGeometry(s2);
            //Sphere s1 = new Sphere(new Point3D(200, 0, -120), 80, new Color(212,60,217), new Material(1, 90, 89, 0.20, 0.2));

        }
        for (t=0;t<11;t++,j+=50,o-=200){

            Sphere s2 = new Sphere(new Point3D(0+o, 0, -150+j), 80, Color.random(10,240), new Material(1, 90, 89, 0.20, 0.2));
            scene.addGeometry(s2);
            //Sphere s1 = new Sphere(new Point3D(200, 0, -120), 80, new Color(212,60,217), new Material(1, 90, 89, 0.20, 0.2));

        }

        for (t=0, j=0,o=0;t<10;t++,j+=50,o-=200){

            Sphere s2 = new Sphere(new Point3D(0+o, 0, -150+j), 80, Color.random(10,240), new Material(1, 90, 89, 0.20, 0.2));
            scene.addGeometry(s2);
            //Sphere s1 = new Sphere(new Point3D(200, 0, -120), 80, new Color(212,60,217), new Material(1, 90, 89, 0.20, 0.2));

        }

        for (t=0;t<11;t++,j+=50,o+=200){

            Color c = Color.random(10,240);
            Sphere s2 = new Sphere(new Point3D(0+o, 0, -150+j), 80, Color.random(10,240), new Material(1, 90, 89, 0.20, 0.2));
            scene.addGeometry(s2);
            //Sphere s1 = new Sphere(new Point3D(200, 0, -120), 80, new Color(212,60,217), new Material(1, 90, 89, 0.20, 0.2));

        }




        Plane p = new Plane(new Vector(0, 1, 0), new Point3D(0, -100, 0), new Color(44,51,57), new Material(1, 1, 19, 0.2, 0.8));
        Plane p1 = new Plane(new Vector(0, 1, 0), new Point3D(0, 300, 0), new Color(java.awt.Color.blue), new Material(1, 1, 19, 0.2, 0.8));

        Triangle tr = new Triangle(new Point3D(-250,0,425),new Point3D(250,0,425), new Point3D(0,50,-100),new Color(java.awt.Color.green), new Material(1, 90, 89, 0.20, 0.2) );
        scene.addGeometry(s);
        scene.addGeometry(tr);
        scene.addGeometry(p);
        //scene.addGeometry(p1);


        scene.getLights().add(new PointLight(new Color(java.awt.Color.red), new Point3D(-100, -800, 2500), 1, 0.0, 0.0000005));
        scene.getLights().add(new SpotLight(new Color(250, 150, 150), new Point3D(400, 500, 1000), 1, 0.0, 0.0000005, new Vector(0,0,-1)));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 800, 800, 800, 800);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImageNotSS();
        render.writeToImage();

    }
}
