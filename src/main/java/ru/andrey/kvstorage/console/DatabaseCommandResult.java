package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }
    static DatabaseCommandResult success(String message) {
        return new DatabaseCommandResult() {
            @Override
            public Optional<String> getResult() {
                return Optional.of(message);
            }

            @Override
            public DatabaseCommandStatus getStatus() {
                return DatabaseCommandStatus.SUCCESS;
            }

            @Override
            public boolean isSuccess() {
                return true;
            }

            @Override
            public String getErrorMessage() {
                return "All OK";
            }
        };
    }
    static DatabaseCommandResult error(String message) {
        return new DatabaseCommandResult() {
            @Override
            public Optional<String> getResult() {
                return Optional.of(message);
            }

            @Override
            public DatabaseCommandStatus getStatus() {
                return DatabaseCommandStatus.FAILED;
            }

            @Override
            public boolean isSuccess() {
                return false;
            }

            @Override
            public String getErrorMessage() {
                return message;
            }
        };
    }
}