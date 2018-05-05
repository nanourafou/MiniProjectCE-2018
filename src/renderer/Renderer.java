package renderer;

import elements.Color;
import elements.Scene;
import geometries.Geometry;
import primitives.Point3D;
import primitives.Ray;

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
                        _imgWrter.writePixel(j,i,calcColor(entry.getKey(), entry.getValue()).getColor());
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

        Color c = _scene.getAmbientLight().getIntensity();
        c.add(g.getEmission());
        return c;
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
}
