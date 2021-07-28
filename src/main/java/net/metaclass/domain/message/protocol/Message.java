package net.metaclass.domain.message.protocol;

public interface Message<T> {

    String toJson();

    T parse(String json);

    T get();

}
