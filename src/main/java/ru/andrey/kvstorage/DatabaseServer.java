package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseCommands;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {

    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        DatabaseCommand command;
        try {
            String[] args = commandText.split(" ");
            command = DatabaseCommands.valueOf(args[0]).getCommand(env, args);
        }
        catch (IllegalArgumentException iae) {
            return DatabaseCommandResult.error("Invalid command!");
        }
        catch (DatabaseException dbe) {
            return DatabaseCommandResult.error(dbe.getMessage());
        }
        catch (NullPointerException npe) {
            return DatabaseCommandResult.error("Nothing to execute!");
        }

        DatabaseCommandResult result;
        try {
            result = command.execute();
        }
        catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
        return result;
    }
}
