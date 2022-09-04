public class PricePerDistrict {
    int price;
    String district;


    public PricePerDistrict(int price, String district) {
        this.price = price;
        this.district = district;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "Квартал - " + district+ " |||||||| " + " Цена - " + price;
    }
}
