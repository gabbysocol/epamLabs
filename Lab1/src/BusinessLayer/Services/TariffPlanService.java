package BusinessLayer.Services;

import BusinessLayer.Models.Comparator.TariffPlanComparator.TariffPlanByIdComparator;
import BusinessLayer.Models.Comparator.TariffPlanComparator.TariffPlanBySubscriptionFreeComparator;
import DataAccess.Models.TariffPlan;

public class TariffPlanService extends BaseService<TariffPlan> {
    private String _fileName;
    public TariffPlanService(String fileName){
        super(fileName);
        this._fileName = fileName;
    }

    public TariffPlanService(){
        super("tariffPlan.txt");
        this._fileName = "tariffPlan.txt";
    }

    public void showTariffPlanSortedById(){
        if (this._entities == null)
            loadEntities();
        this._entities.sort(new TariffPlanByIdComparator());
        for (TariffPlan client: _entities ) {
            System.out.println(client);
        }
    }

    public void showTariffPlanSortedBySubscriptionFree(){
        if (this._entities == null)
            loadEntities();
        this._entities.sort(new TariffPlanBySubscriptionFreeComparator());
        for (TariffPlan client: _entities ) {
            System.out.println(client);
        }
    }

    public TariffPlan getEntityByID(int id){
        if (this._entities == null)
            loadEntities();
        for (TariffPlan entity: this._entities) {
            if(entity.getId() == id){
                return entity;
            }
        }
        return null;
    }
}
