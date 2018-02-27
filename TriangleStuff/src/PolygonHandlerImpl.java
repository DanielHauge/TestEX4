import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.DoubleStream;

public class PolygonHandlerImpl implements PolygonHandler {
    /**
     * Creates a polygon from array of doubles. This array will determine the sides of the polygon
     *
     * @param sides The values that determines the length of the sides
     * @return new polygon object
     * @throws Exception if sides cannot make up a polygon.
     */
    @Override
    public Polygon CreatePolygon(double[] sides) throws Exception {
        if (sides.length<3){
            throw new Exception("Cannot make a polygon with less than 3 sides");
        }
        else if (sides.length>10){
            throw new Exception("A polygon with over 10 sides is getting a little to complicated for our scope");
        }
        else{
            Polygon p = new Polygon();
            p.sides = sides;
            if (AmIValid(p)){
                return p;
            }
            else{
                throw new Exception("Was not a valid Polygon, side lengths could not make up the polygon");
            }

        }
    }

    /**
     * Calculates the Polygon type.
     *
     * @param p The polygon which type is being calculated
     * @return A string which represent the type of the polygon.
     * @throws Exception If polygon is invalid
     */
    @Override
    public String CalculatePolygonType(Polygon p) throws Exception {
        switch (p.sides.length){
            case 3:
                return "Triangle";
            case 4:
                return "Quadrilateral";
            case 5:
                return "Pentagon";
            case 6:
                return "Hexagon";
            case 7:
                return "Heptagon";
            case 8:
                return "Octagon";
            case 9:
                return "Nonagon";
            case 10:
                return "Decagon";
            default:
                throw new Exception("Invalid, out of scope");
        }
    }

    /**
     * This Method, calculates the area of the given polygon
     *
     * @param p which is the polygon that the area is being calculated from
     * @return A double value which represent the area in squaremeters
     * @throws AssertionError If polygon object is invalid or non determinerable without angles.
     */
    @Override
    public double CalculateArea(Polygon p) throws AssertionError {
        if (p.sides.length!=3){
            throw new AssertionError("This Polygon is not a triangle, therefore cannot calculate area based only on sides" + this);
        }
        else{
            double temp = (p.sides[0]+p.sides[1]+p.sides[2])/2;
            double result = Math.sqrt(temp * (temp - p.sides[0]) * (temp - p.sides[1]) * (temp - p.sides[2]));
            DecimalFormat df = new DecimalFormat("#.###");
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
            dfs.setDecimalSeparator('.');
            df.setDecimalFormatSymbols(dfs);
            return Double.parseDouble(df.format(result));
        }
    }

    /**
     * This method will remove one of the sides of the polygon
     *
     * @param index The side which will be removed
     * @param p     The polygon which the side will be removed form
     * @return A new polygon with a side removed.
     * @throws Exception If side cannot be removed (outofindex) or Polygon is invalid.
     */
    @Override
    public Polygon RemoveSide(int index, Polygon p) throws Exception {
        if (index>=p.sides.length||index<0){
            throw new Exception("Cannot remove side that does not exist.");
        }
        else{
            double[] newsides = new double[p.sides.length-1];
            int count = 0;
            for (int i = 0; i<p.sides.length; i++){
                if (index!=i){
                    newsides[count] = p.sides[i];
                    count ++;
                }
            }
            Polygon newp = new Polygon();
            newp.sides = newsides;
            return newp;
        }

    }

    /**
     * This methods, calculated the angles from a polygon which is limited to a triangle
     *
     * @param p Which is the polygon which the angles is being calculated from
     * @return An array of double values which represent the angles in degree's
     * @throws Exception If the polygon is not a triangle.
     */
    @Override
    public double[] CalculateAnglesFromTriangle(Polygon p) throws Exception {
        if (p.sides.length!=3){
            throw new Exception("This Polygon is not a triangle, therefore cannot calculate area based only on sides");
        }
        else {
            DecimalFormat df = new DecimalFormat("#.###");
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
            dfs.setDecimalSeparator('.');
            df.setDecimalFormatSymbols(dfs);
            double[] result = new double[3];
            double upper = Math.pow(p.sides[1], 2) + Math.pow(p.sides[2], 2) - Math.pow(p.sides[0], 2);
            double lower = 2 * p.sides[1] * p.sides[2];
            double radian = Math.acos(upper / lower);
            double res = Double.parseDouble(df.format(radian*(180.0 / Math.PI)));
            result[0] =  res;

            upper = Math.pow(p.sides[0], 2) + Math.pow(p.sides[1], 2) - Math.pow(p.sides[2], 2);
            lower = 2 * p.sides[0] * p.sides[1];
            radian = Math.acos(upper / lower);
            res = Double.parseDouble(df.format(radian*(180.0 / Math.PI)));
            result[1] = res;
            result[2] = 180 - result[0] - result[1];

            return result;
        }
    }

