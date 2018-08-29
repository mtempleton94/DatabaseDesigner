package application;

import javafx.scene.layout.HBox;

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
        this.setStyle("-fx-background-color: cyan;");
    }
    
	/**
	 * Add a button to the tool bar
	 * @param text Label to be displayed for the tool
	 * @return Created ToolButton.
	 */
    public ToolButton addToolButton(String text) {
		ToolButton button = new ToolButton(text);
		this.getChildren().add(button);
		return button;
    }

}
