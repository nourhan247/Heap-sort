//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Heap heap = new Heap(10);
        heap.insert(50);
        heap.insert(30);
        heap.insert(20);
        heap.insert(60);
        heap.insert(10);

        System.out.println("Heap after insertion:");
        heap.printHeap();

        heap.delete(); // Delete root
        System.out.println("Heap after deletion:");
        heap.printHeap();

        System.out.println("Deleted elements:");
        heap.printDeletedHeap();
        }
    }
class Heap{
    int heap[];
    int capacity;
    int size;
    int deletedRoot[];
    int dCount;
    public Heap(int capacity){
        heap = new int[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.dCount=0;
        deletedRoot = new int[capacity];
    }
    private int leftChild(int index){ return index*2+1;}
    private int rightChild(int index){ return index*2+2;}
    private int parent(int index){ return (index-1)/2;}

      private void swap(int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
      }


    public void buildup(){  //o(n)
        for(int i=(size/2)-1;i>=0;i--){  //specify subtrees
            heapifydown(i);

        }
    }
    public void heapifydown(int index){
        while(index<size/2){
            int left=leftChild(index);
            int right=rightChild(index);
            int largest=left;

            if(right<size&&heap[right]>heap[largest]){
                largest=right;
            }
            if(heap[index]>=heap[largest]){
                break;
            }
            swap(index, largest);
            index=largest;

        }
    }

    public void insert(int value){ //o(log n)
        if (size >= capacity) { // Prevent overflow
            throw new IllegalStateException("Heap is full!");
        }
        heap[size++]=value;
        int temp=size-1;
        while(temp>0){
            int parent=parent(temp);
            if(heap[parent]<heap[temp]){
                swap(parent,temp);
                temp=parent;
            }
            else return;
        }
    }
    public void delete(){ //o(log n)
        if (size == 0) {
            throw new IllegalStateException("Heap is empty!");
        }
        int root=heap[0];
        heap[0]=heap[size-1];
        deletedRoot[dCount++]=root;
        size--;
        heapifydown(0);
    }
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
    public void printDeletedHeap() {
        for (int i = 0; i < dCount; i++) {
            System.out.print(deletedRoot[i] + " ");
        }
        System.out.println();
    }
}