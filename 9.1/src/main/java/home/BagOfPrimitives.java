package home;


import java.util.ArrayList;
import java.util.List;

class BagOfPrimitives {
    private final int value1;
    private final String value2;
    List <Integer> value3=new ArrayList<>();
    //private final int value3;

    public BagOfPrimitives(int value1, String value2, List value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public BagOfPrimitives() {
        value1 = 1;
        value2 = "abc";
        value3.add(10);
        value3.add(15);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BagOfPrimitives that = (BagOfPrimitives) o;

        if (value1 != that.value1) return false;
        if (value3 != that.value3) return false;
        return value2 != null ? value2.equals(that.value2) : that.value2 == null;
    }

    @Override
    public String toString() {
        return "BagOfPrimitives{" + "value1=" + value1 + ", value2='" + value2 + '\'' + ", value3=" + value3 + '}';
    }


}
