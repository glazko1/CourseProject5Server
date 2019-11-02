package command.impl;

import command.Command;
import command.exception.CommandException;
import entity.Basket;
import entity.User;
import service.UserService;
import service.exception.ServiceException;
import service.impl.UserServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import java.util.Map;

public class SignInCommand implements Command {

    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public SignInCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        String username = (String) data.get("username");
        String password = (String) data.get("password");
        try {
            User user = service.signIn(username, password);
            Basket basket = service.getBasket(user.getUserId());
            basket.getProducts().forEach(product -> System.out.println(product.getProductName()));
            response.setData(Map.of("user", user));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}