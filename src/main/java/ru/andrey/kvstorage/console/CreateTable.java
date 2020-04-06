package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class CreateTable implements DatabaseCommand {

    ExecutionEnvironment env;
    String dbName, tableName;

    public CreateTable(ExecutionEnvironment env, String... args) throws DatabaseException {
        DatabaseCommand.checkEnvironmentAndArguments(env, 3,  args);
        this.env = env;
        dbName = args[1];
        tableName = args[2];
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (env.getDatabase(dbName).isEmpty()) {
            return DatabaseCommandResult.error("Database "+dbName+" is not found");
        }
        env.getDatabase(dbName).get().createTableIfNotExists(tableName);
        return DatabaseCommandResult.success("Table "+tableName+" created in database "+dbName);
    }
}
