package com.capstone.app.service.dash;

import com.capstone.app.entity.dto.dashboard.request.MemberRequestDTO;
import com.capstone.app.entity.dto.dashboard.response.MemberResponseDTO;
import com.capstone.app.entity.type.MemberRole;
import org.springframework.data.domain.Page;

public interface IAdminService {

	MemberResponseDTO getMemberByID(Integer id);

	Page<MemberResponseDTO> getAllMember(Integer index, Integer size);

	Page<MemberResponseDTO> getAllMemberByRole(MemberRole role, Integer index, Integer size);

	MemberRequestDTO addNewMember(MemberRequestDTO memberRequestDTO);

	MemberRequestDTO updateMember(MemberRequestDTO memberRequestDTO);

	MemberResponseDTO changeMemberActiveStatus(Integer id, Boolean status);

	MemberResponseDTO getMemberFromContext();

	MemberResponseDTO deleteMember(Integer id);

}
