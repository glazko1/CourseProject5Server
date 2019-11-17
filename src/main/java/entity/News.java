package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {

    public News(String newsText, Date newsDateTime) {
        this.newsText = newsText;
        this.newsDateTime = newsDateTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private int newsId;

    @Column(name = "news_text")
    private String newsText;

    @Column(name = "news_date_time")
    private Date newsDateTime;
}