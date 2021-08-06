package pos.machine;

import java.util.List;

public class Receipt {
    private List<ItemDetails> itemDetails;
    private int totalPrice;

    public Receipt(List<ItemDetails> itemDetails, int totalPrice) {
        this.itemDetails = itemDetails;
        this.totalPrice = totalPrice;
    }

    public List<ItemDetails> getItemDetails()
    {
        return itemDetails;
    }

    public int computeTotalPrice()
    {
        return totalPrice;
    }
}
