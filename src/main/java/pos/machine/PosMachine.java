package pos.machine;

import java.util.ArrayList;
import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
    }

    public List<ItemInfo> getBarcodeDetails(List<String> barcodes){
        List<ItemInfo> itemDetails = new ArrayList<>();
        return itemDetails;
    }


    public List<ItemInfo> computeSubtotal(List<ItemInfo> itemDetails)
    {
        List<ItemInfo> itemsSubtotal = new ArrayList<>();
        return itemsSubtotal;
    }

    public List<ItemInfo> computeTotalPrice(List<ItemInfo> itemsSubtotal)
    {
        List<ItemInfo> receipt = new ArrayList<>();
        return receipt;
    }


}
