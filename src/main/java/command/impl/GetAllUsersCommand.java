package command.impl;

import command.Command;
import command.exception.CommandException;
import entity.User;
import service.AdminService;
import service.exception.ServiceException;
import service.impl.AdminServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllUsersCommand implements Command {

    private AdminService service;
    private ClientRequest request;
    private ServerResponse response;

    public GetAllUsersCommand(ClientRequest request, ServerResponse response) {
        this.service = AdminServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        try {
            List<User> users = service.getAllUsers();
            Map<String, Object> data = new HashMap<>();
            data.put("users", users);
            response.setData(data);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}