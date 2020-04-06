package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class ReadKey implements DatabaseCommand {

    ExecutionEnvironment env;
    String dbName, tableName, key;

    public ReadKey(ExecutionEnvironment env, String... args) throws DatabaseException {
        DatabaseCommand.checkEnvironmentAndArguments(env, 4, args);
        this.env = env;
        dbName = args[1];
        tableName = args[2];
        key = args[3];
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (env.getDatabase(dbName).isEmpty()) {
            return DatabaseCommandResult.error("Database "+dbName+" is not found");
        }
        return DatabaseCommandResult.success(env.getDatabase(dbName).get().read(tableName, key));
    }
}
