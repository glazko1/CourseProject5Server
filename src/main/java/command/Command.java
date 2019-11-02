package command;

import command.exception.CommandException;
import util.cooperation.ServerResponse;

public interface Command {

    ServerResponse execute() throws CommandException;
}