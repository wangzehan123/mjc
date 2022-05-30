package archive.coinbase.jiamian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Input: test_mempool_2 = [
//    id, parent_id, size, fee
//    TransactionCPFP(1, None, 10, 100),
//    TransactionCPFP(2, None, 20, 100),
//    TransactionCPFP(3, None, 50, 50),
//    TransactionCPFP(4, None, 20, 1),
//    TransactionCPFP(5, None, 85, 400),
//    TransactionCPFP(6, None, 90, 50),
//    TransactionCPFP(7, None, 30, 10),
//    TransactionCPFP(8, 5, 2, 10),
//    TransactionCPFP(9, 8, 3, 1000),
//    TransactionCPFP(10, None, 5, 5),
//
//    transaction id 8
//    parent_id 5
//
//    ]
//
//
//    Output: [
//    ID 9: parentID: 8 size: 3, fee: 1000,
//    ID 8: parentID: 5 size: 2, fee: 10,
//    ID 5: parentID: None size: 85, fee: 400,
//    ID 1: parentID: None size: 10, fee: 100
//    ]
// Implement a method min_fee(mempool, parent_id, child_size) -> uint64 which returns the fee that a child transaction of size child_size should pay to include itself and its parent in the next block.

public class TransactionSize {

  public static void main(String[] args) {
    Transaction transaction1 = new Transaction(1, -1, 10, 100);
    Transaction transaction2 = new Transaction(2, -1, 20, 100);
    Transaction transaction3 = new Transaction(3, -1, 50, 50);
    Transaction transaction4 = new Transaction(4, -1, 20, 1);
    Transaction transaction5 = new Transaction(5, -1, 85, 400);
    Transaction transaction6 = new Transaction(6, -1, 90, 50);
    Transaction transaction7 = new Transaction(7, -1, 30, 10);
    Transaction transaction8 = new Transaction(8, 5, 2, 10);
    Transaction transaction9 = new Transaction(9, 8, 3, 1000);
    Transaction transaction10 = new Transaction(10, -1, 5, 5);
    List<Transaction> transactions = new ArrayList<>();
    transactions.add(transaction1);
    transactions.add(transaction2);
    transactions.add(transaction3);
    transactions.add(transaction4);
    transactions.add(transaction5);
    transactions.add(transaction6);
    transactions.add(transaction7);
    transactions.add(transaction8);
    transactions.add(transaction9);
    transactions.add(transaction10);
    TransactionParentList results = getResults(transactions,100);
    System.out.println(min_fee(transactions,transaction9, 1000));
    System.out.println(results.ids + " ----- " + results.total_fee);
  }

  static class TransactionResults {
    int maxFees;
    List<Long> transactionIds;

    public TransactionResults(int maxFees, List<Long> transactionIds) {
      this.maxFees = maxFees;
      this.transactionIds = transactionIds;
    }
  }

  static class sortByRatio implements Comparator<Transaction> {
    public int compare(Transaction o1, Transaction o2) {
      return (o2.fee / o2.block_size - o1.fee / o1.block_size);
    }
  }

  static List<Transaction> getParents(List<Transaction> transactions, Transaction children) {
    List<Transaction> list = new ArrayList<>();
    while (children != null && children.parent_transaction_id != -1) {
      children = getParent(transactions, children);
      list.add(children);
    }
    return list;
  }

  static Transaction getParent(List<Transaction> transactions, Transaction children) {
    for (Transaction transaction : transactions) {
      if (children.parent_transaction_id == transaction.transaction_id) {
        return transaction;
      }
    }
    return null;
  }

  static int min_fee(List<Transaction> transactions, Transaction children, int fee) {
    int min = 0;
    List<Transaction> res = getParents(transactions, children);
    for (Transaction transaction : res) {
      min += transaction.fee;
    }
    return min + fee;
  }

  static TransactionParentList getResults(List<Transaction> list, int size) {
    Collections.sort(list, new sortByRatio());
    List<Integer> ids = new ArrayList<>();
    int max = 0;
    for (Transaction transaction : list) {
      if (transaction.parent_transaction_id != -1) {
        List<Transaction> parents = getParents(list, transaction);
        parents.add(transaction);
        int total_size = getSize(parents);
        if (total_size <= size) {
          size -= total_size;
          for (Transaction transaction1 : parents) {
            ids.add(transaction1.transaction_id);
            max += transaction1.fee;
          }
        }
      }else {
        if (transaction.block_size <= size) {
          ids.add(transaction.transaction_id);
          size -= transaction.block_size;
          max+= transaction.fee;
        }
      }
    }
    return new TransactionParentList(max, ids);
  }

  static int getSize(List<Transaction> parents) {
    int size = 0;
    for (Transaction parent : parents) {
      size += parent.block_size;
    }
    return size;
  }

  static class TransactionParentList {
    int total_fee;
    List<Integer> ids;

    public TransactionParentList(int total_fee, List<Integer> ids) {
      this.ids = ids;
      this.total_fee = total_fee;
    }
  }

  static class Transaction {
    int transaction_id;
    int parent_transaction_id;
    int fee;
    int block_size;

    public Transaction(int transaction_id, int parent_transaction_id, int size, int fee) {
      this.transaction_id = transaction_id;
      this.parent_transaction_id = parent_transaction_id;
      this.fee = fee;
      this.block_size = size;
    }
  }

}
