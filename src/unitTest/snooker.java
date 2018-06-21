package unitTest;

import elements.Color;
import elements.Scene;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

public class snooker {

    @Test
    void Snukertest()
    {
        Scene scene = new Scene();
        scene.setCameraDistance(200);
        Material m=new Material(1, 1, 19, 0, 0);
        Material defaultM = new Material(1, 1, 1, 0, 0);

        //??????? ???º?? ?? ??????
        Triangle triangle = new Triangle(new Point3D(-125, 125, -260),//green
                new Point3D(300, 125, -260),
                new Point3D(125, -125, -260),
                new Color(java.awt.Color.green), m);

        Triangle triangle2 = new Triangle(new Point3D(-125, 125, -260),//green
                new Point3D(-300, -125, -260),
                new Point3D(125, -125, -260),
                new Color(java.awt.Color.green),m);

        //??????? ????? ?? ??????
        Triangle triangle3 = new Triangle(new Point3D(-145, 145, -270),//brown
                new Point3D(340, 145, -270),
                new Point3D(145, -145, -270),
                new Color(255,80,0), defaultM );

        Triangle triangle4 = new Triangle(new Point3D(-145, 145, -270),//brown
                new Point3D(-340, -145, -270),
                new Point3D(145, -145, -270),
                new Color(255,80,0), defaultM);

        //?????? ???? ????
        Triangle leg1_1 = new Triangle(new Point3D(-340, -145, -280),
                new Point3D(-300,-145, -280),
                new Point3D(-300, -300, -280),
                new Color(255,80,0),defaultM);
        scene.addGeometry(leg1_1);
        Triangle leg1_2 = new Triangle(new Point3D(-340, -145, -280),
                new Point3D(-340, -300, -280),
                new Point3D(-300, -300, -280),
                new Color(255,80,0), defaultM);
        scene.addGeometry(leg1_2);

        //?????? ???? ????
        Triangle leg2_1 = new Triangle(new Point3D(145, -145, -280),
                new Point3D(105,-145, -280),
                new Point3D(105, -300, -280),
                new Color(255,80,0), defaultM);
        scene.addGeometry(leg2_1);
        Triangle leg2_2 = new Triangle(new Point3D(145, -145, -280),
                new Point3D(145, -300, -280),
                new Point3D(105, -300, -280),
                new Color(255,80,0), defaultM);
        scene.addGeometry(leg2_2);

        //?????? ???? ?????
        Triangle leg3_1 = new Triangle(new Point3D(340, 145, -280),
                new Point3D(340,-40, -280),
                new Point3D(300, -40, -280),
                new Color(255,80,0), defaultM);
        scene.addGeometry(leg3_1);
        Triangle leg3_2 = new Triangle(new Point3D(340, 145, -280),
                new Point3D(300, 145, -280),
                new Point3D(300,-40, -280),
                new Color(255,80,0), defaultM);
        scene.addGeometry(leg3_2);

        Material m1=new Material(1,1,4,0,0);;
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        //??? ????
        scene.getLights().add(new SpotLight(new Color(0, 100, 100), new Point3D(200, 200, -120),
                 0.5, 0.00001, 0.000005,new Vector(-2, -2, -3)));//0.5 ?? ??????

        //????? ????? ?? ??????
        Triangle smalltriangle = new Triangle(new Point3D(40, 40, -149),
                new Point3D(40, -40, -149),
                new Point3D(-45, 0, -149),
                new Color(java.awt.Color.black),defaultM);
        scene.addGeometry(smalltriangle);

        //????? ???º ?? ???????
        Triangle smalltriangle2 = new Triangle(new Point3D(30, 30, -140),
                new Point3D(30, -30, -140),
                new Point3D(-35, 0, -140),
                new Color(java.awt.Color.green),defaultM);
        scene.addGeometry(smalltriangle2);

        //???? ??? ???? ??????
        Sphere sphereWhite = new Sphere(6.5, new Point3D(-80, 0, -135),new Color(java.awt.Color.pink),defaultM);
        Material mw=new Material(1,1,20,0,);
        mw.setn(20);
        mw.setKt(0);
        sphereWhite.setMaterial(mw);
        scene.addGeometry(sphereWhite);


        //?????? ????? 10
        Sphere sphere = new Sphere( new Point3D(22, 18, -135),6.5,new Color(java.awt.Color.black), defaultM);
        Material m2=new Material();
        m2.setn(20);
        m2.setKt(0.5);
        sphere.setMaterial(m2);
        scene.addGeometry(sphere);


        Sphere sphere2 = new Sphere( new Point3D(22, 5, -135),6.5,new Color(java.awt.Color.red),defaultM);
        Material m3=new Material();
        m3.setn(20);
        m3.setKt(0.5);
        sphere.setMaterial(m3);
        scene.addGeometry(sphere2);

        Sphere sphere3 = new Sphere( new Point3D(22, -7, -135),6.5,new Color(java.awt.Color.yellow), defaultM);
        Material m4=new Material();
        m4.setn(20);
        m4.setKt(0.5);
        sphere.setMaterial(m4);
        scene.addGeometry(sphere3);

        Sphere sphere4 = new Sphere( new Point3D(22, -19, -135),6.5,new Color(java.awt.Color.blue), defaultM);
        Material m5=new Material();
        m5.setn(20);
        m5.setKt(0.5);
        sphere.setMaterial(m5);
        scene.addGeometry(sphere4);

        Sphere sphere5 = new Sphere( new Point3D(9, 12, -135),6.5,new Color(255,100,0), defaultM);//orange
        Material m6=new Material();
        m6.setn(20);
        m6.setKt(0.5);
        sphere.setMaterial(m6);
        scene.addGeometry(sphere5);

        Sphere sphere10 = new Sphere( new Point3D(9, -0.5, -135),6.5,new Color(java.awt.Color.black));//purple
        Material m10=new Material();
        m10.setn(20);
        m10.setKt(0.5);
        sphere.setMaterial(m10);
        scene.addGeometry(sphere10);

        Sphere sphere6 = new Sphere( new Point3D(9, -13, -135),6.5,new Color(50,150,255),defaultM);
        Material m7=new Material();
        m7.setn(20);
        m7.setKt(0.5);
        sphere.setMaterial(m7);
        scene.addGeometry(sphere6);

        Sphere sphere7 = new Sphere(6.5, new Point3D(-5,5, -135),Color.pink);
        Material m8=new Material();
        m8.setn(20);
        m8.setKt(0.5);
        sphere.setMaterial(m8);
        scene.addGeometry(sphere7);

        Sphere sphere8 = new Sphere(6.5, new Point3D(-5, -5, -135),new Color(255,0,255));
        Material m9=new Material();
        m9.setn(20);
        m9.setKt(0.5);
        sphere.setMaterial(m9);
        scene.addGeometry(sphere8);

        Sphere sphere9 = new Sphere(6.5, new Point3D(-19, 0, -135),Color.gray);
        Material m0=new Material();
        m0.setn(20);
        m0.setKt(0.5);
        sphere.setMaterial(m0);
        scene.addGeometry(sphere9);

        // 6 ?????? ?? ??????-?????
        Sphere spherem1 = new Sphere(9, new Point3D(300, 125, -260),Color.black);//???? ?????
        m0.setn(20);
        m0.setKt(0.5);
        sphere.setMaterial(m0);
        scene.addGeometry(spherem1);

        Sphere spherem2 = new Sphere(9, new Point3D(-125, 125, -260),Color.black);//???? ?????
        m0.setn(20);
        m0.setKt(0.5);
        sphere.setMaterial(m0);
        scene.addGeometry(spherem2);

        Sphere spherem3 = new Sphere(9, new Point3D(87.5, 125, -260),Color.black);//???? ?????
        m0.setn(20);
        m0.setKt(0.5);
        sphere.setMaterial(m0);
        scene.addGeometry(spherem3);

        Sphere spherem4 = new Sphere(9, new Point3D(125, -125, -260),Color.black);//???? ????
        m0.setn(20);
        m0.setKt(0.5);
        sphere.setMaterial(m0);
        scene.addGeometry(spherem4);

        Sphere spherem5 = new Sphere(9, new Point3D(-300,-125, -260),Color.black);//???? ????
        m0.setn(20);
        m0.setKt(0.5);
        sphere.setMaterial(m0);
        scene.addGeometry(spherem5);

        Sphere spherem6 = new Sphere(9, new Point3D(-87.5, -125, -260),Color.black);//???? ????
        m0.setn(20);
        m0.setKt(0.5);
        sphere.setMaterial(m0);
        scene.addGeometry(spherem6);

        Triangle triangle22 = new Triangle(new Point3D(700, 875, -1000),
                new Point3D(900, 1000, -1000),
                new Point3D(900, 750, -1000),
                new Color(70, 0, 70) );
        triangle22.setnShininies(20);
        scene.addGeometry(triangle22);

        Triangle triangle23 = new Triangle(new Point3D(700, 875, -1000),
                new Point3D(700, 625, -1000),
                new Point3D(900, 750, -1000),
                new Color(51, 0, 51 ));
        triangle23.setnShininies(20);
        scene.addGeometry(triangle23);

        Triangle triangle24 = new Triangle(new Point3D(700, 875, -1000),
                new Point3D(700, 625, -1000),
                new Point3D(500, 750, -1000),
                new Color(0, 76, 153));
        triangle24.setnShininies(20);
        scene.addGeometry(triangle24);

        Triangle triangle25 = new Triangle(new Point3D(700, 875, -1000),
                new Point3D(500, 1000, -1000),
                new Point3D(500, 750, -1000),
                new Color(0, 102, 204));
        triangle25.setnShininies(20);
        scene.addGeometry(triangle25);

        ImageWriter imageWriter = new ImageWriter("Snuker Test", 500, 500, 500, 500);

        Render render = new Render(scene,imageWriter);

        render.renderImage();
        render.getImageWriter().writeToimage();
    }
}
