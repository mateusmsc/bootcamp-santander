package io.github.mateusmsc.bootcamp.exception;

import io.github.mateusmsc.bootcamp.util.MessagesUtil;

public class NotFoundException extends RuntimeException{
    public NotFoundException(){
        super(MessagesUtil.NO_RECORDS_FOUND);
    }
}
