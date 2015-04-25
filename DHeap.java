//ID: 300746683
//User : barelozana
//Name : shay barel ozana
//
//ID: 300433828
//User: amirskal
//Name: Amir Skalka

/**
 * D-Heap
 */

public class DHeap
{
	
	//need to be fixed :
	// global counter/
	//big d issue - fixed!//
	// configuring max size properly- need to ask.
	

	public int size, max_size, d;
	public DHeap_Item[] array;
	//public int counter;
	//    private DHeap_Item[] childs;  //for holding node's childs.

	// Constructor
	// m_d >= 2, m_size > 0
	DHeap(int m_d, int m_size) {

		//counter=0;
		max_size = m_size;
		d = m_d;

		array = new DHeap_Item[max_size];
		size = 0;
	}

	// Getter for size
	public int getSize() {
		return size;
	}

	/**
	 * public void arrayToHeap()
	 *
	 * The function builds a new heap from the given array.
	 * Previous data of the heap should be erased.
	 * preconidtion: array1.length() <= max_size
	 *   postcondition: isHeap()
	 *   size = array.length()
	 */
	public int arrayToHeap(DHeap_Item[] array1) 
	{
		int counter=0;
		array = new DHeap_Item[array1.length];
		array = array1;
		size = array1.length;
		for (int k=0;k<array1.length;k++) //small fix to ensure items positions
			array[k].setPos(k);
		
		int temp = parent(getSize()-1,d);
		for ( int i=temp;i>=0;i--){
			int[] c = new int[]{0};
			counter+=Heapify_down(i,c);
		}
		

		return counter ; // just for illustration - should be replaced by student code
	}

	public int Heapify_down(int i,int c[]){//need to add counter;


		int child_place;
		int smallest=i;
		for (int j = 1 ; j <= d ; j++){
			child_place = child(i,j,d);
			if (child_place < getSize()){
					c[0]++;
					if (array[child_place].getKey() < array[smallest].getKey() ){	
				smallest = child_place;
					}
				
		}}
		if (smallest > i){
			switch_items(array[i],array[smallest]);
			Heapify_down(smallest,c);
		}
		return c[0];		}


	public void switch_items(DHeap_Item a,DHeap_Item b){

		int temp_pos = a.getPos();
		a.setPos(b.getPos());
		b.setPos(temp_pos);

		array[a.getPos()] = a; 
		array[b.getPos()] = b;
		
	}

	public int Heapify_up(int i){

//		boolean b=false;
//		int key =0 ;
//		if (array[i].getKey()<0){ b=true;
//		key = array[i].getKey();
//		}
		int counter=0;
		if (getSize()==0) return 0;
		while ((i > 0) && ( array[i].getKey() < array[parent(i,d)].getKey() )){
			counter++;
			switch_items(array[i],array[parent(i,d)]);
			i = parent(i,d);
		}
//		if (b){System.out.println("key is"+key+"\n");
//				
//		for (int k=0; k<=size-1;k++){
//			System.out.print(array[k].getKey() + " " );
//			
//		} 
//		System.out.println();
//		}
		//if (i!=0) /
		counter++; //counting every last comparsion in while and when i=0
		
		return counter;


	}







	/**
	 * public boolean isHeap()
	 *
	 * The function returns true if and only if the D-ary tree rooted at array[0]
	 * satisfies the heap property or size == 0.
	 *   
	 */
	public boolean isHeap() 
	{
		if (getSize()==0) return true;


		for(int j = 0 ; j < getSize() / d ;j++)	
			for (int i = 1 ; i<= d ; i++){
				int temp = child(j,i,d);
				if (temp < getSize() )
					if (array[j].getKey() > array[temp].getKey() ){
						for (int k=0; k<=size-1;k++)
							System.out.print(array[k].getKey() + " ");
						return false;
						
					}
			}


		return true; 
	}


	/**
	 * public static int parent(i, d), child(i,k,d)
	 * (2 methods)
	 *
	 * precondition: i >= 0, d >= 2
	 *
	 * The methods compute the index of the parent and the k-th child of 
	 * vertex i in a complete D-ary tree stored in an array. 1 <= k <= d.
	 * Note that indices of arrays in Java start from 0.
	 */
	public static int parent(int i, int d) {
		if (i==0) return 0;
		double dou = (i-1)/d;

		return (int) Math.floor(dou);

	} 
	public static int child (int i, int k, int d) { return d*i+k;} // just for illustration - should be replaced by student code 

	/**
	 * public int Insert(DHeap_Item item)
	 *
	 * precondition: item != null
	 *               isHeap()
	 *               size < max_size
	 * 
	 * postcondition: isHeap()
	 */
	public int Insert(DHeap_Item item) 
	{   
		int counter=0;
		item.setPos(size);
		array[size]=item;
		size++;
		counter = Heapify_up(item.getPos());


		return counter ;// should be replaced by student code
	}

	/**
	 * public int Delete_Min()
	 *
	 * precondition: size > 0
	 *               isHeap()
	 * 
	 * postcondition: isHeap()
	 */
	public int Delete_Min()
	{
		int counter=0;
		int []c = new int[]{0};
		switch_items(Get_Min(),array[getSize()-1]);
		array[getSize()-1]=null;
		size--;
		counter = Heapify_down(0,c);

		return counter;// should be replaced by student code
	}


	/**
	 * public String Get_Min()
	 *
	 * precondition: heapsize > 0
	 *               isHeap()
	 *		size > 0
	 * 
	 * postcondition: isHeap()
	 */
	public DHeap_Item Get_Min()
	{
		return array[0];// root is value in place 0.
	}

	/**
	 * public int Decrease_Key(DHeap_Item item, int delta)
	 *
	 * precondition: item.pos < size;
	 *               item != null
	 *               isHeap()
	 * 
	 * postcondition: isHeap()
	 */
	public int Decrease_Key(DHeap_Item item, int delta)
	{
		int counter=0;
		int pos = item.getPos();
		if (delta==Integer.MAX_VALUE)
			array[pos].setKey(Integer.MIN_VALUE);
		else
			array[pos].setKey(item.getKey()-Math.abs(delta));

		counter = Heapify_up(pos);
		return counter;
	}

	/**
	 * public void Delete(DHeap_Item item)
	 *
	 * precondition: item.pos < size;
	 *               item != null
	 *               isHeap()
	 * 
	 * postcondition: isHeap()
	 */
	public int Delete(DHeap_Item item)
	{
		int counter=0;
		counter+=Decrease_Key(item, Integer.MAX_VALUE);//Integer.MAX_VALUE); //2nd implementation option -> decreasekey(item , source.key-item.key)
		counter+=Delete_Min();


		return counter;
	}

	public int get_max(){
		return max_size;
	}
	/**
	Sort the array in-place using heap-sort (build a heap, and 
	perform n times: get-min, del-min).
	Sorting should be done using the DHeap, value of item is irrelevant.
	Return the number of comparison performed.
	 */
	public static int DHeapSort(int[] array, int d) {

		if (array.length==0) return 0;

		DHeap arr = new DHeap(d,array.length); //i hope it wil
		DHeap_Item[] arr2 = new DHeap_Item[array.length]; 
			
		for (int i=0 ; i < array.length ; i++){
			
			arr2[i] = new DHeap_Item("",array[i]);
			arr2[i].setPos(i);
		}
		int count = arr.arrayToHeap(arr2); 
		
		for (int k = 0 ; k < array.length ; k++){
			array[k] = arr.Get_Min().getKey();
			count+=arr.Delete_Min();
		}

		return count;

	}
}
