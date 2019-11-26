package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@JacksonXmlRootElement(localName = "call")
public class Call extends Service {

    @JsonFormat(pattern = "hh:mm:ss")
    private Time duration;

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Call(int source, int destination, Time duration){
        this.sourceClientId = source;
        this.destinationClientId = destination;
        this.duration = duration;
        this.dateTime = new Date();
    }

    public Call(int source, int destination, Time duration, int id){
        this.sourceClientId = source;
        this.destinationClientId = destination;
        this.duration = duration;
        this.serviceId = id;
        this.dateTime = new Date();
    }

    public Call(){
    }

    public String toString(){
        return String.format("%3d|%6d|%6d|%tr|%tr",this.serviceId, this.sourceClientId, this.destinationClientId, this.duration, this.dateTime);
    }
}
