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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import static java.lang.Math.random;



public class Main extends Application { //creates a public class known as Main and inherits the objects and methods of the Application class.
	@Override //override the method
	public void start(Stage primaryStage) { //create a public method known as start that returns nothing and pass it primaryStage which is of type Stage.
		
			Group root = new Group(); //create a new Group called root
			 Scene scene = new Scene(root, 800, 600, Color.BLACK); //create a new scene interface for the group root. Make it 800 pixels by 600 pixels and make it black
		        primaryStage.setScene(scene); //Construct the primary stage using hte platform and set it as scene.
		        
		        
		        
		        primaryStage.show(); //Show the stage
		        
		        Group circles = new Group(); //Create a new group called circles
		        circles.setEffect(new BoxBlur(10, 10, 3)); //Takes the circle group and sets an effect of box blur to blur the circles. Blur a width of 10 and a height of 10, these are doubles, then run for 3 iterations to blur.
		        for (int i = 0; i < 30; i++) { //for loop to create 30 circles
		           Circle circle = new Circle(150, Color.web("white", 0.05)); //create a new circle with a radius of 150, it is white with an opacity of .05
		           circle.setStrokeType(StrokeType.OUTSIDE); //create a border outside of the circle.
		           circle.setStroke(Color.web("white", 0.16)); //the border is white and has an opacity of .16
		           circle.setStrokeWidth(4); //the width of the border is 4 pixels
		           circles.getChildren().add(circle); //add the circle to the children using the getChildren method
		        }
		        
		        Rectangle colors = new Rectangle(scene.getWidth(), scene.getHeight(), //create a rectangle called colors, use the scene's width and height dimensions with a getter method
		        	     new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE, new //create a lineargradient of colors that starts in the lower left hand corner and ends in the upper right hand color. The size of the gradient is proportional to the size of the rectangle. The No Cycle means the colors will not cycle back through.
		        	         Stop[]{ //this stop indicates what the color should be at each point
		        	            new Stop(0, Color.web("#f8bd55")),
		        	            new Stop(0.14, Color.web("#c0fe56")),
		        	            new Stop(0.28, Color.web("#5dfbc1")),
		        	            new Stop(0.43, Color.web("#64c2f8")),
		        	            new Stop(0.57, Color.web("#be4af7")),
		        	            new Stop(0.71, Color.web("#ed5fc2")), 
		        	            new Stop(0.85, Color.web("#ef504c")),
		        	            new Stop(1, Color.web("#f2660f")),}));
		        	colors.widthProperty().bind(scene.widthProperty()); //adjust the size of the gradient to the width of the size of the scene.
		        	colors.heightProperty().bind(scene.heightProperty());//adjust the size of the gradient to the height of the size of the scene.
		        	Group blendModeGroup = 
		        		    new Group(new Group(new Rectangle(scene.getWidth(), scene.getHeight(),
		        		        Color.BLACK), circles), colors); //create a new Group called blendModeGroup that incorporates the rectangle, circls, and colors gradient
		        		colors.setBlendMode(BlendMode.OVERLAY); // blend the circles, colors, and rectangle together
		        		root.getChildren().add(blendModeGroup); //add these new blended objects to the scene as a child of the root node.
		        		
		        		
		        		Timeline timeline = new Timeline(); //create a new time line object called timeline
		        		for (Node circle: circles.getChildren()) { // each child node circle through to the max number of children nodes
		        		    timeline.getKeyFrames().addAll( //animation is done with a timeline. Add two keyframes locations. So, where is the circle going to be at a certain timeline keyframe. Define target values at a specific point in time.
		        		        new KeyFrame(Duration.ZERO, // set start position at 0
		        		            new KeyValue(circle.translateXProperty(), random() * 800), //translate the circles starting at 0 seconds 
		        		            new KeyValue(circle.translateYProperty(), random() * 600)
		        		        ),
		        		        new KeyFrame(new Duration(40000), // //Since we use two keyframes, one at 0 and one at 40 seconds, we will animate until 40 seconds is up.
		        		            new KeyValue(circle.translateXProperty(), random() * 800), // translate until 40 seconds is up.
		        		            new KeyValue(circle.translateYProperty(), random() * 600)
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
