package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Comment;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.response.common.account.EditedAccountInfoResponse;
import com.spring.project.organicfoodshop.domain.response.common.comment.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    AddedCommentResponse toAddedCommentResponse(Comment comment);

    RepliedCommentResponse toRepliedCommentResponse(Comment comment);

    DeletedCommentResponse toDeletedCommentResponse(Comment comment);

    RecoveredCommentResponse toRecoveredCommentResponse(Comment comment);

    DisplayedCommentResponse toDisplayedCommentResponse(Comment comment);

    Set<GotCommentsOfProduct.Item> toGotCommentItemsOfProductResponse(Set<Comment> comments);


}
