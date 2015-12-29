package com.cavalry.lombok;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class UserResp {

    private List<Link> links;
    private Data data;

    @lombok.Data
    public static class Link {
        private String rel;
        private String href;
    }
    @lombok.Data
    public static class Data {
        private String username;
        private String user_id;
        private String birthday;
    }
}
