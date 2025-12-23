package com.ambiguous.buyornot.mypage.domain;

import com.ambiguous.buyornot.mypage.controller.mypageRequest.MypageRequest;
import com.ambiguous.buyornot.mypage.controller.mypageResponse.PostResponse;
import com.ambiguous.buyornot.mypage.storage.MypageRepositoty;
import com.ambiguous.buyornot.mypage.controller.mypageRequest.UpdateRequest;
import com.ambiguous.buyornot.mypage.controller.mypageResponse.UserListResponse;
import com.ambiguous.buyornot.posting.api.domain.Post;
import com.ambiguous.buyornot.posting.storage.PostRepository;
import com.ambiguous.buyornot.user.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MypageService {
    MypageRepositoty mypageRepositoty;
    PostRepository postRepository;

//    UserRepository userRepository;

/*
public UserListResponse findById(Long id){
    User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("채팅이 존재하지 않습니다."));
    return new UserListResponse(
            user.getUserId(),
            user.getUserName(),
            user.getNickname(),
            user.getEmail(),
            user.getBirth(),
            user.getGender());
}*/
/*

    @Transactional
    public void updateUser(UpdateRequest updateRequest) {
        User user = userRepository.findById(updateRequest.id()).orElseThrow(() -> new IllegalArgumentException("채팅이 존재하지 않습니다."));

        if(updateRequest.email() != null) {
            user.changeEmail(updateRequest.email());
        }
        if(updateRequest.password() != null) {
            if(user.getPassword().equals(updateRequest.password())) {
                user.changePassword(updateRequest.changePassword());
            }
        }
        if(updateRequest.nickName() != null && !mypageRepositoty.existsByNickname(updateRequest.nickName())) {
            user.changeNickname(updateRequest.nickName());
        }
        if(updateRequest.userName() != null) {
            user.changeUsername(updateRequest.userName());
        }
    }
*/

    public PostResponse getposting(Long id) {
        Post post = postRepository.findTitleById(id);
        return new PostResponse(post.getUserId(), post.getTitle());

    }

    public void createMypage(MypageRequest mypageRequest) {
        Mypage mypage = new Mypage(mypageRequest.userId(),mypageRequest.likeStock(),mypageRequest.OwnStock());
        mypageRepositoty.save(mypage);
    }


    public void changeStock(MypageRequest mypageRequest) {
        Mypage mypage = mypageRepositoty.findByUserId(mypageRequest.userId());
        if(mypage.likeStock != mypageRequest.likeStock()) {
            Mypage mypage1 = new  Mypage(mypageRequest.userId(),mypageRequest.likeStock(),mypageRequest.OwnStock());
            mypageRepositoty.save(mypage1);
        }

    }
}
