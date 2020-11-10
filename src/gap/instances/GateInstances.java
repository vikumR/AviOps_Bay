/*
* @author: V.R.B. Mahanthe - IT17009164

* This class creates instances of the Model class: Gate. 
* The instances below creates gates.
 */
package gap.instances;

import gap.model.Gate;

public class GateInstances {

    Gate g1 = new Gate(1, "PB", false, 40, 2);
    Gate g2 = new Gate(2, "PB", false, 12, 2);
    Gate g3 = new Gate(3, "RB", false, 15, 2);
    Gate g4 = new Gate(4, "PB", false, 10, 2);
    Gate g5 = new Gate(5, "RB", false, 20, 2);
    Gate g6 = new Gate(6, "PB", false, 14, 2);
    Gate g7 = new Gate(7, "RB", false, 16, 1);
    Gate g8 = new Gate(8, "PB", false, 13, 1);

    Gate g9 = new Gate(9, "RB", false, 20, 1);
    Gate g10 = new Gate(10, "RB", false, 22, 1);
    Gate g11 = new Gate(11, "RB", false, 24, 1);

    public Gate[] getGatesArray() {
//        return new Gate[]{g1, g2, g3, g4, g5, g6, g7, g8};
        return new Gate[]{g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11};
    }

}
