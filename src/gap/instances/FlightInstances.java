/*
* @author: V.R.B. Mahanthe - IT17009164

* This class creates instances of the Model class: Flight. 
* Instances are created in the order of the arrival of flights per assignment day.
* The instances below were used for testing purposes.
* Actual flight instances are created by reading an input file containing flight details per assignment day.
* The data in the input file are stored in flightsArray.
 */
package gap.instances;

import gap.model.Flight;

public class FlightInstances {

    private static Flight[] flightsArray;

    Flight fl1 = new Flight("CMBNY", "FL1", 1, "PB");
    Flight fl2 = new Flight("CMBDS", "FL2", 2, "RB");
    Flight fl3 = new Flight("CMBML", "FL3", 2, "PB");
    Flight fl4 = new Flight("CMBKLP", "FL4", 2, "PB");
    Flight fl5 = new Flight("CMBND", "FL5", 2, "PB");
    Flight fl6 = new Flight("CMBHT", "FL6", 2, "PB");

    Flight fl7 = new Flight("CMBKY", "FL7", 2, "PB");
    Flight fl8 = new Flight("CMBOS", "FL8", 1, "PB");
    Flight fl9 = new Flight("CMBML", "FL9", 1, "RB");
    Flight fl10 = new Flight("CMBVN", "FL10", 1, "RB");

    Flight fl11 = new Flight("R1", "FL11", 2, "PB");
    Flight fl12 = new Flight("R2", "FL12", 1, "RB");
    Flight fl13 = new Flight("R3", "FL13", 1, "RB");

    public Flight[] getFlightsArray() {
        return flightsArray;
    }

    public Flight[] getTestFlightsArray() {
//        return new Flight[]{fl1, fl2, fl3, fl4, fl5, fl6};
        return new Flight[]{fl1, fl2, fl3, fl4, fl5, fl6, fl7, fl8, fl9, fl10};
    }

    public void setFlightsArray(Flight[] flightsArray) {
        FlightInstances.flightsArray = flightsArray;
    }
}
