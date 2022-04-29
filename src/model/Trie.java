package model;

import java.awt.Composite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

interface Node<T> { }

class TailNode<T> implements Node<T> {
	private List<T> listOfData;
	
	protected TailNode(List<T> data) {
		this.listOfData = data;
	}
	
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
	 * @param listOfData the listOfData to set
	 */
	public void setListOfData(List<T> listOfData) {
		this.listOfData = listOfData;
	}
	
}

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
 * This is a prefix tree, where the data is stored only in the EndNode
 * 
 * @param <T> data of the trie
 */
public class Trie<T> implements Tree<T>{
	private static final Character SYMBOL = '!';
	
	private Function<T, String> selectKeyFunction;
	private Function<T, String> identifierFunction;
	private CompositeNode<T> root;
	
	public Trie(List<T> list, Function<T, String> select, Function<T, String> identifier) {
		root = new CompositeNode<T>();
		selectKeyFunction  = select;
		identifierFunction = identifier;
		for (T data: list) insert(data);
	}
	
	public Trie(Function<T, String> select, Function<T, String> identifier) {
		root = new CompositeNode<T>();
		selectKeyFunction  = select;
		identifierFunction = identifier;
	}
	
	@Override
	public void insert(T data) {
		String key = selectKeyFunction.apply(data);
		
		if (key.length() == 0) return;
		CompositeNode<T> curr = root;
		for (Character c : key.toCharArray()) {
			if (!curr.getChildren().containsKey(c)) {
				curr.getChildren().put(c, new CompositeNode<>());
			}
			curr = (CompositeNode<T>) curr.getChildren().get(c);
		}
		
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
	
	public void update(T data, T newData) {
		if (search(selectKeyFunction.apply(data)).size() == 0) {
			System.out.println("Cound't find" + data.toString());
			return;
		} else {
			System.out.println("HEEII");
		}
		delete(data);
		insert(newData);
	}
	
	@Override
	public void delete(T data) {
		deleteHelper(root, selectKeyFunction.apply(data), data);
	}

	public Node<T> deleteHelper(CompositeNode<T> root, String key, T data) {
		HashMap<Character, Node<T>> children = root.getChildren();
		if (children.containsKey(SYMBOL) ) {
			if (key.length() == 0) {
				TailNode<T> dataNode = (TailNode<T>) children.get(SYMBOL);
				if (dataNode.getListOfData().size() > 1)  {
					dataNode.getListOfData().removeIf(e -> identifierFunction.apply(e).equals(identifierFunction.apply(data)));
				} else {
					children.remove(SYMBOL);
				}
				if (children.size() == 0) {
					return new CompositeNode<T>();
				} else {
					return root;
				}
			} 
		} else {
			if (key.length() == 0) return root;
			Character c =  key.charAt(0);
			root.getChildren().put(c, 
						deleteHelper((CompositeNode<T>) root.getChildren().get(c), key.substring(1), data));
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
	public T lookup(String key, Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<T> toList() {
		return root.getChildren().keySet()
				.stream().map(c -> search(String.valueOf(c))) 
				.reduce(new ArrayList<T>(), 
					(result, subresult) -> { 
						result.addAll(subresult); 
						return result; 
				});
	}

//	@Override
//	public T lookup(String key, Predicate<T> predicate) {
//		CompositeNode<T> curr = root;
//		for (Character c: key.toCharArray()) {
//			if (curr.getChildren().containsKey(c)) {
//				curr = (CompositeNode<T>) curr.getChildren().get(c);
//			} else {
//				return null;
//			}
//		}
//		
//		List<T> result = ((TailNode<T>) curr.getChildren().get(SYMBOL)).getListOfData();
//		System.out.println("Whole" +result);
//		return result.size() == 0 ? null : result.stream().filter(e -> predicate.test(e))
//				.collect(Collectors.toList()).get(0);
//	}

}
