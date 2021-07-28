package net.metaclass.domain;

public interface Message<T> {

    String toJson();

    T parse(String json);

}
