package com.ambiguous.buyornot.posting.storage;

import com.ambiguous.buyornot.posting.api.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
