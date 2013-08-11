package test;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;

import org.rosuda.REngine.REXPDouble;

//class REXPDouble2 extends REXPDouble {
//    public REXPDouble2(double load) {
//        super(load);
//    }
//    public REXPDouble2() {
//        super(new double[0]);
//    }
//}

class REXPDouble3 extends REXPDouble implements Externalizable {
    public REXPDouble3() {
        super(new double[0]);
    }

    public REXPDouble3(double load) {
        super(load);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("writeExternal");
        out.writeObject(asDoubles());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("readExternal");
        double[] payload = (double[]) in.readObject();
        System.out.println(Arrays.toString(payload));
        try {
            Field f = REXPDouble.class.getDeclaredField("payload");
            f.setAccessible(true);
            f.set(this, payload);
            f.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();            
        }
    }
}




public class SerializationTest implements Serializable {
    REXPDouble3 value;

    SerializationTest(REXPDouble3 value) {
        this.value = value;
    }

    public String toString() {
        double[] doubles = value.asDoubles();
        return doubles[0] + " : " + Double.doubleToRawLongBits(doubles[0]) + " : " + REXPDouble.isNA(doubles[0]) + " : " + REXPDouble.NA;
    }

    public void write(String filename) {
        File f = new File(filename);
        try {
            ObjectOutputStream stream =
                    new ObjectOutputStream(
                            new FileOutputStream(f));
            stream.writeObject(this);
            stream.close();
        } catch (IOException e) {
            System.out.println(" erreur :" + e.toString());
            e.printStackTrace();
        }
    }

    public void read(String filename) {
        File f = new File(filename);
        try {
            ObjectInputStream stream =
                    new ObjectInputStream(
                            new FileInputStream(f));
            SerializationTest data = (SerializationTest) stream.readObject();
            value = data.value;
            stream.close();
        } catch (Exception e) {
            System.out.println(" erreur :" + e.toString());
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        REXPDouble rd = new REXPDouble(REXPDouble.NA);
        System.out.println(isNA(rd.asDoubles()[0]));
        rd = new REXPDouble(Double.longBitsToDouble(0x7ff00000000007a2L));
        System.out.println(isNA(rd.asDoubles()[0]));
        rd = new REXPDouble(Double.NaN);
        System.out.println(isNA(rd.asDoubles()[0]));

  
//        test(new SerializationTest(new REXPDouble3(2.0)));
//        test(new SerializationTest(new REXPDouble3(Double.longBitsToDouble(0x7ff00000000007a2L))));
//        test(new SerializationTest(new REXPDouble3(Double.NaN)));
    }

    public static void test(SerializationTest x) {
        System.out.println("before serialization : " + x);
        x.write("c:/x");
        x.read("c:/x");
        System.out.println("after serialization  : " + x + "\n");
    }

    public static boolean isNA(double value) {
        return Double.doubleToRawLongBits(value) == Double.doubleToRawLongBits(REXPDouble.NA);
    }

}



