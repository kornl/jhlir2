package test;

public class Test {
    public static void main(String[] args) {

        try {
            Runtime.getRuntime().load("C:\\Programme\\R\\R-2.9.1\\library\\rJava\\jri\\jri.dll");
//            Rengine re = new Rengine();
//        System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
