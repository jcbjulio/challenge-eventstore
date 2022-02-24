package net.intelie.challenges;

import java.util.Iterator;

import static net.intelie.challenges.EventStoreImpl.eventList;

public class IteratorImp implements EventIterator {

    public EventStoreImpl eventStore;
    String type;
    long startTime;
    long endTime;
    int curPos = -1;

    public IteratorImp(String type, long startTime, long endTime, EventStoreImpl eventStore) {
        this.eventStore = eventStore;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    @Override
    public boolean moveNext() {

        if (eventList.iterator().hasNext())
            curPos++;
        return true;
    }


    @Override
    public Event current() {
        {
            return eventList.get(curPos);
        }
    }

    @Override
    public void remove() {

        eventList.remove(curPos);

    }

    @Override
    public void close() throws Exception {

    }
}
