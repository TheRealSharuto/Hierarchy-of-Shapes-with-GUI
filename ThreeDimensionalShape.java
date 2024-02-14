package com.example.belton_shara_project2;
/*
File name: ThreeDimensionalShape
Date: February 4, 2024
Author: Shara Belton
Purpose: Initializes the ThreeDimensionalShape, Volume, Cone, Cylinder, Sphere,
and Torus classes. Each class has its own set of attributes and a calculateVolume
method. An object of the Volume class needs to be created after an object of one of the
shapes are created in order for the volume of that shape to be calculated. Most attributes
are here just for Object attribute examples to display - and for the UML diagram.
 */
public class ThreeDimensionalShape extends Shape {
    private Volume volume; //Three dimensionals HAS-A volume
    private double height;
    private double radius;
    private final int numberOfDimensions = 3;
    private double surfaceArea;
}

class Volume {
    private double value;
    Volume() {

    }
    public double coneVolume(double radius, double height) {
        return Math.PI * Math.pow(radius, 2) * (height/3);
    }
    public double cubeVolume(double side) {
        return Math.pow(side, 3);
    }
    public double cylinderVolume(double radius, double height) {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    public double sphereVolume(double radius) {
        return ((double) 4 /3)*Math.PI*Math.pow(radius, 3);
    }

    public double torusVolume(double majorRadius, double minorRadius) {
        return (Math.PI * Math.pow(minorRadius, 2)*(2 * Math.PI * majorRadius));
    }
}

class Sphere extends ThreeDimensionalShape {
    private double surfaceArea;
    private final double radius;
    private double diameter;
    private double circumference;
    private double volume;

    public Sphere(double radius) {
        this.radius = radius;
    }

    public double calculateVolume() {
        Volume sphereVolume = new Volume();
        double sVolume = sphereVolume.sphereVolume(radius);
        System.out.println("The volume of your sphere is "+ sVolume);
        return sVolume;
    }
}

class Cube extends ThreeDimensionalShape {
    private final int faces = 6;
    private final int edges = 12;
    private final int vertices = 8;

    private final double edgeLength;

    public Cube(double edgeLength) {
        this.edgeLength = edgeLength;
    }
    public double calculateVolume() {
        Volume cubeVolume = new Volume();
        double cVolume = cubeVolume.cubeVolume(edgeLength);
        System.out.println("The volume of your cube is "+ cVolume);
        return cVolume;
    }
}

class Cone extends ThreeDimensionalShape {
    private final double radius;
    private final double height;
    private double slantHeight;
    private final int faces = 1;
    private double surfaceArea;

    Cone(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }
    public double calculateVolume() {
        Volume coneVolume = new Volume();
        double coVolume = coneVolume.coneVolume(radius, height);
        System.out.println("The volume of your cone is "+ coVolume);
        return coVolume;
    }
}

class Cylinder extends ThreeDimensionalShape {
    private final double radius;
    private final double height;
    private double surfaceArea;
    private double lateralSurfaceArea;

    public Cylinder(double radius, double height){
        this.radius = radius;
        this.height = height;
    }
    public double calculateVolume() {
        Volume cylinderVolume = new Volume();
        double cylVolume = cylinderVolume.cylinderVolume(radius, height);
        System.out.println("The volume of your cylinder is "+ cylVolume);
        return cylVolume;
    }
}

class Torus extends ThreeDimensionalShape {
    private final double minorRadius;
    private final double majorRadius;

    public Torus(double majorRadius, double minorRadius) {
        this.majorRadius = majorRadius;
        this.minorRadius = minorRadius;
    }

    public double calculateVolume() {
        Volume torusVolume = new Volume();
        double tVolume = torusVolume.torusVolume(majorRadius, minorRadius);
        System.out.println("The volume of your torus is "+ tVolume);
        return tVolume;
    }
}