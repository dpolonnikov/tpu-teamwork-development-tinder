package tpu.teamwork.tinder.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue
    UUID id;
    String username;
    String password;
    @Column(name = "questionnary_id")
    UUID questionnaryId;
    @CreationTimestamp
    @Column(name = "created_at")
    ZonedDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    ZonedDateTime updatedAt;
    @Version
    Long version;

}
