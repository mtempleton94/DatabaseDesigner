package application;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
* <h1>Table</h1>
* Table class to store database tables and fields.
*
* @author  Mitchell Templeton
* @version 1.0
* @since   2018-08-29
*/
public class Table extends VBox {
	
	private static final AtomicInteger idCount = new AtomicInteger(0); 
	private int tableId;
	String name;
	
	public Table() {
		
		// set the table id 
		setTableId(idCount.incrementAndGet());
		
		// rectangle to hold table contents
		Rectangle rect = new Rectangle();
		int squareSize = 60;
		rect.setWidth(squareSize);
		rect.setHeight(squareSize);
		rect.setFill(Color.BLACK);
		this.getChildren().add(rect);
		
	}

	/**
	 * Add a table reference to the list in the
	 * explorer pane.
	 * @return tableId
	 */
	public int getTableId() {
		return tableId;
	}

	/**
	 * Set the table identification number 
	 * @param tableId new table id 
	 * @return Nothing.
	 */
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

}
