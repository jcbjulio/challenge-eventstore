package net.intelie.challenges;

import java.util.Iterator;

import static net.intelie.challenges.EventStoreImpl.eventList;

public class IteratorImp implements EventIterator {

    public EventStoreImpl eventStore;
    String type;
    long startTime;
    long endTime;
    int current;

    public IteratorImp(String type, long startTime, long endTime, EventStoreImpl eventStore) {
        this.eventStore = eventStore;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    @Override
    public boolean moveNext() {
        return false;
    }


    @Override
    public Event current() {
        return null;
    }

    @Override
    public void remove() {


    }

    @Override
    public void close() throws Exception {

    }
}
