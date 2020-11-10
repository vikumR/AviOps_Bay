/*
* @author: V.R.B. Mahanthe - IT17009164

* This is the model class for a flight. 
* It has the following properties:
    flightName: Name of the flight 
    airCraft: Make of aircraft allocated to the flight
    arrival_dt: Arrival time of the flight
    departure_dt: Departure time of the flight
    aircraftType: Aricraft type (Wide Body - [1], Narrow body - [2])
    reqGate: Type of gate required by the airline
 */
package gap.model;

import java.time.LocalDateTime;

public class Flight {

    private String flightName;
    private String airCraft;
    private LocalDateTime departure_dt;
    private LocalDateTime arrival_dt;
    private int aircraftType;
    private String reqGate;

    public Flight(String flightName, String airCraft, int aircraftType, String reqGate) {
        this.flightName = flightName;
        this.airCraft = airCraft;
        this.aircraftType = aircraftType;
        this.reqGate = reqGate;
    }

    public String getFlightName() {
        return flightName;
    }

    public String getAirCraft() {
        return airCraft;
    }

    public LocalDateTime getDeparture_dt() {
        return departure_dt;
    }

    public LocalDateTime getArrival_dt() {
        return arrival_dt;
    }

    public int getAircraftType() {
        return aircraftType;
    }

    public String getReqGate() {
        return reqGate;
    }

    public void setReqGate(String reqGate) {
        this.reqGate = reqGate;
    }

    public void setAircraftType(int aircraftType) {
        this.aircraftType = aircraftType;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public void setAirCraft(String airCraft) {
        this.airCraft = airCraft;
    }

    public void setDeparture_dt(LocalDateTime departure_dt) {
        this.departure_dt = departure_dt;
    }

    public void setArrival_dt(LocalDateTime arrival_dt) {
        this.arrival_dt = arrival_dt;
    }
}
