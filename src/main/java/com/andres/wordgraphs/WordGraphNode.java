package com.andres.wordgraphs;

import java.util.Map;

public interface WordGraphNode {
    boolean canEndWord();
    Map<String, WordGraphNode> getChildNodes();
    WordGraphNode getChild(char nextChar);
    public String getWordPath();
    void setCanEndWord(boolean canEndWord);
}
