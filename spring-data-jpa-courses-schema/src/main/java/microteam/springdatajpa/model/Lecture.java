package microteam.springdatajpa.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@Entity
//@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Lecture extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @OneToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;
}
