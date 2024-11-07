package microteam.springdatajpa.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper=true)
//@PrimaryKeyJoinColumn(name = "video_id")-> works only with .JOINED strategy
//@DiscriminatorValue("V")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class Video extends Resource{

    private int length;
}
