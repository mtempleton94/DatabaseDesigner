package application;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	
	public ToolButton(String text) {
		
		// button container
	    this.setPadding(new Insets(10));
	    this.setSpacing(8);

		// icon
		Rectangle rect = new Rectangle();
		int squareSize = 30;
		rect.setX(20 - (squareSize / 2));
		rect.setY(20 - (squareSize / 2));
		rect.setWidth(squareSize);
		rect.setHeight(squareSize);
		rect.setFill(Color.BLACK);
		this.getChildren().add(rect);
		
		// label
	    Text title = new Text(text);
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    this.getChildren().add(title);
		
	}
}
