/*
* @author: V.R.B. Mahanthe - IT17009164

* This class handles the file input
 */
package gap.operations;

import gap.instances.FlightInstances;
import gap.model.Flight;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class OpenFlightsFile {

    JFileChooser fileChooser = new JFileChooser();

    //Read from file and set flight details
    public void setFlightDetails() throws FileNotFoundException {
        File file = null;
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        
        //check if file is null
        assert file != null;
        if (file == null){
            System.out.println("File Not Found!");
        }
        Scanner sc = new Scanner(file);
        ArrayList<Flight> fList = new ArrayList<>();

        //Read file line by line
        while (sc.hasNextLine()) {
            String newLine = sc.nextLine();

            //ignore empty lines
            if (checkEmptyStatement(newLine)) {
                continue;
            }
            //remove space and white space
            String line = newLine.replace(" ", "");
            //separate words by a comma
            String[] arrWords = line.split(",");
            //store flight details as an instance of Flights class
            Flight flight = new Flight(arrWords[0], arrWords[1], Integer.parseInt(arrWords[2]), arrWords[3]);
            fList.add(flight);
        }

        Flight[] flightsArray = fList.toArray(new Flight[0]);
        new FlightInstances().setFlightsArray(flightsArray);
    }

    //check for empty lines
    public boolean checkEmptyStatement(String line) {
        return line.equals("");
    }
}
