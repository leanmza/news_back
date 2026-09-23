package com.lean.news.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListPublicationResponse {

    List<PublicationResponse> publications;
}
