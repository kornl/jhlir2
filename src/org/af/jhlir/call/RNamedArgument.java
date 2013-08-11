package org.af.jhlir.call;

public class RNamedArgument {
	RObj robj;
	String name;
	
	public RNamedArgument(String name, RObj robj) {
		this.robj = robj;
		this.name = name;
	}
	
	public String toString() {
		return name+"="+robj;
	}
	
	public RObj getRobj() {
		return robj;
	}

	public String getName() {
		return name;
	}
}
