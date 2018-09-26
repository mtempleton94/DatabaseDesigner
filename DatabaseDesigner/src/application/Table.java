package application;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

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
	private Hyperlink addField;

	// text field for title updates
	private TextField titleTextField;
	
	// variables for timing mouse click
	double clickStartTime = 0;
	double clickEndTime = 0;
	double timeClickHeld = 0;
	boolean clickHeld = false;

	// positioning variables
	double cursorXPosition, cursorYPosition;
	double tableXPosition, tableYPosition;

	public Table() {

		// set the table id 
		this.setTableId(idCount.incrementAndGet());

		// styling for the table container
		this.setStyle("-fx-background-color: white;");
		this.setWidth(80);

		// set minimum size for the table container
		//this.setMinHeight(100);

		// set the default table name based in the id
		this.setTableName("Table " + idCount.toString());

		// add the table id [temporary - will be removed later]
		tableIdText = new Text();
		tableIdText.setFont(new Font(20));
		tableIdText.setWrappingWidth(200);
		tableIdText.setTextAlignment(TextAlignment.JUSTIFY);
		tableIdText.setText(this.getTableName());

		// add event handler for when title is selected
		tableIdText.setOnMousePressed(titlePressHandler);
		tableIdText.setOnMouseReleased(titleReleaseHandler);

		// add change listener to title text field
		titleTextField = new TextField();
		titleTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				titleFocusChange(newValue);
			}
		});

		this.getChildren().add(tableIdText);
		
		// add button for adding field
		addNewFieldButton(this);

		// add event handlers
		this.setOnMousePressed(mousePressed);
		this.setOnMouseDragged(mouseDragged);
		
		table = this;

	}

	/**
	 * Append the Add Field button to the table
	 * @param table current table
	 */
	private void addNewFieldButton(Table table) {
		addField = new Hyperlink();
		addField.setText("Add Field");
		addField.setOnAction(addFieldSelected);
		addField.setMinWidth(200);
		addField.setPrefWidth(80);
		table.getChildren().add(addField);
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
	 * Add a new field to a table
	 * @return Nothing.
	 */
	private void addNewField() {
		TableField field = new TableField();
		table.getChildren().add(field);
	}

	/**
	 * Table title is selected for editing.
	 * Display text entry field
	 * @return Nothing.
	 */
	private void editTitleText() {

		// remove the table title text
		Node tableTitle = table.getChildren().get(0);
		table.getChildren().remove(tableTitle);

		// add the text field for editing the title
		table.getChildren().add(0, titleTextField);
		titleTextField.setText(getTableName());

		// give the text field focus
		titleTextField.selectAll();
		titleTextField.requestFocus();

	}

	/**
	 * Table title focus change
	 * Display text entry field
	 * @param table current table
	 * @return Nothing.
	 */
	public void titleFocusChange(Boolean hasFocus) {

        if (hasFocus)
        {
            titleTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

            	@Override
                public void handle(KeyEvent event) {

            		// escape or enter pressed
	                if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.ESCAPE) {

	                	// update the table name
	                	setTableName(titleTextField.getText());
	            		tableIdText.setText(getTableName());

	                	// remove the title text field
	                	table.getChildren().remove(titleTextField);

	                	// add the title text
	                	table.getChildren().add(0, tableIdText);
	                }
            	}
            });
        }
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
    EventHandler<MouseEvent> titlePressHandler = new EventHandler<MouseEvent>() {

    	@Override
    	public void handle(MouseEvent e) {

    		// start timing length of mouse press
    		clickStartTime = System.nanoTime();
    		clickHeld = true;

    	}
    };

    // event handler for mouse release on table title
    EventHandler<MouseEvent> titleReleaseHandler = new EventHandler<MouseEvent>() {

    	@Override
    	public void handle(MouseEvent e) {

    		// check if mouse is being held
    		if(clickHeld) {
    			clickEndTime = System.nanoTime();
    			clickHeld = false;
    	    }

    	    // check length of mouse press to determine if user is editing
    	    // title or moving the table
    	    timeClickHeld = (clickEndTime - clickStartTime) / Math.pow(10,9);
    	    if(timeClickHeld < 0.2) {
    	    	editTitleText();
    	    }
    	}
    };

	EventHandler<ActionEvent> addFieldSelected = new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent e) {

	    	// remove the add field link while the new field is added
	    	table.getChildren().remove(addField);

	    	// add the new field
	    	addNewField();

	    	// add the new field button again
	    	addNewFieldButton(table);

	    }
	};
}
