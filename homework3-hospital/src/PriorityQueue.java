
public class PriorityQueue <T extends Comparable<? super T>> implements PriorityQueueInterface <T> {
	private Node firstNode;
	private Node lastNode;
	private Node nodeAfter;
	private int numberOfEntries;
	int counter = 0;

	
	public PriorityQueue() {
		this.firstNode = null;
		this.lastNode = null;
		this.numberOfEntries = 0;
	}
	
	@Override
	public void add(T newEntry, String date, int priority) {
		Node newNode = new Node(newEntry, date, priority);
		if (isEmpty()) {
			firstNode = newNode;
		}
		else {
			lastNode.setNextNode(newNode);
		} 

		lastNode = newNode;
		numberOfEntries++;
	}

	@Override
	public T remove() {
		Node nodeBefore = getNodeBefore(firstNode);
		if (nodeBefore == null) {
			Node tempNode = new Node(firstNode.getData(), firstNode.getDate(), firstNode.getPriority());
			if (getNodeAfter() == null) {
				nodeAfter = firstNode.getNextNode();				
			}
			firstNode.setData(null);
			firstNode = getNodeAfter();
			setNodeAfter(null);
			return tempNode.getData();
		}
		else {
			Node tempNode = new Node(nodeBefore.getData(), nodeBefore.getDate(), nodeBefore.getPriority());
			if (getNodeAfter() == null) {
				nodeAfter = nodeBefore.getNextNode();
			}
			else {
				nodeAfter.setNextNode(nodeBefore.getNextNode());				
			}
			nodeBefore.setData(null);	
			firstNode.setNextNode(nodeAfter);
			setNodeAfter(null);
			return tempNode.getData();
		}
	}

	@Override
	public T peek() {
		if (isEmpty()) {
			return null;
		}
		else {
		return firstNode.getData();
		}
	}

	@Override
	public boolean isEmpty() {
		return (firstNode == null) && (lastNode == null);
	}

	@Override
	public int getSize() {
		return numberOfEntries;
	}

	@Override
	public void clear() {
		lastNode = null;
		firstNode = null;
		numberOfEntries = 0;
	}
	
	private Node getNodeBefore(Node anEntry) {
		Node currentNode = firstNode.getNextNode();
		Node nodeBefore = null;
		while ( (currentNode != null) && (anEntry.compareTo(currentNode) > 0)){
			if (anEntry.compareTo(currentNode) == 2) {
				counter++;
			}
			nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
		}
		
		if (counter == 1) {
			setNodeAfter(firstNode.getNextNode());
		}
		else if (counter > 1) {
			nodeBefore = null;
			counter = 0;
		}
		return nodeBefore;
	}
	
	private Node getNodeAfter() {
		return nodeAfter;
	}
	private void setNodeAfter(Node anEntry) {
		this.nodeAfter = anEntry;
	}
	
//	private inner class
	private class Node implements Comparable<Node> {
		private T data;
		private int priority;
		private String date;
		private Node next;
		
		private Node(T dataPortion, String date, int priority) {
			this(dataPortion, date, priority, null);
		}
		
		private Node(T dataPortion, String date, int priority, Node nextNode) {
			this.data = dataPortion;
			this.date = date;
			this.priority = priority;
			this.next = nextNode;
		}

		private int getPriority() {
			return priority;
		}

		@SuppressWarnings("unused")
		private void setPriority(int priority) {
			this.priority = priority;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			this.data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			this.next = nextNode;
		}

		private String getDate() {
			return date;
		}

		@SuppressWarnings("unused")
		private void setDate(String date) {
			this.date = date;
		}

		@Override
		public int compareTo(Node other) {
			if (other.getDate().equals(this.getDate())) {
				if (other.getPriority() > this.getPriority()) {
					return 1;
				}
				else if (other.getPriority() < this.getPriority()) {
					return -1;
				}
				else {
					return 2;
				}
				}	
			else {
				return 0;
			}
		}		
	}



	
}
