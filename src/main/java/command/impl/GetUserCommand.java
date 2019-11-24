package command.impl;

import command.Command;
import command.exception.CommandException;
import entity.User;
import service.UserService;
import service.exception.ServiceException;
import service.impl.UserServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import java.util.Map;

public class GetUserCommand implements Command {

    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public GetUserCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int userId = (int) data.get("userId");
        try {
            User user = service.getUser(userId);
            response.setData(Map.of("user", user));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}