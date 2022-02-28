package net.intelie.challenges;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventStoreImplTest {

  @Test
  public void insert_validEvent_insertedSuccessfully(){
    Map<String, List<Event>> eventByType = new HashMap<>();
    EventStoreImpl store = new EventStoreImpl(eventByType);
    String type = "A";
    Event event = new Event(type, 1L);
    store.insert(event);

    Assert.assertTrue("Map should contain key of type",eventByType.containsKey(type));
    List<Event> events = eventByType.get(type);
    Assert.assertTrue("List should contain event", events.contains(event));
  }

  @Test
  public void insert_nullEvent_doNotInsert(){
    Map<String, List<Event>> eventByType = new HashMap<>();
    EventStoreImpl store = new EventStoreImpl(eventByType);
    Event event = null;
    store.insert(event);

    Assert.assertTrue("Store should remain empty", eventByType.isEmpty());
  }

}