    /**
     * This method will compare 2 polygons perimeters.
     *
     * @param a The first polygon for comparrison
     * @param b The second polygon for comparrison
     * @return The polygon with the largest perimeter
     * @throws Exception If polygon is invalid
     */
    @Override
    public Polygon ComparePolygonByPerimeter(Polygon a, Polygon b) throws Exception {
        double PeriA = DoubleStream.of(a.sides).sum();
        double PeriB = DoubleStream.of(b.sides).sum();
        if (Double.compare(PeriA, PeriB) == 0){
            return a;
        }
        else if (Double.compare(PeriA, PeriB)==1){
            return a;
        }
        else{
            return b;
        }
    }

    /**
     * This method will intersect a polygons perimeter with another
     *
     * @param a The polygon which will get intersected.
     * @param b The polygon which will be intersecting.
     * @return A double value that represents the intersection between the 2 polygons perimeter.
     * @throws Exception If polygon is invalid or non determinerable without angles.
     */
    @Override
    public double IntersectPerimeterPolygon(Polygon a, Polygon b) throws Exception {
        double PeriA = DoubleStream.of(a.sides).sum();
        double PeriB = DoubleStream.of(b.sides).sum();

        if (PeriA < PeriB){
            throw new Exception("Cannot intersect, A is smaller than B. Result will give negative area, not a relevant result. Try changing the order");
        } else{
            return PeriA - PeriB;
        }

    }

    /**
     * This method will union a polygons perimeter with another.
     *
     * @param a The first polygon to be unioned
     * @param b The second polygon to be unioned
     * @return A double value that represents the union between the 2 polygons perimeter.
     * @throws Exception If polygon is invalid
     */
    @Override
    public double UnionPerimeterPolygon(Polygon a, Polygon b) throws Exception {
        return DoubleStream.of(a.sides).sum()+ DoubleStream.of(b.sides).sum();
    }

    /**
     * This method will sort an arraylist of polygon by their area.
     *
     * @param polygonArrayList Which is the array that needs to be sorted
     * @return A new Array that is sorted, Lowest area first.
     * @throws Exception If polygons within the array is invalid or non comparable (ie. cannot calculate area because of missing angles in a square).
     */
    @Override
    public ArrayList<Polygon> SortByArea(ArrayList<Polygon> polygonArrayList) throws Exception {

        for (Polygon polygon : polygonArrayList) {
            if (polygon.sides.length!=3){
                throw new Exception("Hey!, not all Polygons was triangles, cannot calculate area without angles then");
            }
        }

        polygonArrayList.sort(new Comparator<Polygon>() {
            @Override
            public int compare(Polygon o1, Polygon o2) {
                return Double.compare(CalculateArea(o1), CalculateArea(o2));
            }
        });

        return polygonArrayList;

    }

    /**
     * This method will sort an arraylist of polygons by their total value of aggregated side length's
     *
     * @param polygonArrayList The array which will get sorted
     * @return A new Arraylist which has been sorted. Lowest perimeter first.
     * @throws Exception If polygons within the array is invalid or non comparable.
     */
    @Override
    public ArrayList<Polygon> SortByPerimeter(ArrayList<Polygon> polygonArrayList) throws Exception {

        polygonArrayList.sort(new Comparator<Polygon>() {
            @Override
            public int compare(Polygon o1, Polygon o2) {
                return Double.compare(CalculatePerimeter(o1), CalculatePerimeter(o2));
            }
        });

        return polygonArrayList;
    }


    private double CalculatePerimeter(Polygon p){
        return DoubleStream.of(p.sides).sum();
    }



    private boolean AmIValid(Polygon p){
        double MaxSideLength = DoubleStream.of(p.sides).sum()/2;
        for (double side : p.sides) {
            if (side>=MaxSideLength){
                return false;
            }
        }

        return true;
    }



}


