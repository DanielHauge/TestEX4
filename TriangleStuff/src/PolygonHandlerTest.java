
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.*;

class PolygonHandlerTest {

    PolygonHandler PH;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        PH = new PolygonHandlerImpl();
    }

    /*
    Usual test cases.
     */
    @org.junit.jupiter.api.Test
    void createPolygon() throws Exception {
        double[] sides = {1,2,2};
        Polygon P = PH.CreatePolygon(sides);
        assertTrue(sides==P.sides);
    }

    @org.junit.jupiter.api.Test
    void calculatePolygonType() throws Exception {

        // Triangle
        double[] sides = new double[]{1,1,1};
        Polygon P = PH.CreatePolygon(sides);
        String pType = PH.CalculatePolygonType(P);
        assertTrue(pType=="Triangle");

        // Quadrilateral
        sides = new double[]{1,1,1,1};
        P = PH.CreatePolygon(sides);
        pType = PH.CalculatePolygonType(P);
        assertTrue(pType=="Quadrilateral");

        // Hexagon
        sides = new double[]{1,1,1,1,1};
        P = PH.CreatePolygon(sides);
        pType = PH.CalculatePolygonType(P);
        assertTrue(pType=="Pentagon");

        // Hexagon
        sides = new double[]{1,1,1,1,1,1};
        P = PH.CreatePolygon(sides);
        pType = PH.CalculatePolygonType(P);
        assertTrue(pType=="Hexagon");

        // Heptagon
        sides = new double[]{1,1,1,1,1,1,1};
        P = PH.CreatePolygon(sides);
        pType = PH.CalculatePolygonType(P);
        assertTrue(pType=="Heptagon");

        // Octagon
        sides = new double[]{1,1,1,1,1,1,1,1};
        P = PH.CreatePolygon(sides);
        pType = PH.CalculatePolygonType(P);
        assertTrue(pType=="Octagon");

        // Nonagon
        sides = new double[]{1,1,1,1,1,1,1,1,1};
        P = PH.CreatePolygon(sides);
        pType = PH.CalculatePolygonType(P);
        assertTrue(pType=="Nonagon");

        // Decagon
        sides = new double[]{1,1,1,1,1,1,1,1,1,1};
        P = PH.CreatePolygon(sides);
        pType = PH.CalculatePolygonType(P);
        assertTrue(pType=="Decagon");

    }
    @org.junit.jupiter.api.Test
    void calculateArea() throws Exception {

        // Invalid - Cannot even create polygon that has less than 3 sides
        double[] sides = {5,5};
        double area = 0;
        double[] finalSides = sides;
        assertThrows(Exception.class, () -> {   Polygon error = PH.CreatePolygon(finalSides);   });

        // Triangle
        sides = new double[] {5,5,5};
        Polygon P = PH.CreatePolygon(sides);
        area = PH.CalculateArea(P);
        assertTrue(area==10.825);

        /* Only holds if all sides are of equal length. Otherwise no angles can be determined.
        // Quadrilateral
        sides = new double[] {5,5,5,5};
        P = PH.CreatePolygon(sides);
        area = PH.CalculateArea(P);
        assertTrue(area==25);

        // Pentagon
        sides = new double[] {5,5,5,5,5};
        P = PH.CreatePolygon(sides);
        area = PH.CalculateArea(P);
        assertTrue(area==43.012);

        // Hexagon
        sides = new double[] {5,5,5,5,5,5};
        P = PH.CreatePolygon(sides);
        area = PH.CalculateArea(P);
        assertTrue(area==64.952);

        // Heptagon
        sides = new double[] {5,5,5,5,5,5,5};
        P = PH.CreatePolygon(sides);
        area = PH.CalculateArea(P);
        assertTrue(area==90.848);

        // Octagon
        sides = new double[] {5,5,5,5,5,5,5,5};
        P = PH.CreatePolygon(sides);
        area = PH.CalculateArea(P);
        assertTrue(area==120.711);

        // Nonagon
        sides = new double[] {5,5,5,5,5,5,5,5,5,5};
        P = PH.CreatePolygon(sides);
        area = PH.CalculateArea(P);
        assertTrue(area==154.546);

        // Decagon
        sides = new double[] {5,5,5,5,5,5,5,5,5,5,5};
        P = PH.CreatePolygon(sides);
        area = PH.CalculateArea(P);
        assertTrue(area==192.355);
        */
    }
    @org.junit.jupiter.api.Test
    void removeSide() throws Exception {

        double[] sides = {2,3,4,5,6};

        double[] expected = {2,3,5,6};

        Polygon P = PH.CreatePolygon(sides);

        Polygon New = PH.RemoveSide(2, P);

        assertArrayEquals(expected, New.sides);

    }
    @org.junit.jupiter.api.Test
    void calculateAnglesFromTriangle() throws Exception {

        double[] sides = {2,4,3.5};
        Polygon P = PH.CreatePolygon(sides);
        double[] angles = PH.CalculateAnglesFromTriangle(P);

        assertTrue(DoubleStream.of(angles).anyMatch(x -> x == 29.995));
        assertTrue(DoubleStream.of(angles).anyMatch(x -> x == 88.977));
        assertTrue(DoubleStream.of(angles).anyMatch(x -> x == 61.028));

    }
    @org.junit.jupiter.api.Test
    void comparePolygonByPerimeter() throws Exception {

        double[] sides = {2,3,4};
        Polygon a = PH.CreatePolygon(sides);
        sides = new double[] {2.3,3.3,3.5};
        Polygon b = PH.CreatePolygon(sides);
        Polygon biggest = PH.ComparePolygonByPerimeter(a, b);

        assertTrue(biggest.sides==b.sides&&biggest!=null);


    }
    @org.junit.jupiter.api.Test
    void intersectPolygon() throws Exception {

        double[] sides = {2,3,4,8}; //17
        Polygon a = PH.CreatePolygon(sides);
        sides = new double[] {3,4,5,3}; //15
        Polygon b = PH.CreatePolygon(sides);

        double result = PH.IntersectPerimeterPolygon(a, b);

        assertTrue(result==2);

    }
    @org.junit.jupiter.api.Test
    void unionPolygon() throws Exception {

        double[] sides = {2,3,4,8}; //17
        Polygon a = PH.CreatePolygon(sides);
        sides = new double[] {3,4,5,3}; //15
        Polygon b = PH.CreatePolygon(sides);

        double result = PH.UnionPerimeterPolygon(a,b);

        assertTrue(result==32);

    }
    @org.junit.jupiter.api.Test
    void sortByArea() throws Exception {

        double[] sides = {7 , 5 , 6};
        Polygon a = PH.CreatePolygon(sides);
        sides = new double[] {3 , 3 , 3};
        Polygon b = PH.CreatePolygon(sides);
        sides = new double[] {56 , 46 , 67};
        Polygon c = PH.CreatePolygon(sides);
        sides = new double[] {1 , 1 , 1};
        Polygon d = PH.CreatePolygon(sides);
        sides = new double[] {4500 , 4000 , 5000};
        Polygon e = PH.CreatePolygon(sides);
        sides = new double[] {3 , 4 , 5 };
        Polygon f = PH.CreatePolygon(sides);

        ArrayList<Polygon> list = new ArrayList<Polygon>();
        list.add(b);
        list.add(f);
        list.add(e);
        list.add(d);
        list.add(c);
        list.add(a);

        ArrayList<Polygon> sortedlist = PH.SortByArea(list);

        assertAll(
                () -> assertTrue(sortedlist.get(0).sides[1] == d.sides[1]),
                () -> assertTrue(sortedlist.get(1).sides == b.sides),
                () -> assertTrue(sortedlist.get(2).sides == f.sides),
                () -> assertTrue(sortedlist.get(3).sides == a.sides),
                () -> assertTrue(sortedlist.get(4).sides == c.sides),
                () -> assertTrue(sortedlist.get(5).sides == e.sides)
        );


        // Could also have done a foreach and just done, as long as i'm bigger than previus it's fine. But wanted a more static one for this one.

    }
    @org.junit.jupiter.api.Test
    void sortByPerimeter() throws Exception {

        double[] sides = {7,5,6,5 ,4};
        Polygon a = PH.CreatePolygon(sides);
        sides = new double[] {3,3,3 ,3 ,3};
        Polygon b = PH.CreatePolygon(sides);
        sides = new double[] {56,46,67, 69, 53, 53 , 23};
        Polygon c = PH.CreatePolygon(sides);
        sides = new double[] {1,1,1};
        Polygon d = PH.CreatePolygon(sides);
        sides = new double[] {4500, 4000,5000, 3000, 4000, 6400, 2200, 3500};
        Polygon e = PH.CreatePolygon(sides);
        sides = new double[] {3,4,5};
        Polygon f = PH.CreatePolygon(sides);

        ArrayList<Polygon> list = new ArrayList<Polygon>();
        list.add(b);
        list.add(f);
        list.add(e);
        list.add(d);
        list.add(c);
        list.add(a);

        ArrayList<Polygon> sortedlist = PH.SortByPerimeter(list);

        assertAll(
                () -> assertTrue(sortedlist.get(0) ==d),
                () -> assertTrue(sortedlist.get(1) == f),
                () -> assertTrue(sortedlist.get(2) == b),
                () -> assertTrue(sortedlist.get(3) == a),
                () -> assertTrue(sortedlist.get(4)==c),
                () -> assertTrue(sortedlist.get(5)==e)
        );

        // Could also have done a foreach and just done, as long as i'm bigger than previus it's fine. But wanted a more static one for this one.

    }
    /*
    BlackBox Tests
    - Boundary Analysis
    - Equivalence classes
     */
    @org.junit.jupiter.api.Test
    void BoundaryCreate() throws Exception{
        // Invalid with 2 sides
        double[] sides = {7,5};
        double[] finalSides = sides;
        assertThrows(Exception.class, () -> {     Polygon b = PH.CreatePolygon(finalSides);   });

        // Valid with 3 sides
        sides = new double[]{7,5,4};
        Polygon a = PH.CreatePolygon(sides);
        assertTrue(a.sides[0] == 7&&a.sides[1] == 5&&a.sides[2] == 4);

        // Valid with 10 sides
        sides = new double[]{101,102,103,104,105,106,107,108,109,110};
        a = PH.CreatePolygon(sides);
        assertTrue(a.sides[0] == 101&&a.sides[1] == 102&&a.sides[2] == 103&&a.sides[3] == 104&&a.sides[4] == 105&&a.sides[5] == 106&&a.sides[6] == 107&&a.sides[7] == 108&&a.sides[8] == 109&&a.sides[9] == 110);

        // Invalid with 11 sides
        sides = new double[]{101,102,103,104,105,106,107,108,109,110,111};
        double[] finalSides1 = sides;
        assertThrows(Exception.class, () -> {     Polygon b = PH.CreatePolygon(finalSides1);   });
    }
    @org.junit.jupiter.api.Test
    void BoundaryRemoveSide() throws Exception{

        // Invalid -1
        double[] sides = {7,5,5,5,5};
        Polygon p = PH.CreatePolygon(sides);
        Polygon finalP = p;
        assertThrows(Exception.class, () -> {     Polygon b = PH.RemoveSide(-1, finalP);   });

        // Valid 0
        sides = new double[] {7,5,5,5,5};
        p = PH.CreatePolygon(sides);
        Polygon removedside = PH.RemoveSide(0,p);
        assertTrue(removedside.sides.length==p.sides.length-1);

        // Valid side.count-1
        sides = new double[] {7,5,5,5,5};
        p = PH.CreatePolygon(sides);
        removedside = PH.RemoveSide(p.sides.length-1,p);
        assertTrue(removedside.sides.length==p.sides.length-1);

        // Invalid side.count
        sides = new double[] {7,5,5,5,5};
        p = PH.CreatePolygon(sides);
        Polygon finalP1 = p;
        assertThrows(Exception.class, () -> {     Polygon b = PH.RemoveSide(finalP1.sides.length, finalP1);   });

    }
    @org.junit.jupiter.api.Test
    void EquivalenceClassCreate() throws Exception{

        // Valid with 5 sides
        double[] sides = new double[] {5,5,5,5,5};
        Polygon p = PH.CreatePolygon(sides);
        assertTrue(p.sides.length==5);


        // Invalid with 1 side
        sides = new double[] {5};
        double[] finalSides = sides;
        assertThrows(Exception.class, () -> {     Polygon b = PH.CreatePolygon(finalSides);   });

        // Valid with 525 as value
        sides = new double[] {525, 525, 525, 525};
        p = PH.CreatePolygon(sides);
        assertTrue(p.sides[0]==525);


        // Invalid with value of -5
        sides = new double[] {-5, 0, -525, 525};
        double[] finalSides1 = sides;
        assertThrows(Exception.class, () -> {     Polygon b = PH.CreatePolygon(finalSides1);   });

    }
    @org.junit.jupiter.api.Test
    void EquivalenceClassType() throws Exception{

        // A side is bigger than the summation of other sides
        double[] sides = new double[] {1,2,3};
        double[] finalSides = sides;
        assertThrows(Exception.class, () -> {     Polygon b = PH.CreatePolygon(finalSides);   });


        // Any side is not bigger than the summation of other sides
        sides = new double[] {2,2,2};
        Polygon p = PH.CreatePolygon(sides);
        assertTrue(p.sides.length==3);

    }
    @org.junit.jupiter.api.Test
    void EquivalenceClassCalculateAreaAndAngles() throws Exception{

        // With 3 sides. Valid
        double[] sides = {6,5,4};
        Polygon p = PH.CreatePolygon(sides);
        double[] Angles = PH.CalculateAnglesFromTriangle(p);
        double Area = PH.CalculateArea(p);

        // With 4 sides. Invalid
        sides = new double[]{6,5,6,5};
        p = PH.CreatePolygon(sides);
        Polygon finalP = p;
        assertThrows(Exception.class, () -> {     double[] angles = PH.CalculateAnglesFromTriangle(finalP);   });
        assertThrows(AssertionError.class, () -> {     double area = PH.CalculateArea(finalP);   });

        // With 2 sides. Invalid = Cannot even create polygon with 2 sides only.
        sides = new double[]{6,5};
        double[] finalSides = sides;
        assertThrows(Exception.class, () -> {     Polygon a = PH.CreatePolygon(finalSides);   });

    }
    @org.junit.jupiter.api.Test
    void EquivalenceClassIntersectPerimeterPolygon() throws Exception{

        // A is bigger = Valid
        double[] sides = {15,15,15};
        Polygon a = PH.CreatePolygon(sides);
        sides = new double[] {10,10,10};
        Polygon b = PH.CreatePolygon(sides);
        assertTrue(PH.IntersectPerimeterPolygon(a,b)==15);

        // B is bigger = Invalid
        sides =  new double[] {10,10,10};
        a = PH.CreatePolygon(sides);
        sides = new double[] {15,15,15,5};
        b = PH.CreatePolygon(sides);
        Polygon finalA = a;
        Polygon finalB = b;
        assertThrows(Exception.class, () -> { double errorresult = PH.IntersectPerimeterPolygon(finalA, finalB); });

    }
    @org.junit.jupiter.api.Test
    void EquivalenceClassSortByArea() throws Exception{
        // All polygons are triangles
        ArrayList<Polygon> list = new ArrayList<Polygon>();
        double[] sides = {15,15,15};
        Polygon p = PH.CreatePolygon(sides);
        list.add(p);
        sides = new double[] {10, 10.5, 15};
        p = PH.CreatePolygon(sides);
        list.add(p);
        sides = new double[] {15, 12.5, 13};
        p = PH.CreatePolygon(sides);
        list.add(p);
        sides = new double[] {50, 51, 52};
        p = PH.CreatePolygon(sides);
        list.add(p);
        sides = new double[] {100, 100.5, 125};
        p = PH.CreatePolygon(sides);
        list.add(p);

        ArrayList<Polygon> sortedList = PH.SortByArea(list);

        // Not all polygons are triangles
        list = new ArrayList<Polygon>();
        sides = new double[] {10, 10.5, 15, 25}; //// One more side, not a triangle anymore.
        p = PH.CreatePolygon(sides);
        list.add(p);
        sides = new double[] {15, 12.5, 13};
        p = PH.CreatePolygon(sides);
        list.add(p);
        sides = new double[] {50, 51, 52};
        p = PH.CreatePolygon(sides);
        list.add(p);
        sides = new double[] {100, 100.5, 125};
        p = PH.CreatePolygon(sides);
        list.add(p);

        ArrayList<Polygon> finalList = list;
        assertThrows(Exception.class, () -> { ArrayList<Polygon> ErrorList = PH.SortByArea(finalList); });

    }


    @Test
    void dataDrivenTest1() throws Exception{

    }

}