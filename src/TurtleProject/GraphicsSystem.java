package TurtleProject;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import uk.ac.leedsbeckett.oop.LBUGraphics;

public class GraphicsSystem extends LBUGraphics{
	
	public static void main(String[] args)
    {
            new GraphicsSystem(); //create instance of class that extends LBUGraphics (could be separate class without main), gets out of static context
    }
	private boolean fill; //Setting the fill status
	public GraphicsSystem() 
	{
		//Declaring all these in order to display the output as a frame
        JFrame MainFrame = new JFrame();            //create a frame to display the turtle panel on
        MainFrame.setLayout(new FlowLayout());     //not strictly necessary
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //to close the window when asked
        MainFrame.add(this);                       //"this" is this object that extends turtle graphics so we are adding a turtle graphics panel to the frame
        MainFrame.setSize(850,450);                //set the frame to a size we can see
        MainFrame.setVisible(true);                //now display it
		//Calling already defined method to make the turtle face down and also make the penDown and display name at line
        penDown();
		displayMessage(" Tarun Agarwal ");
	}
	
	//Array to validate the commands entered by user
	final String[] array = {"about", "penup", "pendown","turnleft","turnright","forward","backward","black","green","red","white","reset","clear","load","save","square","pencolor","penwidth", "triangle", "circle", "rectangle", "help", "randomColor"};
	
	// ArrayList holding commands entered by the user to save
	public ArrayList<String> list= new ArrayList<String>() ;
	
