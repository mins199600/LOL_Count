package hello.lol.item.repository;

public class ItemDto {
    private int craftedId;
    private String craftedName;
    private String craftedImageUrl;
    private String materialName1;
    private String materialImageUrl1;
    private String materialName2;
    private String materialImageUrl2;

    // Getters and Setters
    public int getCraftedId() {
        return craftedId;
    }

    public void setCraftedId(int craftedId) {
        this.craftedId = craftedId;
    }

    public String getCraftedName() {
        return craftedName;
    }

    public void setCraftedName(String craftedName) {
        this.craftedName = craftedName;
    }

    public String getCraftedImageUrl() {
        return craftedImageUrl;
    }

    public void setCraftedImageUrl(String craftedImageUrl) {
        this.craftedImageUrl = craftedImageUrl;
    }

    public String getMaterialName1() {
        return materialName1;
    }

    public void setMaterialName1(String materialName1) {
        this.materialName1 = materialName1;
    }

    public String getMaterialImageUrl1() {
        return materialImageUrl1;
    }

    public void setMaterialImageUrl1(String materialImageUrl1) {
        this.materialImageUrl1 = materialImageUrl1;
    }

    public String getMaterialName2() {
        return materialName2;
    }

    public void setMaterialName2(String materialName2) {
        this.materialName2 = materialName2;
    }

    public String getMaterialImageUrl2() {
        return materialImageUrl2;
    }

    public void setMaterialImageUrl2(String materialImageUrl2) {
        this.materialImageUrl2 = materialImageUrl2;
    }
}