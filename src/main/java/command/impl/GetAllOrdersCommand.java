package command.impl;

import command.Command;
import command.exception.CommandException;
import entity.Order;
import service.AdminService;
import service.exception.ServiceException;
import service.impl.AdminServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import java.util.List;
import java.util.Map;

public class GetAllOrdersCommand implements Command {

    private AdminService service;
    private ClientRequest request;
    private ServerResponse response;

    public GetAllOrdersCommand(ClientRequest request, ServerResponse response) {
        this.service = AdminServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        try {
            List<Order> orders = service.getAllOrders();
            response.setData(Map.of("orders", orders));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}