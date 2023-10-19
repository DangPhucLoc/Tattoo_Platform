package SWP391.TattooPlatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "Studio_Tattoo_Manager")
public class Studio_Tattoo_Manager {

    @Id
    @Column(name = "studio_Manager_email")
    private String studioManagerEmail;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "System_Staff_email")
    private String SystemStaffEmail;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "studio_Tattoo_Manager", cascade = CascadeType.ALL)
    private Collection<Studio_Certificate> certificates;

    @JsonIgnore
    @OneToMany(mappedBy = "studioTattooManager", cascade = CascadeType.ALL)
    private Collection<Service> services;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "System_Staff_email", insertable=false, updatable=false)
    private SystemStaff systemStaff;

    @OneToMany(mappedBy = "studio_Tattoo_Manager", cascade = CascadeType.ALL)
    private Collection<Artist> artists;

    @OneToMany(mappedBy = "studio_Tattoo_Manager", cascade = CascadeType.ALL)
    private Collection<Service> service;
}
