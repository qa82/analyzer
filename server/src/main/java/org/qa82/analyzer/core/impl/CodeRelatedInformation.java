package org.qa82.analyzer.core.impl;

import javax.validation.constraints.NotNull;
import java.io.File;

/**
 * Information related to a specific line of code.
 *
 * @author Max Vogler, Karlsruhe Institute of Technology, Germany
 */
public class CodeRelatedInformation {

    private final @NotNull int line;

    private final @NotNull String file;

    private final @NotNull String message;

    public CodeRelatedInformation(@NotNull String file, @NotNull int line, @NotNull String message) {
        this.line = line;
        this.file = file;
        this.message = message;
    }

    public @NotNull int getLine() {
        return line;
    }

    public @NotNull String getFile() {
        return file;
    }

    public @NotNull String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new File(file).getName() + ":" + line + " " + message;
    }
}
