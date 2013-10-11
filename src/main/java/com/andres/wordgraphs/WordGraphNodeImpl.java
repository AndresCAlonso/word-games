package com.andres.wordgraphs;

import java.util.HashMap;
import java.util.Map;

public class WordGraphNodeImpl implements WordGraphNode, Comparable<WordGraphNodeImpl> {

	private boolean canEndWord;
	private Map<String, WordGraphNode> children;
	private String wordPath;
	
	public WordGraphNodeImpl(String wordPath) {
		if (wordPath == null) {
			throw new IllegalArgumentException("The word path must never be null!");
		}
		
		this.wordPath = wordPath;
	}
	
	@Override
	public boolean canEndWord() {
		return canEndWord;
	}
	
	@Override
	public void setCanEndWord(boolean canEndWord) {
		this.canEndWord = canEndWord;
	}

	@Override
	public Map<String, WordGraphNode> getChildNodes() {
		if (children == null) {
			children = new HashMap<>();
		}
		
		return children;
	}

	@Override
	public WordGraphNode getChild(char nextChar) {
		return null;
	}
	
	@Override
	public String getWordPath() {
		return wordPath;
	}

	@Override
	public boolean equals(Object o){
		if (o == this) {
			return true;
		}
		if (!(o instanceof WordGraphNodeImpl)) {
			return false;
		}
		
		WordGraphNodeImpl otherNode = (WordGraphNodeImpl) o;
		
		return this.getWordPath().equals(otherNode.getWordPath());
	}
	
	@Override
	public int hashCode() {
		return this.getWordPath().hashCode();
	}
	
	@Override
	public int compareTo(WordGraphNodeImpl otherNode) {
		return this.getWordPath().compareTo(otherNode.getWordPath());
	}
}
