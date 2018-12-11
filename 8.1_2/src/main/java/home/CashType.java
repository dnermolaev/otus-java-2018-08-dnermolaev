package home;


   enum CashType {
       notes50(50),

       notes100(100),
       notes500(500),
       notes1000(1000),
       notes5000(5000);



    private int nominal;

       CashType(int nominal) {
           this.nominal = nominal;
       }



       public int getNominal() {
           return nominal;
       }

   }

