package com.quiet.onterview.video.dto.response;

import com.quiet.onterview.file.dto.response.FileInformationResponse;
import com.quiet.onterview.question.dto.response.MyQuestionResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VideoDetailResponse {

    private Long videoId;
    private Long myQuestionId;
    private Long interviewQuestionId;
    private String title;
    private FileInformationResponse videoUrl;
    private FileInformationResponse thumbnailUrl;
    private String feedback;
    private Boolean bookmark;
}
