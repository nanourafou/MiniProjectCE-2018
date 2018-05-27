package unitTest;

import elements.*;
import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Renderer;

public class RendererTest {

    @Test
    public void RenderTest(){
        Scene s = new Scene("sc2",
                new Color(0,0,0),
                new Camera(new Point3D(0,0,0), new Vector(0,-1,0),new Vector(0,0,-1)),
                new AmbientLight(new Color(255,255,255),1),
                200, null);

        s.addGeometry(new Sphere(new Point3D(0,0,-400),200, new Color(1,1,1),new Material(1,1,1)));
        s.addGeometry(new Triangle(new Point3D(0,-100,-200), new Point3D(100,100,-200),new Point3D(-100,100,-200), new Color(java.awt.Color.YELLOW), new Material(1,1,1)));

        ImageWriter imgWrt = new ImageWriter(s.getName(),500,500,3,3);

        Renderer r = new Renderer(s, imgWrt);


        r.renderImage();
        r.printGrid(10);
        r.writeToImage();

    }

    @Test
    public void basicRendering(){
        Scene scene = new Scene();
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1),new Vector(0, -1, 0)));
        scene.setCameraDistance(100);
        scene.setBackground(new Color(255, 255, 255));
        scene.setAmbientLight(new AmbientLight( new Color(java.awt.Color.black),0.1));
        Geometries geometries = scene.getGeometriesManager();
        geometries.addGeometry(new Sphere( new Point3D(0, 0, 150),50,  new Color(45,67,125), new Material(1,1,1)));

        geometries.addGeometry(new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D( 100, 100, 149), new Color(java.awt.Color.red),new Material(1,1,1)));

        geometries.addGeometry(new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, -100, 149),
                new Point3D( 100,-100, 149), new Color(2,3,4), new Material(1,1,1)));

        geometries.addGeometry(new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D(-100, 100, 149), new Color(2,100,150), new Material(1,1,1)));


        Triangle tr = new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0,  -100, 149),
                new Point3D(-100, -100, 149), new Color(200,33,255), new Material(1,1,1));
        geometries.addGeometry(tr);
        scene.getLights().add((new SpotLight(new Color(255, 199, 135),new Point3D(100,100,-100),0,0.000001,0.00001,new Vector(-2,-2,-3))));

        ImageWriter imageWriter = new ImageWriter("test1", 500, 500, 500, 500);
        Renderer render = new Renderer(scene,imageWriter);


        render.renderImage();
        //render.printGrid(50);
        render.writeToImage();
    }


    @Test
    public void colorRendering(){
        Scene scene = new Scene();
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1),new Vector(0, -1, 0)));
        scene.setCameraDistance(400);
        scene.setBackground(new Color(0,0,0));
        scene.setAmbientLight(new AmbientLight( new Color(40,60,255),1));
        Geometries geometries = scene.getGeometriesManager();
        geometries.addGeometry(new Sphere( new Point3D(0, 0, 150),50,  new Color(java.awt.Color.darkGray), new Material(1,1,1)));

        geometries.addGeometry(new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D( 100, 100, 149), new Color(java.awt.Color.red),new Material(1,1,1)));

        geometries.addGeometry(new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, -100, 149),
                new Point3D( 100,-100, 149), new Color(java.awt.Color.YELLOW),new Material(1,1,1)));

        geometries.addGeometry(new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D(-100, 100, 149), new Color(java.awt.Color.CYAN),new Material(1,1,1)));


        Triangle tr = new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0,  -100, 149),
                new Point3D(-100, -100, 149), new Color(java.awt.Color.MAGENTA),new Material(1,1,1));
        geometries.addGeometry(tr);


        ImageWriter imageWriter = new ImageWriter("colorRendering", 500, 500, 500, 500);
        Renderer render = new Renderer(scene,imageWriter);


        render.renderImage();
        //render.printGrid(50);
        render.writeToImage();
    }
}
