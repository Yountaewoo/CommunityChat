package CommunityChat.post;

import CommunityChat.board.Board;
import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    private Board board;

    protected Post() {
    }

    public Post(String title, String content, Board board) {
        this.title = title;
        this.content = content;
        this.board = board;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Board getBoard() {
        return board;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void updatePost(String title,
                           String content){
        this.title = title;
        this.content= content;
    }
}
