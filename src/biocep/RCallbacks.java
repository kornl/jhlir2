//package biocep;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.rosuda.JRI.RMainLoopCallbacks;
//import org.rosuda.JRI.Rengine;
//
//public class RCallbacks implements RMainLoopCallbacks {
//    private static Log logger = LogFactory.getLog(RCallbacks.class);
//
//    public void rBusy(Rengine rengine, int i) {
//        logger.debug("R Message: R is busy!");
//    }
//
//    public String rChooseFile(Rengine rengine, int i) {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void rFlushConsole(Rengine rengine) {
//        //logger..out.flush();
//    }
//
//    public void rLoadHistory(Rengine rengine, String string) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public String rReadConsole(Rengine rengine, String string, int i) {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void rSaveHistory(Rengine rengine, String string) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void rShowMessage(Rengine rengine, String string) {
//        logger.info("R Message: " + string);
//    }
//
//    public void rWriteConsole(Rengine rengine, String string) {
//        logger.info("R Console: " + string);
//    }
//
//    public void rWriteConsole(Rengine rengine, String string, int i) {
//        logger.info("R Console: " + string);
//    }
//}
