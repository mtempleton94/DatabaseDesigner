package application;

import javafx.scene.layout.Pane;

/**
* <h1>Designer Pane</h1>
* Pane where visual database schema
* design is built.
*
* @author  Mitchell Templeton
* @version 1.0
* @since   2018-08-28
*/
public class DesignerPane extends Pane {
	
	public DesignerPane() {
		this.setStyle("-fx-background-color: yellow;");
	}
	
	/**
	 * Create a new table object and display it in the 
	 * designer area
	 * @return Nothing.
	 */
	public void createTable() {
		Table table = new Table();
		this.getChildren().add(table);
	}
	
	/**
	 * Activate the tool for creating a new 
	 * relationship in the designer area
	 * @return Nothing.
	 */
	public void createRelationship() {
		System.out.println("[TODO] CREATE RELATIONSHIP");
	}
}
