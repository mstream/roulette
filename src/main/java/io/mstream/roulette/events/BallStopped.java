package io.mstream.roulette.events;

public class BallStopped {

    private final int slotNumber;

    public BallStopped(int slotNumber) {
        this.slotNumber = slotNumber;
    }
}
