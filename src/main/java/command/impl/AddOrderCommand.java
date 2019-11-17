package command.impl;

import command.Command;
import command.exception.CommandException;
import main.Runner;
import service.UserService;
import service.exception.ServiceException;
import service.impl.UserServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import java.util.Map;

public class AddOrderCommand implements Command {

    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public AddOrderCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int userId = (int) data.get("userId");
        String region = (String) data.get("region");
        String locality = (String) data.get("locality");
        String street = (String) data.get("street");
        int houseNumber = (int) data.get("house");
        int flatNumber = (int) data.get("flat");
        try {
            service.addOrder(userId, region, locality, street, houseNumber, flatNumber);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}