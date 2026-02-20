package tpu.teamwork.tinder.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Image {
    @Id
    @GeneratedValue
    UUID id;
    @Column(name = "file_key")
    String fileKey;
    @Column(name = "original_name")
    String originalName;
    @Column(name = "mime_type")
    String mimeType;
    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;
    @Version
    Long version;
}
