package com.tyrael.laundry.reference;

/**
 * @author mbmartinez
 */
public enum JobItemType {

    HANDKERCHIEF("Handkerchief"),
    UNDERWEAR("Underwear"),
    SOCKS("Socks"),
    SHORTS("Shorts"),
    PAJAMA("Pajama"),
    BLOUSE("Blouse"),
    JACKET("Jacket"),
    DRESS("Dress"),
    SHIRT_COLLAR("T-shirt w/ collar"),
    SHIRT_SS("Shirt, short sleeves"),
    SHIRT_LS("Shirt, long sleeves"),
    SLACKS("Slacks, pants, trousers");

    private String label;
    private JobItemType(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
