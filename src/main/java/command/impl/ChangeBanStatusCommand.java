package command.impl;

import command.Command;
import command.exception.CommandException;
import service.AdminService;
import service.impl.AdminServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

public class ChangeBanStatusCommand implements Command {

    private AdminService service;
    private ClientRequest request;
    private ServerResponse response;

    public ChangeBanStatusCommand(ClientRequest request, ServerResponse response) {
        this.service = AdminServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        return null;
    }
}