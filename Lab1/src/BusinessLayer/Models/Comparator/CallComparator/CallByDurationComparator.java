package BusinessLayer.Models.Comparator.CallComparator;

import DataAccess.Models.Call;

import java.util.Comparator;

public class CallByDurationComparator implements Comparator<Call> {
    @Override
    public int compare(Call call1, Call call2){
        return call1.getDuration().compareTo(call2.getDuration());
    }
}

