import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        PolygonHandler PH = new PolygonHandlerImpl();
        ArrayList<Polygon> ListToSave = new ArrayList<>();
        try {
            ListToSave.add(PH.CreatePolygon(new double[]{76,76,76}));
            ListToSave.add(PH.CreatePolygon(new double[]{15,15,15}));
            ListToSave.add(PH.CreatePolygon(new double[]{1,1,1}));
            ListToSave.add(PH.CreatePolygon(new double[]{2,2,2}));
            ListToSave.add(PH.CreatePolygon(new double[]{3,3,3}));
            ListToSave.add(PH.CreatePolygon(new double[]{4,5,5}));
            ListToSave.add(PH.CreatePolygon(new double[]{6,5,5}));
            ListToSave.add(PH.CreatePolygon(new double[]{7,6,7}));
            ListToSave.add(PH.CreatePolygon(new double[]{2,3,4}));
            ListToSave.add(PH.CreatePolygon(new double[]{100,100,100}));
            ListToSave.add(PH.CreatePolygon(new double[]{4,4,3}));
            ListToSave.add(PH.CreatePolygon(new double[]{9,7,8}));
            ListToSave.add(PH.CreatePolygon(new double[]{7,6,8}));
            ListToSave.add(PH.CreatePolygon(new double[]{8,4,5}));
            ListToSave.add(PH.CreatePolygon(new double[]{7,6,8}));
            ListToSave.add(PH.CreatePolygon(new double[]{14,13,12}));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File("C:\\output\\mylist.json"), ListToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
