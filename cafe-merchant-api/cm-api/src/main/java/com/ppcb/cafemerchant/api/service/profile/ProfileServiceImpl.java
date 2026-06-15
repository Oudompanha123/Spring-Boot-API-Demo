package com.ppcb.cafemerchant.api.service.profile;

import com.ppcb.cafemerchant.common.config.properties.FileInfoProperties;
import com.ppcb.cafemerchant.common.domain.user.UserRepository;
import com.ppcb.cafemerchant.common.domain.user.profile.ProfileResponse;
import com.ppcb.cafemerchant.common.domain.user.profile.ProfileResponseInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    private final UserRepository userRepository;
    private final FileInfoProperties fileInfoProperties;

    @Override
    public Object getProfileInfo() {

        ProfileResponseInterface userProfile = userRepository.findUserProfileById(2);

        String profilePictureUrl = null;
        if (userProfile.getProfileImage() != null && !userProfile.getProfileImage().isEmpty()) {
            profilePictureUrl = fileInfoProperties.getBaseUrl() + "/" + userProfile.getProfileImage();
        }

        return ProfileResponse.builder()
                .fullName(userProfile.getFullName())
                .profileImage(profilePictureUrl)
                .phoneNumber(userProfile.getPhoneNumber())
                .companyName(userProfile.getCompanyName())
                .email(userProfile.getEmail())
                .address(userProfile.getAddress())
                .roleName(userProfile.getRoleName())
                .roleId(userProfile.getRoleId())
                .build();
    }
}
