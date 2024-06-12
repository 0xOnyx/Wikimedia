package kafta.onyx.kafkaconsumerdatabases.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "wikimedia_data")
@Data
public class WikimediaData {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "data")
    private String WikiEventData;

}
