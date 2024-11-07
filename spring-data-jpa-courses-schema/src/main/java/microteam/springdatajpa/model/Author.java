package microteam.springdatajpa.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@SuperBuilder // Inherit the builder pattern from BaseEntity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NamedQueries(
        {
                @NamedQuery(
                        name = "Author.findByNamedQuery",
                        query = "select a from Author a where a.age >= :age"
                ),
                @NamedQuery(
                        name = "Author.updateByNamedQuery",
                        query = "update Author a set a.age = :age"
                )
        }
)
public class Author extends BaseEntity {

    @Column(name = "f_name", length = 35)
    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private Integer age;

    @Column(insertable = false)
    private LocalDateTime lastModified;

    @ManyToMany(mappedBy = "authors")
    private List<Course> courses;

    // Removed @PrePersist here as it is handled by BaseEntity.
}
