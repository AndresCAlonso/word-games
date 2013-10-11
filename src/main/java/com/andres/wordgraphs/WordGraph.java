package com.andres.wordgraphs;

public interface WordGraph {
	WordGraphNode findNode(String wordPath);
	boolean isWord(String word);
	boolean isPrefix(String prefix);
}
