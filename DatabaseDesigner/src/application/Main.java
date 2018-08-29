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
			ToolButton createTableButton = toolPane.addToolButton("Table");
			ToolButton createRelationshipButton = toolPane.addToolButton("Relationship");

			// define actions for the buttons
			createTableButton.addCreateAction(DatabaseElement.TABLE, designerPane);
			createRelationshipButton.addCreateAction(DatabaseElement.RELATIONSHIP, designerPane);
			
			// create explorer pane
			ExplorerPane explorer = new ExplorerPane();
			explorer.addTable("Placeholder 1");
			explorer.addTable("Placeholder 2");
			explorer.updateTableExplorer();
			
			// remove one of the tables (for testing purposes)
			explorer.removeTable("Placeholder 1");
			explorer.updateTableExplorer();

			// add the panes to the window
			BorderPane root = new BorderPane(designerPane, toolPane, null, null, explorer);
			
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
