package net.intelie.challenges;

import java.util.List;
import java.util.stream.Collectors;

public class EventIteratorImpl implements EventIterator {

  private static int WITHOUT_CURRENT_EVENT = -1;

  private EventStoreImpl eventStore;
  private String type;
  private long startTime;
  private long endTime;

  private int pos = WITHOUT_CURRENT_EVENT;
  private Event[] events = new Event[0];

  public EventIteratorImpl(EventStoreImpl eventStore, String type, long startTime, long endTime) {
    this.eventStore = eventStore;
    this.type = type;
    this.startTime = startTime;
    this.endTime = endTime;
    init();
  }

  private void init() {
    List<Event> eventOfType = eventStore.getEvents().get(type);
    if (eventOfType != null) {
      this.events = eventOfType.stream()
              .filter(e -> startTime <= e.timestamp() && e.timestamp() < endTime)
              .collect(Collectors.toList()).toArray(new Event[0]);
    }
  }

  @Override
  public boolean moveNext() {
    if (events == null){
      return false;
    } else if (pos >= events.length - 1) {
      pos = WITHOUT_CURRENT_EVENT;
      events = null;
      return false;
    }
    pos++;
    return true;
  }

  @Override
  public Event current() {
    validateState();
    return events[pos];
  }


  @Override
  public void remove() {
    validateState();
    eventStore.remove(events[pos]);
  }

  @Override
  public void close() throws Exception {
  }

  private void validateState() {
    if (pos == WITHOUT_CURRENT_EVENT) {
      throw new IllegalStateException();
    }
  }

  public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append("# Iterator state for events type: ").append(type).append(", in range: ").append(startTime).append("~").append(endTime).append(", from store: ").append(eventStore).append(".\n");
    if(events == null){
      builder.append("## (IllegalState) 'moveNext' last result was false");
    }else if(pos == WITHOUT_CURRENT_EVENT){
      builder.append("## (IllegalState) 'moveNext' was never called");
    } else{
      builder.append("## Content (").append(events.length).append(" events):");
      for (int i = 0; i < events.length; i++) {
        Event event = events[i];
        builder.append("\n### (").append(i);
        if(pos == i){
          builder.append(" -> CURRENT");
        }
        builder.append(") type: ").append(event.type()).append(", timestamp: ").append(event.timestamp()).append("");
      }
    }
    return builder.toString();
  }
}
