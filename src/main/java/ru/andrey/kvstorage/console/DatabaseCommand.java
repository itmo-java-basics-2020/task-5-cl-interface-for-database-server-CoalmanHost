package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public interface DatabaseCommand {
    DatabaseCommandResult execute() throws DatabaseException;

    static void checkEnvironmentAndArguments(ExecutionEnvironment env, int targetArgsCount, String... args) throws DatabaseException {
        if (env == null) {
            throw new DatabaseException("Execution environment is not found!");
        }
        else if (args.length != targetArgsCount) {
            throw new DatabaseException("Invalid arguments count!");
        }
    }
}
