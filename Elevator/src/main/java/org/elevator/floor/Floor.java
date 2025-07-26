package org.elevator.floor;

import org.elevator.listener.RequestListener;
import org.elevator.panel.OutdoorPanel;

public class Floor {
    int floorNumber;
    OutdoorPanel outdoorPanel;

    public Floor(int floorNumber, RequestListener controller) {
        this.floorNumber = floorNumber;
        this.outdoorPanel = new OutdoorPanel(floorNumber, controller);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public OutdoorPanel getOutdoorPanel() {
        return outdoorPanel;
    }
}
