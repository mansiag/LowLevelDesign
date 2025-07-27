package org.elevator;

import org.elevator.core.Elevator;
import org.elevator.core.ElevatorController;
import org.elevator.floor.Floor;
import org.elevator.panel.OutdoorPanel;
import org.elevator.core.strategy.ElevatorMovementStrategy;
import org.elevator.core.strategy.ElevatorSelectionStrategy;
import org.elevator.core.strategy.NearestElevatorSelectionStrategy;
import org.elevator.core.strategy.ScanMovementStrategy;


public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        ElevatorMovementStrategy movementStrategy = new ScanMovementStrategy();
        ElevatorSelectionStrategy selectionStrategy = new NearestElevatorSelectionStrategy();
        Elevator elevator = new Elevator(1, movementStrategy);
        ElevatorController controller = new ElevatorController(selectionStrategy);
        controller.addElevator(elevator);
        OutdoorPanel outdoorPanel1 = new OutdoorPanel(5, controller);
        Floor floor = new Floor(1, controller);
        outdoorPanel1.pressUp();
        elevator.getInternalPanel().pressButton(3);
    }
}