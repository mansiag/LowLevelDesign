package org.elevator.core;

import org.elevator.core.strategy.ElevatorMovementStrategy;
import org.elevator.models.Direction;
import org.elevator.models.ElevatorState;
import org.elevator.panel.InternalPanel;

public class Elevator implements Runnable {

    private volatile int currentFloor;
    private final int elevatorId;
    private volatile Direction direction;
    private volatile ElevatorState elevatorState;
    InternalPanel internalPanel;
    ElevatorMovementStrategy movementStrategy;
    private volatile boolean isRunning;
    private final Object stateLock = new Object();


    @Override
    public void run() {
        while (isRunning && !Thread.currentThread().isInterrupted()) {
            try {
                synchronized (stateLock) {
                    if (elevatorState != ElevatorState.MAINTENANCE) {
                        move();
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public Elevator(int elevatorId, ElevatorMovementStrategy movementStrategy) {
        this.elevatorId = elevatorId;
        this.movementStrategy = movementStrategy;
        this.currentFloor = 0;
        this.elevatorState = ElevatorState.IDLE;
        this.direction = Direction.UP;
        this.isRunning = true;
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
        synchronized (stateLock) {
            System.out.println("Door is opening for elevator " + elevatorId);
            elevatorState = ElevatorState.DOOR_OPENING;
        }
    }

    public void closeDoor() {
        synchronized (stateLock) {
            System.out.println("Door is closing for elevator " + elevatorId);
            elevatorState = ElevatorState.DOOR_CLOSING;
        }
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

    public void stop() {
        isRunning = false;
    }
}
