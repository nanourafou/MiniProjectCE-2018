package renderer;

import elements.Color;
import elements.LightSource;
import elements.Scene;
import geometries.Geometry;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Renderer {

    private Scene _scene;
    private ImageWriter _imgWrter;


    public void printGrid(int interval){
        int height = _imgWrter.getHeight();
        int width = _imgWrter.getWidth();

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){

                if (i % interval == 0 || j % interval == 0)
                    _imgWrter.writePixel(j, i, 255, 255, 255);

            }
        }
    }

    /**
     * Constructor
     * @param _scene The scene
     * @param _imgWriter The image Writer
     */
    public Renderer(Scene _scene, ImageWriter _imgWriter){
        this._scene = _scene;
        this._imgWrter = _imgWriter;

    }

    /**
     * Default Constructor
     */
    public Renderer(){
        this._scene = new Scene();
        this._imgWrter = new ImageWriter("Test 1",150,150,3,3);

    }

    /**
     * Generate a Image
     */
    public void renderImage(){
        for (int i=0;i<_imgWrter.getHeight();i++)
        {
            for (int j=0;j<_imgWrter.getWidth();j++)
            {
                Ray r = _scene.getCamera().constructRayThoughPixel(_imgWrter.getNx(),_imgWrter.getNy(),i,j,_scene.getCameraDistance(),_imgWrter.getWidth(),_imgWrter.getHeight());
                Map<Geometry, List<Point3D>> lstIntersections = _scene.getGeometriesManager().findIntersections(r);

                if(lstIntersections.size()==0)
                    _imgWrter.writePixel(j,i,_scene.getBackground().getColor());
                else {
                    Map<Geometry, Point3D> closePoint = getClosestPoint(lstIntersections);
                    for (Map.Entry<Geometry, Point3D> entry: closePoint.entrySet()) {
                        if(entry.getValue()!=null){
                            _imgWrter.writePixel(j,i,calcColor(entry.getKey(), entry.getValue()).getColor());
                        }
                        else {
                            _imgWrter.writePixel(j,i,_scene.getBackground().getColor());
                        }

                    }

                }
            }

        }

    }

    /**
     * @param p The point to analuys
     * @return The light with the good intensity
     */
    private Color calcColor(Geometry g, Point3D p){ //Why need a point ???

        Color color = _scene.getAmbientLight().getIntensity();
        color.add(g.getEmission());
        Vector n = g.getNormal(p);
        int nShininess =  g.getMterial().getNShininess();
        double kd = g.getMterial().getKd();
        double ks = g.getMterial().getKs();

        if(_scene.getLights()!=null){
            for (LightSource lightSource : _scene.getLights()) {
                Color lightIntensity = lightSource.getIntensity(p);
                Vector l = lightSource.getL(p);
                Vector v = p.subVector(_scene.getCamera().getP0());
                color.add(calcDiffusive(kd, l, n, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }



    /**
     * Get the closest point
     * @param mPoints Map<Geometry, List<Point3D>> of intersections
     * @return Map<Geometry, Point3D>
     */
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>>  mPoints){
        double distance = Double.MAX_VALUE;
        Point3D p0 = _scene.getCamera().getP0();
        Point3D minDistancePoint=null;
        Geometry key=null;


        for(Map.Entry<Geometry, List<Point3D>> entry : mPoints.entrySet() ){
            key = entry.getKey();
            List<Point3D> points = entry.getValue();

            for (Point3D pt: points){
                if(p0.distance(pt)<distance){
                    minDistancePoint = new Point3D(pt);
                    distance = p0.distance(pt);
                }
            }

        }
        if(key==null)
            return null;
        Map<Geometry, Point3D> m1 = new HashMap<>();
        m1.put(key,minDistancePoint);

        return m1;
    }
    /**
     * Write to image function
     */
    public void writeToImage(){
        _imgWrter.writeToimage();
    }


    /**
     * Calcul Specular
     * @param ks
     * @param v
     * @param normal
     * @param l
     * @param shininess
     * @param lightIntensity
     * @return
     */
    private Color calcSpecular(double ks, Vector v, Vector normal, Vector l, double shininess, Color lightIntensity){
        v = v.normalize();
        normal = normal.normalize();
        l = l.normalize();

        normal = normal.mult(-2 * l.dotProduct(normal));
        l = l.add(normal);
        Vector R = new Vector(l);
        R = R.normalize();
        double k = 0;

        if (v.dotProduct(R) > 0)
            k = ks * Math.pow(v.dotProduct(R), shininess);

        lightIntensity.scale(k);

        return lightIntensity;
    }

    /**
     * Calul Diffusion
     * @param kd
     * @param normal
     * @param l
     * @param lightIntensity
     * @return
     */
    private Color calcDiffusive(double kd, Vector normal,
                                    Vector l, Color lightIntensity) {

        normal = normal.normalize();
        l =l.normalize();
        double k = Math.abs(kd * normal.dotProduct(l));
        lightIntensity.scale(k);

        return lightIntensity;
    }
}
