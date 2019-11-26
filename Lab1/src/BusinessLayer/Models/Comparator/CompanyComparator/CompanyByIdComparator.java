package BusinessLayer.Models.Comparator.CompanyComparator;

import DataAccess.Models.Company;

import java.util.Comparator;

public class CompanyByIdComparator implements Comparator<Company> {
    @Override
    public int compare(Company company1, Company company2) {
        return company1.getCompanyId() - company2.getCompanyId();
    }
}