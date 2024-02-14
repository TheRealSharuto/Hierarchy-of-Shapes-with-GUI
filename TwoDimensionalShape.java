package com.example.belton_shara_project2;
/*
File name: TwoDimensionalShape
Date: February 4, 2024
Author: Shara Belton
Purpose: Initializes the TwoDimensionalShape, Area, Circle, Rectangle, Square,
and Triangle classes. Each class has its own set of attributes and a calculateArea
method. An object of the Area class needs to be created after an object of one of the
shapes are created in order for the area of that shape to be calculated. Most attributes
are here just for Object attribute examples to display - and for the UML diagram.
 */
import java.util.Scanner;
import java.lang.Math;
public abstract class TwoDimensionalShape extends Shape {
    public Area area; // two-dimensional HAS-A area.
    public double perimeter;
    int numberOfDimensions = 2;
}

class Area {
    private double value;
    public Scanner scan = new Scanner(System.in);
    public Area() {

    }

    public double getValue() {
        return value;
    }

    public double triangleArea(double tB, double tH) {
        return 0.5*tB*tH;
    }

    public double rectangleArea(double length, double width) {
        return length*width;
    }

    public double circleArea(double radius) {
        return Math.PI*(Math.pow(radius, 2));
    }

    public double squareArea(double lengthAndHeight) {
        return Math.pow(lengthAndHeight, 2);
    }

}

class Circle extends TwoDimensionalShape {
    private double radius;
    private double diameter;
    private double circumference;
    public Circle (double radius) {
        this.radius = radius;
    }
    public double calculateArea() {
        Area circleArea = new Area();
        double cArea = circleArea.circleArea(radius);
        System.out.println("The area of your circle is "+ cArea);
        return cArea;
    }
}

class Square extends TwoDimensionalShape {
    private final double lengthAndHeight;
    public Square(double lengthAndHeight) {
        this.lengthAndHeight = lengthAndHeight;
    }
    public double calculateArea() {
        Area squareArea = new Area();
        double sArea = squareArea.squareArea(lengthAndHeight);
        System.out.println("The area of your square is "+ sArea);
        return sArea;
    }
}

class Triangle extends TwoDimensionalShape {
    private final double base;
    private final double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    public double calculateArea() {
        Area triangleArea = new Area();
        double tArea = triangleArea.triangleArea(base, height);
        System.out.println("The area of your triangle is "+ tArea);
        return tArea;
    }
}

class Rectangle extends TwoDimensionalShape {
    private final double length;
    private final double width;
    public Rectangle(double length, double width){
        this.length = length;
        this.width = width;
    }
    public double calculateArea() {
        Area rectangleArea = new Area();
        double rArea = rectangleArea.rectangleArea(length, width);
        System.out.println("The area of your rectangle is "+ rArea);
        return rArea;
    }
}
