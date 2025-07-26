package org.elevator.listener;

import org.elevator.models.ExternalRequest;

public interface RequestListener {
    void onExternalRequest(ExternalRequest request);
}
