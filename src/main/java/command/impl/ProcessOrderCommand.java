package command.impl;

import command.Command;
import command.exception.CommandException;
import service.AdminService;
import service.exception.ServiceException;
import service.impl.AdminServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import java.util.Map;

public class ProcessOrderCommand implements Command {

    private AdminService service;
    private ClientRequest request;
    private ServerResponse response;

    public ProcessOrderCommand(ClientRequest request, ServerResponse response) {
        this.service = AdminServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int orderId = (int) data.get("orderId");
        try {
            service.processOrder(orderId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}