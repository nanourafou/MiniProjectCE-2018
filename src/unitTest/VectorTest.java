package unitTest;

import org.junit.jupiter.api.Test;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class VectorTest {

    @Test
    public void globalVectorTest(){
        Vector v = new Vector(2,3,4);
        Vector v1 = new Vector(0,3,4);


        assertEquals(new Vector(-2,-3,-4),v.getReverseVector()); //Reverse Vector
        assertNotEquals(v,v1);//Equals function
        assertFalse(v.isColinear(v1));//Colinear Vector
        assertEquals(25.0, v.dotProduct(v1)); //Dot product
        assertEquals(new Vector(2,6,8),v.add(v1)); // Add
        assertEquals(new Vector(2,0,0), v.sub(v1)); // Sub
        assertEquals(new Vector(0,-8,6),v.crossProdcut(v1)); // Cross product
        assertEquals(new Vector(0,6,8),v1.mult(2)); //mult scalar


    }
}
