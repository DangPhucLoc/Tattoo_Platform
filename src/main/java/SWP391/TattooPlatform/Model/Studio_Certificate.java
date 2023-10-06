package SWP391.TattooPlatform.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "Studio_Certificate")
public class Studio_Certificate {

    @Id
    private String studio_Certificate_ID;

    private String studio_Certificate_name;

    private String description;

    private String studio_Manager_Email;

    @ManyToOne
    @JoinColumn(name = "studio_Manager_email", insertable = false, updatable = false)
    private Studio_Tattoo_Manager studio_Tattoo_Manager;
}