package abstractEngine;

import java.util.LinkedList;

import abstractGraph.AbstractModel;
import abstractGraph.AbstractState;
import abstractGraph.AbstractStateMachine;
import abstractGraph.AbstractTransition;
import abstractGraph.AbstractGlobalState;
import abstractGraph.events.ExternalEvent;

public abstract class AbstractGraphSimulator<M extends AbstractStateMachine<S, T>, S extends AbstractState<T>, T extends AbstractTransition<S>> {

  protected AbstractModel<M, S, T> functional_model;
  protected AbstractModel<M, S, T> proof_model;
  protected AbstractModel<M, S, T> external_environment;

  private AbstractGlobalState<M, S, T> global_state;

  abstract public void eat(LinkedList<ExternalEvent> l);

  abstract public AbstractGlobalState<M, S, T> execute(ExternalEvent e);

  abstract public AbstractGlobalState<M, S, T> execute(AbstractGlobalState<M, S, T> starting_state,
      ExternalEvent e);

  /**
   * 
   * @return true if there are still external events to execute.
   */
  abstract public boolean executeOneExtEvt();

  /**
   * 
   * @return true if a safety property is not verified
   */
  abstract public boolean isP5();

  /**
   * 
   * @return true if a predicate is not ensure.
   */
  abstract public boolean isP6();

  /**
   * 
   * @return true if a functionality is not ensured.
   */
  abstract public boolean isP7();

  public AbstractGlobalState<M, S, T> getGlobalState() {
    return global_state;
  }

  public void setGlobalState(AbstractGlobalState<M, S, T> global_state) {
    this.global_state = global_state;
  }

  public AbstractModel<M, S, T> getExternalEnvironment() {
    return external_environment;
  }

  public void setExternalEnvironment(
      AbstractModel<M, S, T> external_environment) {
    this.external_environment = external_environment;
  }
}
