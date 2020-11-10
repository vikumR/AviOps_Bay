/*
* @author: V.R.B. Mahanthe - IT17009164

* This is the model class for a gate. 
* It has the following properties:
    gateNumber: Predetermined gate identification number
    gateType: Type of gate {Pier Gate - [PB], Remote Gate - [RB]}
    isFlightAssigned: Determines whether a flight is assigned to a gate
    occupancyRate: Rate of Gate Occupancy by airlines
    gateSize: Size of the gate {Large(Wide Body capable) - [1], Medium(Narrow Body capable) - [2]}
 */
package gap.model;

public class Gate {

    private int gateNumber;
    private String gateType;
    private boolean isFlightAssigned;
    private float occupancyRate;
    private int gateSize;

    public Gate(int gateNumber, String gateType, boolean isFlightAssigned, float occupancyRate, int gateSize) {
        this.gateNumber = gateNumber;
        this.gateType = gateType;
        this.isFlightAssigned = isFlightAssigned;
        this.occupancyRate = occupancyRate;
        this.gateSize = gateSize;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public String getGateType() {
        return gateType;
    }

    public boolean isFlightAssigned() {
        return isFlightAssigned;
    }

    public float getOccupancyRate() {
        return occupancyRate;
    }

    public int getGateSize() {
        return gateSize;
    }

    public void setGateSize(int gateSize) {
        this.gateSize = gateSize;
    }

    public void setGateNumber(int gateNumber) {
        this.gateNumber = gateNumber;
    }

    public void setGateType(String gateType) {
        this.gateType = gateType;
    }

    public void setFlightAssigned(boolean flightAssigned) {
        this.isFlightAssigned = flightAssigned;
    }

    public void setOccupancyRate(float occupancyRate) {
        this.occupancyRate = occupancyRate;
    }
}
