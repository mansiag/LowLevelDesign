package org.elevator.models;

public class ExternalRequest {
    int floorNumber;
    Direction direction;

    public ExternalRequest(int floorNumber, Direction direction) {
        this.floorNumber = floorNumber;
        this.direction = direction;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Direction getDirection() {
        return direction;
    }
}

