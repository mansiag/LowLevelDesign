Elevator Low Level Design

Existing Features

- **Elevator Controller**: Handles the movement of the elevator between floors.




Pending Features

- **Request serving**: Only serve requests that the elevator can currently handle while choosing the elevator (does not consider that some elevator can serve once the direction changes), else ignore the request. needs to be fixed.
- **Idempotency handling**: If a request is made to an elevator that is already serving that request, it should not be added again.
- Add listeners to the elevator controller to notify when the elevator has reached a floor or when it is idle.
- Concurrent requests: Handle multiple requests to the same elevator concurrently.
- 