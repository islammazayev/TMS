package se.TMS.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "timeLog")

public class TimeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "stopTime")
    private LocalDateTime stopTime;

    @Column(name = "timeCategory")
    private String timeCategory;

}
