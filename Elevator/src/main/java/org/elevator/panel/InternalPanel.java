package org.elevator.panel;

import org.elevator.core.Elevator;

public class InternalPanel {
    Elevator elevator;

    public InternalPanel(Elevator elevator) {
        this.elevator = elevator;
    }

    public void pressButton(int floor) {
        elevator.handleInternalRequest(floor);
    }
}
