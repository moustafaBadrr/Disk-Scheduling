package data_structures;

public class HardDisk {

    private int numberOfCylinders;
    private int currentHeadPosition;

    public HardDisk(int numberOfCylinders, int currentHeadPosition) {
        this.numberOfCylinders = numberOfCylinders;
        this.currentHeadPosition = currentHeadPosition;
    }

    public HardDisk(int numberOfCylinders) {
        this(numberOfCylinders, 0);
    }

    public HardDisk(HardDisk rhs) {
        this.numberOfCylinders = rhs.numberOfCylinders;
        this.currentHeadPosition = rhs.currentHeadPosition;
    }

    public int getNumberOfCylinders() {
        return numberOfCylinders;
    }

    public void setNumberOfCylinders(int numberOfCylinders) {
        this.numberOfCylinders = numberOfCylinders;
    }

    public int getCurrentHeadPosition() {
        return currentHeadPosition;
    }

    public void setCurrentHeadPosition(int currentHeadPosition) {
        this.currentHeadPosition = currentHeadPosition;
    }

    @Override
    public String toString() {
        return numberOfCylinders + " " + currentHeadPosition;
    }
}
