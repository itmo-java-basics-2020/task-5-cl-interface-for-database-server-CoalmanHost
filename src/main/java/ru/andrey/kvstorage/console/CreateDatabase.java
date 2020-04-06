package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.DatabaseExample;

public class CreateDatabase implements DatabaseCommand {

    ExecutionEnvironment env;
    String dbName;

    public CreateDatabase(ExecutionEnvironment env, String... args) throws DatabaseException {
        DatabaseCommand.checkEnvironmentAndArguments(env, 2, args);
        this.env = env;
        dbName = args[1];
    }

    @Override
    public DatabaseCommandResult execute() {

        if (env.getDatabase(dbName).isPresent()) {
            return DatabaseCommandResult.error("Database "+dbName+" already exists");
        }
        env.addDatabase(new DatabaseExample(dbName));
        return DatabaseCommandResult.error("Database "+dbName+" is created");
    }
}
