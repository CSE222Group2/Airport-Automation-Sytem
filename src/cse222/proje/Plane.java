package cse222.proje;

import java.util.Objects;

public class Plane {
	int planeID;
	StateOfPlane stateOfPlane;

	int uniqePlaneId;

	public int getUniqePlaneId() {
		return uniqePlaneId;
	}

	public void setUniqePlaneId(int iD) {
		this.uniqePlaneId = iD;
	}

	public Plane() {

	}

	public enum StateOfPlane{
		ReadyToFly, Flying, Landed  ;
	}
	public Plane(int planeID,int uniqePlaneId) {
		this.planeID = planeID;
		this.uniqePlaneId = uniqePlaneId;
	}

	public int getPlaneID() {
		return planeID;
	}

	public void setPlaneID(int id) { planeID = id; };

	public void setReadinessOfPlane(StateOfPlane stateOfPlane) {
		this.stateOfPlane = stateOfPlane;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Plane)) return false;
		Plane plane = (Plane) o;
		return Objects.equals(planeID, plane.planeID);
	}

}