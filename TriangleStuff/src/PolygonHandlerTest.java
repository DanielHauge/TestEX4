import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PolygonHandlerTest {

    PolygonHandler PH;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        PH = new PolygonHandlerImpl();
    }

    @org.junit.jupiter.api.Test
    void createPolygon() throws Exception {
        double[] sides = {1,1,2};
        Polygon P = PH.CreatePolygon(sides);

    }

    @org.junit.jupiter.api.Test
    void calculatePolygonType() {
    }

    @org.junit.jupiter.api.Test
    void calculateArea() {
    }

    @org.junit.jupiter.api.Test
    void removeSide() {
    }

    @org.junit.jupiter.api.Test
    void calculateAnglesFromTriangle() {
    }

    @org.junit.jupiter.api.Test
    void comparePolygonByArea() {
    }

    @org.junit.jupiter.api.Test
    void intersectPolygon() {
    }

    @org.junit.jupiter.api.Test
    void unionPolygon() {
    }

    @org.junit.jupiter.api.Test
    void sortByArea() {
    }

    @org.junit.jupiter.api.Test
    void sortBySides() {
    }

}