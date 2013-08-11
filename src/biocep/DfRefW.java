//package biocep;
//
//import org.kchine.r.*;
//import org.kchine.r.server.RServices;
//import org.kchine.r.server.ReferenceInterface;
//
//import java.rmi.RemoteException;
//
//public class DfRefW extends RDataFrameBiocep {
//    protected String name;
//
//	public DfRefW(RServices rs, RDataFrameRef dfRef) {
//        super(rs, dfRef);
//    }
//
//
//    // works also if you pass the NA_VAL
//    public void setValue(int row, int col, Object val) throws RemoteException{
//        rs.assignReference("df", getReference());
//        rs.putAndAssign(val, "v");
//        rs.evaluate("df["+(row+1)+","+(col+1)+"]<-v");
//        setRObj((RDataFrameRef)rs.getReference("df"));
//    }
//
//    public <E extends RObject> void changeColumnType(int col, Class<E> type) throws RemoteException {
//        if (!RFactor.class.isAssignableFrom(type) && !RVector.class.isAssignableFrom(type))
//            throw new RuntimeException("Cannot convert dataframe column to " + type);
//
//        RVectorFactorBiocep colWrapper = getColW(col);
//        RList data = getObj().getData();
//
//        RObject newCol = colWrapper.convert(type);
//        setCol(col, (RVector)newCol);
//    }
//
//////    public String toString() {
//////        String s = "";
//////
//////        for (int i=0; i<getRowNr(); i++) {
//////            for (int j=0; j<getColNr(); j++) {
//////                s += getElement(i,j) + "   ";
//////            }
//////            s += "\n";
//////        }
//////        return s;
//////    }
//
//    // change object state
//
//
//    // TODO this will be very slow
//    public void setVarName(RLegalName oldName, RLegalName newName) throws RemoteException{
//        int i = getColIndex(oldName);
//        rs.assignReference("df", getReference());
//        rs.evaluate("names(df)["+(i+1)+"]<-\"" + newName.toString() + "\"");
//        setRObj((RDataFrameRef)rs.getReference("df"));
//    }
//
//    // adds row of NAs, ith row will be shifted downwards
//    //TODO use [
//    //TODO add constraints for row
//    public void addRow(int row) throws RemoteException{
//        rs.assignReference("df", getReference());
//        String exp1 = "seq(1," + row + ", length=" + row + ")";
//        String exp2 = "seq(" + (row+1) + ",nrow(df), length=nrow(df)-" + row + ")";
//        String exp3 = "rbind(df[" + exp1 + ",,drop=F], NA, df[" + exp2 + ",,drop=F])";
//        setRObj((RDataFrameRef)rs.getReference(exp3));
//    }
//
//    public void setCol(int col, RVector v) throws RemoteException{
//        rs.assignReference("df", getReference());
//        if (v instanceof ReferenceInterface)
//            rs.assignReference("v", v);
//        else
//            rs.putAndAssign(v, "v");
//        rs.evaluate("df[,"+(col+1)+"]<-v");
//        setRObj((RDataFrameRef)rs.getReference("df"));
//    }
//
//    private void addCol(int col, String name, RObject v) throws RemoteException{
//        rs.assignReference("df", getReference());
//        if (v instanceof ReferenceInterface)
//            rs.assignReference("v", v);
//        else
//            rs.putAndAssign(v, "v");
//        String exp1 = "seq(1," + col + ", length=" + col + ")";
//        String exp2 = "seq(" + (col+1) + ",ncol(df), length=ncol(df)-" + col + ")";
//        String exp3 = "cbind(df[," + exp1 + ",drop=F]," + name + "=v, df[," + exp2 + ",drop=F], stringsAsFactors=F)";
//        setRObj((RDataFrameRef)rs.getReference(exp3));
//    }
//
//    public void addCol(int col, RLegalName name, RVector v) throws RemoteException{
//        addCol(col, name.getName(), v);
//    }
//
//    public void addCol(int col, RLegalName name, RFactor v) throws RemoteException{
//        addCol(col, name.getName(), v);
//    }
//
//    public void addCol(RLegalName name, RVectorFactorBiocep v) throws RemoteException{
//        addCol(getColNr(), name.getName(), v.getObj());
//    }
//
//    public void delCol(int col) throws RemoteException{
//        setRObj((RDataFrameRef)rs.callAndGetReference("\"[\"", getObj(), true, -(col+1), false));
//    }
//
//    public void delRow(int row) throws RemoteException{
//        setRObj((RDataFrameRef)rs.callAndGetReference("\"[\"", getObj(), -(row+1), true, false));
//    }
//
//    public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//}
