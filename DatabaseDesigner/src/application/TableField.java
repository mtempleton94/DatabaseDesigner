package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TableField extends HBox {
	
	TableField tableField;

	private Boolean primaryKey;
	private String fieldName = "New Field";
	private Text fieldNameText;
	private TextField fieldNameTextField;
	private Text dataTypeText;
	private DataType dataType;
	private DataType defaultDataType = DataType.NCHAR;
	private Boolean allowNulls;
	ComboBox<DataType> dataTypeSelect;

	public TableField () {

		// table row styling
		this.setAlignment(Pos.CENTER);
		this.setSpacing(5);

		// primary key icon
		Image image = new Image("primary-key.png");
		ImageView view = new ImageView();
		view.setImage(image);
		view.setFitHeight(12);
		view.setPreserveRatio(true);
		this.getChildren().add(view);

		// field name static text
		fieldNameText = new Text(fieldName);
		fieldNameText.setOnMouseClicked(editFieldNameHandler);
		this.getChildren().add(fieldNameText);

		// field name editable text field
		fieldNameTextField = new TextField(fieldName);
		fieldNameTextField.setMaxWidth(100);
		fieldNameTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				fieldNameFocusChange(newValue);
			}
		});

		// data type text
		dataTypeText = new Text(defaultDataType.toString());
		dataTypeText.setOnMouseClicked(editDataTypeHandler);
		this.getChildren().add(dataTypeText);

		// data type select
		dataTypeSelect = new ComboBox<>();
		dataTypeSelect.getItems().setAll(DataType.values());
		dataTypeSelect.setPrefWidth(80);
		// [TODO] Add focus change listener

		// check box for allow nulls
		CheckBox allowNulls = new CheckBox("Null");
		allowNulls.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				System.out.println("Checkbox Selected: " + newValue);
			}
		});
		this.getChildren().add(allowNulls);

		tableField = this;

	}

	/**
	 * Set the field name
	 * @param fieldName new field name
	 * @return Nothing.
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;

		// [TODO] check the width of the text and only display as can fit
		//Text text = new Text(fieldName);
		//final double width = text.getLayoutBounds().getWidth();

	}

	/**
	 * Get field name
	 * @return field name
	 */
	public String getFieldName() {
		return this.fieldName;
	}

	/**
	 * Set the field data type
	 * @param dataType new field data type
	 * @return Nothing.
	 */
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	/**
	 * Get data type
	 * @return field data type
	 */
	public DataType getDataType() {
		return this.dataType;
	}

    // event handler for selecting the field name
    EventHandler<MouseEvent> editFieldNameHandler = new EventHandler<MouseEvent>() {

    	@Override
    	public void handle(MouseEvent e) {

    		// remove the field name text
    		Node fieldNameText = tableField.getChildren().get(1);
    		tableField.getChildren().remove(fieldNameText);

    		// add the text field for editing the field name
    		tableField.getChildren().add(1, fieldNameTextField);
    		fieldNameTextField.setText(getFieldName());

    		// give the text field focus
    		fieldNameTextField.selectAll();
    		fieldNameTextField.requestFocus();

    	}
    };

    // event handler for data type selection
    EventHandler<MouseEvent> editDataTypeHandler = new EventHandler<MouseEvent>() {

    	@Override
    	public void handle (MouseEvent e) {

    		// remove the data type text
    		Node dataTypeText = tableField.getChildren().get(2);
    		tableField.getChildren().remove(dataTypeText);
    		
    		// add the drop down menu for selecting data type
    		tableField.getChildren().add(2, dataTypeSelect);
    		dataTypeSelect.setValue(defaultDataType);
    		
    		// display the list of items
    		dataTypeSelect.show();

    	}
    };

	/**
	 * field name focus change
	 * Display text entry field
	 * @param hasFocus text entry field is focused
	 * @return Nothing.
	 */
	public void fieldNameFocusChange(Boolean hasFocus) {

        if (hasFocus)
        {
            fieldNameTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

            	@Override
                public void handle(KeyEvent event) {

            		// escape or enter pressed
	                if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.ESCAPE) {

	                	// update the field name
	                	setFieldName(fieldNameTextField.getText());
	            		fieldNameText.setText(getFieldName());

	                	// remove the field name text field
	                	tableField.getChildren().remove(fieldNameTextField);

	                	// add the field name text
	                	tableField.getChildren().add(1, fieldNameText);
	                }
            	}
            });
        }
	}

}
