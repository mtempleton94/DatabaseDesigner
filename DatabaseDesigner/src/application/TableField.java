package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TableField extends HBox {
	
	private List<KeyType> keyTypes = new ArrayList<KeyType>();
	private String name = "Default Name";
	private DataType dataType;
	private Boolean allowNulls;
	
	public TableField () {
		Text title = new Text(name);
		this.getChildren().add(title);
	}

}
