/**
* A Singly Linked Class
* Storing a list of SNode nodes with integer elements
*/

public class SinglyLinkedList {
	private SNode head;		//The head node of the list
	private SNode tail;		//The tail node of the list
	int size;				//The number of nodes in the list

	//Constructor method
	public SinglyLinkedList() {
		 head = null;		//head and tail are null in an empty list
		 tail = null;
		 size = 0;
	}

	//ACCESS METHODS
	//Returns the number of nodes in the list
	public int size() {
		return size;
	}

	//Returns true of the list is empty, false otherwise
	public boolean isEmpty() {
		return size == 0;
	}

	//Returns the first element in the list, null if empty
	public GameScore first() {
		if (isEmpty()) return null;
		return head.getElement();
	}

	//Returns the last element in the list, null if empty
	public GameScore last() {
		if (isEmpty()) return null;
		return tail.getElement();
	}

	//Adds a new element to the front of the list
	public void addFirst(GameScore e) {
		SNode newNode = new SNode(e, head);
		head = newNode;
		if (isEmpty()) { tail = head; }	//Special case
		size++;
	}

	//Add a new element to the end of the list
	public void addLast(GameScore e) {
		SNode newNode = new SNode(e, null);
		if (isEmpty()) { head = newNode; }	//Special case
		else {
			tail.setNext(newNode);
		}
		tail = newNode;
		size++;
	}

	//Removes and returns the first element of the list
	//Returns null if list is empty
	public GameScore removeFirst() {
		if (isEmpty()) { return null; }
		SNode temp = head;
		head = head.getNext();
		size--;
		if (isEmpty()) { tail = null; }
		temp.setNext(null);		//Optional
		return temp.getElement();
	}

	//Prints the elements of the list
	public void display() {
		SNode current = head;
		while (current != null) {      // for each SNode,
			System.out.print(current + " ");  // display it		}
			current = current.getNext();
		}
		System.out.println("");
	}
	
	public boolean equals(SinglyLinkedList other) {
		if (other == null) return false;
		if (size != other.size()) return false;
		SNode current = head;
		SNode otherCurrent = other.head;
		while (current != null) {
			if (current.getElement() != otherCurrent.getElement())
				return false;
			//If elements were objects then we would use the equals method instead of !=
			//if (!current.getElement().equals(otherCurrent.getElement()))
			current = current.getNext();
			otherCurrent = otherCurrent.getNext();
		}
		return true;
	}
	
	//Removes and returns the last element in the list
	public GameScore removeLast() {
		if (isEmpty()) return null;
		if (size == 1) return removeFirst();
		
		SNode current = head;
		SNode ahead = head.getNext();
		while (ahead.getNext() != null) {
			current = current.getNext();
			ahead = ahead.getNext();
		}
		current.setNext(null);
		tail = current;
		size--;
		return ahead.getElement();
	}


	/**
	 * Checks to see if a given GameScore is contained in the linked list
	 * @param target - A GameScore object to be searched for
	 * @return - the SNode containing the target GameScore if found, null otherwise
	 * @author Annika Hoag
	 */
	public SNode find(GameScore target) {
		SNode current = head;

		while(current.getNext() != null){					//loop until you're at last node

			if(current.getElement().equals(target)){		//return node if its element equals target
				return current;
			}

			current = current.getNext();					//else reset current to the next node
		}//close while loop

		if (current.getNext()==null){						//test for if last node is equal to target
			if(current.getElement().equals(target)){
				return current;
			}
		}	
		return null;										//if we've reached this target is not in the list
	}


