package ru.otus.spring.service;

import java.io.*;

public class IOServiceConsole implements IOService {
    private final BufferedReader in;
    private final PrintStream out;

    public IOServiceConsole(PrintStream out, InputStream in) {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = out;
    }

    @Override
    public void out(String message) {
        out.println(message);
    }

    @Override
    public String readLn() throws IOException {
        return in.readLine();
    }
}
