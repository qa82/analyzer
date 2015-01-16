package org.qa82.analyzer.core.providers.java.parser.checkstyle;

import java.io.File;

public class CheckstyleError {

    private final int line;

    private final String file;

    private final String message;

    public CheckstyleError(String file, int line, String message) {
        this.line = line;
        this.file = file;
        this.message = message;
    }

    public int getLine() {
        return line;
    }

    public String getFile() {
        return file;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new File(file).getName() + ":" + line + " " + message;
    }
}
