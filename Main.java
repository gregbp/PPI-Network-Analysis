package application;
	
import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// Main class creates a network from a user given input file and provides user with basic network analysis tools 
// These tools are provided by Main class by implementing GUI
public class Main extends Application {
	@Override
	public void start(Stage stage1) {
		try {
			 
			
			/*
			 * GUI consists of 4 windows (Stages), 1st, 2nd, 3rd-a and 3rd-b. The flow is 1st -> 2nd -> 3rd-a or 3rd-b
			 * and it can go backwards too.
			 */
			
			
			
			// <-- LINES 40-170 - GUI elements and Layout declaration and creation for each of the 4 stages -->
			
			// Initialize a Network object
			Network nw = new Network();
			
			// Alert message used in many different occasions for stating to user a warning, error or an informative 
			// message of successfully executed function
			Alert alert_msg = new Alert(AlertType.NONE);
			
			// 1st window - stage1 contains 2 labels (txtWelcome, lb1), 2 buttons (btnCreateNW, btnSelectInputFile) and a file chooser
			// In stage1 the user chooses an input file and a new network is created 
			Label txtWelcome = new Label("Welcome");			
			Label lb1 = new Label("");			
			Button btnCreateNW = new Button("Create Network");
	        Button btnSelectInputFile = new Button("Select File");
	        
	        FileChooser fileChooser = new FileChooser();	
			
			// The layout of stage1 is BorderPane
	        BorderPane BPane = new BorderPane(); 	        
	        BPane.setTop(txtWelcome);
	        BPane.setCenter(lb1);
	        BPane.setLeft(btnSelectInputFile);
	        BPane.setRight(btnCreateNW);	         
	        
	        BorderPane.setAlignment(txtWelcome, Pos.CENTER);
	        BorderPane.setAlignment(lb1, Pos.CENTER);
	        BorderPane.setAlignment(btnSelectInputFile, Pos.CENTER_RIGHT);
	        BorderPane.setAlignment(btnCreateNW, Pos.CENTER_LEFT);
	        
	        Scene scene=new Scene(BPane,600,400); 	         	             
	        stage1.setTitle("PPI Network Analysis");  
	        stage1.setScene(scene);  
	        stage1.show(); 
	        
	        
	        
	        
	        /* 2nd window - stage2 contains 1 label (lb2) and 3 buttons (btnBack2stage1, btnTo3aWindow, btnNWanalytics)
	     	   In stage2 user selects if he wants to add a new network interaction (goes to stage3a) or
	           to use network analytics tools (goes to stage3b). Also, he can return to stage1
	        */
	        Label lb2 = new Label("Choose one of the following:");
	        Button btnBack2stage1 = new Button("Back"); 
	        Button btnTo3aWindow = new Button("Add network interaction");  
	        Button btnNWanalytics = new Button("Network Analytics"); 	        
	        
	        // The layout of stage2 is GridPane
	        GridPane grid2 = new GridPane();
	        grid2.setPadding(new Insets(10, 10, 10, 10)); 
	        grid2.setVgap(10);
	        grid2.setHgap(10); 
	        
		    GridPane.setConstraints(lb2, 0, 10);
		    GridPane.setConstraints(btnBack2stage1, 0, 15);
		    GridPane.setConstraints(btnTo3aWindow, 4, 15);
		    GridPane.setConstraints(btnNWanalytics, 15, 15);		     
		    grid2.getChildren().addAll(lb2,btnTo3aWindow,btnNWanalytics,btnBack2stage1);
		    
		    Scene scene2=new Scene(grid2,600,400); 	        
            Stage stage2 = new Stage();
            stage2.setTitle("PPI Network Analysis");
            stage2.setScene(scene2);
          
	        
            
	         
            // 3rd-A window - stage3a contains 2 text fields (tf3a1, tf3a2) and 2 buttons (btnAddInter, btnBack3aToStage2). 
            // In stage3a the user adds a new interaction to the network or goes back to stage2.         	
	        TextField tf3a1 = new TextField();
	        TextField tf3a2 = new TextField();
	        Button btnAddInter = new Button("Add interaction");  
	        Button btnBack3aToStage2 = new Button("Back");         
	        
	        // The layout of stage3a is HBox
	        HBox root3a = new HBox(); 
            root3a.setAlignment(Pos.CENTER);       
	        root3a.getChildren().addAll(btnBack3aToStage2,tf3a1,tf3a2,btnAddInter);
	        
	        Scene scene3 = new Scene(root3a,600,400);                    
            Stage stage3a = new Stage();
            stage3a.setTitle("PPI Network Analysis");
            stage3a.setScene(scene3);
           
            
            
            
            /* 3rd-B window - stage3b contains 6 labels, 4 buttons, 2 text fields, a MenuButton with 4 MenuItems
               and a directory chooser. In stage3b user selects one of the four options of MenuButton, each of one providing
               a different network analysis information. Also, he can return to stage2.
	        */           
            Label lb3b2 = new Label("");
            Label lb3b1a = new Label("");
            Label lb3b1b = new Label("");
            Label lb3b4a = new Label("Selected Directory Path: ");
        	Label lb3b4b = new Label("");
        	Label lb3b4c = new Label("Write the file name: ");  
        	
        	Button btnBack3bto2 = new Button("Back");
        	Button btnSubmit = new Button("Submit");
        	Button btnSelect = new Button("Select Folder");
        	Button btnCreateDistrFile = new Button("Create File");
        	
        	TextField tf3b1 = new TextField();
            TextField tf3b4 = new TextField();
            
        	MenuItem mItem1 = new MenuItem("Get Degree of a node");
            MenuItem mItem2 = new MenuItem("Get Average Degree of nodes and total number of Nodes and Edges");
            MenuItem mItem3 = new MenuItem("Network Hubs");
            MenuItem mItem4 = new MenuItem("Distribution of node degrees");
            MenuButton menuBtn = new MenuButton("Options", null, mItem1, mItem2, mItem3, mItem4);
            
            DirectoryChooser directoryChooser = new DirectoryChooser();  
            
            // The layout of stage3b is VBox
            VBox root3b = new VBox(); 
            root3b.setAlignment(Pos.CENTER); 
	                 
	        Scene scene3b = new Scene(root3b,600,400); 
            Stage stage3b = new Stage();
            stage3b.setTitle("PPI Network Analysis");
            stage3b.setScene(scene3b);  
            
            
            
            
            
            
            
            // <-- LINES 170-576 - EventHandler definitions for each of the 4 stages -->
            
            // LINES 176-222 - stage1 EventHandlers          
            // It selects the input file that we will be used for the creation of the network
	        btnSelectInputFile.setOnAction(e -> {
	        	
	            File selectedFile = fileChooser.showOpenDialog(stage1);	 // Opens file chooser           
	            String fullpath;
	            
	            try {  
	            	fullpath = selectedFile.getAbsolutePath();
		            lb1.setText(fullpath); // Show path of selected file in label lb1
	            }catch(NullPointerException ex){
                	System.out.println("NO selectedDirectory = ");
                }
	        });
	        
	        
	        // It creates a new network and proceeds to stage2, if the network creation was successful
	        btnCreateNW.setOnAction(new EventHandler<ActionEvent>() {  
	              
	            @Override  
	            public void handle(ActionEvent arg0) { 
		             
					try { 
						
						// When a file is selected (lb1 is not empty, but it contains the full path), then create a network
						if(lb1.getText()!="") {
							
							// Empty nodes and edges from previously created network
							nw.edgesList.clear();
							nw.nodesList.clear();
							
							// Create a new network based on the selected input file
							nw.createNetworkFromFile(lb1.getText()); 						
							
							// Close stage 1 and proceed to stage 2
							stage1.close();
							stage2.show();
						
						// btnCreateNW is clicked before input file selection - Alert message
						}else {
							alert_msg.setAlertType(AlertType.WARNING); 
		                	alert_msg.setContentText("An import file is required.");
		                    alert_msg.show(); 							
						}
						
		            }catch(NullPointerException ex){
	                	System.out.println("NO selectedDirectory = ");                	
	                }
	            }
	        });

			 
	        
 
	        // LINES 226-260 - stage2 EventHandlers
	        
	        // Proceed to stage3a in order to add a new network interaction
	        btnTo3aWindow.setOnAction(new EventHandler<ActionEvent>() {  
	              
	            @Override  
	            public void handle(ActionEvent arg0) {
	            	
	                // Close stage2 and proceed to stage3a
	                stage2.close();
					stage3a.show();
					
					// Inform user about how to fill in the text fields of stage3a
					alert_msg.setAlertType(AlertType.INFORMATION); 
                	alert_msg.setContentText("Fill in the text fields by giving two protein names.");
                	alert_msg.show(); 
	            }  
	        }); 
	        
	        
	        // Go back to stage1
	        btnBack2stage1.setOnAction(new EventHandler<ActionEvent>() {  
	              
	            @Override  
	            public void handle(ActionEvent arg0) {
	            	
	                // Empty lb1 when return to window 1
	                lb1.setText("");
	             
	                // Close stage2 and return to stage1 
	                stage2.close();
					stage1.show();
	            }  
	        });
	        
	        
	        
	        
	        // LINES 264-320 - stage3a EventHandlers
	        
	        // Adds a new interaction to network
	        btnAddInter.setOnAction(new EventHandler<ActionEvent>() {  
	              
	            @Override  
	            public void handle(ActionEvent arg0) {  
	            	
	                // Both text fields must be filled in 
	                if (!tf3a1.getText().trim().isEmpty() && !tf3a2.getText().trim().isEmpty()) {
	                	
		                Node nd1 = new Node(tf3a1.getText());
		                Node nd2 = new Node(tf3a2.getText());	              
		                
		                // Network.addNetworkInteraction returns 1 - Successful insertion of new interaction
		                if(nw.addNetworkInteraction(nd1, nd2)==1) {
		                	alert_msg.setAlertType(AlertType.INFORMATION); 
		                	alert_msg.setContentText("Successful insertion of new interaction.");
		                	alert_msg.show(); 
		                	
		                }else { // Given interaction already exists in the network
		                	alert_msg.setAlertType(AlertType.ERROR); 
		                	alert_msg.setContentText("Interaction (Edge) already exists in the network.");
		                	alert_msg.show(); 
		                	
		                }		        		
		        				        		
		        		// Empty text fields after successful insertion
		        		tf3a1.setText("");
		        		tf3a2.setText("");
		                		                
	                }else { // User presses "Add interaction" button before having completed both text fields - Warning alert            	
	                	
	                	alert_msg.setAlertType(AlertType.ERROR); 
	                	alert_msg.setContentText("All fields must be filled in");
	                	alert_msg.show(); 	                	
	                }
	            }  
	        });
	        
	        
	        // Returns back to stage2 and closes stage3a
	        btnBack3aToStage2.setOnAction(new EventHandler<ActionEvent>() {  
	              
	            @Override  
	            public void handle(ActionEvent arg0) {  
	                
	                // Empty text fields after returning to window 2
	        		tf3a1.setText("");
	        		tf3a2.setText("");
	                
	        		// Close stage3a and show stage2
	        		stage3a.close();
					stage2.show();                 
	            }  
	        }); 
	        
	         	
	        
	        
	        // LINES 324-544 - stage2 - stage3b EventHandler - the following EventHandler belongs to "btnNWanalytics" button
	        // of stage2, but it affects stage3b, as it creates all the GUI for stage3b
	        
	        // Closes stage2, proceeds to stage3b and adds GUI elements to stage3b. In stage3b the GUI is created “dynamically”,
	        // as different GUI elements appear, depending on the selected option in the menu. 
	        btnNWanalytics.setOnAction(new EventHandler<ActionEvent>() {  
	              
	            @Override  
	            public void handle(ActionEvent arg0) {  
	                
	            	// Adding "back" button and "option" menu to root3b - checking if they already exist in root3b
	                if(root3b.getChildren().contains(btnBack3bto2) == false)
	                	root3b.getChildren().add(btnBack3bto2);
	                
	                if(root3b.getChildren().contains(menuBtn) == false)
	                	root3b.getChildren().add(menuBtn);  
	                
	                try {
	                	// Close stage2 and show stage3b
	                	stage2.close();	                	 
	                	stage3b.show();	                	 
	                	
	                	// 1st menu option - Get degree of a node
	                    mItem1.setOnAction(new EventHandler<ActionEvent>() {
	                    	
	                        @Override
	                        public void handle(ActionEvent event) {                        	
	                        	
	                        	// Removing GUI elements of other menu options (mItem2, mItem3 and mItem4)
	                        	root3b.getChildren().removeAll(lb3b2,lb3b4a,lb3b4b,lb3b4c,btnSelect,tf3b4,btnCreateDistrFile);
	                        	
	                            // Adding the GUI elements of this option to root3b of stage3b - 2 labels, 1 text field and 1 button
	                            if(root3b.getChildren().contains(lb3b1a) == false) 
	                            	root3b.getChildren().add(lb3b1a); 	
	                            if(root3b.getChildren().contains(tf3b1) == false)
	                            	root3b.getChildren().add(tf3b1); 	                            
	                            if(root3b.getChildren().contains(lb3b1b) == false) 
	                            	root3b.getChildren().add(lb3b1b);  
	                            if(root3b.getChildren().contains(btnSubmit) == false)
	                            	root3b.getChildren().add(btnSubmit); 
	                              
	                            lb3b1a.setText("Give a node name:");
            	                lb3b1b.setText(""); // It is used for showing the given node's degree
	                            
	                            // Searches the network in order to find given node's degree
	                            btnSubmit.setOnAction(new EventHandler<ActionEvent>() {  
	                	              
	                	            @Override  
	                	            public void handle(ActionEvent arg0) { 
	                	                
	                	                // Empty label from previous result
	                	                lb3b1b.setText("");
	                	                
	                	                // User clicks "Submit" button without filling in the text field - Warning alert
	                	                if(tf3b1.getText()=="") { 
	                	                	alert_msg.setAlertType(AlertType.WARNING);        	                                   
	                	                	alert_msg.setContentText("Node name not given.");        	                                   
    	                                    alert_msg.show(); 	                	                	
	                	                }else {
	                	                	Node n = new Node(tf3b1.getText());	                	                	 
	                	                 
	                	                	// User given node doeasn't exist in network
	                	                	if(nw.getNodeDegree(n) == -1) {
	                	                		alert_msg.setAlertType(AlertType.WARNING);        	                                   
	                	                		alert_msg.setContentText("Node "+tf3b1.getText()+" wasn't found.");        	                                   
	                	                		alert_msg.show(); 
	                	                		
	                	                		// Empty text field from user given node
	                	                		tf3b1.setText("");
	                	                	
	                	                	// User given node was found in network	
	                	                	}else {
	                	                		// Show node degree in label and empty text field from user given node
	                	                		lb3b1b.setText("Degree of node "+tf3b1.getText()+": "+nw.getNodeDegree(n));
	                	                		tf3b1.setText("");
	                	                	}
	                	                }
	                	            }  
	                	        }); 
	                        }
	                    });
	                    
	                    // 2nd menu option - Get average degree of nodes and total number of nodes and edges
	                    mItem2.setOnAction(new EventHandler<ActionEvent>() {
	                        @Override
	                        public void handle(ActionEvent event) {
	                        	 
	                            // Removing GUI elements of other menu options (mItem1, mItem3 and mItem4)
	                        	root3b.getChildren().removeAll(tf3b1,btnSubmit,lb3b1a,lb3b1b,lb3b4a,lb3b4b,lb3b4c,btnSelect,tf3b4,btnCreateDistrFile);
	                        	
	                        	// Adding the GUI elements of this option to root3b of stage3b - 1 label  
	                        	if(root3b.getChildren().contains(lb3b2) == false)
	                        		root3b.getChildren().add(lb3b2); 
	                        	
	                            // Write average degree of nodes and total number of nodes and edges to label 
	                            lb3b2.setText("Average Node Degree: "+nw.getAvgNodeDegree()+"\nTotal number of nodes: "+nw.nodesList.size()+"\nTotal number of edges: "+nw.edgesList.size());	                          
	                        }
	                    });
	                    
	                    // 3rd menu option - Return network hubs
	                    mItem3.setOnAction(new EventHandler<ActionEvent>() {
	                        @Override
	                        public void handle(ActionEvent event) {                       	

	                            // Removing GUI elements of other menu options (mItem1, mItem2 and mItem4)
	                        	root3b.getChildren().removeAll(tf3b1,btnSubmit,lb3b1b,lb3b4a,lb3b4b,lb3b4c,btnSelect,tf3b4,btnCreateDistrFile);
	                        	
	                        	// Adding the GUI elements of this option to root3b of stage3b - 2 labels
	                        	if(root3b.getChildren().contains(lb3b1a) == false)  
	                        		root3b.getChildren().add(lb3b1a);	// label used for showing highest node degree
	                        	if(root3b.getChildren().contains(lb3b2) == false)
	                        		root3b.getChildren().add(lb3b2); // label used for showing nodes that have the highest node degree
	                        	
	                        	// First element of ArrayList "hub_res" is the highest node degree
	                        	// See Network.findNetworkHubs() for more info
	                            ArrayList<String> hub_res = nw.findNetworkHubs();
	                            lb3b1a.setText("Highest node degree: "+hub_res.get(0)); 
	                            
	                            // The rest of the ArrayList "hub_res" elements are the nodes that have the highest node degree
	                            String hubs = ""; 
	                            for(int i=1; i<hub_res.size(); i++) {
	                            	hubs = hubs + hub_res.get(i) +" ";	                            	
	                            }	                             
	                            lb3b2.setText("Hub nodes: "+hubs);
	                        }
	                    });
	                    
	                    // 4th menu option - Write to a file the Distribution of node degrees
	                    mItem4.setOnAction(new EventHandler<ActionEvent>() {
	                    	
	                        @Override
	                        public void handle(ActionEvent event) {
	                        	
	                        	
	                        	// Removing GUI elements of other menu options (mItem1, mItem2 and mItem3)
	                        	root3b.getChildren().removeAll(tf3b1,btnSubmit,lb3b1b,lb3b2);
	                        	
	                        	// Adding the GUI elements of this option to root3b of stage3b - 4 labels, 2 buttons and 1 text field
	                        	if(root3b.getChildren().contains(lb3b1a) == false)
	                        		root3b.getChildren().add(lb3b1a);  
	                            if(root3b.getChildren().contains(lb3b4a) == false)
	                            	root3b.getChildren().add(lb3b4a);	                            
	                            if(root3b.getChildren().contains(lb3b4b) == false)
	                            	root3b.getChildren().add(lb3b4b);	                            
	                            if(root3b.getChildren().contains(btnSelect) == false)
	                            	root3b.getChildren().add(btnSelect);	                            
	                            if(root3b.getChildren().contains(lb3b4c) == false)
	                            	root3b.getChildren().add(lb3b4c);	                            
	                            if(root3b.getChildren().contains(tf3b4) == false)
	                            	root3b.getChildren().add(tf3b4);	                            
	                            if(root3b.getChildren().contains(btnCreateDistrFile) == false)
	                            	root3b.getChildren().add(btnCreateDistrFile);
	                            
	                            lb3b1a.setText("Distribution of node degrees");
	                             
	                            // Opens a directory chooser window in order to help user select where to save the node
	                            // degree distribution file
	                            btnSelect.setOnAction(e -> {
	                               
	                            	File selectedDirectory;
	                                selectedDirectory = directoryChooser.showDialog(stage1);	            
	                                
	                                // Write the selected directory path to label  
	                                try {	                                
	                                	lb3b4b.setText(selectedDirectory.getAbsolutePath());	                                	 
	                                }catch (NullPointerException ex){
	                                	System.out.println("NO selectedDirectory = ");    
	                                }
	                            });
	                             
	                           
	                            // Creates node degree distribution file
	                            btnCreateDistrFile.setOnAction(new EventHandler<ActionEvent>() {
        	                        @Override
        	                        public void handle(ActionEvent event) {  
        	                        	
        	                        	try {        	                        		
        	                        		// When user has selected directory path and has provided a file name, 
        	                        		// then the distribution file is created  
        	                        		if(tf3b4.getText() !="" && lb3b4b.getText() != "") {        	                        			 
        	                        			
        	                        			String finalPath = lb3b4b.getText() + "\\" + tf3b4.getText();             	                        		
            	                        		tf3b4.setText(""); // Empty text field after inserting file name 
            	                        		nw.getFullDegreeDistributionArray(finalPath); // Create distribution file
            	                        		
            	                        		// Inform user for the successful creation of the file 
            	                        		alert_msg.setAlertType(AlertType.INFORMATION); 
        	                                    alert_msg.setContentText("File created at: \n"+finalPath);
        	                                    alert_msg.show(); 
            	                        		
        	                                // User clicks "Create file", without having provided a file name - Warning alert   
        	                        		}else if(tf3b4.getText() =="") {
        	                        			
        	                        			alert_msg.setAlertType(AlertType.ERROR); 
        	                        			alert_msg.setContentText("File name is required");
        	                        			alert_msg.show(); 
        	                        			
        	                        		// User clicks "Create file", without having chosen a directory - Warning alert   	
        	                        		}else if(lb3b4a.getText() == "Selected Directory Path: ") {
        	                        			 
        	                        			alert_msg.setAlertType(AlertType.ERROR); 
        	                        			alert_msg.setContentText("Directory is not selected");
        	                        			alert_msg.show();         	                        			
        	                        		}
        	                        		 
        	                        	}catch (NullPointerException ex){
    	                                	System.out.println("NO selectedDirectory = ");  
    	                                }    	                                       	                             
        	                        }
        	                    }); 	
	                        }
	                    
	                    });	                    
	                }
	                catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }  
	        });  
	        
	        
	        
	        
	        // LINES 547-576 - stage3b EventHandler
	        
	        // Close stage3b and return to stage2
	        btnBack3bto2.setOnAction(new EventHandler<ActionEvent>() {  
	              
	            @Override  
	            public void handle(ActionEvent arg0) { 	              
	                 
	            	// Close stage 3b 
	            	stage3b.close();
					
	            	// Empty text fields 
	            	lb3b1b.setText("");
            		tf3b1.setText("");	            	  
	            	tf3b4.setText("");   
	            	
	            	// Remove everything from window 3b in order to have only "option" menu and "back" button 
	            	// when stage3b opens again
	            	root3b.getChildren().removeAll(menuBtn,lb3b1a,lb3b1b,lb3b2,lb3b4a,lb3b4b,lb3b4c,btnBack3bto2,btnSubmit,btnSelect,btnCreateDistrFile,tf3b1,tf3b4);
	            	 
	            	// Show stage2
					stage2.show();	                 
	            }  
	        }); 			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		// Launch GUI
		launch(args);
	}
}
