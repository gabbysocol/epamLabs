package BusinessLayer.Models.Comparator.CompanyComparator;

import DataAccess.Models.Company;

import java.util.Comparator;

public class CompanyByNameComparator implements Comparator<Company> {
    @Override
    public int compare(Company company1, Company company2) {
        return company1.getName().compareTo(company2.getName());
    }
}
