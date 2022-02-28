package net.intelie.challenges;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static net.intelie.challenges.EventStoreImpl.eventList;

public class IteratorImp implements EventIterator {

    public EventStoreImpl eventStore;
    String type;
    long startTime;
    long endTime;
    int curPos = 0;
    static List<Event> qList = new ArrayList<>();

    public IteratorImp(String type, long startTime, long endTime, EventStoreImpl eventStore) {
        this.eventStore = eventStore;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        qList = eventList.stream().filter(event -> Objects.equals(event.type(), type)
                && event.timestamp() >= startTime
                && event.timestamp() < endTime).collect(Collectors.toList());
    }

    @Override
    public boolean moveNext() {
        if (curPos < qList.size()) {
            curPos++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Event current() {
        if (curPos < qList.size()) {
            return qList.get(curPos);
        } else
            throw new IllegalStateException("End of List");
    }

    @Override
    public void remove() {
        if (curPos < qList.size()) {
            qList.remove(curPos);
        } else
            throw new IllegalStateException("End of List");
    }


    @Override
    public void close() throws Exception {
        IllegalStateException Exception = new IllegalStateException("End of List");

    }
}
