package unitTest;

import elements.AmbientLight;
import elements.Camera;
import elements.Color;
import elements.Scene;
import geometries.Geometries;
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
        Scene s = new Scene("sc2",
                new Color(0,0,0),
                new Camera(new Point3D(0,0,0), new Vector(0,-1,0),new Vector(0,0,-1)),
                new AmbientLight(new Color(255,255,255),1),
                200, null);

        s.addGeometry(new Sphere(new Point3D(0,0,-400),200, new Color(1,1,1),null));
        s.addGeometry(new Triangle(new Point3D(0,-100,-200), new Point3D(100,100,-200),new Point3D(-100,100,-200), new Color(java.awt.Color.YELLOW), null));

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
        geometries.addGeometry(new Sphere( new Point3D(0, 0, 150),50,  new Color(45,67,125), null));

        geometries.addGeometry(new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D( 100, 100, 149), new Color(java.awt.Color.red),null));

        geometries.addGeometry(new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, -100, 149),
                new Point3D( 100,-100, 149), new Color(2,3,4), null));

        geometries.addGeometry(new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D(-100, 100, 149), new Color(2,100,150), null));


        Triangle tr = new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0,  -100, 149),
                new Point3D(-100, -100, 149), new Color(200,33,255), null);
        geometries.addGeometry(tr);


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
        //scene.setAmbientLight(new AmbientLight( new Color(40,60,255),1));
        Geometries geometries = scene.getGeometriesManager();
        geometries.addGeometry(new Sphere( new Point3D(0, 0, 150),50,  new Color(java.awt.Color.darkGray), null));

        geometries.addGeometry(new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D( 100, 100, 149), new Color(java.awt.Color.red),null));

        geometries.addGeometry(new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, -100, 149),
                new Point3D( 100,-100, 149), new Color(java.awt.Color.YELLOW),null));

        geometries.addGeometry(new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D(-100, 100, 149), new Color(java.awt.Color.CYAN),null));


        Triangle tr = new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0,  -100, 149),
                new Point3D(-100, -100, 149), new Color(java.awt.Color.MAGENTA),null);
        geometries.addGeometry(tr);


        ImageWriter imageWriter = new ImageWriter("colorRendering", 500, 500, 500, 500);
        Renderer render = new Renderer(scene,imageWriter);


        render.renderImage();
        //render.printGrid(50);
        render.writeToImage();
    }
}
