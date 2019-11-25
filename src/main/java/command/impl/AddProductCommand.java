package command.impl;

import command.Command;
import command.exception.CommandException;
import service.AdminService;
import service.exception.ServiceException;
import service.impl.AdminServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import java.util.Map;

public class AddProductCommand implements Command {

    private AdminService service;
    private ClientRequest request;
    private ServerResponse response;

    public AddProductCommand(ClientRequest request, ServerResponse response) {
        this.service = AdminServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        String productName = (String) data.get("productName");
        int departmentId = (int) data.get("departmentId");
        double price = (double) data.get("price");
        int amount = (int) data.get("amount");
        try {
            service.addProduct(productName, departmentId, price, amount);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}