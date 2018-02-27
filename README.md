# Test excercise 4 (AP Degree Exam)
This repository is a exercise project for Software development (PBA) Test course. Daniel (cph-dh136)

## Description
This excercise is to create an interface. Make unit tests for the interface, and then implement a solution for all tests to pass. The excercise is an AP degree exam, and it is based off of this [Ressource](FirstSemesterEksam.pdf)

## Interface
The methods as descriped does not need to be advanced or complicated. Simple methods as examples provided in the excercise description.
The interface methods are as below:

```java
    public Polygon CreatePolygon(double[] sides) throws Exception;

    public String CalculatePolygonType(Polygon p) throws Exception;

    public double CalculateArea(Polygon p) throws Exception;

    public Polygon RemoveSide(int index, Polygon p) throws Exception;

    public double[] CalculateAnglesFromTriangle(Polygon p) throws Exception;

    public Polygon ComparePolygonByPerimeter(Polygon a, Polygon b) throws Exception;

    public double IntersectPerimeterPolygon(Polygon a, Polygon b) throws Exception;

    public double UnionPerimeterPolygon(Polygon a, Polygon b) throws Exception;

    public ArrayList<Polygon> SortByArea(ArrayList<Polygon> polygonArrayList) throws Exception;

    public ArrayList<Polygon> SortByPerimeter(ArrayList<Polygon> polygonArrayList) throws Exception;
```
- Note: Javadocs included in java file. [HERE]()

## Ressources
A Polygon class has been used as a ressource (Class) in the interface. It is realy simple.

```java
public class Polygon {

    public double sides[];

    public void Print(){
        System.out.println("Sides of polygon");
        for (double side : sides) {
            System.out.println(side);
        }
    }
    
}
```

## Unit Tests
#### First derived test cases
The first set of cases are just to see if the methods work as inteded when used correctly or normal cases. These tests can be found in the testing class [PolygonHandlerTest](TriangleStuff/src/PolygonHandlerTest.java). It is the first 10 unit tests, and is basicly just testing little bit of normal behavior.

### Boundary analysis

#### CreatePolygons

- Side amount

Invalid | Valid   | Invalid
------- | ------- | -----------
-∞    2 | 3    10 | 11    ∞

- Side value

Invalid | Valid          | Invalid
------- | -------------- | -----------
-∞    0 | 0.???  int.max | double.max+1     ∞

#### RemoveSide

- Index

Invalid   | Valid                    | Invalid
--------- | ------------------------ | -----------
-∞     -1 | 0   polygon.side.count-1 | polygon.side.count     ∞

### Equivalence classes
##### CreatePolygons
Class        | Testcase
------------ | -----------------
2 < sides.count < 11 | 5 valid, 1 invalid
0 < sides.value < big.int | valid: 525, -5 invalid.

- To be a recognized polygon, it needs to have atleast 3 sides. In this case, we have limited it to 10 to not get to extreme.

#### CalculatePolygonType
Class | Testcase
----- | ----------
side[i] < ∑(!side[i]) | invalid [1,2,3], valid [2,2,2]

- Any given side most be less than the summation of all other sides.

#### CalculateArea & CalculateAnglesFromTriangle
Class | Testcase
----- | ------------
sides.count == 3 | valid: 3, invalid: 4

- To calculate area or angles only based on sides, it is limited to triangles.

#### RemoveSide
Class | Testcase
----- | ------------
-1 < index < sides.count | valid: sides 4 - index 2, invalid: sides 4 - index 5

- Index needs to be known in the polygon, cannot remove side which polygon doesn't have. (OutOfBounds)

#### IntersectPerimeterPolygon
Class | Testcase
Polygon a perimeter > Polygon b perimeter | Valid: A - 45 . B - 30, Invalid: A - 30 . B 50

#### SortByArea
Class | Testcase
----- | --------
Side count of array[i] == 3 | N/A

- All polygons within the array needs to be triangles to determine area only based on sides.

--------------------------
All unit tests can be found in this file [PolygonHandlerTest](TriangleStuff/src/PolygonHandlerTest.java). Including tests derived from Boundary and Equivelance classes.

## Solution
- First take on implementation:
[![https://gyazo.com/953a89b3a53babe632487fc43801e0c5](https://i.gyazo.com/953a89b3a53babe632487fc43801e0c5.png)](https://gyazo.com/953a89b3a53babe632487fc43801e0c5)

Some tests passed, some tests failed. Got to look closer to those who failed the tests.

When looking into the errors. They were incorrect number format, this comes from the DecimalFormatter. So i changed the DecimalFormatter to a coma from a dot.
```java
DecimalFormat df = new DecimalFormat("#,###");
```
That solved most of the test cases that was failing because of this formatting issue. Another reason for some of the tests that fails, is because the implementation needed another type of exception to work on comparator. "AssertionError" instead of a regular exception. That needs to be changed in the testing instead of the implementation. Therefor changed the tests on Calculate area exceptions to AssertionError instead of regular Exception.
```java
assertThrows(AssertionError.class, () -> {     double area = PH.CalculateArea(finalP);   });
```

There was also a typo in the SortByPerimeter test, that had one of the sides an additional value which wasn't asserted to fail. It was meant for the addtional decimal to not be there. And also in the create Polygon, 1,1,2 isn't a valid polygon therefor changed to 1,2,2.

- Second take
[![https://gyazo.com/a10fe5b659b1cf792e51d817a67e65c1](https://i.gyazo.com/a10fe5b659b1cf792e51d817a67e65c1.png)](https://gyazo.com/a10fe5b659b1cf792e51d817a67e65c1)
Still some errors. 1 with multiple points and then some java.lang.NumberFormatException: multiple points, but still got some weird errors on the parsing of the double. I later figured out, that for it to work. I needed to make a DecimalFormatSybol seperator. So therefor added this to the implementation:
```java
DecimalFormat df = new DecimalFormat("#.###");
DecimalFormatSymbols dfs = new DecimalFormatSymbols();
dfs.setDecimalSeparator('.');
df.setDecimalFormatSymbols(dfs);
```
And changed the decimalformatter back to ```.```. This seemed to work.

- Third take
[![https://gyazo.com/5a52aa7a18f25c3d813a48e460d546d2](https://i.gyazo.com/5a52aa7a18f25c3d813a48e460d546d2.png)](https://gyazo.com/5a52aa7a18f25c3d813a48e460d546d2)
Only test that doesn't pass, is the ComparePolygonByPerimeter. Then i noticed, that i was asserting something that should be wrong. The perimeter of the one i initially set to be the biggest was 9 in perimeter, but the one i thought was the smallest was 9.1 in perimeter. So i was asserting the wrong thing. So now asserting the actual biggest to be the biggest of the 2.
```java
assertTrue(biggest.sides==b.sides&&biggest!=null);
```
Now all tests pass.
[![https://gyazo.com/868b7da714c916e8ab0d6a82233d2951](https://i.gyazo.com/868b7da714c916e8ab0d6a82233d2951.png)](https://gyazo.com/868b7da714c916e8ab0d6a82233d2951)
