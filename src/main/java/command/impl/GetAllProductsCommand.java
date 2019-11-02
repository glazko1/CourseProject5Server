package command.impl;

import command.Command;
import command.exception.CommandException;
import entity.Product;
import service.UserService;
import service.exception.ServiceException;
import service.impl.UserServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import javax.sql.rowset.serial.SerialException;
import java.util.List;
import java.util.Map;

public class GetAllProductsCommand implements Command {

    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public GetAllProductsCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        try {
            List<Product> products = service.getAllProducts();
            response.setData(Map.of("products", products));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}