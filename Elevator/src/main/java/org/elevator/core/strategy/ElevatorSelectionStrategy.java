package org.elevator.core.strategy;

import org.elevator.models.ExternalRequest;
import org.elevator.core.Elevator;

import java.util.List;

public interface ElevatorSelectionStrategy {
    Elevator selectElevator(List<Elevator> elevatorList, ExternalRequest request);
}
