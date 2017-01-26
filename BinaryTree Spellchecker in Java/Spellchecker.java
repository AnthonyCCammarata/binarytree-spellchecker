//Spellchecker
//Anthony Cammarata

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Spellchecker {
  
  private TreeNode root;
  
  public Spellchecker() {
    this.root = null; 
  }
  
  public Spellchecker(String wordFile) {
    
    this.root = null; 
    
    try {
      Scanner file = new Scanner(new File(wordFile));
      
      while (file.hasNext()) {
        String s = file.nextLine();
        insert(s);
      } 
    }  
    catch (FileNotFoundException e) {
      e.printStackTrace(); 
    }
  }
  
  public void insert(String newRecord) {
    
    TreeNode newNode = new TreeNode(newRecord); 
    
    if (this.root == null) {
      this.root = newNode;
      return;
    }
    
    TreeNode currentNode = this.root;
    boolean searching = true;
    
    while (searching) {
      
      int order = newNode.getRecord().compareTo(currentNode.getRecord());
      
      if (order < 0) {
        TreeNode nextNode = currentNode.getLeftChild();
        
        if (nextNode == null) {
          currentNode.setLeftChild(newNode);
          newNode.setParent(currentNode.getParent());
          searching = false;
        }
        else {
          currentNode = nextNode; 
        }
      }
      
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

  public boolean search(String searchWord) {
    TreeNode currentNode = this.root;
      
    while (currentNode != null) {
      int order = searchWord.compareTo(currentNode.getRecord());
      if (order == 0) {
        return true; 
      }
      else if (order < 0) {
        currentNode = currentNode.getLeftChild();
      }
      else {
        currentNode = currentNode.getRightChild(); 
      }
    }
    return false;
  }
  
  
  public void doTraverse(TreeNode node) {
    if (node == null)
      return;
    
    doTraverse(node.getLeftChild());
    System.out.println(node.getRecord());
    doTraverse(node.getRightChild());
  }
 
  public void traverse() { 
    doTraverse(this.root);  
  }
  
  public ArrayList<String> suggestions(String word) {
    ArrayList<String> output = new ArrayList<String>();
    
    String letters = "abcdefghijklmnopqrstuvwxyz"; 
    
    for (int i = 0; i < word.length(); i++) { 
      for (int j = 0; j < letters.length(); j++) { 
        
        String candidate = word.substring(0, i) + letters.charAt(j) + word.substring(i+1, word.length()); 
        if (this.search(candidate) == true) { 
          output.add(candidate);
        } 
      } 
    }
    
    for (int i = 0; i < word.length(); i++) { 
      for (int j = 0; j < letters.length(); j++) { 
        
        String candidate = word.substring(0, i+1) + letters.charAt(j) + word.substring(i+1, word.length()); 
        if (this.search(candidate) == true) { 
          output.add(candidate);
        } 
      } 
    }
    
    for (int i = 0; i < word.length(); i++) { 
      for (int j = 0; j < letters.length(); j++) { 
        
        String candidate = word + j; 
        if (this.search(candidate) == true) { 
          output.add(candidate);
        } 
      } 
    }
    
    for (int i = 0; i < word.length(); i++) { 
      for (int j = 0; j < letters.length(); j++) { 
        
        String candidate = j + word; 
        if (this.search(candidate) == true) { 
          output.add(candidate);
        } 
      } 
    }
    
    for (int i = 0; i < word.length(); i++) { 
      String candidate = word.substring(0, i) + word.substring(i+1, word.length()); 
      if (this.search(candidate) == true) { 
        output.add(candidate);
      } 
       
    }
    
    
    return output;
  }
  
  public void checkfile(String fileName) {
   try {
      Scanner checkfile = new Scanner(new File(fileName));
      
      while (checkfile.hasNext()) {
        String a = checkfile.next().toLowerCase();
        if(!search(a)) {
          System.out.println("Mispelled: " + a + "\n" + suggestions(a));
        }
      } 
    }  
    catch (FileNotFoundException e) {
      e.printStackTrace(); 
    } 
  }

  private class TreeNode {
     
    private String record;  
    TreeNode leftChild;
    TreeNode rightChild;
    TreeNode parent;
    
    public TreeNode(String newRecord) {
      this.record = newRecord;
      this.leftChild = null;
      this.rightChild = null;
      this.parent = null;
    }
    
    public String getRecord() {
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
    
    public void setRecord(String newRecord) {
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
  
  public static void main(String[] args) {
    Spellchecker s = new Spellchecker("shuffled_word_list.txt");
    
    s.checkfile("the_raven.txt");
    
  }
}