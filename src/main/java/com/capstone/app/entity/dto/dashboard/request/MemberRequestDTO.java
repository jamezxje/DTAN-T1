package com.capstone.app.entity.dto.dashboard.request;

import com.capstone.app.entity.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDTO {

    private Integer memberId;
    private MultipartFile featureImage;
    private String featureImageUrl;
    private String nationalId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String role;
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
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Boolean deleteFeatureImage;

    public Member toEntity() {
        return Member.builder()
                .nationalId(nationalId)
                .fullName(fullName)
                .dateOfBirth(dateOfBirth)
                .email(email)
                .phoneNumber(phoneNumber)
                .address(address)
                .build();
    }

    public static MemberRequestDTO toDTO(Member member) {
        if (member.getFeatureImage() != null) {
            return MemberRequestDTO.builder()
                    .memberId(member.getMemberId())
                    .featureImageUrl(member.getFeatureImage().getImageUrl())
                    .fullName(member.getFullName())
                    .nationalId(member.getNationalId())
                    .dateOfBirth(member.getDateOfBirth())
                    .email(member.getEmail())
                    .phoneNumber(member.getPhoneNumber())
                    .address(member.getAddress())
                    .build();
        } else {
            return MemberRequestDTO.builder()
                    .memberId(member.getMemberId())
                    .fullName(member.getFullName())
                    .nationalId(member.getNationalId())
                    .dateOfBirth(member.getDateOfBirth())
                    .email(member.getEmail())
                    .phoneNumber(member.getPhoneNumber())
                    .address(member.getAddress())
                    .build();
        }
    }

    public void updateEntity(Member member) {
        member.setFullName(fullName);
        member.setNationalId(nationalId);
        member.setDateOfBirth(dateOfBirth);
        member.setEmail(email);
        member.setPhoneNumber(phoneNumber);
        member.setAddress(address);
    }

}
