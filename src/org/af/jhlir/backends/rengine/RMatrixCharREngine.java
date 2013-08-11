//package org.af.jhlir.backends.rengine;
//
//import org.rosuda.REngine.*;
//import org.af.jhlir.call.RMatrixDouble;
//import org.af.jhlir.call.RMatrixChar;
//
//public class RMatrixCharREngine
//        extends RMatrixREngine<REXPString, REXPString, String[][], String>
//        implements RMatrixChar<REXPString> {
//
//    public RMatrixCharREngine(RCallServicesREngine rs, REXPString wrapped) {
//        super(rs, wrapped);
//    }
//
////    public String[][] getData() {
////        try {
////                String[] ct = getWrapped().asStrings();
////                REXPInteger dim = (REXPInteger)getWrapped().getAttribute("dim");
////                int[] ds = dim.asIntegers();
////                int m = ds[0], n = ds[1];
////                double[][] r=new double[m][n];
////                // R stores matrices as matrix(c(1,2,3,4),2,2) = col1:(1,2), col2:(3,4)
////                // we need to copy everything, since we create 2d array from 1d array
////                int i=0,k=0;
////                while (i<n) {
////                    int j=0;
////                    while (j<m) {
////                        r[j++][i]=ct[k++];
////                    }
////                    i++;
////                }
////                return r;
////            }
////        } catch (REXPMismatchException e) {}
////        return null;
////    }
//
//    protected String[][] createArr(int rows, int cols) {
//        return new String[rows][cols];
//    }
//
//
//    public String get(int i, int j) {
//        return getResolved().asStrings()[getIndex(i,j)];
//    }
//
//    public boolean isNA(int i, int j) {
////        return REXPString.isget(i,j).equals(RCallServicesREngine.);
//    }
//
//
//}