package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TableField extends HBox {
	
	private Boolean primaryKey;
	private String fieldNameText = "Default Name";
	private DataType dataType;
	private Boolean allowNulls;
	
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

		// field name text
		TextField fieldName = new TextField(fieldNameText);
		fieldName.setMaxWidth(100);
		this.getChildren().add(fieldName);

		// data type select
		ComboBox<DataType> dataTypeSelect = new ComboBox<>();
		dataTypeSelect.getItems().setAll(DataType.values());
		dataTypeSelect.setPrefWidth(80);
		this.getChildren().add(dataTypeSelect);

		// check box for allow nulls
		CheckBox allowNulls = new CheckBox("Null");
		allowNulls.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				System.out.println("Checkbox Selected: " + newValue);
			}
		});
		this.getChildren().add(allowNulls);

	}

}
