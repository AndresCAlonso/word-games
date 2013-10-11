package com.andres.wordgraphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;

public class DirectedAcyclicWordGraph implements WordGraph {

	private WordGraphNodeImpl root;

	// TODO: delete this
	public static void main(String[] args) {
		WordGraph wordGraph = new DirectedAcyclicWordGraph();

		System.out.println("is aaaa word:" + wordGraph.isWord("aaaa"));
		System.out.println("is cart word:" + wordGraph.isWord("cart"));
		System.out.println("is cartilaginou prefix: " + wordGraph.isPrefix("cartilaginou"));
	}

	public DirectedAcyclicWordGraph() {
		long startTime = new Date().getTime();
		root = new WordGraphNodeImpl("");

		InputStream stream = DirectedAcyclicWordGraph.class.getResourceAsStream("/main.dict");
		String line;

		BufferedReader dictReader = new BufferedReader(new InputStreamReader(stream));
		try {
			while ((line = dictReader.readLine()) != null) {
				String word = line.trim();
				if (!word.isEmpty()) {
					addWordToGraph(word);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		long endTime = new Date().getTime();
		System.out.println("Time to build dawg (ms): " + (endTime - startTime));
	}

	private void addWordToGraph(String word) {
		StringBuilder currentPrefix = new StringBuilder("");
		char[] wordCharacters = word.toCharArray();

		WordGraphNode currentNode = root;

		for (char letter : wordCharacters) {
			currentPrefix.append(letter);
			String prefixString = currentPrefix.toString();
			Map<String, WordGraphNode> nodesToExamine = currentNode.getChildNodes();

			WordGraphNode newNode = nodesToExamine.get(prefixString);

			if (newNode == null) {
				newNode = new WordGraphNodeImpl(prefixString);
				nodesToExamine.put(prefixString, newNode);
			}

			if (prefixString.equals(word)) {
				newNode.setCanEndWord(true);
			}

			currentNode = newNode;
		}
	}

	@Override
	public WordGraphNode findNode(String wordPath) {
		StringBuilder currentPrefix = new StringBuilder("");
		WordGraphNode currentNode = root;
		char[] wordCharacters = wordPath.toCharArray();

		for (char letter : wordCharacters) {
			currentPrefix.append(letter);
			Map<String, WordGraphNode> nodesToExamine = currentNode.getChildNodes();

			WordGraphNode newNode = nodesToExamine.get(currentPrefix.toString());

			if (newNode == null) {
				return null;
			}

			currentNode = newNode;
		}

		return currentNode;
	}

	@Override
	public boolean isWord(String word) {
		WordGraphNode node = findNode(word);

		return node != null && node.canEndWord();
	}

	@Override
	public boolean isPrefix(String word) {
		return findNode(word) != null;
	}
}
