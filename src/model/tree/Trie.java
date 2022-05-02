package model.tree;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 *
 * Super class for node to have dynamic dispatch return
 * Nothing here, there is no overlap signature between subclasses
 * @param <T> Type of the data
 */
interface Node<T> { }

/**
 * Tail node that contains actual data 
 * of the prefix trie. Note that,
 * one key can hold more than 1 data
 * instead of 1 key is mapped to 1 data.
 * @author DucAnh2
 *
 * @param <T> Abitrary type of the data
 */
class TailNode<T> implements Node<T> {
	private List<T> listOfData;
	
	/**
	 * Constructor of the tail node
	 * @param data
	 */
	protected TailNode(List<T> data) {
		this.listOfData = data;
	}
	
	/**
	 * Different constructor of the tail node
	 * that can receive a list of data for the
	 * same node.
	 * @param data
	 */
	protected TailNode(T data) {
		this.listOfData = new ArrayList<>(Arrays.asList(data));
	}

	/**
	 * @return the listOfData
	 */
	public List<T> getListOfData() {
		return listOfData;
	}

	/**
	 * Setter 
	 * @param listOfData the listOfData to set
	 */
	public void setListOfData(List<T> listOfData) {
		this.listOfData = listOfData;
	}
	
}

/**
 * This node can have children of either CompositeNode
 * or TailNode
 * @author DucAnh2
 *
 * @param <T> Type of data of the trie
 */
class CompositeNode<T> implements Node<T> {
	private HashMap<Character, Node<T>> children;
	
	public CompositeNode() {
		setChildren(new HashMap<>());
	}

	public HashMap<Character, Node<T>> getChildren() {
		return children;
	}

	public void setChildren(HashMap<Character, Node<T>> children) {
		this.children = children;
	}
}

/**
 * 
 * @author DucAnh2
 * This is a prefix tree, where the data is stored only in the Tail Node
 * The CompositeNode can hold both CompositeNode or TailNode
 * Each Composite Node will have a children hash map, where the key set
 * is a set of Characters and the value set is set of Tail Node.
 * The end of each prefix (where we store Tail Node) is denoted as 
 * SYMBOL, represent the end of the word.
 * 
 * The tree needs 2 function one is for generating the key
 * the other function is for identifying the object in order to
 * delete it in the TailNode as TailNode will hold a list of 
 * data having the same key.
 * @param <T> data of the trie
 */
public class Trie<T> implements Tree<T>{
	private static final Character SYMBOL = '!';
	
	private Function<T, String> selectKeyFunction;
	private Function<T, String> identifierFunction;
	private CompositeNode<T> root;
	
	/**
	 * Constructor that receives a list of data to 
	 * construct the whole tree.
	 * @param list
	 * @param select
	 * @param identifier
	 */
	public Trie(List<T> list, Function<T, String> select, Function<T, String> identifier) {
		root = new CompositeNode<T>();
		selectKeyFunction  = select;
		identifierFunction = identifier;
		for (T data: list) insert(data);
	}
	
	/**
	 * This constructor only create the Composite root and
	 * set up the select key and the identifying functions.
	 * @param select
	 * @param identifier
	 */
	public Trie(Function<T, String> select, Function<T, String> identifier) {
		root = new CompositeNode<T>();
		selectKeyFunction  = select;
		identifierFunction = identifier;
	}
	
	@Override
	public void insert(T data) {
		String key = selectKeyFunction.apply(data);
		if (key.length() == 0) return;
		
		/*
		 * Constantly move down by each character of 
		 * the prefix.
		 */
		CompositeNode<T> curr = root;
		for (Character c : key.toCharArray()) {
			if (!curr.getChildren().containsKey(c)) {
				curr.getChildren().put(c, new CompositeNode<>());
			}
			curr = (CompositeNode<T>) curr.getChildren().get(c);
		}
		
		/*
		 * At the end of the prefix, we either add more
		 * to the TailNode if it exists, otherwise we
		 * will create a new TailNode 
		 */
		HashMap<Character, Node<T>> mp = curr.getChildren();
		if (mp.containsKey(SYMBOL)) {
			/*
			 * Basically add all of the data that has the same keys
			 */
			List<T> listOfData = ((TailNode<T>) mp.get(SYMBOL)).getListOfData();
			listOfData.add(data);
			curr.getChildren().put(SYMBOL, new TailNode<>(listOfData));
		} else {
			mp.put(SYMBOL, new TailNode<>(data));
		}
	}
	
	/**
	 * Update the data by delete old data and insert the new data
	 * @param data old data
	 * @param newData new data
	 */
	public void update(T data, T newData) {
		if (search(selectKeyFunction.apply(data)).size() == 0) return;
		delete(data);
		insert(newData);
	}
	
	@Override
	public void delete(T data) {
		deleteHelper(root, selectKeyFunction.apply(data), data);
	}

	/**
	 * Helper that delete the node using recursion
	 * References: https://www.geeksforgeeks.org/trie-delete/?ref=lbp
	 * @param root
	 * @param key
	 * @param data
	 * @return
	 */
	public Node<T> deleteHelper(CompositeNode<T> root, String prefix, T data) {
		HashMap<Character, Node<T>> children = root.getChildren();
		
		/*
		 * Either delete the current data from a list or delete the whole node
		 * by removing symbol.
		 * 
		 * If there is no more children, we should return a new CompositeNode
		 * instead of returning the root, so that we can delete the whole 
		 * branch.
		 */
		if (children.containsKey(SYMBOL) ) {
			if (prefix.length() == 0) {
				TailNode<T> dataNode = (TailNode<T>) children.get(SYMBOL);
				if (dataNode.getListOfData().size() > 1)  {
					dataNode.getListOfData().removeIf(e -> identifierFunction.apply(e).equals(identifierFunction.apply(data)));
				} else {
					children.remove(SYMBOL);
				}
				
				return children.size() == 0 ? new CompositeNode<T>() : root;
			} 
		} else {
			if (prefix.length() == 0) return root;
			Character c =  prefix.charAt(0);
			root.getChildren().put(c, 
					deleteHelper((CompositeNode<T>) root.getChildren().get(c), 
					prefix.substring(1), data)); //Call the next recursion with the new prefix. Ex: "word" -> "ord"
		}
		
		return root;
	}


	@Override
	public List<T> search(String prefix) {
		ArrayList<T> result = new ArrayList<>();
		//Reach till the end of this key
		CompositeNode<T> curr = root;
		for (Character c: prefix.toCharArray()) {
			if (curr.getChildren().containsKey(c)) {
				curr = (CompositeNode<T>) curr.getChildren().get(c);
			} else {
				return result;
			}
		}
		
		traverse(curr, result);
		return result;
	}
	
	/**
	 * Help traverse at a current node of the tree. 
	 * It will get all of the possible data from the current
	 * search prefix.
	 * 
	 * @param node node to be traversed
	 * @param result a list of possible data from the node
	 */
	private void traverse(Node<T> node, List<T> result) {
		if (node instanceof TailNode) {
			result.addAll(((TailNode<T>) node).getListOfData());
			return;
		}
		
		CompositeNode<T> curr = (CompositeNode<T>) node;
		for (Character c : curr.getChildren().keySet()) {
			traverse(curr.getChildren().get(c), result);
		}
	}

	@Override
	public List<T> toList() {
		/*
		 * Basically apply the search to all characters
		 * of the first key.
		 */
		return root.getChildren().keySet()
				.stream().map(c -> search(String.valueOf(c))) 
				.reduce(new ArrayList<T>(), 
					(result, subresult) -> { 
						result.addAll(subresult); 
						return result; 
				});
	}

}
