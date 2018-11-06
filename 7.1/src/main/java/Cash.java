
public enum Cash {

    Analysis {
        public void doAction (CashType type){
            if ((CashType.moneyAmount%200)==0){
                CashType.setType(Multiple200);}
            else {if ((CashType.moneyAmount%100)==0)
                CashType.setType(Multiple100);
            else {CashType.setType(Multiple50);}}
        }
    },

    Multiple50 {
        public void doAction (CashType type){
            System.out.println("Operations with cash multiple 50");
        }
    },

    Multiple100 {
        public void doAction (CashType type){
            System.out.println("Operations with cash multiple 100");
        }

    },

    Multiple200 {
        public void doAction (CashType type){
            System.out.println("Operations with cash multiple 200");
        }
    };

    public abstract void doAction(CashType context);
}
