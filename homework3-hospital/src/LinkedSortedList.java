
public class LinkedSortedList <T extends Comparable<? super T>> implements SortedListInterface <T>{
	private Node firstNode;
	private int numberOfEntries;
	
	public LinkedSortedList(){
		initializeDataFields();
	}
	
//	removes all entries from this list
	public void clear() {
		initializeDataFields();
	}
//	adds a new entry to the end of this list
	public void add(T newEntry) {
		Node newNode = new Node(newEntry);
		Node nodeBefore = getNodeBefore(newEntry);
		if (isEmpty() || (nodeBefore == null)) {
			newNode.setNextNode(firstNode);
			firstNode = newNode;
		}
		else {
			Node nodeAfter = nodeBefore.getNextNode();
			newNode.setNextNode(nodeAfter);
			nodeBefore.setNextNode(newNode);
		} 
		numberOfEntries++;
	}
	
//	removes the entry at a given position from this list
	public T remove(int givenPosition) {
		T result = null;
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
//			case 1 : remove the first entry
			if (givenPosition == 1) {
				result = firstNode.getData();
				firstNode = firstNode.getNextNode();
			}
//			case 2 : not first entry
			else {
				Node nodeBefore = getNodeAt((givenPosition - 1));
				Node nodeToRemove = nodeBefore.getNextNode();
				result = nodeToRemove.getData();
				Node nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);
			}
			numberOfEntries--;
		}
		else {
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
		}
		return result;
	}

	
//	sees whether this list is empty
	public boolean isEmpty() {
		boolean result;
		if(numberOfEntries == 0) {
			assert firstNode == null;
			result = true;
		}
		else {
			assert firstNode != null;
			result = false;
		}
		return result;
	}
	
//	retrieves all entries that are in this list in the order in which they occur in the list
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		
		int index = 0;
		Node currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		}
		return result;
	}
	
//	retrieves the entry at a given position in this list
	public T getEntry(int givenPosition) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			return getNodeAt(givenPosition).getData();
		}
		else {
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
		}
	}
	
//	gets the length of this list
	public int getLength() {
		return numberOfEntries;
	}
	
//	sees whether this list contains a given entry
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData())){
				found = true;
			}
			else {
				currentNode = currentNode.getNextNode();
			}
		}
		return found;
	}
	
//	initializes the class' data fields to indicate an empty list
	private void initializeDataFields() {
		firstNode = null;
		numberOfEntries = 0;
	}
	
//	returns a reference to the node at a given position
	private Node getNodeAt(int givenPosition) {
		assert(firstNode != null) && (1<= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;
		
		for (int counter = 1; counter < givenPosition; counter++) {
			currentNode = currentNode.getNextNode();
		}
		
		assert currentNode != null;
		return currentNode;
	}
	
	private Node getNodeBefore(T anEntry) {
		Node currentNode = firstNode;
		Node nodeBefore = null;
		while ( (currentNode != null) && (anEntry.compareTo(currentNode.getData()) > 0)){
			nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
		}
		return nodeBefore;
	}
	
	//	private inner class
	private class Node {
		private T data;
		private Node next;
		
		private Node(T dataPortion) {
			this(dataPortion, null);
		}
		
		private Node(T dataPortion, Node nextNode) {
			this.data = dataPortion;
			this.next = nextNode;
		}

		private T getData() {
			return data;
		}

		@SuppressWarnings("unused")
		public void setData(T newData) {
			this.data = newData;
		}

		public Node getNextNode() {
			return next;
		}

		public void setNextNode(Node nextNode) {
			this.next = nextNode;
		}		
	}

}
