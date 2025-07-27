package org.elevator.core.strategy;

import java.util.concurrent.ConcurrentSkipListSet;
import org.elevator.models.Direction;
import org.elevator.core.Elevator;
import org.elevator.models.ElevatorState;

import java.util.Comparator;
import java.util.TreeSet;

public class ScanMovementStrategy implements ElevatorMovementStrategy {

    private ConcurrentSkipListSet<Integer> upQueue = new ConcurrentSkipListSet<>();
    private ConcurrentSkipListSet<Integer> downQueue = new ConcurrentSkipListSet<>(Comparator.reverseOrder());

    @Override
    public void addExternalRequest(Elevator elevator, int floor) {
        if(floor > elevator.getCurrentFloor()) upQueue.add(floor);
        else downQueue.add(floor);
    }

    @Override
    public void addInternalRequest(Elevator elevator, int floor) {
        if(floor > elevator.getCurrentFloor()) upQueue.add(floor);
        else downQueue.add(floor);
    }

    @Override
    public void move(Elevator elevator) {
        int current = elevator.getCurrentFloor();
        if(elevator.getDirection() == Direction.UP) {
            if(!upQueue.isEmpty()) {
                int next = upQueue.first();
                if(next > current) {
                    elevator.setCurrentFloor(current + 1);
                    System.out.println("Elevator " + elevator.getElevatorId() + " moving up to " + next);
                } else {
                    System.out.println("Elevator " + elevator.getElevatorId() + " reached floor " + elevator.getCurrentFloor());
                    upQueue.remove(next);
                }
            } else if (!downQueue.isEmpty()) {
                elevator.setDirection(Direction.DOWN);
            } else {
                elevator.setElevatorState(ElevatorState.IDLE);
            }
        }
        if(elevator.getDirection() == Direction.DOWN) {
            if(!downQueue.isEmpty()) {
                int next = downQueue.first();
                if(next < current) {
                    elevator.setCurrentFloor(current - 1);
                    System.out.println("Elevator " + elevator.getElevatorId() + " moving down to " + next);
                } else {
                    System.out.println("Elevator " + elevator.getElevatorId() + " reached floor " + elevator.getCurrentFloor());
                    downQueue.remove(next);
                }
            } else if (!upQueue.isEmpty()) {
                elevator.setDirection(Direction.UP);
            } else {
                elevator.setElevatorState(ElevatorState.IDLE);
            }
        }
    }
}
