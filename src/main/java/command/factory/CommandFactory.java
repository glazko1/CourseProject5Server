package command.factory;

import command.Command;
import command.impl.*;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

public class CommandFactory {

    private static final CommandFactory INSTANCE = new CommandFactory();

    public static CommandFactory getInstance() {
        return INSTANCE;
    }

    private CommandFactory() {}

    public Command createCommand(String name, ClientRequest request, ServerResponse response) {
        switch (name) {
            case "sendImage":
                return new SendImageCommand(request, response);
            case "signIn":
                return new SignInCommand(request, response);
            case "signUp":
                return new SignUpCommand(request, response);
            case "restorePassword":
                return new RestorePasswordCommand(request, response);
            case "getAllProducts":
                return new GetAllProductsCommand(request, response);
            case "getBasketProducts":
                return new GetBasketProductsCommand(request, response);
            case "addProductToBasket":
                return new AddProductToBasketCommand(request, response);
            case "removeProductFromBasket":
                return new RemoveProductFromBasketCommand(request, response);
            case "getAllDepartments":
                return new GetAllDepartmentsCommand(request, response);
            case "addProduct":
                return new AddProductCommand(request, response);
            case "addOrder":
                return new AddOrderCommand(request, response);
            case "getUserOrders":
                return new GetUserOrdersCommand(request, response);
            case "getAllOrders":
                return new GetAllOrdersCommand(request, response);
            case "processOrder":
                return new ProcessOrderCommand(request, response);
            case "deleteProduct":
                return new DeleteProductCommand(request, response);
            case "getUser":
                return new GetUserCommand(request, response);
            case "changeAvatar":
                return new ChangeAvatarCommand(request, response);
            case "changeEmail":
                return new ChangeEmailCommand(request, response);
            case "changePassword":
                return new ChangePasswordCommand(request, response);
            case "editProduct":
                return new EditProductCommand(request, response);
            case "getAllUsers":
                return new GetAllUsersCommand(request, response);
            case "changeBanStatus":
                return new ChangeBanStatusCommand(request, response);
            case "changeUserStatus":
                return new ChangeUserStatusCommand(request, response);
            case "getAllNews":
                return new GetAllNewsCommand(request, response);
            case "cancelOrder":
                return new CancelOrderCommand(request, response);
        }
        throw new RuntimeException();
    }
}