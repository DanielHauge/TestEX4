import org.testng.TestNG;

import static org.testng.Assert.*;

public class PolygonHandlerTestNG {


    PolygonHandler PH;

    @org.testng.annotations.BeforeMethod
    public void setUp() throws Exception {
        PH = new PolygonHandlerImpl();
    }

    @org.testng.annotations.Test
    public void testCreatePolygon() throws Exception {
        double[] sides = {1,2,2};
        Polygon P = PH.CreatePolygon(sides);
        assertEquals(sides, P.sides);
    }

    @org.testng.annotations.Test
    public void testComparePolygonByPerimeter() throws Exception {

        double[] sides = {2,3,4};
        Polygon a = PH.CreatePolygon(sides);
        sides = new double[] {2.3,3.3,3.5};
        Polygon b = PH.CreatePolygon(sides);
        Polygon biggest = PH.ComparePolygonByPerimeter(a, b);
        assertTrue(biggest.sides==b.sides&&biggest!=null);
    }
}