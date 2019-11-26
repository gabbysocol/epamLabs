package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public abstract class Service implements Serializable {
    protected int serviceId;
    protected int sourceClientId;
    protected int destinationClientId;

    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date dateTime;

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

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale = "hu", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "CET")
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
