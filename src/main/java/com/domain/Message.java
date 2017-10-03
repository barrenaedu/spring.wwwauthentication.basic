package com.domain;

public class Message {
    private long id;
    private String text;

    private Message(MessageBuilder builder) {
        this.id = builder.id;
        this.text = builder.text;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public static class MessageBuilder {
        private long id;
        private String text;

        public MessageBuilder id(long id) {
            this.id = id;
            return this;
        }

        public MessageBuilder text(String text) {
            this.text = text;
            return this;
        }

        public Message build() {
            return new Message(this);
        }

    }

}
