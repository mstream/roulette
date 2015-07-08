package io.mstream.roulette.events;

public class BallStopped implements Event {

    private final int slotNumber;

    public BallStopped(int slotNumber) {
        this.slotNumber = slotNumber;
    }
}
