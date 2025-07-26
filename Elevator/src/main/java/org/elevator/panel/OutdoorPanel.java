package org.elevator.panel;

import org.elevator.models.Direction;
import org.elevator.models.ExternalRequest;
import org.elevator.listener.RequestListener;

public class OutdoorPanel {
    int floorNumber;
    RequestListener listener;

    public OutdoorPanel(int floorNumber, RequestListener listener) {
        this.floorNumber = floorNumber;
        this.listener = listener;
    }

    public void pressUp() {
        if(floorNumber == getTopFloor()) {
            System.out.println("UP button disabled for Top floor");
        }
        listener.onExternalRequest(new ExternalRequest(floorNumber, Direction.UP));
    }

    public void pressDown() {
        if(floorNumber == 0) {
            System.out.println("Down button disabled for Ground floor");
        }
        listener.onExternalRequest(new ExternalRequest(floorNumber, Direction.DOWN));
    }

    private int getTopFloor() {
        return 10;
    }
}
