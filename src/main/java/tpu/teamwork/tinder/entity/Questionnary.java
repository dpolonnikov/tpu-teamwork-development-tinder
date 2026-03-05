package tpu.teamwork.tinder.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "questionnaires")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Questionnary {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    String description;
    Integer age;
    Character gender;
    Integer course;
    String faculty;
    UUID dormitory;
    UUID image;
    @Column(name = "is_active")
    Boolean isActive;
    @CreationTimestamp
    @Column(name = "created_at")
    ZonedDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    ZonedDateTime updatedAt;
    @Version
    Long version;
}
