package winchester.library.data.model.items;

public class ItemStock {

    private final Item item;
    private final Enum<?> subtype;
    private int copiesAvailable;
    private int copiesOnLoan;

    public ItemStock(Item item, Enum<?> subtype, int copiesAvailable, int copiesOnLoan) {
        this.item = item;
        this.subtype = subtype;
        this.copiesAvailable = copiesAvailable;
        this.copiesOnLoan = copiesOnLoan;
    }

    public ItemType getItemType() {
        return this.item.getType();
    }

    public Item getItem() {
        return switch (this.item.getType()) {
            case BOOK -> (Book) this.item;
            case FILM -> (Film) this.item;
        };
    }

    public Enum<?> getItemSubType() {
        return switch (this.item.getType()) {
            case BOOK -> (BookFormat)subtype;
            case FILM -> (FilmFormat)subtype;
        };
    }

    public int getCopiesAvailable() {
        return this.copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public int getCopiesOnLoan() {
        return this.copiesOnLoan;
    }

    public void setCopiesOnLoan(int copiesOnLoan) {
        this.copiesOnLoan = copiesOnLoan;
    }
}
