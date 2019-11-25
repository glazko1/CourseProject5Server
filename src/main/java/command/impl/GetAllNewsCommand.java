package command.impl;

import command.Command;
import command.exception.CommandException;
import entity.News;
import service.UserService;
import service.exception.ServiceException;
import service.impl.UserServiceImpl;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllNewsCommand implements Command {

    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public GetAllNewsCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        try {
            List<News> news = service.getAllNews();
            Map<String, Object> data = new HashMap<>();
            data.put("news", news);
            response.setData(data);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}