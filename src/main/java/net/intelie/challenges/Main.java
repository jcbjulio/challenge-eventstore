package net.intelie.challenges;

import static net.intelie.challenges.EventStoreImpl.eventList;

public class Main {


    public static void main(String[] args) {

        EventStore eventStore = new EventStoreImpl();

        eventStore.insert(new Event("A", 1L));
        eventStore.insert(new Event("B", 2L));
        eventStore.insert(new Event("C", 3L));
        eventStore.insert(new Event("D", 4L));
        eventStore.insert(new Event("A", 2L));
        eventStore.insert(new Event("C", 5L));

        System.out.println("Event List " + eventList);
        String type = "C";
        System.out.println("Remove type:" + type);

        eventStore.removeAll(type);

        System.out.println("Event List " + eventList);

        EventIterator iterator = eventStore.query("A", 1, 5);

        System.out.println("List has next: " + iterator.moveNext());

        System.out.println("Find and remove specific event from: " + eventList);

        System.out.println(iterator.current());

        iterator.remove();

        System.out.println(eventList);

        eventStore.insert(new Event("A", 2L));
        eventStore.insert(new Event("C", 3L));
        eventStore.insert(new Event("C", 5L));
        eventStore.insert(new Event("D", 6L));
        eventStore.insert(new Event("E", 6L));


    }


}

