package com.service;

import com.domain.Message;

import java.util.Collection;

public interface MessageManager {

    Message createMessage(Message msg);

    Collection<Message> getMessages();
}
