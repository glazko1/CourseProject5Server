package command.impl;

import command.Command;
import command.exception.CommandException;
import service.AdminService;
import service.exception.ServiceException;
import service.impl.AdminServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import java.util.Map;

public class EditProductCommand implements Command {

    private AdminService service;
    private ClientRequest request;
    private ServerResponse response;

    public EditProductCommand(ClientRequest request, ServerResponse response) {
        this.service = AdminServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int productId = (int) data.get("productId");
        String productName = (String) data.get("productName");
        int departmentId = (int) data.get("departmentId");
        double price = (double) data.get("price");
        int amount = (int) data.get("amount");
        try {
            service.editProduct(productId, productName, departmentId, price, amount);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}