package application;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
	Table table;
	private int tableId;
	String tableName;
	Text tableIdText;
	
	// positioning variables
	double cursorXPosition, cursorYPosition;
	double tableXPosition, tableYPosition;

	public Table() {

		// set the table id 
		this.setTableId(idCount.incrementAndGet());

		// styling for the table container
		this.setStyle("-fx-background-color: white;");
		this.setWidth(80);

		// set the default table name based in the id
		this.setTableName("Table " + idCount.toString());

		// add the table id [temporary - will be removed later]
		Text tableIdText = new Text();
		tableIdText.setFont(new Font(20));
		tableIdText.setWrappingWidth(200);
		tableIdText.setTextAlignment(TextAlignment.JUSTIFY);
		tableIdText.setText(this.getTableName());
		this.tableIdText = tableIdText;

		// add event handler for when title is selected
		//tableIdText.addEventHandler(MouseEvent.MOUSE_CLICKED, titleSelectHandler);
		tableIdText.setOnMouseClicked(titleSelectHandler);

		this.getChildren().add(tableIdText);
		
		// add event handlers
		this.setOnMousePressed(mousePressed);
		this.setOnMouseDragged(mouseDragged);
		
		table = this;

	}

	/**
	 * Get table identification number
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

	/**
	 * Get table name
	 * @return tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Set the table name
	 * @param tableName new table name
	 * @return Nothing.
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Handlers for mouse events
	 */
    EventHandler<MouseEvent> mousePressed = new EventHandler<MouseEvent>() {

    	@Override
		public void handle(MouseEvent event) {

			// get the selected table
			VBox table = (VBox)event.getSource();
   	
			// get the cursor position
			cursorXPosition = event.getSceneX();
			cursorYPosition = event.getSceneY();
 
			// get the current table position
			tableXPosition = table.getTranslateX();
			tableYPosition = table.getTranslateY();

    	}
    };
         
    EventHandler<MouseEvent> mouseDragged = new EventHandler<MouseEvent>() {

    	@Override
        public void handle(MouseEvent event) {

    		// get the dragged table
    		VBox table = (VBox)event.getSource();

    		/*
    		 * Determine the difference between the current cursor
    		 * position and the cursor position when the table
    		 * was initially selected.
    		 */
    		double cursorXOffset = event.getSceneX() - cursorXPosition;
    		double cursorYOffset = event.getSceneY() - cursorYPosition;

    		/* Move the table based on its original position
    		 * and the cursor offset.
    		 */
    		double tableXMovement = tableXPosition + cursorXOffset;
    		double tableYMovement = tableYPosition + cursorYOffset;

    		// move the table to the new position
    		table.setTranslateX(tableXMovement);
    		table.setTranslateY(tableYMovement);
    	}
    };
    
    // event handler for selecting the table title
    EventHandler<MouseEvent> titleSelectHandler = new EventHandler<MouseEvent>() {

    	@Override
    	public void handle(MouseEvent e) {
    		System.out.println("Title was selected");

    		// stop displaying the static title text
    		//table.getChildren().remove(tableIdText);

    	}
    };
}
