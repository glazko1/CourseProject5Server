package command.impl;

import command.Command;
import command.exception.CommandException;
import entity.Basket;
import entity.Product;
import service.UserService;
import service.exception.ServiceException;
import service.impl.UserServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import java.util.List;
import java.util.Map;

public class GetBasketProductsCommand implements Command {

    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public GetBasketProductsCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int userId = (int) data.get("userId");
        try {
            Basket basket = service.getBasket(userId);
            System.out.println(basket.getProducts());
            List<Product> products = basket.getProducts();
            response.setData(Map.of("products", products));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}