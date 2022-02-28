package net.intelie.challenges;

import static net.intelie.challenges.EventStoreImpl.eventList;

public class Main {


    public static void main(String[] args) {

        EventStore eventStore = new EventStoreImpl();

        eventStore.insert(new Event("A", 1L));
        eventStore.insert(new Event("B", 2L));
        eventStore.insert(new Event("C", 3L));
        eventStore.insert(new Event("D", 4L));
        eventStore.insert(new Event("A", 4L));
        eventStore.insert(new Event("A", 5L));
        eventStore.insert(new Event("C", 6L));
        eventStore.insert(new Event("A", 6L));
        eventStore.insert(new Event("D", 6L));
        eventStore.insert(new Event("A", 7L));

        System.out.println("Event List " + eventList);

        System.out.println("Remove type: C");

        eventStore.removeAll(("C"));

        System.out.println("Event List " + eventList);

        System.out.println("Query Events type A from 1 to 6");

        EventIterator iterator = eventStore.query("A", 1, 6);

        System.out.println(eventList);

        iterator.moveNext();

        System.out.println("Find and remove current event from: " + eventList);

        System.out.println(iterator.current());

        iterator.remove();

        System.out.println(eventList);


    }


}

