package tpu.teamwork.tinder.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "dormitories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dormitory {
    @Id
    @GeneratedValue
    UUID id;
    Integer number;
    String address;
    String description;
    @CreationTimestamp
    @Column(name = "created_at")
    ZonedDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    ZonedDateTime updatedAt;
    @Version
    Long version;
}
