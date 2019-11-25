package command.impl;

import command.Command;
import command.exception.CommandException;
import entity.User;
import org.apache.commons.lang3.RandomStringUtils;
import service.UserService;
import service.exception.ServiceException;
import service.impl.UserServiceImpl;
import util.EmailSender;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;
import util.hasher.PasswordHashKeeper;

import java.util.Map;

public class RestorePasswordCommand implements Command {

    private UserService service;
    private ClientRequest request;
    private ServerResponse response;
    private EmailSender sender = EmailSender.getInstance();
    private PasswordHashKeeper hashKeeper = PasswordHashKeeper.getInstance();

    public RestorePasswordCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        String email = (String) data.get("email");
        String password = RandomStringUtils.random(10, true, true);
        sender.sendEmail(email, "Восстановление пароля",
                "Здравствуйте!\n" +
                        "Ваш новый пароль для входа в систему: " + password);
        try {
            User user = service.getUserByEmail(email);
            String encoded = hashKeeper.generateHash(user.getUsername(), password);
            service.restorePassword(user.getUserId(), encoded);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}