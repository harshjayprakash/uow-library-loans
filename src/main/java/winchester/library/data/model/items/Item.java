package winchester.library.data.model.items;

import java.util.ArrayList;
import winchester.library.data.model.loans.LoansManager;

/**
 * A base item class to represent the image, loans and stock available.
 */
public abstract class Item {

    protected ArrayList<ItemStock> stockAvailable;
    protected LoansManager loans;
    protected String imageUrl;

    /**
     * The default constructor for the Item class, initialising the stock available and loans manager.
     */
    public Item() {
        this.stockAvailable = new ArrayList<>();
        this.loans = new LoansManager();
    }

    /**
     * An accessor to retrieve the stock available.
     * @return an array list of the item stock available.
     */
    public ArrayList<ItemStock> getStockAvailable() {
        return this.stockAvailable;
    }

    /**
     * An accessor to retrieve the loans associated to the item.
     * @return the LoansManager instance associated to the item.
     */
    public LoansManager getLoansManager() {
        return this.loans;
    }

    /**
     * An accessor to retrieve the image of the item.
     * @return the image url of image of the item.
     */
    public String getImageUrl() {
        return this.imageUrl;
    }

    /**
     * A mutator to assign the image of the item.
     * @param url the image url of the item cover.
     */
    public void setImageUrl(String url) {
        this.imageUrl = url;
    }

    /**
     * An accessor for the item type.
     * @return a ItemType enumeration constant.
     */
    public abstract ItemType getType();

}
