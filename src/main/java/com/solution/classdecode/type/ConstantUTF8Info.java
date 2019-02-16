package com.solution.classdecode.type;

import java.io.UnsupportedEncodingException;

/**
 * Created by Altair on 2017/3/30.
 */
public class ConstantUTF8Info {
    private int tag;
    private int length;
    private String content;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setContent(byte[] bytes) throws UnsupportedEncodingException {
        this.content = new String(bytes, "UTF-8");
    }

    public String toString() {
        return String.format("%s\ntag: %d\nlength: %d\ncontent: %s\n",
                this.getClass().getName(), tag, length, content);
    }
}
