package unitTest;

import org.junit.jupiter.api.Test;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorTest {

    Vector v=new Vector(1,1,1);
    Vector v2 = new Vector(2,3,4);
    Vector v3 = new Vector(0,3,4);

    @Test
    public void TestAdd()  {
        assertEquals(v.add(new Vector(1,1,1)), new Vector(2,2,2));
    }

    @Test
    public void subtractTest()  {
        assertEquals(v.sub(v), new Vector(0,0,0));
    }

    @Test
    public void scalarMultTest() {
        assertEquals(v.mult(2), new Vector(2,2,2));
    }

    //cross:(u2v3-u3v2,u3v1-u1v3,u1v2-u2v1)
    @Test
    public void crossProductTest() {
        assertEquals(v.crossProdcut(v), new Vector(0,0,0));
        assertEquals(new Vector(0,-8,6),v2.crossProdcut(v3)); // Cross product
    }

    @Test
    public void sizeTest() {
        assertEquals(v3.size(),5);
    }

    @Test
    public void normalizeTest()  {
        Vector v4 = new Vector(3.5,-5,10);
        v4 = v4.normalize();
        assertEquals(1, v4.size(),1e-10);
        v4 = new Vector(0,0,0);
        try {
            v4 = v4.normalize();
            fail("Didn't throw divide by zero exception!");
        } catch (ArithmeticException e) {
            assertTrue(true);
        }
    }

    @Test
    public void dotProductTest() {
        assertEquals(25.0, v2.dotProduct(v3)); //Dot product
    }
}
