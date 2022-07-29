package br.com.authapi.entities.user.student;

import br.com.authapi.entities.brazilUf.BrazilUf;
import br.com.authapi.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_student_address")
public class StudentAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String zipcode;
    private String street;
    private String number;
    private String district;
    private String city;

    @OneToOne
    @JoinColumn(name = "uf_id")
    private BrazilUf ufId;

    @OneToOne
    private User user;
}
