package model.tree;

import java.util.List;

/**
 * ADT for prefix tree
 * Subclasses must contain insert, delete, search, toList operations
 * @author Duc Anh
 *
 * @param <T>
 */
public interface Tree<T> {
	/**
	 * Insert the data to the tree
	 * @param data the data to be inserted to the tree
	 */
	public void insert(T data);
	
	/**
	 * Delete the data of the tree
	 * The key will be determined by the data
	 * @param data
	 */
	public void delete(T data);
	
	/**
	 * Return all data begin with the prefix
	 * @param prefix
	 * @return
	 */
	public List<T> search(String prefix);
	
	/**
	 * Print all of the data of this tree
	 * This can be used to push to the database for 
	 * backup
	 * @return List of all data from this tree
	 */
	public List<T> toList();
}
