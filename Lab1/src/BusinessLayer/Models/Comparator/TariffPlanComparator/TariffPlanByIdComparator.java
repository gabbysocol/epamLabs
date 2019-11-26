package BusinessLayer.Models.Comparator.TariffPlanComparator;

import DataAccess.Models.TariffPlan;

import java.util.Comparator;

public class TariffPlanByIdComparator implements Comparator<TariffPlan> {
    @Override
    public int compare(TariffPlan tariffPlan1, TariffPlan tariffPlan2){
        return tariffPlan1.getId() - tariffPlan2.getId();
    }
}
