package net.intelie.challenges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventStoreImpl implements EventStore {

  private Map<String, List<Event>> eventByType = new HashMap<>();

  public EventStoreImpl() {
  }

  public EventStoreImpl(Map<String, List<Event>> eventByType) {
    this.eventByType = eventByType;
  }

  @Override
  public void insert(Event event) {
    if(event == null || event.type() == null){
      return;
    }
    String type = event.type();
    if(!eventByType.containsKey(event.type())){
      eventByType.put(type, new ArrayList<>());
    }
    eventByType.get(type).add(event);
  }

  @Override
  public void removeAll(String type) {
    eventByType.remove(type);
  }

  @Override
  public EventIterator query(String type, long startTime, long endTime) {
    return new EventIteratorImpl(this, type, startTime, endTime);
  }

  protected Map<String, List<Event>> getEvents(){
    return eventByType;
  }

  protected void remove(Event event){
    if (eventByType.containsKey(event.type())) {
      eventByType.get(event.type()).remove(event);
    }
  }
}
