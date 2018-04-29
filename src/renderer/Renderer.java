package renderer;

import elements.Color;
import elements.Scene;
import primitives.Point3D;
import primitives.Ray;

import java.util.List;

public class Renderer {

    private Scene _scene;
    private ImageWriter _imgWrter;

    //???
    public void printImage(){

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

    //???
    private void renderImage(){
        for (int i=0;true;i++) //STOP ??
        {
            for (int j=0;true;j++) //STOP
            {
                Ray r = _scene.getCamera().constructRayThoughPixel(_imgWrter.getNx(),_imgWrter.getNy(),i,j,_scene.getCameraDistance(),_imgWrter.getWidth(),_imgWrter.getHeight());
                List<Point3D> lstIntersections = _scene.getGeometriesManager().findIntersections(r);

                if(lstIntersections.size()==0)  //j,i or i,j ???
                    _imgWrter.writePixel(j,i,_scene.getBackground().getColor());
                else {
                    Point3D closePoint = getClosestPoint(lstIntersections);
                    _imgWrter.writePixel(j,i,calcColor(closePoint).getColor());
                }
            }

        }
    }

    /**
     * @param p The point to analuys
     * @return The light with the good intensity
     */
    private Color calcColor(Point3D p){ //Why need a point ???
        return _scene.getAmbientLight().getIntensity();
    }

    /**
     * Get the closest point of the view
     * @param points The list of all intersections points
     * @return The closest point of the view
     */
    private Point3D getClosestPoint(List<Point3D> points){
        double distance = Double.MAX_VALUE;
        Point3D p0 = _scene.getCamera().getP0();
        Point3D minDistancePoint=null;

        for (Point3D pt: points){
            if(p0.distance(pt)<distance){
                minDistancePoint = new Point3D(pt);
                distance = p0.distance(pt);
            }
        }
        return minDistancePoint;
    }


}
