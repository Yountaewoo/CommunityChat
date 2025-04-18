package CommunityChat.Chat;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String roomName;
    private boolean isDeleted = false;

    @OneToMany(mappedBy = "chatroom")
    private List<Message> messages = new ArrayList<>();

    protected ChatRoom() {
    }

    public ChatRoom(String roomName, boolean isDeleted) {
        this.roomName = roomName;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
