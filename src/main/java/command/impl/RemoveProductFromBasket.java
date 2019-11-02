package command.impl;

import command.Command;
import command.exception.CommandException;
import service.UserService;
import service.exception.ServiceException;
import service.impl.UserServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import java.util.Map;
import java.util.UUID;

public class RemoveProductFromBasket implements Command {

    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public RemoveProductFromBasket(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        UUID userId = UUID.fromString((String) data.get("userId"));
        UUID productId = UUID.fromString((String) data.get("productId"));
        try {
            service.removeProductFromBasket(userId, productId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}