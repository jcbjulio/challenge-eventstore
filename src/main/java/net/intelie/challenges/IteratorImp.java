package net.intelie.challenges;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.intelie.challenges.EventStoreImpl.eventList;

public class IteratorImp implements EventIterator {

    public EventStoreImpl eventStore;
    String type;
    long startTime;
    long endTime;
    int curPos = -1;
    public ArrayList<Event> eventStream;


    public IteratorImp(String type, long startTime, long endTime, EventStoreImpl eventStore) {
        this.eventStore = eventStore;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;

        eventList = (ArrayList<Event>) eventList.stream().filter(event -> event.type() == type
                && event.timestamp() >= startTime
                && event.timestamp() < endTime).collect(Collectors.toList());
    }

    @Override
    public boolean moveNext() {
        if (eventList.iterator().hasNext()) curPos++;
        return false;
    }

    @Override
    public Event current() {
        return eventList.get(curPos);

    }


    @Override
    public void remove() {

        eventList.remove(curPos);

    }

    @Override
    public void close() throws Exception {

    }
}
