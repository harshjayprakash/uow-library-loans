package winchester.library.data.model.items;

import java.util.Optional;
import winchester.library.data.access.DatabaseEntity;

@DatabaseEntity(table = "item_types")
public enum ItemType {
    BOOK(1, "Book"),
    FILM(2, "Film");

    private final int identifier;
    private final String value;

    ItemType(int identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public static Optional<ItemType> fromIdentifier(int identifier) {
        for (ItemType type : ItemType.values()) {
            if (type.identifier == identifier) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return this.value;
    }
}
