package data_structures;

public class Request {

    private int requestID;
    private int cylinderNumber;

    public Request() {
        this(-1, -1);
    }

    public Request(int requestID, int cylinderNumber) {
        this.requestID = requestID;
        this.cylinderNumber = cylinderNumber;
    }

    public Request(Request rhs) {
        this.requestID = rhs.requestID;
        this.cylinderNumber = rhs.cylinderNumber;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getCylinderNumber() {
        return cylinderNumber;
    }

    public void setCylinderNumber(int cylinderNumber) {
        this.cylinderNumber = cylinderNumber;
    }

    @Override
    public String toString() {
        return requestID + " " + cylinderNumber;
    }
}
