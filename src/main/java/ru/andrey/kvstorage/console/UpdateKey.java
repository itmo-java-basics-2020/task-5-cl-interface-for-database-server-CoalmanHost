package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class UpdateKey implements DatabaseCommand {

    ExecutionEnvironment env;
    String dbName, tableName, key, value;

    public UpdateKey(ExecutionEnvironment env, String... args) throws DatabaseException {
        DatabaseCommand.checkEnvironmentAndArguments(env, 5, args);
        this.env = env;
        dbName = args[1];
        tableName = args[2];
        key = args[3];
        value = args[4];
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (env.getDatabase(dbName).isEmpty()) {
            return DatabaseCommandResult.error("Database "+dbName+" is not found");
        }
        env.getDatabase(dbName).get().write(tableName, key, value);
        return DatabaseCommandResult.success("In database "+dbName+" in table "+tableName+" updated key "+key+" with value "+value);
    }
}
