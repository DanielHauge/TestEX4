import org.junit.jupiter.api.Test;

import static java.util.concurrent.CompletableFuture.anyOf;
import static org.hamcrest.junit.JUnitMatchers.containsString;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.hamcrest.junit.*;
import org.hamcrest.junit.JUnitMatchers.*;

class PolygonHandlerTestHam {

    @Test
    void createPolygon() {
        assertThat("test", anyOf(is("testing"), containsString("est")));
    }

    @Test
    void comparePolygonByPerimeter() {
    }
}