package org.elevator.core;

import org.elevator.core.strategy.ElevatorMovementStrategy;
import org.elevator.models.Direction;
import org.elevator.models.ElevatorState;
import org.elevator.panel.InternalPanel;

public class Elevator {
    int currentFloor;
    int elevatorId;
    Direction direction;
    ElevatorState elevatorState;
    InternalPanel internalPanel;
    ElevatorMovementStrategy movementStrategy;

    public Elevator(int elevatorId, ElevatorMovementStrategy movementStrategy) {
        this.elevatorId = elevatorId;
        this.movementStrategy = movementStrategy;
        this.currentFloor = 0;
        this.elevatorState = ElevatorState.IDLE;
        this.direction = Direction.UP;
        this.internalPanel = new InternalPanel(this);
    }

    public void handleExternalRequest(int floor) {
        movementStrategy.addExternalRequest(this, floor);
    }

    public void handleInternalRequest(int floor) {
        movementStrategy.addInternalRequest(this, floor);
    }

    public void move() {
        movementStrategy.move(this);
    }

    public void openDoor() {
        System.out.println("Door is opening for elevator " + elevatorId);
        elevatorState = ElevatorState.DOOR_OPENING;
    }

    public void closeDoor() {
        System.out.println("Door is closing for elevator " + elevatorId);
        elevatorState = ElevatorState.DOOR_OPENING;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public void setElevatorState(ElevatorState elevatorState) {
        this.elevatorState = elevatorState;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public InternalPanel getInternalPanel() {
        return internalPanel;
    }
}
