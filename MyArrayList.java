/**                  File Header
*MyArrayList.java is my replication of the ArrayList provided
*by java. It implements the key methods of the ArrayList 
*as enforced by MyList interface, showing comprehention of
*how the ArrayList manipulates the heap. If you have any questions
*my name is Nathalie Franklin and I can be reached at 
*nfranki@ucsd.edu  
*/
 

 /** 
 *This class is a more flexible array that keeps a sorted list while
 *letting you append, prepend, while it automatically adjusts the field
 *array or you can trimToSize the array's extra space or expandCapacity
 *ifyou want more control over allocated space. What more it will 
 *let you remove, prepend and insert while automatically shifting the
 *other refrences to correct place and correctly keep track of size for
 *all methods that adjust it.
 */
 public class MyArrayList<E> implements MyList<E>{
     Object[] data;
     int size;
     private static final int DEFAULT_CAPACITY = 5;
     /** 
     * Constructor that intializes the global fields;
     * size to 0 and data array length to defualt valuas 
     * @param none
     * @return none
     */
     public MyArrayList (){
         data = new Object[DEFAULT_CAPACITY];
         size = 0;

     } 
     /** 
     * Constructor that intializes the global fields
     * size to 0 and data array length to initialCapacity
     * @param initialCapacity (type: int)
     * @return none
     */
     public MyArrayList (int initialCapacity){
         if (initialCapacity < 0){
             throw new IllegalArgumentException();

         }
         else{
             data = new Object[initialCapacity];
             size= 0;
         } 

     }
     /** 
     * Constructor that intializes the gloabel fields;
     * size to 0 and the arraylist to a shallow copy of the
     * arraylist passed in array.
     * @param arr (type:E[] arr) 
     * @return none
     */
     public MyArrayList (E[] arr){
         if( arr == null){
             data = new Object[DEFAULT_CAPACITY];
             size = 0;
         }
         else{
            data = arr.clone();
            size = arr.length;
         }
       
         
         
    }
     /** 
     * If ArrayList is at risk of not storing an
     * added variable this methods is called to either to
     * change lenth 0 to 5, double the length or use 
     * required Capicity if more then double current length or 5.
     * Unless invalid requiredCapacity is passed in.
     * @param requiredCapacity(type: int) minimum capacity to increase too
     * @return void
     */
     public void expandCapacity(int requiredCapacity){
         int max_index;
         if(requiredCapacity<data.length){
             throw new IndexOutOfBoundsException();
         }
         // Checks if capacity should be default or parameter
         if ( data.length ==0){
            if (requiredCapacity >DEFAULT_CAPACITY){
                data = new Object[requiredCapacity];
             
            }
            else {
                data = new Object[DEFAULT_CAPACITY];
            }
         }
         //Checks if capacity should be double or parameter
         else{
            if ((data.length*2) < requiredCapacity){
                 max_index = requiredCapacity;
            }
            else{
                 max_index = data.length*2;
            }
            Object[] temp_data = new Object[max_index];
            for(int index =0; index< size; index ++ ){
                temp_data[index] = data[index];  
            }
            data = temp_data;
         }

     }
     /** 
     * Returns the Capacity
     * @param none
     * @returns data.length(type:int)how much space is left
     */
     public int getCapacity(){
         return data.length;
     }
     /** 
     * Inserts the element passed in to the index passed and increases size. 
     * Makes sure to shift all the variables and expand the 
     * capacity if no space is left. 
     * Unless invalid indes is passed in.
     * @param index(type:int)where to insert
     * @param element(type:E)what to insert
     * @return The scrabble value of the letter
     */
     public void insert(int index, E element){
         if ( index < 0|| index > size ){
             throw new IndexOutOfBoundsException();
         }
         
         if (size >= data.length){
              expandCapacity(data.length+1);
         }
         Object [] shift = data.clone();
         shift[index]=element;
         //shifts to the right the elements for insertion
         for( int index_2 =index; index_2 < size; index_2++){

             shift[index_2+1] = data[index_2];
             
         }
         data= shift;
         size+=1;
     }
     /** 
     * Adds element to the end of the list, increase size
     * but first makes sure there is enough space.
     *
     * @param element(type:E)element to add
     * @return void
     */
     public void append(E element){
         if (size >= data.length){
              expandCapacity(data.length+1);
         }
       
        data[size] = element;
        size +=1;
     }
     /** 
     * Adds a variable to the begging of the list shifting all others 
     * back and increases size 
     * @param element(type E)element to add
     * @return void
     */
     public void prepend(E element){
         int before_index = 1;

         if (size >= data.length){
              expandCapacity(data.length+1);
         }
         if(size ==0){
            data[size]=element;

         }
         else{
          //shifts to the right the refrences after prepention
          for( int index_2 = size; index_2 > 0 ; index_2--){
             data[index_2] = data[index_2-1];
             
            }
          data[0] = element;
         }
         size +=1;
     }
    /** 
    * Returns the refrence in the location of the
    * passed variable or null if can't be loacted 
    * unless passed invalid index
    * @param letter The letter in question
    * @return The scrabble value of the letter
    */
    public E get(int index) {
        if (index >= size || index <0){
            throw new IndexOutOfBoundsException(); 
        }
        else{
            return (E) data[index];
        }

    }
    /** 
    * Replaces the refrence in the passed index
    * with the refrence passed in unless invalid 
    * index
    * @param letter The letter in question
    * @return The scrabble value of the letter
    */
    public E set(int index, E element){
        if (index >= size || index <0){
            throw new IndexOutOfBoundsException(); 
        }
        data[index] = element;
        return (E)data[index];
    }
    /** 
    * Removes the refrence in the passed index and shift all other refrences
    * unless invalid index.
    * @param index(type:int)where to remove
    * @return return_val(type:E)what was removed
    */
    public E remove(int index){
        
        if (index >= size || index <0){
            throw new IndexOutOfBoundsException(); 
        } 
        Object[] shift = data.clone();
        E return_val = (E)data[index];
        //shifts the refrences the left after deltion
        for (int index_2 = index;  index_2 < size-1; index_2++){
            
           shift[index_2] = data[index_2+1]; 

        } 
        shift[size-1] = null;
        size-=1; 
        return return_val;
    }
    /** 
    *Returns the amount of actual elements 
    * in the array  
    * @param none
    * @return size(type:int) size of the arraylist
    */
    public int size(){
        return size; 
    }
    /** 
    * Removes extra space alocated by the array
    * that's not occupied. 
    * @param letter The letter in question
    * @return The scrabble value of the letter
    */
    public void trimToSize(){
        if (size < data.length){
            Object[] val = new  Object[size];
            for (int index = 0; index < size; index ++){
                val[index]= data[index];
            }
            data = val; 
        }


    }
    /** 
    * Return index of the passed in refrence but 
    * if it can't be found return null 
    * @param element(type:E) refrence to be found
    * @return index(type:int)location of the refrence in array
    */
    public int indexOf(E element){
        if (element == null){
            for (int index = 0; index < size; index ++){
                if(data[index] == element){
                    return index;
                }
            }

        }
        else{
            for (int index = 0; index < size; index ++){
                if(data[index].equals(element)){
                    return index;
                }
            }
        }
        return -1;
            
         
    }
    
    
 }
