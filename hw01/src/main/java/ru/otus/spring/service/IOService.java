package ru.otus.spring.service;

import java.io.IOException;

public interface IOService {
    void out(String message);

    String readLine() throws IOException;
}
