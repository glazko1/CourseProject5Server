package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import command.Command;
import command.exception.CommandException;
import command.factory.CommandFactory;
import util.cooperation.ClientRequest;
import util.cooperation.ServerResponse;
import main.Runner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        boolean continueRunning = true;
        Runner.incrementConnectionsNumber();
        Runner.incrementUsersOnline();
        while (continueRunning) {
            try {
                ClientRequest request = getData();
                String action = request.getCommandName();
                CommandFactory factory = CommandFactory.getInstance();
                Command command = factory.createCommand(action, request, new ServerResponse());
                try {
                    ServerResponse response = command.execute();
                    sendData(response);
                } catch (CommandException e) {
                    sendData(new ServerResponse(null, true));
                    System.out.println(e.getMessage());
                }
            } catch (SocketException e) {
                continueRunning = false;
                Runner.decrementUsersOnline();
                e.printStackTrace();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendData(ServerResponse response) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        String outputJson = mapper.writeValueAsString(response);
        outputStream.writeObject(outputJson);
    }

    private ClientRequest getData() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        String inputJson = (String) inputStream.readObject();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputJson, ClientRequest.class);
    }
}