package ablw.projetprototypageinterfaceablw;

import java.util.ArrayList;

public class Chat {
    private ArrayList<Message> messages = new ArrayList<>();

    public Chat(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public void newMessage(String user, String contenu) {
        messages.add(new Message(user, contenu));
    }

    public Message getMessageAt(int id) {
        return messages.get(id);
    }

    public ArrayList<Message> getAllMessages() {
        return messages;
    }
}