	/**
	 * Adds a new GameScore object into the linked list
	 * The list is sorted in decreasing order by score, this method creates an SNode with the given
	 * GameScore and then inserts it in the appropriate spot in the already-sorted list
	 * @param newScore - A GameScore object to be added to the list
	 * @author Annika Hoag
	 */
	public void add(GameScore newScore){
		if (isEmpty()){										//test if list is empty
			this.addFirst(newScore);						//if it is use addFirst
		
		}else if (size==1){									//test for size 1 list to avoid errors when using getNext() method
			
			//if head is greater than newScore it goes to the right of head 
			if (head.getElement().getScore() >= newScore.getScore() ){
				SNode newNode = new SNode (newScore, null);
				head.setNext(newNode);
				size++;

			//else it goes in the first spot	
			}else{
				this.addFirst(newScore);
			}

		}else{

			SNode current = head;						
			SNode ahead = head.getNext();
			SNode temp;

			while (ahead.getNext() != null){			//loop until you've reached last node
			
				//test is newScore's score is in between the current and ahead node's score
				if ( (current.getElement().getScore() >= newScore.getScore()) && (ahead.getElement().getScore() < newScore.getScore()) ){
					SNode newNode = new SNode (newScore, ahead);		//create new node with ahead being its next
					current.setNext(newNode);							//make the new node current's next
					size++;
					break;

				//test if newScore is greater than current, 
				//this means it's the new head b/c sorted list gurantees newScore will always be in btwn 2 nodes otherwise	
				}else if (newScore.getScore() >= current.getElement().getScore()){
					SNode newNode = new SNode (newScore, current);		//create new node with current being its next
					head = newNode;										//set head to the new node
					size++;
					break;
				
				//else newScore goes somewhere else
				}else{
					current = ahead;			//reset current to be ahead
					ahead = ahead.getNext();	//reset ahead to its next to continue through the loop
				}

			}//closes while

			//testing for if newScore goes at the end
			//Note: didn't call addLast() being that would replace whatever node is currently last
			if (ahead.getNext()==null){	

				//only goes at end if ahead (last element) if greater than new Score
				if (ahead.getElement().getScore() >= newScore.getScore()){
					SNode newNode = new SNode(newScore, null);		//create new node with null being its next (end of list)
					ahead.setNext(newNode);							//make ahead's next the new node
					tail = newNode;									//make the new node the nail
					size++;
				}
			}	
		
		}//closes else

		
	}


	/**
	 * Deletes a given GameScore entry from the linked list
	 * @param gameScore - A GameScore object to be searched for and deleted
	 * @return the SNode containing the target GameScore if search and deletion was   successful, null otherwise.
	 * @author Annika Hoag
	 */
	public SNode delete(GameScore gameScore) {
		SNode temp=null;

		//check for empty list
		if (isEmpty()){
			return null;
		
		//check for size 1 list that has gameScore in it or gameScore being the head
		}else if (size==1 && head.getElement().equals(gameScore) || head.getElement().equals(gameScore)){
			temp = head;
			temp.setElement(this.removeFirst());				//call removeFirst() in this case
			size--;
			return temp;

		}else{
			SNode previous = head;			//keep track of previous for resetting values later
			SNode current = head.getNext();

			//loop until end of list
			while (current.getNext() != null ){

				//if found gameScore
				if (current.getElement().equals(gameScore) ){
					previous.setNext(current.getNext());	//set previous' next to current's next
					temp = current;
					size--;
					return temp;
				
				//else reset previous to current and current to its next to continue through list
				}else{
					previous = current;						
					current = current.getNext();
				}

			}//closes while loop

			//test for last node in list
			if (current.getNext() == null){
				temp = tail;
				temp.setElement(this.removeLast());
				size--;
				return temp;
			}


			return null;
		}//closes else

	}



	
	
	public static void main(String[] args) {
		//creates an instance of singly linked list class
		SinglyLinkedList scoreBoard = new SinglyLinkedList();

		//adding GameScore objects 
		GameScore player1 = new GameScore("Erin", 50);
		GameScore player2 = new GameScore("David", 67);
		GameScore player3 = new GameScore("Annika", 98);
		GameScore player4 = new GameScore("Diane", 77);
		GameScore player5 = new GameScore("Carter", 1000);

		scoreBoard.add(player3);
		scoreBoard.add(new GameScore("Erica", 81));
		scoreBoard.add(player2);
		scoreBoard.add(player5);
		scoreBoard.add(player1);
		scoreBoard.add(new GameScore("Carol", 126));
		scoreBoard.add(new GameScore("Bob", 90));
		scoreBoard.add(player4);
		scoreBoard.add(new GameScore("Bill", 90));
		scoreBoard.add(new GameScore("Lincoln", 72));
		

		//display
		System.out.println("\nThis is what the singly linked list looks like: ");
		scoreBoard.display();

		//testing find
		System.out.println("\nFound " + scoreBoard.find(player1));
		System.out.println("Found " + scoreBoard.find(player2));

		//testing remove
		System.out.println("\nRemoved " + scoreBoard.delete(player3));
		System.out.println("Removed " + scoreBoard.delete(player4));
		System.out.println("Removed " + scoreBoard.delete(player1));
		System.out.println("Removed " + scoreBoard.delete(player5));

		//display
		System.out.println("\nThis is what the singly linked list looks like now: ");
		scoreBoard.display();

	}

}//closes SinglyLinkedList