package com.service;

import com.domain.Message;

import java.util.Collection;

public interface MessageManager {

    public Message createMessage(Message msg);

    public boolean updateMessage(Message msg);

    public boolean deleteMessage(long id);

    public Message getMessage(long id);

    public Collection<Message> getMessages();
}
