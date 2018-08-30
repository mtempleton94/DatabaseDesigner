package application;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
* <h1>Tool Button</h1>
* Creates a button to be added to the tool
* bar. Each button consists of a label and 
* an icon.
*
* @author  Mitchell Templeton
* @version 1.0
* @since   2018-08-28
*/
public class ToolButton extends VBox {
	
	// store number of buttons created to help with positioning
	private static final AtomicInteger buttonCount = new AtomicInteger(0);

	public ToolButton(String text, DatabaseElement element) {

		// set button count
		buttonCount.incrementAndGet();
		
		// set button position and alignment
		this.setMinWidth(100);
		this.setMaxHeight(80);
		this.setTranslateY(10);
		this.setTranslateX(10 * buttonCount.intValue());
		this.setAlignment(Pos.CENTER);

		// set background and border of the button
		this.setStyle("-fx-background-color: #adafb2;" + "-fx-border-style: solid inside;"
	            + "-fx-border-width: 1;" + "-fx-border-color: white;");

		// add appropriate icon for button
		switch (element) {
			case TABLE:
				this.getChildren().add(createTableIcon());
				break;
			case RELATIONSHIP:
				this.getChildren().add(createRelationshipIcon());
				break;
			default:
				break;
		}
		
		// label
	    Text title = new Text(text);
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    this.getChildren().add(title);
		
	}

	/**
	 * Add an action to the button that enables the creation of
	 * a new element in the database designer pane.
	 * @param element Type of element to be created
	 * @param designerPane DesignerPane on which to create the element
	 * @return Nothing.
	 */
	public void addCreateAction(DatabaseElement element, DesignerPane designerPane) {

		// call the relevant DesignerPane element creation method when clicked.
		this.setOnMouseClicked((e)->
		{
        	// set method based on element
        	switch (element) {
			case TABLE:
				designerPane.createTable();
				break;
			case RELATIONSHIP:
				designerPane.createRelationship();
				break;
			default:
				break;
        	}
        });

		// set appearance on mouse entry
		this.setOnMouseEntered((e)->
		{
			this.setStyle("-fx-background-color: #efefff;" + "-fx-border-style: solid inside;"
					+ "-fx-border-width: 1;" + "-fx-border-color: white;");
		});

		// set appearance on mouse exit
		this.setOnMouseExited((e)->
		{
			this.setStyle("-fx-background-color: #adafb2;" + "-fx-border-style: solid inside;"
					+ "-fx-border-width: 1;" + "-fx-border-color: white;");
		});

	}

	/**
	 * Create the Icon to be displayed for the table button
	 * @return VBox newly created icon.
	 */
	public VBox createTableIcon() {

		int iconSize = 60;
		int titleHeight = 15;

		// create container for the icon
		VBox iconContainer = new VBox();

		// rectangle representing title
		Rectangle rect = new Rectangle();
		rect.setX(0);
		rect.setY(0);
		rect.setWidth(iconSize);
		rect.setHeight(titleHeight);
		rect.setFill(Color.web("#1e1f21"));

		// set container dimensions
		iconContainer.setPrefWidth(iconSize);
		iconContainer.setMaxWidth(iconSize);
		iconContainer.setPrefHeight(iconSize);

		// add a border
		iconContainer.setStyle("-fx-border-style: solid inside;"
	            + "-fx-border-width: 1;"
	            +"-fx-border-color: white;"
	            + "-fx-background-color: #3d3e3f;");

		// add the title panel
		iconContainer.getChildren().add(rect);

		// add lines representing rows in the table
		for (int i = 0; i < 40; i += 10) {
			Line horizontalLine = new Line();
			horizontalLine.setStartX(0);
			horizontalLine.setStartY(0);
			horizontalLine.setEndX(iconSize);
			horizontalLine.setEndY(0);
			horizontalLine.setTranslateY(i);
			horizontalLine.setStyle("-fx-stroke: white;");
			iconContainer.getChildren().add(horizontalLine);
		}

		return iconContainer;

	}

	/**
	 * Create the Icon to be displayed for the relationship button
	 * @return Pane newly created icon.
	 */
	public Pane createRelationshipIcon() {

		// create container for the icon
		Pane iconContainer = new Pane();
		iconContainer.setMinHeight(60);

		// create diagonal line to join two squares
		Line line = new Line();
		line.setStartX(10);
		line.setStartY(20);
		line.setEndX(90);
		line.setEndY(40);
		line.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
		iconContainer.getChildren().add(line);

		// create rectangles representing tables
		Rectangle rect = new Rectangle();
		rect.setX(5);
		rect.setY(15);
		rect.setWidth(10);
		rect.setHeight(10);
		rect.setStroke(Color.WHITE);
		rect.setStrokeWidth(2);
		rect.setFill(Color.web("#3d3e3f"));

		Rectangle rect2 = new Rectangle();
		rect2.setX(80);
		rect2.setY(35);
		rect2.setWidth(10);
		rect2.setHeight(10);
		rect2.setStroke(Color.WHITE);
		rect2.setStrokeWidth(2);
		rect2.setFill(Color.web("#3d3e3f"));

		iconContainer.getChildren().add(rect);
		iconContainer.getChildren().add(rect2);

		return iconContainer;

	}

}
