package command.impl;

import command.Command;
import command.exception.CommandException;
import service.UserService;
import service.impl.UserServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

public class ChangeAvatarCommand implements Command {

    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public ChangeAvatarCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        return null;
    }
}