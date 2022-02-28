package net.intelie.challenges;

import javafx.event.EventType;

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
    int curPos = 0;

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
        while (curPos < eventList.size())
                curPos++;
        return false;
    }

    @Override
    public Event current() {
        return eventList.get(curPos);


    }

    @Override
    public void remove() {

        eventList.remove(current());

    }

    @Override
    public void close() throws Exception {

    }
}
