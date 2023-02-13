
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.*;

public class TextEditor extends Application {

	BorderPane pane;
	MenuItem newFile;
	MenuItem openFile;
	MenuItem saveFile;
	MenuItem exitFile;

	MenuItem undoEdit;
	MenuItem cutEdit;
	MenuItem copyEdit;
	MenuItem pasteEdit;
	MenuItem deleteEdit;
	MenuItem selectAllEdit;
	public String content;
	public String selectedContent="";
	TextArea textArea;
	MenuItem helpItem;

	@Override
	public void init() throws Exception {
		super.init();
		MenuBar bar = new MenuBar();

		Menu fileMenu = new Menu("File");

		newFile = new MenuItem("New");
		newFile.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
		openFile = new MenuItem("Open..");
		openFile.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
		saveFile = new MenuItem("Save");
		saveFile.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		SeparatorMenuItem sep = new SeparatorMenuItem();
		exitFile = new MenuItem("Exit");

		fileMenu.getItems().addAll(newFile, openFile, saveFile, sep, exitFile);

		Menu EditMenu = new Menu("Edit");

		undoEdit = new MenuItem("Undo");
		SeparatorMenuItem sep2 = new SeparatorMenuItem();
		cutEdit = new MenuItem("Cut");
		cutEdit.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
		copyEdit = new MenuItem("Copy");
		copyEdit.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
		pasteEdit = new MenuItem("Paste");
		pasteEdit.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));
		deleteEdit = new MenuItem("Delete");
		SeparatorMenuItem sep3 = new SeparatorMenuItem();
		selectAllEdit = new MenuItem("Select All");
		selectAllEdit.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));

		EditMenu.getItems().addAll(undoEdit, sep2, cutEdit, copyEdit, pasteEdit, deleteEdit, sep3, selectAllEdit);
		Menu HelpMenu = new Menu("Help");

		helpItem = new MenuItem("About Notepad");
		HelpMenu.getItems().addAll(helpItem);

		textArea = new TextArea();

		bar.getMenus().addAll(fileMenu, EditMenu, HelpMenu);
		pane = new BorderPane();
		pane.setTop(bar);
		pane.setCenter(textArea);
	}

	@Override
	public void start(Stage primaryStage) {

		Scene scene = new Scene(pane, 300, 250);
		
		
		EventHandler<ActionEvent> saveEvent = new EventHandler<ActionEvent>() {
			FileChooser fil_chooser = new FileChooser();

			// Label label = new Label("no files selected");
			public void handle(ActionEvent e) {
				// get the file selected
				content = textArea.getText();
				File file = fil_chooser.showSaveDialog(primaryStage);

				if (file != null) {
					try {
						FileWriter myWriter = new FileWriter(file.getAbsolutePath());
						myWriter.write(content);
						myWriter.close();
						primaryStage.setTitle(file.getName());
					} catch (IOException e1) {
						System.out.println("An error occurred.");
						e1.printStackTrace();
					}
				}
			}
		};

		EventHandler<ActionEvent> openEvent = new EventHandler<ActionEvent>() {
			FileChooser fil_chooser = new FileChooser();

			public void handle(ActionEvent e) {
				// get the file selected
				File file = fil_chooser.showOpenDialog(primaryStage);

				if (file != null) {

					try {
						Scanner myReader = new Scanner(file);
						content = "";
						while (myReader.hasNextLine()) {
							content += myReader.nextLine();
						}
						myReader.close();
						textArea.setText(content);
						primaryStage.setTitle(file.getName());
					} catch (FileNotFoundException e1) {
						System.out.println("An error occurred.");
						e1.printStackTrace();
					}
				}
			}
		};
		EventHandler<ActionEvent> newEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textArea.clear();
				content = "";
			}
		};
		EventHandler<ActionEvent> exitEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Platform.exit();
			}
		};
		
		EventHandler<ActionEvent> selectAllEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textArea.selectAll();
			}
		};
		EventHandler<ActionEvent> copyEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				selectedContent = textArea.getSelectedText();
			}
		};
		EventHandler<ActionEvent> cutEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				selectedContent = textArea.getSelectedText();
				textArea.replaceSelection("");
			}
		};
		
		EventHandler<ActionEvent> pasteEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textArea.replaceSelection(selectedContent);
			}
		};
		EventHandler<ActionEvent> deleteEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textArea.replaceSelection("");
			}
		};
		EventHandler<ActionEvent> undoEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textArea.undo();
			}
		};
		
		
		Dialog<String> dialog = new Dialog<String>();
		dialog.setTitle("About Nodepad");
		dialog.setContentText("Hagar Abdelzaher created this notepad.");
		ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(type);
		helpItem.setOnAction(e -> {
			dialog.showAndWait();
		});

		newFile.setOnAction(newEvent);
		saveFile.setOnAction(saveEvent);
		openFile.setOnAction(openEvent);
		exitFile.setOnAction(exitEvent);
		
		selectAllEdit.setOnAction(selectAllEvent);
		copyEdit.setOnAction(copyEvent);
		cutEdit.setOnAction(cutEvent);
		pasteEdit.setOnAction(pasteEvent);
		deleteEdit.setOnAction(deleteEvent);
		undoEdit.setOnAction(undoEvent);

		primaryStage.setTitle("FX Note pad");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
