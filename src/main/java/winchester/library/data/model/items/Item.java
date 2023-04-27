package winchester.library.data.model.items;

import java.util.ArrayList;
import winchester.library.data.model.loans.LoansManager;

public abstract class Item {

    protected ArrayList<ItemStock> stockAvailable;
    protected LoansManager loans;

    public Item() {
        this.stockAvailable = new ArrayList<>();
        this.loans = new LoansManager();
    }

    public ArrayList<ItemStock> getStockAvailable() {
        return this.stockAvailable;
    }

    public LoansManager getLoans() {
        return this.loans;
    }

    public abstract ItemType getType();

}
