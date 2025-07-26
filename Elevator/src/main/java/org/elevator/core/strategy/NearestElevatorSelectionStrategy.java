package org.elevator.core.strategy;

import org.elevator.models.ExternalRequest;
import org.elevator.models.Direction;
import org.elevator.core.Elevator;
import org.elevator.models.ElevatorState;

import java.util.List;

public class NearestElevatorSelectionStrategy implements ElevatorSelectionStrategy {
    @Override
    public Elevator selectElevator(List<Elevator> elevatorList, ExternalRequest request) {
        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;
        for(Elevator elevator: elevatorList) {
            int floorDiff = request.getFloorNumber() - elevator.getCurrentFloor();
            Direction elevatorDirection = elevator.getDirection();
            boolean isIdle = elevator.getElevatorState() == ElevatorState.IDLE;
            boolean isMovingTowards = (floorDiff > 0 && elevatorDirection == Direction.UP) || (floorDiff < 0 && elevatorDirection == Direction.DOWN);
            boolean sameDirection = (request.getDirection() == elevatorDirection);
            if((isMovingTowards && sameDirection) || isIdle) {
                int distance = Math.abs(floorDiff);
                if(distance < minDistance) {
                    minDistance = distance;
                    best = elevator;
                }
            }
        }
        return best;
    }
}
