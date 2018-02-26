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
The first set of cases are just to see if the methods work as inteded when used correctly or normal cases. These tests can be found in the testing class [PolygonHandlerTest](link). It is the first 10 unit tests, and is basicly just testing little bit of normal behavior.

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

## Solution

