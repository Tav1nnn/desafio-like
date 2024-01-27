package Service;

import dto.EntryBudget;
import dto.EntryBudgetProduct;
import dto.ExitBudget;
import dto.ExitBudgetProduct;
import org.springframework.stereotype.Service;
import repository.BudgetRepositoy;

import java.util.HashSet;
import java.util.Set;

@Service
public class BudgetService {

    private final BudgetRepositoy budgetRepositoy;

    public BudgetService(BudgetRepositoy budgetRepositoy) {
        this.budgetRepositoy = budgetRepositoy;
    }

    public ExitBudget calculateBudget(EntryBudget entryBudget) {
        Set<ExitBudgetProduct> exitBudgetProductSet = new HashSet<>();
        double totalBudget = 0.0;

        for(EntryBudgetProduct product : entryBudget.products()) {
            double totalValue = product.quantity()* product.productValue();
            totalBudget += totalValue;
            ExitBudgetProduct exitProduct = new ExitBudgetProduct(
                    product.productName(),
                    product.productValue(),
                    product.quantity(),
                    totalValue
            );
            exitBudgetProductSet.add(exitProduct);
        }

        return new ExitBudget(
                entryBudget.clientName(),
                entryBudget.date(),
                exitBudgetProductSet,
                totalBudget
        );
    }
}
