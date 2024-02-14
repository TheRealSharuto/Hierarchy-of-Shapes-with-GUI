/*
File name: DrawShape
Date: February 4, 2024
Author: Shara Belton
Purpose: Initializes the DrawShape class, that extends Application. This class
deals with all shape drawings and creation of the panel, scene, gridpane, and canvas.
All these classes within the JavaFX class work together to allow user interaction with
dropdowns and buttons - which ultimately create shapes that vary in size according to
the dimensions that the user enter. External classes are used to display the volume or
area of the generated shape.
 */
package com.example.belton_shara_project2;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class DrawShape extends Application {
    private BorderPane borderPane;
    private Canvas canvas;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        ComboBox<String> shapesComboBox = new ComboBox<>();
        shapesComboBox.getItems().addAll(
                "Circle",
                "Rectangle",
                "Square",
                "Triangle",
                "Cone",
                "Cube",
                "Cylinder",
                "Sphere",
                "Torus"
        );
        shapesComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            updateInputFields(newValue);
        });
        HBox topContainer = new HBox(shapesComboBox);
        topContainer.setAlignment(Pos.CENTER);

        canvas = new Canvas(700, 700); // Initialize the canvas with preferred size
        Pane drawingPane = new Pane(canvas); // Wrap the canvas in a pane area

        borderPane = new BorderPane();
        borderPane.setTop(topContainer);
        borderPane.setCenter(drawingPane); // Set the drawing pane in the center

        Scene scene = new Scene(borderPane, 700, 700);
        primaryStage.setTitle("Drawing Shapes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateInputFields(String shape) {
        GridPane inputFields = new GridPane();
        inputFields.setAlignment(Pos.CENTER);
        inputFields.setHgap(10);
        inputFields.setVgap(10);

        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        Button enterButton = new Button("Enter");

        GraphicsContext gc = canvas.getGraphicsContext2D();
        System.out.println("Graphics Context created.");

            switch (shape) {

                case "Circle" -> {

                    // Clear existing content from inputFields GridPane
                    inputFields.getChildren().clear();
                    // Input fields
                    inputFields.add(new Label("Radius:"), 0, 0);
                    inputFields.add(textField1, 1, 0);
                    inputFields.add(enterButton, 1, 1);

                    borderPane.setBottom(inputFields);

                    enterButton.setOnAction(event -> {
                        try {
                            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                            double radius = Double.parseDouble(textField1.getText());
                            System.out.println("radius pulled.");
                            Circle circle = new Circle(radius);
                            System.out.println("Circle radius: " + radius);

                            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                            System.out.println("Canvas cleared.");

                            double diameter = 2 * radius;
                            // Calculate the center position for the circle
                            double centerX = canvas.getWidth() / 2;
                            double centerY = canvas.getHeight() / 2;

                            // Calculate top-left corner based on the center position
                            double topLeftX = centerX - radius;
                            double topLeftY = centerY - radius;
                            gc.strokeOval(topLeftX, topLeftY, diameter, diameter); // Draw the circle

                            Double area = circle.calculateArea();
                            String areaText = String.format("Area: %.2f", area); // Format the area to two decimal places
                            gc.fillText(areaText, 10, 20); // Adjust (10, 20) to position the text appropriately
                        } catch (NumberFormatException e) {
                            positiveDoubleCheck();
                        }
                    });
                }
                case "Sphere" -> {
                    // Clear existing content from inputFields GridPane
                    inputFields.getChildren().clear();
                    // Input fields
                    inputFields.add(new Label("Radius:"), 0, 0);
                    inputFields.add(textField1, 1, 0);
                    inputFields.add(enterButton, 1, 1);

                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                    System.out.println("Canvas cleared.");

                    borderPane.setBottom(inputFields);

                    enterButton.setOnAction(event -> {
                        try {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                        double radius = Double.parseDouble(textField1.getText());
                        Sphere sphere = new Sphere(radius);

                        double centerX = canvas.getWidth() / 2;
                        double centerY = canvas.getHeight() / 2;
                        double topLeftX = centerX - radius;
                        double topLeftY = centerY - radius;

                        // Create a radial gradient to simulate light on the sphere
                        RadialGradient gradient = new RadialGradient(0, 0, centerX, centerY - radius / 3, radius,
                                false, CycleMethod.NO_CYCLE,
                                new Stop(0, Color.WHITE),
                                new Stop(1, Color.DARKGRAY));

                        gc.setFill(gradient);
                        gc.fillOval(topLeftX, topLeftY, 2 * radius, 2 * radius); // Use fillOval for a filled circle with gradient

                            Double volume = sphere.calculateVolume();
                            String volumeText = String.format("Volume: %.2f", volume);
                            gc.fillText(volumeText, 10, 20);

                        System.out.println("Sphere radius: " + radius);
                        } catch (NumberFormatException e) {
                            positiveDoubleCheck();
                        }
                    });
                }
                case "Rectangle" -> {
                    // Clear existing content from inputFields GridPane
                    inputFields.getChildren().clear();

                    inputFields.add(new Label("Width:"), 0, 0);
                    inputFields.add(textField1, 1, 0);
                    inputFields.add(new Label("Height:"), 0, 1);
                    inputFields.add(textField2, 1, 1);
                    inputFields.add(enterButton, 1, 2);

                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                    System.out.println("Canvas cleared.");

                    borderPane.setBottom(inputFields);

                    enterButton.setOnAction(event -> {
                        try {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                        double width = Double.parseDouble(textField1.getText());
                        double height = Double.parseDouble(textField2.getText());
                        Rectangle rectangle = new Rectangle(height, width);

                        // Calculate the center position for the rectangle
                        double centerX = canvas.getWidth() / 2;
                        double centerY = canvas.getHeight() / 2;

                        // Calculate top-left corner based on the center position
                        double topLeftX = centerX - (width / 2);
                        double topLeftY = centerY - (height / 2);

                        gc.strokeRect(topLeftX, topLeftY, width, height);

                        rectangle.calculateArea();
                        System.out.println("Rectangle width: " + width + ", length: " + height);

                            Double area = rectangle.calculateArea();
                            String areaText = String.format("Area: %.2f", area);
                            gc.fillText(areaText, 10, 20);
                        } catch (NumberFormatException e) {
                            positiveDoubleCheck();
                        }
                    });
                }
                case "Square" -> {
                    // Clear existing content from inputFields GridPane
                    inputFields.getChildren().clear();

                    inputFields.add(new Label("Side Length:"), 0, 0);
                    inputFields.add(textField1, 1, 0);
                    inputFields.add(enterButton, 1, 1);

                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                    System.out.println("Canvas cleared.");

                    borderPane.setBottom(inputFields);


                    enterButton.setOnAction(event -> {
                        try {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                        double side = Double.parseDouble(textField1.getText());
                        Square square = new Square(side);
                        // Calculate the center position for the square
                        double centerX = canvas.getWidth() / 2;
                        double centerY = canvas.getHeight() / 2;

                        // Calculate top-left corner based on the center position
                        double topLeftX = centerX - (side / 2);
                        double topLeftY = centerY - (side / 2);

                        gc.strokeRect(topLeftX, topLeftY, side, side); // Draw the square centered

                            Double area = square.calculateArea();
                        String areaText = String.format("Area: %.2f", area);
                            gc.fillText(areaText, 10, 20);

                        System.out.println("Square side: " + side);
                        } catch (NumberFormatException e) {
                            positiveDoubleCheck();
                        }
                    });
                }
                case "Cube" -> {
                    // Clear existing content from inputFields GridPane
                    inputFields.getChildren().clear();

                    inputFields.add(new Label("Length:"), 0, 0);
                    inputFields.add(textField1, 1, 0);
                    inputFields.add(enterButton, 1, 1);

                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                    System.out.println("Canvas cleared.");

                    borderPane.setBottom(inputFields);


                    enterButton.setOnAction(event -> {
                        try {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                        double length = Double.parseDouble(textField1.getText());
                        Cube cube = new Cube(length);
                        double side = Double.parseDouble(textField1.getText()); // Parse side length
                        drawCube(gc, 350, 350, side); // Center of the canvas and side length

                            Double volume = cube.calculateVolume();
                            String volumeText = String.format("Volume: %.2f", volume);
                            gc.fillText(volumeText, 10, 20);

                        System.out.println("Cube side: " + length);
                        } catch (NumberFormatException e) {
                            positiveDoubleCheck();
                        }
                    });
                }
                case "Triangle" -> {
                    // Clear existing content from inputFields GridPane
                    inputFields.getChildren().clear();

                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                    System.out.println("Canvas cleared.");

                    inputFields.add(new Label("Base:"), 0, 0);
                    inputFields.add(textField1, 1, 0);
                    inputFields.add(new Label("Height:"), 0, 1);
                    inputFields.add(textField2, 1, 1);
                    inputFields.add(enterButton, 1, 2);

                    borderPane.setBottom(inputFields);

                    enterButton.setOnAction(event -> {
                        try {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        double base = Double.parseDouble(textField1.getText());
                        double height = Double.parseDouble(textField2.getText());
                        Triangle triangle = new Triangle(base, height);
                        drawTriangle(gc, 350, 350, base, height);

                            Double area = triangle.calculateArea();
                            String areaText = String.format("Area: %.2f", area);
                            gc.fillText(areaText, 10, 20);
                        } catch (NumberFormatException e) {
                            positiveDoubleCheck();
                        }
                    });
                }
                case "Cone" -> {
                    // Clear existing content from inputFields GridPane
                    inputFields.getChildren().clear();

                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                    System.out.println("Canvas cleared.");

                    inputFields.add(new Label("Radius:"), 0, 0);
                    inputFields.add(textField1, 1, 0);
                    inputFields.add(new Label("Height:"), 0, 1);
                    inputFields.add(textField2, 1, 1);
                    inputFields.add(enterButton, 1, 2);

                    borderPane.setBottom(inputFields);

                    enterButton.setOnAction(event -> {
                        try {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                        double radius = Double.parseDouble(textField1.getText());
                        double height = Double.parseDouble(textField2.getText());
                        Cone cone = new Cone(radius, height);
                        drawCone(gc, 350, 350, radius, height); // Center of the canvas, radius, and height

                            Double volume = cone.calculateVolume();
                            String volumeText = String.format("Volume: %.2f", volume);
                            gc.fillText(volumeText, 10, 20);
                        } catch (NumberFormatException e) {
                            positiveDoubleCheck();
                        }
                    });
                }
                case "Cylinder" -> {
                    // Clear existing content from inputFields GridPane
                    inputFields.getChildren().clear();

                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                    System.out.println("Canvas cleared.");

                    inputFields.add(new Label("Radius:"), 0, 0);
                    inputFields.add(textField1, 1, 0);
                    inputFields.add(new Label("Height:"), 0, 1);
                    inputFields.add(textField2, 1, 1);
                    inputFields.add(enterButton, 1, 2);

                    borderPane.setBottom(inputFields);

                    enterButton.setOnAction(event -> {
                        try {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                        double radius = Double.parseDouble(textField1.getText());
                        double height = Double.parseDouble(textField2.getText());
                        Cylinder cylinder = new Cylinder(radius, height);

                        drawCylinder(gc, 350, 350, radius, height); // Center of the canvas, radius, and height

                            Double volume = cylinder.calculateVolume();
                            String volumeText = String.format("Volume: %.2f", volume);
                            gc.fillText(volumeText, 10, 20);
                        } catch (NumberFormatException e) {
                            positiveDoubleCheck();
                        }
                    });
                }
                case "Torus" -> {
                    // Clear existing content from inputFields GridPane
                    inputFields.getChildren().clear();

                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
                    System.out.println("Canvas cleared.");

                    inputFields.add(new Label("Minor Radius:"), 0, 0);
                    inputFields.add(textField1, 1, 0);
                    inputFields.add(new Label("Major Radius:"), 0, 1);
                    inputFields.add(textField2, 1, 1);
                    inputFields.add(enterButton, 1, 2);

                    borderPane.setBottom(inputFields);

                    enterButton.setOnAction((event -> {
                        try {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        double majorRadius = Double.parseDouble(textField2.getText());
                        double minorRadius = Double.parseDouble(textField1.getText());
                        /* ALERT POP UP NEEDED HERE */
                        if (minorRadius >= majorRadius) {
                            // Show an alert popup
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Invalid Input");
                            alert.setHeaderText("Minor radius must be smaller than the major radius.");
                            alert.setContentText("Please enter a valid minor radius that is smaller than the major radius.");
                            alert.showAndWait();
                        } else {
                            // Proceed with drawing the torus
                            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear previous drawings
                            // Your code to draw the torus based on the entered radii
                            Torus torus = new Torus(majorRadius, minorRadius);

                            drawTorus(gc, 350, 350, majorRadius, minorRadius); // Center of the canvas, major and minor radius


                            Double volume = torus.calculateVolume();
                            String volumeText = String.format("Volume: %.2f", volume);
                            gc.fillText(volumeText, 10, 20);
                        }
                        } catch (NumberFormatException e) {
                            positiveDoubleCheck();
                        }
                    }));

                }
            }
    }

        private void drawCube (GraphicsContext gc,double centerX, double centerY, double side){
            double halfSide = side / 2;
            double depth = side / 2; // Depth for 3D effect

            // Calculate vertices of the cube
            double[] frontTopLeft = {centerX - halfSide, centerY - halfSide};
            double[] frontTopRight = {centerX + halfSide, centerY - halfSide};
            double[] frontBottomLeft = {centerX - halfSide, centerY + halfSide};
            double[] frontBottomRight = {centerX + halfSide, centerY + halfSide};

            double[] backTopLeft = {centerX - halfSide + depth, centerY - halfSide - depth};
            double[] backTopRight = {centerX + halfSide + depth, centerY - halfSide - depth};
            double[] backBottomLeft = {centerX - halfSide + depth, centerY + halfSide - depth};
            double[] backBottomRight = {centerX + halfSide + depth, centerY + halfSide - depth};

            // Draw front face
            gc.strokeLine(frontTopLeft[0], frontTopLeft[1], frontTopRight[0], frontTopRight[1]);
            gc.strokeLine(frontTopLeft[0], frontTopLeft[1], frontBottomLeft[0], frontBottomLeft[1]);
            gc.strokeLine(frontBottomRight[0], frontBottomRight[1], frontTopRight[0], frontTopRight[1]);
            gc.strokeLine(frontBottomRight[0], frontBottomRight[1], frontBottomLeft[0], frontBottomLeft[1]);

            // Draw back face
            gc.strokeLine(backTopLeft[0], backTopLeft[1], backTopRight[0], backTopRight[1]);
            gc.strokeLine(backTopLeft[0], backTopLeft[1], backBottomLeft[0], backBottomLeft[1]);
            gc.strokeLine(backBottomRight[0], backBottomRight[1], backTopRight[0], backTopRight[1]);
            gc.strokeLine(backBottomRight[0], backBottomRight[1], backBottomLeft[0], backBottomLeft[1]);

            // Connect front and back faces
            gc.strokeLine(frontTopLeft[0], frontTopLeft[1], backTopLeft[0], backTopLeft[1]);
            gc.strokeLine(frontTopRight[0], frontTopRight[1], backTopRight[0], backTopRight[1]);
            gc.strokeLine(frontBottomLeft[0], frontBottomLeft[1], backBottomLeft[0], backBottomLeft[1]);
            gc.strokeLine(frontBottomRight[0], frontBottomRight[1], backBottomRight[0], backBottomRight[1]);
        }

        private void drawCone (GraphicsContext gc,double centerX, double bottomY, double radius, double height){
            // Draw the base of the cone
            gc.strokeOval(centerX - radius, bottomY - radius, 2 * radius, 2 * radius);

            // Calculate the apex of the cone
            double apexX = centerX;
            double apexY = bottomY - height;

            // Draw lines from the apex to the edge of the base
            gc.strokeLine(apexX, apexY, centerX - radius, bottomY);
            gc.strokeLine(apexX, apexY, centerX + radius, bottomY);

            // Optional: Draw a vertical line from the base's center to the apex if needed
            gc.strokeLine(centerX, bottomY, apexX, apexY);
        }

        private void drawCylinder (GraphicsContext gc,double centerX, double centerY, double radius, double height){
            // Calculate top and bottom centers of the cylinder
            double topCenterY = centerY - height / 2;
            double bottomCenterY = centerY + height / 2;

            // Draw top ellipse (top face of the cylinder)
            gc.strokeOval(centerX - radius, topCenterY - radius * 0.25, 2 * radius, radius * 0.5);

            // Draw bottom ellipse (bottom face of the cylinder)
            gc.strokeOval(centerX - radius, bottomCenterY - radius * 0.25, 2 * radius, radius * 0.5);

            // Draw side lines of the cylinder
            gc.strokeLine(centerX - radius, topCenterY, centerX - radius, bottomCenterY);
            gc.strokeLine(centerX + radius, topCenterY, centerX + radius, bottomCenterY);

            // Optional: Fill top ellipse to simulate lighting/shading
            gc.setFill(new RadialGradient(0, 0, centerX, centerY - radius / 3, radius,
                    false, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.WHITE),
                    new Stop(1, Color.DARKGRAY)));
            gc.fillOval(centerX - radius, topCenterY - radius * 0.25, 2 * radius, radius * 0.5);
        }

        private void drawTorus (GraphicsContext gc,double centerX, double centerY, double majorRadius,
        double minorRadius){
            double outerDiameter = 2 * majorRadius;
            double innerDiameter = 2 * (majorRadius - minorRadius);

            // Create a radial gradient for the outer circle to simulate basic shading
            RadialGradient gradientOuter = new RadialGradient(0, 0, centerX, centerY - majorRadius, majorRadius,
                    false, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.DARKGRAY),
                    new Stop(1, Color.BLACK));

            // Apply gradient and draw the outer circle
            gc.setFill(gradientOuter);
            gc.fillOval(centerX - majorRadius, centerY - majorRadius, outerDiameter, outerDiameter);

            // Calculate the position and size for the "hole" in the middle
            double holeX = centerX - (majorRadius - minorRadius);
            double holeY = centerY - (majorRadius - minorRadius);

            // Overdraw the inner part with a smaller ellipse to simulate the hole, using background color
            // Assume the background color is white; adjust if your background is different
            gc.setFill(Color.WHITE);
            gc.fillOval(holeX, holeY, innerDiameter, innerDiameter);

            // Optionally, draw an inner shadow or gradient to enhance the 3D effect
            RadialGradient gradientInner = new RadialGradient(0, 0, centerX, centerY, (majorRadius - minorRadius),
                    false, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.rgb(255, 255, 255, 0.2)),  // Slightly transparent white
                    new Stop(1, Color.rgb(255, 255, 255, 0)));  // Fully transparent

            gc.setFill(gradientInner);
            gc.fillOval(holeX, holeY, innerDiameter, innerDiameter);
        }

        private void drawTriangle (GraphicsContext gc,double centerX, double centerY, double base, double height){
            double halfBase = base / 2;

            // Calculate vertices of the triangle
            double[] xPoints = {centerX - halfBase, centerX + halfBase, centerX};
            double[] yPoints = {centerY + height / 2, centerY + height / 2, centerY - height / 2};

            // Draw the triangle
            gc.strokePolygon(xPoints, yPoints, 3);
        }


        private static void positiveDoubleCheck() {
            // Show an alert popup
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Type Exception");
            alert.setContentText("You must only enter positive numbers. Decimals are allowed.");
            alert.showAndWait();
        }

    }
