package CommunityChat.Chat;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long roomId;

    //유저:메세지 = 1:n
    @ManyToOne
    private User sender;

    @Column(nullable = false)
    private String message;

    private boolean isDeleted = false;

    private LocalDateTime createdAt;
//    private LocalDateTime deletedAt;  ?? 삭제 필요한지?


    //메세지:채팅방 = n:1


    public Message(Long roomId, User sender, String message) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public User getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}