package com.capstone.app.mapper;

import com.capstone.app.entity.Member;
import com.capstone.app.entity.dto.dashboard.request.MemberRequestDTO;
import com.capstone.app.entity.dto.dashboard.response.MemberResponseDTO;
import com.capstone.app.entity.type.MemberRole;
import com.capstone.app.util.DateTimeUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

	public MemberResponseDTO entityToResponseDTO(Member member) {
		MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
		memberResponseDTO.setMemberId(member.getMemberId());
		memberResponseDTO.setFeatureImage(member.getFeatureImage());
		memberResponseDTO.setNationalId(member.getNationalId());
		memberResponseDTO.setFullName(member.getFullName());
		memberResponseDTO.setRole(member.getRole());
		memberResponseDTO.setWalletBalance(member.getWalletBalance());
		memberResponseDTO.setDateOfBirth(member.getDateOfBirth());
		memberResponseDTO.setEmail(member.getEmail());
		memberResponseDTO.setPhoneNumber(member.getPhoneNumber());
		memberResponseDTO.setAddress(member.getAddress());
		memberResponseDTO.setUserName(member.getUserName());
		memberResponseDTO.setPassword(member.getPassword());
		memberResponseDTO.setIsVerified(member.getIsVerified());
		memberResponseDTO.setIsActive(member.getIsActive());
		memberResponseDTO.setCreatedDate(DateTimeUtils.formatLocalDateTime(member.getCreatedDate()));
		memberResponseDTO.setLastModifiedDate(DateTimeUtils.formatLocalDateTime(member.getLastModifiedDate()));

		return memberResponseDTO;
	}

	public Page<MemberResponseDTO> toListResponseDTO(Page<Member> members) {
        return members.map(this::entityToResponseDTO);
	}

	public Member requestDTOToEntity(MemberRequestDTO memberRequestDTO) {
		Member member = new Member();
		member.setNationalId(memberRequestDTO.getNationalId());
		if (memberRequestDTO.getFirstName() == null || memberRequestDTO.getLastName() == null) {
			member.setFullName(memberRequestDTO.getFullName());
		} else {
			member.setFullName(memberRequestDTO.getFirstName().trim() + " " + memberRequestDTO.getLastName().trim());
		}
		if (memberRequestDTO.getRole() != null) {
			if (memberRequestDTO.getRole().trim().equalsIgnoreCase("owner")) {
				member.setRole(MemberRole.ROLE_OWNER);
			} else if (memberRequestDTO.getRole().trim().equalsIgnoreCase("customer")) {
				member.setRole(MemberRole.ROLE_CUSTOMER);
			} else if (memberRequestDTO.getRole().trim().equalsIgnoreCase("admin")) {
				member.setRole(MemberRole.ROLE_ADMIN);
			}
		}

		member.setWalletBalance(memberRequestDTO.getWalletBalance());
		member.setDateOfBirth(memberRequestDTO.getDateOfBirth());
		member.setEmail(memberRequestDTO.getEmail());
		member.setPhoneNumber(memberRequestDTO.getPhoneNumber());
		member.setAddress(memberRequestDTO.getAddress());
		member.setUserName(memberRequestDTO.getUserName());
		member.setPassword(memberRequestDTO.getPassword());
		member.setIsVerified(memberRequestDTO.getIsVerified());
		member.setIsActive(memberRequestDTO.getIsActive());
		member.setCreatedDate(memberRequestDTO.getCreatedDate());
		member.setLastModifiedDate(memberRequestDTO.getLastModifiedDate());
		return member;
	}


}
