package pos.machine;

import java.util.ArrayList;
import java.util.List;

import static pos.machine.ItemDataLoader.retriveItemInfo;

public class PosMachine {
    int totalPrice;
    int total;

    public String printReceipt(List<String> loadBarcodes) {
        List<ItemDetails> itemList = getBarcodeDetails(loadBarcodes);
        getItemQuantity(loadBarcodes,itemList);
        Receipt receipt = retrieveItemInfo(itemList);

        return renderReceipt(receipt);
    }

    private String renderReceipt(Receipt receipt) {
        String receiptOutput = "***<store earning no money>Receipt***\n";
        
        for (ItemDetails details : receipt.getItemDetails()) {
            receiptOutput = receiptOutput.concat(String.format("Name: %s, ", details.getName()));
            receiptOutput = receiptOutput.concat(String.format("Quantity: %s, ",details.getQuantity()));
            receiptOutput =  receiptOutput.concat(String.format("Unit price: %s (yuan), ", details.getUnitPrice()));
            receiptOutput =  receiptOutput.concat(String.format("Subtotal: %s (yuan)", details.getSubtotal()));
            receiptOutput =  receiptOutput.concat("\n");
        }
        receiptOutput = receiptOutput.concat("----------------------\n");
        receiptOutput = receiptOutput.concat("Total: " + receipt.computeTotalPrice());
        receiptOutput = receiptOutput.concat(" (yuan)\n**********************");


        return receiptOutput;
    }

    private List<ItemDetails> getBarcodeDetails(List<String> loadBarcodes) {
        List<String> barcodeList = new ArrayList<>();
        List<ItemDetails> receipt = new ArrayList<>();
        List<ItemInfo> itemInfos = retriveItemInfo();

        return receipt;
    }

    private Receipt retrieveItemInfo(List<ItemDetails> itemList) {
        computeSubtotal(itemList);
        totalPrice = computeTotalPrice(itemList);
        return new Receipt(itemList,totalPrice);
    }

    private int computeTotalPrice(List<ItemDetails> itemList) {
        total = 0;
        for(ItemDetails itemDetails : itemList)
            total += itemDetails.getSubtotal();

        return total;
    }

    private void computeSubtotal(List<ItemDetails> itemList) {
        for (ItemDetails itemDetails : itemList)
            itemDetails.setSubtotal(itemDetails.getSubtotal() * itemDetails.getUnitPrice());
    }

    private void getItemQuantity(List<String> loadBarcodes, List<ItemDetails> itemList) {
        int quantity;
        for(ItemDetails itemDetails : itemList){
            quantity = 0;
            for (String barcode: loadBarcodes)
            {
                quantity = itemDetails.getBarcode().equals(loadBarcodes) ? quantity + 1 : quantity;
            }
            itemDetails.setQuantity(quantity);
        }
    }
}
