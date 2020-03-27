package br.com.ottimizza.statusreportapi.domain.dtos.user;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.ottimizza.statusreportapi.domain.dtos.organization.OrganizationDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @formatter:off
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    static final long serialVersionUID = 1L;

    @Getter @Setter
    private BigInteger id;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String phone;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private Integer type;

    @Getter @Setter
    private String avatar;

    @Getter @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigInteger organizationId;

    @Getter @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OrganizationDTO organization;

}
