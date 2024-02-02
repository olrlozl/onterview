package com.quiet.onterview.community.service;

import com.quiet.onterview.community.dto.request.ArticleModifyContentRequest;
import com.quiet.onterview.community.dto.request.ArticlePostRequest;
import com.quiet.onterview.community.dto.response.ArticleListResponse;
import com.quiet.onterview.community.dto.response.ArticlePostResponse;
import java.util.List;

public interface ArticleService {

    ArticlePostResponse postArticle(Long memberId, ArticlePostRequest articlePostRequest);

    void modifyArticleContent(Long memberId, Long articleId, ArticleModifyContentRequest articleModifyContentRequest);

    void deleteArticle(Long memberId, Long articleId);

    List<ArticleListResponse> getAllMyArticle(Long memberId, String order);
}
