public class CashType {

    public static long moneyAmount;

    private static Cash type;


    public CashType(long moneyAmount) {
        this.moneyAmount=moneyAmount;
        type = Cash.Analysis;
    }

    public static void setType(Cash type) {
        CashType.type = type;
    }

    public static Cash getType() {
        return type;
    }

    public void doAction (){
        type.doAction(this);
    }
}
