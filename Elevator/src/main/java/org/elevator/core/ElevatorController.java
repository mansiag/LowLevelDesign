package org.elevator.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.elevator.core.strategy.ElevatorSelectionStrategy;
import org.elevator.models.ExternalRequest;
import org.elevator.listener.RequestListener;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController implements RequestListener {
    List<Elevator>elevators = new ArrayList<>();
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    ElevatorSelectionStrategy selectionStrategy;

    public ElevatorController(ElevatorSelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    @Override
    public void onExternalRequest(ExternalRequest request) {
        Elevator selected = selectionStrategy.selectElevator(elevators, request);
        if(selected != null) {
            selected.handleExternalRequest(request.getFloorNumber());
        }
    }

    public void addElevator(Elevator elevator) {
        this.elevators.add(elevator);
        executorService.submit(elevator);
    }

    public void stop() {
        elevators.forEach(Elevator::stop);
        executorService.shutdown();
    }

}
