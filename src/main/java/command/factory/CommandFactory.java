package command.factory;

import command.Command;
import command.impl.AddOrderCommand;
import command.impl.AddProductCommand;
import command.impl.AddProductToBasketCommand;
import command.impl.ChangeAvatarCommand;
import command.impl.ChangeBanStatusCommand;
import command.impl.ChangeEmailCommand;
import command.impl.ChangePasswordCommand;
import command.impl.ChangeUserStatusCommand;
import command.impl.DeleteProductCommand;
import command.impl.EditProductCommand;
import command.impl.GetAllDepartmentsCommand;
import command.impl.GetAllNewsCommand;
import command.impl.GetAllOrdersCommand;
import command.impl.GetAllProductsCommand;
import command.impl.GetAllUsersCommand;
import command.impl.GetBasketProductsCommand;
import command.impl.GetUserCommand;
import command.impl.GetUserOrdersCommand;
import command.impl.ProcessOrderCommand;
import command.impl.RemoveProductFromBasketCommand;
import command.impl.RestorePasswordCommand;
import command.impl.SendImageCommand;
import command.impl.SignInCommand;
import command.impl.SignUpCommand;
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
        }
        throw new RuntimeException();
    }
}