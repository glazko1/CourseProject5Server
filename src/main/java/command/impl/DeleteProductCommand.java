package command.impl;

import command.Command;
import command.exception.CommandException;
import service.AdminService;
import service.exception.ServiceException;
import service.impl.AdminServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import java.util.Map;

public class DeleteProductCommand implements Command {

    private AdminService service;
    private ClientRequest request;
    private ServerResponse response;

    public DeleteProductCommand(ClientRequest request, ServerResponse response) {
        this.service = AdminServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int productId = (int) data.get("productId");
        try {
            service.deleteProduct(productId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}