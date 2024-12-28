package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.request.common.comment.EditCommentRequest;
import com.spring.project.organicfoodshop.domain.request.common.comment.PostCommentRequest;
import com.spring.project.organicfoodshop.service.CommentService;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @ApiRequestMessage("Call post comment API request")
    public void postComment(@RequestBody PostCommentRequest request) {

    }

    @PatchMapping("/{commentId}")
    @ApiRequestMessage("Call edit commnet API request")
    private void editComment(@PathVariable Long commentId, @RequestBody EditCommentRequest request) {}

    @DeleteMapping("/{commentId}")
    @ApiRequestMessage("Call delete commnet API request")
    private void deleteComment(@PathVariable Long commentId) {}
}
