package application;
	//Credit to docs.oracle.com
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import static java.lang.Math.random;

import java.util.Scanner;

import javax.swing.JOptionPane;



public class Main extends Application { //creates a public class known as Main and inherits the objects and methods of the Application class.
	@Override //override Main
	public void start(Stage primaryStage) { //create a public method known as start that returns nothing and pass it primaryStage which is of type Stage.
		
		
		Scanner scan = new Scanner(System.in);
		String a;
		a = JOptionPane.showInputDialog ( "put your message here" ); 
        
		int numberofboxes = Integer.parseInt (a); 
        		
        		
			Group root = new Group(); //create a new Group called root. This is the parent node.
			 Scene scene = new Scene(root, 800, 600, Color.WHITE); //create a new scene interface for the group root parent node. Make it 800 pixels by 600 pixels and make it black
		        primaryStage.setScene(scene); //Construct the primary stage using the platform and set it as scene.
		        
		        
		        
		        primaryStage.show(); //Show the stage
		        
		        Group polys = new Group(); //Create a new group called circles. These will be child nodes
		        polys.setEffect(new BoxBlur(0,0,50)); //Takes the circle group and sets an effect of box blur to blur the circles. Blur a width of 10 and a height of 10, these are doubles, then run for 3 iterations to blur.
		        for (int i = 0; i < numberofboxes; i++) { //for loop to create 30 circles
		        	Polygon poly = new Polygon();
		        	poly.getPoints().addAll(new Double[]{ //docs.oracle.com. Creates a polygon.
		        	    0.0, 0.0,
		        	    0.0, 70.0,
		        	    70.0, 70.0,
		        	    70.0, 0.0,
		        	    0.0, 0.0}); //create a new circle with a radius of 150, it is white with an opacity of .05
		           poly.setStrokeType(StrokeType.INSIDE); //create a border outside of the circle.
		           poly.setStroke(Color.web("white", 0.16)); //the border is white and has an opacity of .16
		           poly.setStrokeWidth(4); //the width of the border is 4 pixels
		           polys.getChildren().add(poly); //add the circle to the children using the getChildren method
		        }
		        
		        Rectangle colors = new Rectangle(scene.getWidth(), scene.getHeight(), //create a rectangle called colors, use the scene's width and height dimensions with a getter method
		        	     new LinearGradient(1f, 0f, 0f, 1f, true, CycleMethod.REPEAT, new //create a lineargradient of colors that starts in the lower left hand corner and ends in the upper right hand color. The size of the gradient is proportional to the size of the rectangle. The No Cycle means the colors will not cycle back through.
		        	         
		        	    		 
		        	    		 Stop[]{ //this stop indicates what the color should be at each point
		        	            new Stop(0, Color.RED),
		        	            new Stop(0.14, Color.WHITE),
		        	            new Stop(0.28, Color.BLUE),
		        	            new Stop(0.43, Color.RED),
		        	            new Stop(0.57, Color.WHITE),
		        	            new Stop(0.71, Color.BLUE), 
		        	            new Stop(0.85, Color.RED),
		        	            new Stop(0.95, Color.WHITE),
		        	            new Stop(1, Color.BLUE),}));
		        	colors.widthProperty().bind(scene.widthProperty()); //adjust the size of the gradient to the width of the size of the scene.
		        	colors.heightProperty().bind(scene.heightProperty());//adjust the size of the gradient to the height of the size of the scene.
		        	Group blendModeGroup = 
		        		    new Group(new Group(new Rectangle(scene.getWidth(), scene.getHeight(),
		        		        Color.WHITE), polys), colors); //create a new Group called blendModeGroup that incorporates the rectangle, circls, and colors gradient
		        		colors.setBlendMode(BlendMode.OVERLAY); // blend the circles, colors, and rectangle together
		        		root.getChildren().add(blendModeGroup); //add these new blended objects to the scene as a child of the root node.
		        		
		        		
		        		Timeline timeline = new Timeline(); //create a new time line object called timeline
		        		for (Node poly: polys.getChildren()) { // each child node circle through to the max number of children nodes
		        		    timeline.getKeyFrames().addAll( //animation is done with a timeline. Add two keyframes locations. So, where is the circle going to be at a certain timeline keyframe. Define target values at a specific point in time.
		        		        new KeyFrame(Duration.ZERO, // set start position at 0
		        		            new KeyValue(poly.translateXProperty(), random() * 800), //translate the circles starting at 0 seconds 
		        		            new KeyValue(poly.translateYProperty(), random() * 600)
		        		        ),
		        		        new KeyFrame(new Duration(40000), // //Since we use two keyframes, one at 0 and one at 40 seconds, we will animate until 40 seconds is up.
		        		            new KeyValue(poly.translateXProperty(), random() * 800), // translate until 40 seconds is up.
		        		            new KeyValue(poly.translateYProperty(), random() * 600)
		        		        )
		        		    );
		        		}
		        		// play 40s of animation
		        		timeline.play(); //play the timeline and translations
		        
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
