package com.capstone.app.entity.dto.dashboard.response;

import com.capstone.app.entity.Image;
import com.capstone.app.entity.type.MemberRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class MemberResponseDTO {

	private Integer memberId;

	private Image featureImage;

	private String nationalId;

	private String fullName;

	private MemberRole role;

	private Double walletBalance;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	private String email;

	private String phoneNumber;


	private String address;


	private String userName;

	private String password;

	private Boolean isVerified;

	private Boolean isActive;


	private String createdDate;

	private String lastModifiedDate;
}
