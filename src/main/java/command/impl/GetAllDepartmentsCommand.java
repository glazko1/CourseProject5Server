package command.impl;

import command.Command;
import command.exception.CommandException;
import entity.Department;
import service.UserService;
import service.exception.ServiceException;
import service.impl.UserServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import java.util.List;
import java.util.Map;

public class GetAllDepartmentsCommand implements Command {

    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public GetAllDepartmentsCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        try {
            List<Department> departments = service.getAllDepartments();
            response.setData(Map.of("departments", departments));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}