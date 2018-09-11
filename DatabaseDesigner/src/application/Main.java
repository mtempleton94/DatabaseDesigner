package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
* <h1>Main</h1>
* Main class for application creation
* and execution.
*
* @author  Mitchell Templeton
* @version 1.0
* @since   2018-08-28
*/
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// create schema designer pane
			DesignerPane designerPane = new DesignerPane();
			
			// create tools menu pane			
			ToolPane toolPane = new ToolPane();
			ToolButton createTableButton = toolPane.addToolButton("Table", DesignerTool.TABLE_CREATE, ButtonType.BUTTON);
			ToolButton createRelationshipButton = toolPane.addToolButton("Relationship", DesignerTool.RELATIONSHIP_CREATE, ButtonType.TOGGLE);

			// create explorer pane
			ExplorerPane explorerPane = new ExplorerPane();
			
			// define actions for the buttons
			createTableButton.addCreateAction(DesignerTool.TABLE_CREATE, designerPane, toolPane, explorerPane);
			createRelationshipButton.addCreateAction(DesignerTool.RELATIONSHIP_CREATE, designerPane, toolPane, explorerPane);

			// add the panes to the window
			BorderPane root = new BorderPane(designerPane, toolPane, null, null, explorerPane);
			
			// create the scene and add it to the stage
			Scene scene = new Scene(root,1200,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Stage");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	   /**
	   * This is the main method which makes use of launch method.
	   * @param args Unused.
	   * @return Nothing.
	   */
	public static void main(String[] args) {
		launch(args);
	}
}
