package home;


import java.util.ArrayList;
import java.util.List;

class BagOfPrimitives {
    private final int value1;
    private final String value2;
    List <Integer> value3=new ArrayList<>();
    Object value4;
    String value5;


    public BagOfPrimitives(int value1, String value2, List value3, Object value4, String value5) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4=value4;
        this.value5=value5;
    }

    public BagOfPrimitives() {
        value1 = 1;
        value2 = "abc";
        value3.add(10);
        value3.add(15);
        value4 = new int[] {1, 2, 3};
        String value5=null;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BagOfPrimitives that = (BagOfPrimitives) o;

        if (value1 != that.value1) return false;
        if (value3 != that.value3) return false;
        if (value4 != that.value4) return false;
        if (value5 != that.value5) return false;
        if (value2 != null) return value2.equals(that.value2);
        else return that.value2 == null;
    }

    @Override
    public String toString() {
        return "BagOfPrimitives{" + "value1=" + value1 + ", value2='" + value2 + '\'' + ", value3=" + value3 +", value4="
                + value4.toString() + ", value5=" + value5 + '}';
    }


}
