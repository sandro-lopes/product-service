package com.codingbetter.domain.shared.event;

import java.time.LocalDateTime;
import java.util.UUID;
import java.io.Serializable;

public interface DomainEvent extends Serializable {
    UUID getId();
    LocalDateTime getOccurredOn();
}
