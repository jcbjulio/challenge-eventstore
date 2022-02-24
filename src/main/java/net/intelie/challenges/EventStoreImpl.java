package net.intelie.challenges;

import java.util.ArrayList;
import java.util.Objects;


public class EventStoreImpl implements EventStore {

    public static ArrayList<Event> eventList = new ArrayList<>();

    @Override
    public void insert(Event event) {
        if (event == null || event.type() == null) {
            return;
        }
        String type = event.type();
        long timestamp = event.timestamp();
        eventList.add(new Event(type, timestamp));
    }

    @Override
    public void removeAll(String type) {

        eventList.removeIf(event -> Objects.equals(event.type(), type));

    }

    @Override
    public EventIterator query(String type, long startTime, long endTime) {

        EventIterator iterator = new IteratorImp(type, startTime, endTime, this);


//        Stream<Event> eventStream = eventList.stream().filter(event -> event.timestamp() >= startTime && event.timestamp() < endTime);

        return iterator;


    }
}