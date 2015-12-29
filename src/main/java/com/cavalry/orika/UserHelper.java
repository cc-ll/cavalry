package com.cavalry.orika;

import com.cavalry.lombok.User;
import com.cavalry.lombok.UserResp;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: master
 * date: 2015/12/29
 */
public class UserHelper {

    private MapperFacade mapper;

    public UserHelper() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(User.class, UserResp.class)
                .field("userName", "data.username")
                .field("userId", "data.user_id")
                .field("birthday", "data.birthday")
                .byDefault().register();
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new BidirectionalConverter<String, Date>() {
            @Override
            public Date convertTo(String source, Type<Date> destinationType) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return df.parse(source);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public String convertFrom(Date source, Type<String> destinationType) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                return df.format(source);
            }
        });
        mapper = mapperFactory.getMapperFacade();
    }

    public User convert(UserResp resp) {
        return mapper.map(resp, User.class);
    }

}
