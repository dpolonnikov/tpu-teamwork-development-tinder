package tpu.teamwork.tinder.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
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
    @GeneratedValue
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
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;
    @Version
    Long version;
}
