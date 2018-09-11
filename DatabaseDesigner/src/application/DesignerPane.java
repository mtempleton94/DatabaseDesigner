package application;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

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
		this.setStyle("-fx-background-color: #3d3e3f;");

		// add a grid to the pane
		double paneMaxSize = 2000;
		for(int i = 0; i <= paneMaxSize; i += 20) {

			// vertical line
			Line verticalLine = new Line();
			verticalLine.setStartX(i);
			verticalLine.setStartY(0.0);
			verticalLine.setEndX(i);
			verticalLine.setEndY(paneMaxSize);
			verticalLine.setStyle("-fx-stroke: #4b4d4f;");
			this.getChildren().add(verticalLine);

			// horizontal line
			Line horizontalLine = new Line();
			horizontalLine.setStartX(0.0);
			horizontalLine.setStartY(i);
			horizontalLine.setEndX(paneMaxSize);
			horizontalLine.setEndY(i);
			horizontalLine.setStyle("-fx-stroke: #4b4d4f;");
			this.getChildren().add(horizontalLine);
		}

	}
	
	/**
	 * Create a new table object and display it in the 
	 * designer area
	 * @return Table new table.
	 */
	public Table createTable() {
		Table table = new Table();
		this.getChildren().add(table);
		return table;
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
