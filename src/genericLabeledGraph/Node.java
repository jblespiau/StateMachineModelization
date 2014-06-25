package genericLabeledGraph;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @param <N>
 *          The class of the nodes.
 * @param <L>
 *          The class of the label.
 */
public class Node<N, L> implements Iterable<Edge<N, L>> {

  public LinkedHashMap<L, LinkedList<Edge<N, L>>> transitions = new LinkedHashMap<>();

  public Node() {

  }

  public void add(Edge<N, L> hedge) {
    LinkedList<Edge<N, L>> list = transitions.get(hedge.label);
    if (list == null) {
      list = new LinkedList<Edge<N, L>>();
    }
    list.add(hedge);
  }

  public Iterable<Edge<N, L>> getLabeledBy(L label) {
    LinkedList<Edge<N, L>> result = transitions.get(label);
    if (result == null) {
      return Collections.emptyList();
    } else {
      return result;
    }
  }

  @Override
  public Iterator<Edge<N, L>> iterator() {
    class LocalIterator implements Iterator<Edge<N, L>> {

      private Iterator<Edge<N, L>> current_list_it;
      private Iterator<LinkedList<Edge<N, L>>> iterator_over_lists;

      public LocalIterator(Node<N, L> node) {
        iterator_over_lists = node.transitions.values().iterator();
      }

      @Override
      public boolean hasNext() {
        if (current_list_it != null && current_list_it.hasNext()) {
          return true;
        }
        while (iterator_over_lists.hasNext()) {
          current_list_it = iterator_over_lists.next().iterator();
          if (current_list_it.hasNext()) {
            return true;
          }
        }
        return false;
      }

      @Override
      public Edge<N, L> next() {
        return current_list_it.next();
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    }

    return new LocalIterator(this);
  }
}
