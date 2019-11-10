package command.impl;

import command.Command;
import command.exception.CommandException;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

public class SendImageCommand implements Command {

    private ClientRequest request;
    private ServerResponse response;

    public SendImageCommand(ClientRequest request, ServerResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        try {
            Map<String, Object> data = request.getData();
            String imageString = (String) data.get("image");
            String name = (String) data.get("name");
            byte[] imageData = Base64.getDecoder().decode(imageString);
            FileOutputStream imageStream = new FileOutputStream("E:\\Java\\CourseProject5\\Server\\src\\main\\resources\\img" + "\\" + name + ".jpg");
            imageStream.write(imageData);
            imageStream.close();
        } catch (IOException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
