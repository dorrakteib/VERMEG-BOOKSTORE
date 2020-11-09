public class Purchase {

    private int qty;
    private float unitP;

    public Purchase(int qty, double unitP) {
        this.qty = qty;
        this.unitP =(float) unitP;
    }

    public float calculateTotalPrice(){
        return qty*unitP;
    }


    @Override
    public String toString() {
        return "\nPurchase:" +
                "\n\tUnit price: " + unitP + " dt\n\tQuantity: " + qty ;
    }
}
