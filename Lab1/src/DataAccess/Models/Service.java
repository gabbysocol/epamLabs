package DataAccess.Models;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Service implements Serializable {
    protected int serviceId;
    protected int sourceClientId;
    protected int destinationClientId;
    protected LocalDateTime dateTime;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getSourceClientId() {
        return sourceClientId;
    }

    public void setSourceClientId(int sourceClientId) {
        this.sourceClientId = sourceClientId;
    }

    public int getDestinationClientId() {
        return destinationClientId;
    }

    public void setDestinationClientId(int destinationClientId) {
        this.destinationClientId = destinationClientId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
