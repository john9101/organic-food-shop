package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.Comment;
import com.spring.project.organicfoodshop.domain.Product;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.request.common.comment.AddCommentRequest;
import com.spring.project.organicfoodshop.domain.request.common.comment.ReplyCommentRequest;
import com.spring.project.organicfoodshop.domain.response.common.comment.*;
import com.spring.project.organicfoodshop.service.CommentService;
import com.spring.project.organicfoodshop.service.ProductService;
import com.spring.project.organicfoodshop.service.UserService;
import com.spring.project.organicfoodshop.service.mapper.CommentMapper;
import com.spring.project.organicfoodshop.util.SecurityUtil;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/products/{productId}")
    @ApiRequestMessage("Call post comment API request")
    public ResponseEntity<AddedCommentResponse> addComment(@RequestBody AddCommentRequest request, @PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        String email = SecurityUtil.getCurrentUserPrincipal().orElse(null);
        User user = userService.getUserByEmail(email, false);
        Comment comment = new Comment();
        comment.setProduct(product);
        comment.setUser(user);
        comment.setContent(request.getContent());
        comment = commentService.handleSaveComment(comment);
        AddedCommentResponse response = CommentMapper.INSTANCE.toAddedCommentResponse(comment);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{repliedCommentId}")
    @ApiRequestMessage("Call edit commnet API request")
    private ResponseEntity<RepliedCommentResponse> replyComment(@PathVariable Long repliedCommentId, @RequestBody ReplyCommentRequest request) {
        Comment repliedComment = commentService.getCommentById(repliedCommentId);
        String email = SecurityUtil.getCurrentUserPrincipal().orElse(null);
        User user = userService.getUserByEmail(email, false);
        Comment comment = new Comment();
        comment.setProduct(repliedComment.getProduct());
        comment.setUser(user);
        comment.setParent(repliedComment);
        comment.setContent(request.getContent());
        comment = commentService.handleSaveComment(comment);
        RepliedCommentResponse response = CommentMapper.INSTANCE.toRepliedCommentResponse(comment);
        response.setRepliedId(repliedCommentId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{commentId}")
    @ApiRequestMessage("Call delete commnet API request")
    public ResponseEntity<DeletedCommentResponse> deleteComment(@PathVariable Long commentId) {
        Comment comment = commentService.getCommentById(commentId);
        comment.setIsDeleted(true);
        comment = commentService.handleSaveComment(comment);
        DeletedCommentResponse response = CommentMapper.INSTANCE.toDeletedCommentResponse(comment);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{commentId}/recovery")
    @ApiRequestMessage("Call recover commnet API request")
    public ResponseEntity<RecoveredCommentResponse> recoverComment(@PathVariable Long commentId) {
        Comment comment = commentService.getCommentById(commentId);
        comment.setIsDeleted(false);
        comment = commentService.handleSaveComment(comment);
        RecoveredCommentResponse response = CommentMapper.INSTANCE.toRecoveredCommentResponse(comment);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{commentId}/visibility/{isVisible}")
    @ApiRequestMessage("Call display comment API request")
    public ResponseEntity<DisplayedCommentResponse> displaycomment(@PathVariable Long commentId, @PathVariable Boolean isVisible) {
        Comment comment = commentService.getCommentById(commentId);
        comment.setIsVisible(isVisible);
        comment = commentService.handleSaveComment(comment);
        DisplayedCommentResponse response = CommentMapper.INSTANCE.toDisplayedCommentResponse(comment);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/{productId}")
    @ApiRequestMessage("Call get comments of product API request")
    public ResponseEntity<GotCommentsOfProduct> getCommentsOfProduct(@PathVariable("productId") Long productId) {
        Set<Comment> comments = commentService.getCommentsByProductId(productId).stream().filter(Comment::getIsVisible).collect(Collectors.toSet());
        Set<GotCommentsOfProduct.Item> items = CommentMapper.INSTANCE.toGotCommentItemsOfProductResponse(comments);
        for (GotCommentsOfProduct.Item item : items) {
            item.setChildItems(CommentMapper.INSTANCE.toGotCommentItemsOfProductResponse(item.getChildren()));
            for (GotCommentsOfProduct.Item childItem : item.getChildItems()) {
                childItem.setChildItems(CommentMapper.INSTANCE.toGotCommentItemsOfProductResponse(childItem.getChildren()));
            }
        }
        GotCommentsOfProduct response = GotCommentsOfProduct.builder().items(items).build();
        return ResponseEntity.ok(response);
    }
}
