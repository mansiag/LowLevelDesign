package org.elevator.core.strategy;

import org.elevator.core.Elevator;

public interface ElevatorMovementStrategy {
    void addExternalRequest(Elevator elevator, int floor);
    void addInternalRequest(Elevator elevator, int floor);
    void move(Elevator elevator);
}
