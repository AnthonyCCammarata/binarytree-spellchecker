// Binary search tree
// DSM, 2014

public class BinarySearchTree {
  
  private TreeNode root;
  
  
  /*** Create a new, empty search tree ***/
  public BinarySearchTree() {
    this.root = null; 
  }
  
  
  /*** Insert into the tree ***/
  public void insert(int newKey, Object newRecord) {
    
    // Create a new key containing the searchable key and corresponding record data
    TreeNode newNode = new TreeNode(newKey, newRecord); 
    
    // Special case to insert into an empty tree
    if (this.root == null) {
      this.root = newNode;
      return;
    }
    
    // Beginning at the root, search through the tree for the appropriate empty spot
    TreeNode currentNode = this.root;
    boolean searching = true;
    
    while (searching) {
      
      // If key < currentNode's key, go left
      if (newNode.getKey() < currentNode.getKey()) {
        TreeNode nextNode = currentNode.getLeftChild();
        
        // If the next position is empty, insert there
        if (nextNode == null) {
          currentNode.setLeftChild(newNode);
          newNode.setParent(currentNode.getParent());
          searching = false;
        }
        else {
          currentNode = nextNode; 
        }
      }
      
      // If key > currentNode's key, go right
      else {
        TreeNode nextNode = currentNode.getRightChild();
        
        if (nextNode == null) {
          currentNode.setRightChild(newNode);
          newNode.setParent(currentNode.getParent());
          searching = false;
        }
        else {
          currentNode = nextNode; 
        }
      }
    }
  }
    
  
  /*** Search for a key and return its corresponding record ***/
  public Object search(int searchKey) {
    TreeNode currentNode = this.root;
      
    while (currentNode != null) {
        
      // Key found, return corresponding record
      if (searchKey == currentNode.getKey()) {
        return currentNode.getRecord(); 
      }
        
      // If key < currentNode key, go left
      else if (searchKey < currentNode.getKey()) {
        currentNode = currentNode.getLeftChild();
      }
       
      // Else, go right
      else {
        currentNode = currentNode.getRightChild(); 
      }
    }
      
    // If search fails, return null
    return null;
  }
  
  
  /*** Perform in-order traversal to print values in sorted order ***/
  public void doTraverse(TreeNode node) {
    if (node == null)
      return;
    
    doTraverse(node.getLeftChild());
    System.out.println("Key: " + node.getKey() + "\tRecord: " + node.getRecord());
    doTraverse(node.getRightChild());
  }
 
  public void traverse() { 
    doTraverse(this.root);  
  }

  
  /*** Main ***/  
  public static void main(String[] args) throws Exception {
    BinarySearchTree bst = new BinarySearchTree();
      
    bst.insert(3, "THREE");
    bst.insert(1, "ONE");
    bst.insert(2, "TWO");
    bst.insert(5, "FIVE");
    bst.insert(4, "FOUR");
      
    bst.traverse();
    
    for (int i = 1; i <= 5; i++) {
      String val = (String) bst.search(i);
      System.out.println(val);
    }
  }
  
  
  /*** Internal class for a node in the tree ***/
  private class TreeNode {
    
    private int key;  // searchable unique identifier for each record
    private Object record;  // actual data associated with entry
    TreeNode leftChild;
    TreeNode rightChild;
    TreeNode parent;
    
    public TreeNode(int newKey, Object newRecord) {
      this.key = newKey;
      this.record = newRecord;
      this.leftChild = null;
      this.rightChild = null;
      this.parent = null;
    }
    
    public int getKey() {
      return this.key; 
    }
    
    public Object getRecord() {
      return this.record; 
    }
    
    public TreeNode getLeftChild() {
      return this.leftChild; 
    } 
    
    public TreeNode getRightChild() {
      return this.rightChild; 
    }
    
    public TreeNode getParent() {
      return this.parent;
    }
    
    public void setKey(int newKey) {
      this.key = newKey; 
    }
    
    public void setRecord(int newRecord) {
      this.record = newRecord; 
    }
    
    public void setLeftChild(TreeNode newLeftChild) {
      this.leftChild = newLeftChild; 
    }
    
    public void setRightChild(TreeNode newRightChild) {
      this.rightChild = newRightChild; 
    }
    
    public void setParent(TreeNode newParent) {
      this.parent = newParent; 
    }
    
  }
}