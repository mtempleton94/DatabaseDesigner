package application;

import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
* <h1>Tool Pane</h1>
* Tool pane displays a list of tools used
* to create various elements in the database 
* schema design.
*
* @author  Mitchell Templeton
* @version 1.0
* @since   2018-08-28
*/
public class ToolPane extends HBox {
        
    public ToolPane() {
        this.setStyle("-fx-background-color: #2a2a2b;");
        this.setMinHeight(100);

        // add bottom border to separate tools from designer pane
        this.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, Color.TRANSPARENT, Color.WHITE, Color.TRANSPARENT,
                BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
                CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
    }
    
	/**
	 * Add a button to the tool bar
	 * @param text Label to be displayed for the tool
	 * @return Created ToolButton.
	 */
    public ToolButton addToolButton(String text, DatabaseElement element) {
		ToolButton button = new ToolButton(text, element);
		this.getChildren().add(button);
		return button;
    }

}
