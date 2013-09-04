package junit;

import org.af.jhlir.backends.rengine.RCallServices;
import org.junit.Before;
import org.rosuda.REngine.JRI.JRIEngine;


public abstract class MyTestCase {
    private static RCallServices rs;
    
    public static Double eps = 0.0001;


    @Before
    public void setUp() throws Exception {
        if (rs == null) {
            if (this.getClass().toString().startsWith("class junit.JRI")) {
                rs = new RCallServices(new JRIEngine());
            }
        }
    }

    public RCallServices getRServices() {
        return rs;
    }


//    protected void tearDown() {
//        try {
//            if (this.getClass().toString().startsWith("class junit.biocep")) {
//                ((RCallServicesBiocep) rs).getRServices().stop();
//            }
//            if (this.getClass().toString().startsWith("class junit.JRI")) {
//                boolean b = ((JRIEngine)((RCallServicesREngine) rs).getREngine()).close() ;
//                System.out.println(b);
//            }
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }

}
