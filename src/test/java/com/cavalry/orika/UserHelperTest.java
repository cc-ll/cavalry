package com.cavalry.orika;

import com.cavalry.lombok.User;
import com.cavalry.lombok.UserResp;
import com.google.common.collect.Lists;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * author: master
 * date: 2015/12/29
 */
public class UserHelperTest {

    private UserHelper helper = new UserHelper();

    @Test
    public void testConvert() throws Exception {
        UserResp.Data data = new UserResp.Data();
        data.setBirthday("1990-12-17");
        data.setUser_id("123456");
        data.setUsername("bigbigx");

        UserResp.Link link = new UserResp.Link();
        link.setHref("http://jnb.ociweb.com/jnb/jnbJan2010.html");
        link.setRel("https://projectlombok.org/");

        UserResp resp = new UserResp();
        resp.setData(data);
        resp.setLinks(Lists.newArrayList(link));

        User user = helper.convert(resp);
        System.out.println(user);
    }
}