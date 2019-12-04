package command.impl;

import command.Command;
import command.exception.CommandException;
import service.UserService;
import service.exception.ServiceException;
import service.impl.UserServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import java.util.Map;

public class CancelOrderCommand implements Command {

    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public CancelOrderCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int orderId = (int) data.get("orderId");
        try {
            service.cancelOrder(orderId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}