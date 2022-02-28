package net.intelie.challenges;

public class ClientExample {

  public static void main(String arg[]){
    EventStore store = new EventStoreImpl();
    store.insert(null);
    store.insert(new Event(null, 1L));

    store.insert(new Event("A", 1L));
    store.insert(new Event("B", 1L));
    store.insert(new Event("B", 2L));
    store.insert(new Event("C", 1L));
    store.insert(new Event("A", 2L));
    store.insert(new Event("A", 3L));
    store.insert(new Event("A", 4L));
    store.insert(new Event("A", 5L));
    readContent(store.query("C", 0L, 1000L));
    store.removeAll("B");

    System.out.println("No events of B");
    readContent(store.query("B", 0L, 1000L));

    System.out.println("No events of A between this timestamp range");
    readContent(store.query("A", 1000L, 1001L));

    System.out.println("All events of A");
    readContent(store.query("A", 0L, 1000L));

    System.out.println("3 events of A in the timestamp range");
    readContent(store.query("A", 2L, 5L));


    System.out.println("Querying A, removing second returned element");
    EventIterator iterator = store.query("A", 2L, 5L);
    iterator.moveNext();
    iterator.moveNext();
    iterator.remove();
    System.out.println("Querying A, after removal");
    readContent(store.query("A", 2L, 5L));
  }

  private static void readContent(EventIterator iterator) {
    System.out.println("Iterating over all elements:");
    System.out.println(iterator.toString());
    while(iterator.moveNext()){
      System.out.println("-> moveNext returned: true");
      Event event = iterator.current();
      System.out.println("-> current returned: Event type: "+event.type()+", timestamp: "+event.timestamp());
      System.out.println(iterator.toString());
    }
    System.out.println("-> moveNext returned: false");
    System.out.println(iterator.toString());
    System.out.println();
  }

}
