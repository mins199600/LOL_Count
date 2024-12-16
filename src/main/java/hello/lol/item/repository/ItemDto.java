package hello.lol.item.repository;

public class ItemDto {
    private int craftedId;
    private int materialId1;
    private int materialId2;

    public int getCraftedId() {
        return craftedId;
    }

    public void setCraftedId(int craftedId) {
        this.craftedId = craftedId;
    }

    public int getMaterialId1() {
        return materialId1;
    }

    public void setMaterialId1(int materialId1) {
        this.materialId1 = materialId1;
    }

    public int getMaterialId2() {
        return materialId2;
    }

    public void setMaterialId2(int materialId2) {
        this.materialId2 = materialId2;
    }
}