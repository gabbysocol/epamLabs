package BusinessLayer.Models.Comparator.SMSComparator;

import DataAccess.Models.SMS;

import java.util.Comparator;

public class SmsByTextSizeComparator implements Comparator<SMS> {
    @Override
    public int compare(SMS sms1, SMS sms2){
        return sms1.getTextSize() - sms2.getTextSize();
    }
}