	@Override
	public void processCommand(String command) {
		// TODO Auto-generated method stub
		try
		{
			//Requirement 2
			if(command.contains(" "))      //if the value has an space in between this part runs
			{
				String storeCommand[] = command.split(" ");	
				
				if(storeCommand[0].equals("turnleft")==true && Integer.parseInt(storeCommand[1])>0 ) //This part is executed if the input value by user is turnleft <int> 
				{
					turnLeft(Integer.parseInt(storeCommand[1]));
					list.add(command);          //add the command is saved in text file
				}
				else if(storeCommand[0].equals("turnright")==true && Integer.parseInt(storeCommand[1])>0 ) //This part is executed if the input value by user is turnright <int> 
				{
					turnRight(Integer.parseInt(storeCommand[1]));
					list.add(command);          //add the command is saved in text file
				}
				else if(storeCommand[0].equals("forward")==true && Integer.parseInt(storeCommand[1])>0 ) //This part is executed if the input value by user is forward <int> 
				{
					forward(Integer.parseInt(storeCommand[1]));
					list.add(command);          //add the command is saved in text file
				}
				else if(storeCommand[0].equals("backward")==true && Integer.parseInt(storeCommand[1])>0 ) //This part is executed if the input value by user is backward <int> 
				{
					forward(-Integer.parseInt(storeCommand[1]));
					list.add(command);          //add the command is saved in text file
				}
				else if(storeCommand[0].equals("square")==true && Integer.parseInt(storeCommand[1])>0 ) //This part is executed if the input value by user is square <int> 
				{
					square(Integer.parseInt(storeCommand[1]));
					list.add(command);          //add the command is saved in text file
					forward(0);
				}
				else if(storeCommand[0].equals("pencolor")==true && storeCommand.length==4 && Integer.parseInt(storeCommand[1])>0 && Integer.parseInt(storeCommand[2])>0 && Integer.parseInt(storeCommand[3])>0 ) //This part is executed if the input value by user is backward <int> 
				{
					if(Integer.parseInt(storeCommand[1])>255 || Integer.parseInt(storeCommand[2])>255 || Integer.parseInt(storeCommand[3])>255)
		 				JOptionPane.showMessageDialog(null, "Color value can only be in the range of 0-255!!","Error",JOptionPane.ERROR_MESSAGE);
		 			else
		 				setPenColour(new Color(Integer.parseInt(storeCommand[1]), Integer.parseInt(storeCommand[2]), Integer.parseInt(storeCommand[3])));         //add the command is saved in text file
						list.add(command);
				}
				else if(storeCommand[0].equals("pencolor")==true && storeCommand.length<=4) //This part is executed if the input value by user is passed without enough parameter in pencolor
				{
					JOptionPane.showMessageDialog(null, "Not Enough Parameter Passed!!","Error",JOptionPane.ERROR_MESSAGE);         //add the command is saved in text file
				}
				else if(storeCommand[0].equals("penwidth")==true && Integer.parseInt(storeCommand[1])>0 ) //This part is executed if the input value by user is penwidth
				{
					setStroke(Integer.parseInt(storeCommand[1]));
					list.add(command);          //add the command is saved in text file
				}
				else if(storeCommand[0].equals("triangle")==true && storeCommand.length==4 && Integer.parseInt(storeCommand[1])>0 && Integer.parseInt(storeCommand[2])>0 && Integer.parseInt(storeCommand[3])>0 ) //This part is executed if the input value by user is triangle <int><int><int>
				{
					triangle(Integer.parseInt(storeCommand[1]), Integer.parseInt(storeCommand[2]), Integer.parseInt(storeCommand[3]));         //add the command is saved in text file
					list.add(command);
					forward(0);
				}
				else if(storeCommand[0].equals("triangle")==true && Integer.parseInt(storeCommand[1])>0 ) //This part is executed if the input value by user is backward <int> 
				{
					triangle(Integer.parseInt(storeCommand[1]));
					list.add(command);
					forward(0);//add the command is saved in text file
				}
				else if(storeCommand[0].equals("circle")==true && Integer.parseInt(storeCommand[1])>0 ) //This part is executed if the input value by user is backward <int> 
				{
					circle(Integer.parseInt(storeCommand[1]));
					list.add(command);
					forward(0);//add the command is saved in text file
				}
				else if(storeCommand[0].equals("rectangle")==true && Integer.parseInt(storeCommand[1])>0 && Integer.parseInt(storeCommand[2])>0)
		 		{
		 			rectangle(Integer.parseInt(storeCommand[1]), Integer.parseInt(storeCommand[2]));
		 			list.add(command);
		 			forward(0);
		 		}
				//Requirement 3
		 		else if (Integer.parseInt(storeCommand[1])<0) //This part is executed if the input value is less than 0 
		 		{
		 			JOptionPane.showMessageDialog(null, "Parameter Not Bounded!!","Error",JOptionPane.ERROR_MESSAGE);
		 		}
				
		 		else if(storeCommand[0].equals(array)==false) //This part is executed if the input is outside the valid array list
		 		{
		 			JOptionPane.showMessageDialog(null, "Not a Valid Command!!","Error",JOptionPane.ERROR_MESSAGE);
		 		}
				
			}
			else
			{
				//Requirement 1 & 5
				if(command.equals("about")==true)         //This part is executed if the input value by user is about
				{
					about();	
					list.add(command);                    //add the command is saved in text file
				}
				//Requirement 2
				else if(command.equals("penup")==true)         //This part is executed if the input value by user is about
				{
					penUp();	
					list.add(command);                    //add the command is saved in text file
				}
				else if(command.equals("pendown")==true)         //This part is executed if the input value by user is about
				{
					penDown();	
					list.add(command);                    //add the command is saved in text file
				}
				else if(command.equals("turnleft")==true) // if user entered value has no parameter for turnleft
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed!!","Error",JOptionPane.ERROR_MESSAGE);
		 		}
				else if(command.equals("turnright")==true) // if user entered value has no parameter for turnright 
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed!!","Error",JOptionPane.ERROR_MESSAGE);
		 		}
				else if(command.equals("forward")==true) // if user entered value has no parameter for forward 
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed!!","Error",JOptionPane.ERROR_MESSAGE);
		 		}
				else if(command.equals("backward")==true) // if user entered value has no parameter for backward
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed!!","Error",JOptionPane.ERROR_MESSAGE);
		 		}
				
				else if(command.equals("black")==true)    //This part is executed if the input value by user is black
		 		{
		 			setPenColour(Color.black);
		 			list.add(command);                    //add the command is saved in text file
		 		}
		 		else if(command.equals("red")==true)      //This part is executed if the input value by user is red
		 		{
		 			setPenColour(Color.red);
		 			list.add(command);                    //add the command is saved in text file
		 		}
		 		else if(command.equals("green")==true)    //This part is executed if the input value by user is green
		 		{
		 			setPenColour(Color.green);
		 			list.add(command);                    //add the command is saved in text file
		 		}
		 		else if(command.equals("white")==true)    //This part is executed if the input value by user is white
		 		{
		 			setPenColour(Color.white);
		 			list.add(command);                    //add the command is saved in text file
		 		}
		 		else if(command.equals("reset")==true)    //This part is executed if the input value by user is reset
		 		{
		 			reset();
		 			list.add(command);                    //add the command is saved in text file
		 		}
		 		else if(command.equals("clear")==true)    //This part is executed if the input value by user is clear
		 		{
		 			clear();
		 			list.add(command);                    //add the command is saved in text file
		 		}
		 		else if(command.equals("load")==true)    //This part is executed if the input value by user is load
		 		{
		 			load();
		 		}
		 		else if(command.equals("save")==true)    //This part is executed if the input value by user is save
		 		{
		 			save();
		 		}
		 		else if(command.equals("square")==true) // if user entered value has no parameter for backward
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed!!","Error",JOptionPane.ERROR_MESSAGE);
		 		}
		 		else if(command.equals("pencolor")==true) // if user entered value has no parameter for pencolor
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed!!","Error",JOptionPane.ERROR_MESSAGE);
		 		}
		 		else if(command.equals("penwidth")==true) // if user entered value has no parameter for penwidth
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed!!","Error",JOptionPane.ERROR_MESSAGE);
		 		}
		 		else if(command.equals("triangle")==true) // if user entered value has no parameter for triangle
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed!!","Error",JOptionPane.ERROR_MESSAGE);
		 		}
		 		else if(command.equals("circle")==true) // if user entered value has no parameter for triangle
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed!!","Error",JOptionPane.ERROR_MESSAGE);
		 		}
		 		else if(command.equals("rectangle")==true) // if user entered value has no parameter for triangle
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed!!","Error",JOptionPane.ERROR_MESSAGE);
		 		}
		 		else if(command.equals("help")==true)    //This part is executed if the input value by user is save
		 		{
		 			Help();
		 		}
		 		else if(command.equals("randomColor")==true)    //This part is executed if the input value by user is save
		 		{
		 			randomColor();
		 		}
		 		else if(command.equals(array)==false) 	  // This part is executed if the command of the valid command array list 
		 		{
		 			JOptionPane.showMessageDialog(null, command+" not on the commands list above","Error",JOptionPane.ERROR_MESSAGE);
		 		}
		 		
		 		
			}
		}
		//Requirement 3 
		catch(Exception e)     //if the user input value contradicts all other condition the system prompts this message
		{
			JOptionPane.showMessageDialog(null, "Non-Numeric Value passed!!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	//Requirement 4
	public void saveCommand(ArrayList arr) //to save the command entered by user linearly 
	{
		Path output = Paths.get("D:\\College\\L4S2\\OOPS\\FinalTurtleProject\\command.txt"); //path to save the commands.txt
		
		//Trying to write the command of the user in a text file and if this wont happen catch part will be executed
		try 
		{
			Files.write(output,arr);
		}
		catch(Exception e) 
		{
			JOptionPane.showMessageDialog(null, "File not saved!!","Save error",JOptionPane.ERROR_MESSAGE);
		}
	}
	//Method to save command or to save image
	public void save() 
	{
		String choose[]= {"Save Commands","Save Image"};           //Storing two options in array
		int value = JOptionPane.showOptionDialog(null, "What to save ?\nSave Commands \nSave Image ", "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choose, choose[1]);
		if (value==0)     //If the value clicked by user is save command this part will be executed
		{
			saveCommand(list);
			JOptionPane.showMessageDialog(null, "Command saved sucessfully!!","Save Command",JOptionPane.PLAIN_MESSAGE);
		}
		else if (value==1)  //If the value clicked by user is save image this part will be executed
		{
			try 
			{	
				Thread.sleep(120);
				Robot r =new Robot();
				
//				It saves screenshot to desired path
				String path ="D:\\College\\L4S2\\OOPS\\FinalTurtleProject\\image.png";
				
//				Used to get Screen size and capture image	
				Rectangle capture=new Rectangle(0,0,850,450);
				
				//Capture the command as image
				BufferedImage Image=r.createScreenCapture(capture);
				ImageIO.write(Image, "png", new File(path));     //Write the image captured in that file
				JOptionPane.showMessageDialog(null, "Image saved sucessfully!!","Save Image",JOptionPane.PLAIN_MESSAGE);
			} 
			catch(AWTException|IOException|InterruptedException ex)           //If the image is not saved in a file then execute catch part for error dialog box
			{
				JOptionPane.showMessageDialog(null, "Current Image not saved!!","ImageSave error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else        //If both the options are not clicked by the user this part will be executed
		{
			JOptionPane.showMessageDialog(null, "No any request passed!!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void loadSequence() 
    {
    	JFileChooser fileChooser = new JFileChooser();

		fileChooser.setCurrentDirectory(new File("D:\\College\\L4S2\\OOPS\\FinalTurtleProject")); //sets current directory

		int response = fileChooser.showOpenDialog(null);
		
		
		if(response == JFileChooser.APPROVE_OPTION) {

			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			
			Scanner s;
			try 
        	{
	        	s = new Scanner(file);
	        	ArrayList<String> listS = new ArrayList<String>();
	
	        	while (s.hasNextLine())
	        	listS.add(s.nextLine());
	
	        	GraphicsSystem turtleTest = new GraphicsSystem();
	
	        	for (int i = 0; i <= listS.size(); i++) {
	
	        	turtleTest.processCommand(listS.get(i));
        	}} 
        	
        	catch (Exception e) 
        	{
	        	System.out.println("");
        	}
			
		}
    }
	//Method to load command or to load image
	public void load()
	{
        String options[]= {"Load commands","Load Image"};            //Storing two options in array
        int store = JOptionPane.showOptionDialog(null,"What to load ? \nCommand File \nImage File","Load", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
        if(store==0)                        //If the value clicked by user is load command this part will be executed
        {
    		File commandFile = new File("D:\\College\\L4S2\\OOPS\\FinalTurtleProject\\command.txt");
    		try
    		{
    			reset();
    			penDown();
    			Scanner scan = new Scanner (commandFile);             //Created a object called scan
    			while(scan.hasNextLine())
    			{
    				String line =scan.nextLine();                     //Check each and every line
    				processCommand(line);
    				penDown();//loads the command in screen which was passed by user itself
    				JOptionPane.showMessageDialog(null, "Command loaded sucessfully!!","Load Command",JOptionPane.PLAIN_MESSAGE);
    			}
    			scan.close();
    			
    		}
    		catch (FileNotFoundException e)           //If the command is not loaded in a file then execute catch part for error dialog box
    		{ 
    			JOptionPane.showMessageDialog(null, "File couldn't load!!","Fileload error",JOptionPane.ERROR_MESSAGE);
    		}
        }
        else if (store==1)                            //If the value clicked by user is load image this part will be executed
        {
//       		File imageFile = new File("D:\\College\\L4S2\\OOPS\\FinalTurtleProject\\image.png");
    			JFrame fr =new JFrame();                  //New JFrame object fr is created
    			if (list.isEmpty())
    			{}
    			else 
    			{
    				String choose[]= {"Yes","No"}; 
    				int value = JOptionPane.showOptionDialog(null, "Do you want to save your current image?\nYes \nNo ", "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choose, choose[1]);
    				if (value==0)     //If the value clicked by user is save command this part will be executed
        			{
        				save();
        			}
        			else if (value==1)  //If the value clicked by user is save image this part will be executed
        			{
    				}
    			}
    			//Loads the file where the image is saved and displays in screen
    			
//                fr.setContentPane(new JLabel(new ImageIcon(ImageIO.read(imageFile))));
    			loadSequence();
                fr.pack();
                fr.setVisible(true);
                JOptionPane.showMessageDialog(null, "Image loaded sucessfully!!","Image Command",JOptionPane.PLAIN_MESSAGE);
         }
        else                             //If both the options are not clicked by the user this part will be executed 
        {
        	JOptionPane.showMessageDialog(null, "No any request passed!!","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
	private void square(int side) 
	{
		Graphics canvas = getGraphicsContext();    //Creating a Graphics object called canvas
		canvas.setColor(Color.blue);               //sets the color to blue
		
		if (fill)        //if fill is passed by the user and square is called, filled square will be displayed
		{
			canvas.fillRect(xPos - side / 2, yPos, side, side);
		}
		else            //if fill is not passed then normal square is drawn without the filled color
			canvas.drawRect(xPos - side / 2, yPos, side, side);
	}
	private void triangle(int sideA, int sideB, int sideC)
	{
		Graphics canvas = getGraphicsContext();       //Creating a Graphics object called canvas
		
		//Creating triangle on the basis of the coordinates passed
		int[] xPoints = {sideA, sideB, sideC};
		int[] yPoints = {sideB, sideC, sideA};
		
		if (fill)      //if fill is passed by the user and triangle is called, filled triangle will be displayed
		{
			canvas.fillPolygon(xPoints, yPoints, 3);
		}
		else          //if fill is not passed then normal triangle is drawn without the filled color 
			canvas.drawPolygon(xPoints, yPoints, 3);
	}

	/*Equilateral Triangle is drawn in the screen below the turtle
	 * Parameter passed is one side of triangle which is equal to all side
	 */
	private void triangle(int oneside)
	{
		//Finding out the value of new coordinates of triangle
		int v = oneside/2;
		int w = (int) Math.round(Math.sqrt(Math.pow(oneside, 2)-Math.pow(v, 2)));
		
		Graphics canvas = getGraphicsContext();     //Creating a Graphics object called canvas
		//Creating triangle on the basis of new coordinates passed
		int[] xPoints = { xPos, xPos+v, xPos-v};
		int[] yPoints = { yPos, yPos+w, yPos+w};
		
		if (fill)        //if fill is passed by the user and triangle is called, filled equilateral triangle will be displayed
		{
			canvas.fillPolygon(xPoints, yPoints, 3);
		}
		else            //if fill is not passed then normal equilateral triangle is drawn without the filled color 
			canvas.drawPolygon(xPoints, yPoints, 3);
	}
	@Override
	//Requirement 5
	public void about() 
	{
		super.about(); //calling main function 
		getGraphicsContext().drawString("Tarun Agarwal", 250,300); //added name and cordinated to print name
	}
	
	private void rectangle(int breadth, int height)
	{
		Graphics canvas = getGraphicsContext();      //Creating a Graphics object called canvas
		canvas.setColor(Color.yellow);               //sets the color to yellow
		
		if (fill)           //if fill is passed by the user and rectangle is called, filled rectangle will be displayed
		{
			canvas.fillRect(xPos - breadth/2, yPos, breadth, height);
		}
		else                //if fill is not passed then normal rectangle is drawn without the filled color
			canvas.drawRect(xPos - breadth/2, yPos, breadth,height);
		forward(0);
	}
	private void randomColor()    //Its sets the pen color to a random color as soon as this function is called
	{
		Random rgb = new Random();    // Creating a Random object called rgb
		setPenColour(new Color(rgb.nextInt(256),rgb.nextInt(256),rgb.nextInt(256)));	 		
	}
	private void Help()    //Its displays the help detail of this whole program
	{
		//All detail stored in string
		String detail = "about: Display the turtle dance moving round and the name of the author\n"+
						"penup: Lifts the pen from the canvas, so that movement does not get shown\n"+
						"pendown: Places the pen down on the canvas so movement gets shown as a drawn line\n"+
						"black: Make the pen color black\n"+
						"green: Makes the pen color green\n"+
						"red: Makes the pen color red\n"+
						"white: Makes the pen color white\n"+
						"clear: Clears the whole output screen\n"+
						"reset: Resets the canvas to its initial state with turtle pointing down but does not clear the display\n"+
						"save: It provides options to save command or to save image\n"+
						"load: It provides options to load command or to load image\n"+
						"pencolor <int><int><int>: It takes three diferent color value to make RGB color\n"+
						"penwidth: It takes one parameter and sets the pen stroke\n"+
						"help: Display this menu!\n"+
						"randomColor: sets the color of the pen to random color"+
						"\n"+
						"DRAWINGS"+
						"\n"+
						"circle <radius>: It draws the circle of the radius user enters\n"+
						"rectangle <BREADTH> AND <HEIGHT>: Draws a rectangle\n"+
						"square <SIDE>: Draws a square with the same length of all sides\n"+
						"triangle <int>: Equilateral triangle is drawn\n"+
						"triangle <int><int><int>:Three parameter is passed it draws normal traingle\n"+
						"\n"+
						"LINES BY PASSING PARAMETERS"+
						"\n"+
						"forward <int>: Forwards the turtle by the units passed\n"+
						"backward <int>: Trutle will move backwards by the units passed\n"+
						"turnleft <int>: Turn the turtle to right by degrees passed\n"+
						"turnright <int>: Turn the turtle to left by degree passed\n";
		
		//Displays all the detail of help as a dialog box
		JOptionPane.showMessageDialog(null, detail);     
	}
}