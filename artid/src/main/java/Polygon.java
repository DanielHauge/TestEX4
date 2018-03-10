import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

public class Polygon implements Serializable {

    @JsonSerialize
    public double sides[];

    public void Print(){
        System.out.println("Sides of polygon");
        for (double side : sides) {
            System.out.println(side);
        }
    }

}
