package gson;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class RawCollectionsExample {
  static class Event {
    private String name;
    private String source;
    private Event(String name, String source) {
      this.name = name;
      this.source = source;
    }
    @Override
    public String toString() {
      return String.format("(name=%s, source=%s)", name, source);
    }
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static void main(String[] args) {
    Gson gson = new Gson();
    Collection collection = new ArrayList();
    collection.add("hello");
    collection.add(5);
    collection.add(new Event("GREETINGS", "guest"));
    String json = gson.toJson(collection);
    
    System.out.println("序列化后的字符串: " + json);
    
    JsonParser parser = new JsonParser();
    JsonArray array = parser.parse(json).getAsJsonArray();
    
    String message = gson.fromJson(array.get(0), String.class);
    int number = gson.fromJson(array.get(1), int.class);
    Event event = gson.fromJson(array.get(2), Event.class);
    System.out.printf("反序列化后得到的信息: %s, %d, %s", message, number, event);
  }
}