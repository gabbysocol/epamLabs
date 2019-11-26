package BusinessLayer.Services;

import BusinessLayer.Models.Comparator.CallComparator.CallByDurationComparator;
import BusinessLayer.Models.Comparator.ServiceComparator.ServiceByDateTimeComparator;
import BusinessLayer.Models.Comparator.ServiceComparator.ServiceByIdComparator;
import BusinessLayer.Models.Comparator.ServiceComparator.ServiceBySourceComparator;
import DataAccess.Models.Call;

public class CallService extends BaseService<Call> {
    private String _fileName;

    public CallService(String fileName){
        super(fileName);
        this._fileName = fileName;
    }

    public CallService(){
        super("call.txt");
        this._fileName = "call.txt";
    }

    public void showCallSortedById()  {
        if (_entities == null)
            loadEntities();
        this._entities.sort(new ServiceByIdComparator());
        for (Call call: _entities ) {
            System.out.println(call);
        }
    }

    public void showCallSortedByDate() {
        if (_entities == null)
            loadEntities();
        this._entities.sort(new ServiceByDateTimeComparator());
        for (Call call: _entities ) {
            System.out.println(call);
        }
    }

    public void showCallSortedBySource()  {
        if (_entities == null)
            loadEntities();
        this._entities.sort(new ServiceBySourceComparator());
        for (Call call: _entities ) {
            System.out.println(call);
        }
    }

    public void showCallSortedByDuration() {
        if (_entities == null)
            loadEntities();
        this._entities.sort(new CallByDurationComparator());
        for (Call call: _entities ) {
            System.out.println(call);
        }
    }

    public Call getEntityByID(int id){
        if (this._entities == null)
            loadEntities();
        for (Call call: _entities) {
            if(call.getServiceId() == id){
                return call;
            }
        }
        return null;
    }
}

