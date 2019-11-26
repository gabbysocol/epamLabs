package models;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;

public class SMS extends Service {
    private int textSize;

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public SMS(){

    }

    public SMS(int id, int source, int destination, int textSize){
        this.sourceClientId = source;
        this.textSize = textSize;
        this.destinationClientId = destination;
        this.serviceId = id;
        this.dateTime = new Date();

    }

    public String toString(){
        return String.format("%3d|%6d|%6d|%d|%tr",this.serviceId, this.sourceClientId, this.destinationClientId, this.textSize, this.dateTime);
    }
}
