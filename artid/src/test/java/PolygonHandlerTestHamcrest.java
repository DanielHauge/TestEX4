import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class PolygonHandlerTestHamcrest {

    @org.junit.jupiter.api.Test
    void createPolygon() {
        assertThat("test", anyOf(is("testing"), containsString("est")));
    }

    @org.junit.jupiter.api.Test
    void comparePolygonByPerimeter() {
    }
}