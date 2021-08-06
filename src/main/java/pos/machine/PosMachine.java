package pos.machine;

import java.util.ArrayList;
import java.util.List;

import static pos.machine.ItemDataLoader.retreiveItemInfos;

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
        List<ItemInfo> itemInfos = retreiveItemInfos();

        for (String data : loadBarcodes) {
            if(barcodeList.contains(data)){
                //System.out.println(data);
            }
            else{
                barcodeList.add(data);
            }
        }
        for (String barcode : barcodeList) {
            for (ItemInfo infos : itemInfos) {
                if (infos.getBarcode().equals(barcode)) {
                    ItemDetails items = new ItemDetails(infos.getBarcode(),infos.getName(),0,infos.getPrice(),0);
                    receipt.add(items);
                }
            }
        }

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
            itemDetails.setSubtotal(itemDetails.getQuantity() * itemDetails.getUnitPrice());
    }

    private void getItemQuantity(List<String> loadBarcodes, List<ItemDetails> itemList) {
        int quantity = 0;
        for(ItemDetails itemDetails : itemList){
            quantity = 0;
            for (String barcode: loadBarcodes)
            {
                quantity = itemDetails.getBarcode().equals(barcode) ? quantity+1 : quantity;
            }
            itemDetails.setQuantity(quantity);
        }
    }
}
