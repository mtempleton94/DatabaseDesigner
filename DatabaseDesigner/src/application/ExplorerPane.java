package application;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
* <h1>Explorer Pane</h1>
* Explorer pane displaying a list of tables
* created as part of the database design.
*
* @author  Mitchell Templeton
* @version 1.0
* @since   2018-08-28
*/
public class ExplorerPane extends VBox {
	
	List<Hyperlink> tables = new ArrayList<Hyperlink>();

	public ExplorerPane() {
		 
		// set layout
		this.setPadding(new Insets(10));
		this.setSpacing(8);
		
		// add list title
		Text title = new Text("Tables");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		this.getChildren().add(title);
	}
	
	/**
	 * Add a table reference to the list in the
	 * explorer pane.
	 * @param tableName
	 */
	public void addTable(String tableName) {
		// add table to list of tables
		tables.add(new Hyperlink(tableName));
	}
	
	/**
	 * Remove a table reference from the list
	 * in the explorer pane.
	 * @param tableName title of the table to be removed
	 * @return Nothing.
	 */
	public void removeTable(String tableName) {
		for (Hyperlink tableLink : tables) {
			if (tableLink.getText() == tableName) {
				tables.remove(tableLink);
				this.getChildren().remove(tableLink);
				break;
			}
		}
	}
	
	/**
	 * Redraw the list of tables in the 
	 * explorer pane.
	 * @return Nothing.
	 */
	public void updateTableExplorer() {
		// remove existing list of tables
		for (Hyperlink tableLink : tables) {
			this.getChildren().remove(tableLink);
		}
		
		// display list of tables
		for (Hyperlink tableLink : tables) {
			VBox.setMargin(tableLink, new Insets(0, 0, 0, 8));
			this.getChildren().add(tableLink);
		}
	}
}
