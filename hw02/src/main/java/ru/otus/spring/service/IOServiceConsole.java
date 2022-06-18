package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.MyIOException;

import java.io.*;

@Service
public class IOServiceConsole implements IOService {
    private final BufferedReader in;
    private final PrintStream out;

    public IOServiceConsole() {
        this.in = new BufferedReader(new InputStreamReader(System.in));
        this.out = System.out;
    }

    @Override
    public void out(String message) {
        out.println(message);
    }

    @Override
    public String readLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new MyIOException("IOException", e);
        }
    }
}
