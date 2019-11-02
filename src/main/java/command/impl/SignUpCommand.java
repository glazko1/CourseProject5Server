package command.impl;

import command.Command;
import command.exception.CommandException;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;
import service.UserService;
import service.exception.ServiceException;
import service.impl.UserServiceImpl;

import java.util.Map;

public class SignUpCommand implements Command {

    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public SignUpCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        String username = (String) data.get("username");
        String firstName = (String) data.get("firstName");
        String lastName = (String) data.get("lastName");
        String email = (String) data.get("email");
        String password = (String) data.get("password");
        int avatar = (int) data.get("avatar");
        try {
            service.signUp(username, firstName, lastName, email, password, avatar);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}