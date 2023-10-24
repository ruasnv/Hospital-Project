
public class LList <T> implements ListInterface<T>{
	private Node firstNode;
	private Node lastNode;
	private int numberOfEntries;
	
//	constructor
	public LList() {
		initializeDataFields();
	}
	
//	removes all entries from this list
	public void clear() {
		initializeDataFields();
	}
	
//	adds a new entry to the end of this list
	public void add(T newEntry) {
		Node newNode = new Node(newEntry);
		if (isEmpty()) {
			firstNode = newNode;
		}
		else {
			lastNode.setNextNode(newNode);
		}
		lastNode = newNode;
		numberOfEntries++;
	}
	
//	adds a new entry at a specified position within this list
	public void add(int newPosition, T newEntry) {
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
			Node newNode = new Node(newEntry);
			if (isEmpty()) {
				firstNode = newNode;
				lastNode = newNode;
			}
			else if (newPosition == 1) {
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}
			else if (newPosition == numberOfEntries + 1){
				lastNode.setNextNode(newNode);
				lastNode = newNode;
			}
			else {
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			}
			numberOfEntries++;
		}
		else {
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
		}
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
				if (numberOfEntries == 1) {
					lastNode = null;
				}
			}
//			case 2 : not first entry
			else {
				Node nodeBefore = getNodeAt((givenPosition - 1));
				Node nodeToRemove = nodeBefore.getNextNode();
				Node nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);
				result = nodeToRemove.getData();
				if (givenPosition == numberOfEntries) {
					lastNode = nodeBefore;
				}
			}
			numberOfEntries--;
		}
		else {
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
		}
		return result;
	}
	
//	replaces the entry at a given position in this list
	public T replace(int givenPosition, T newEntry) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			Node desiredNode= getNodeAt(givenPosition);
			T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
			return originalEntry;
		}
		else {
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
		}
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
		lastNode = null;
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

