/**
 * 
 */
package io.sarl.jaak.environment.internal.model;

import io.sarl.jaak.environment.external.body.TurtleBody;
import io.sarl.jaak.environment.external.endogenous.AutonomousEndogenousProcess;
import io.sarl.jaak.environment.external.perception.Burrow;
import io.sarl.jaak.environment.external.perception.EnvironmentalObject;
import io.sarl.jaak.environment.external.perception.ObjectManipulator;
import io.sarl.jaak.environment.external.perception.Substance;
import io.sarl.jaak.environment.internal.ContinuousModel;
import io.sarl.jaak.environment.internal.solver.ActionApplier;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

/**
 * This class defines a wrapper of the JBox2d world. All methods interacting
 * directly with JaBox2D world should be defined here.
 * 
 * @author Edwin Wilson
 *
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class JaakContinuousWorld implements ContinuousModel, ActionApplier {

	private final Map<UUID, Body> bodies = new TreeMap<>();
	private final Map<String,Body> environmentObjectBodies = new TreeMap<>();
	private World world;
	private float width;
	private float height;
	private ObjectManipulator objectManipulator;
	private WeakReference<JaakEnvironment> environmentRef;
	private final Collection<AutonomousEndogenousProcess> autonomousProcesses = new LinkedList<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.sarl.jaak.environment.internal.ContinuousModel#getWidth()
	 */
	@Override
	public float getWidth() {
		return this.width;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.sarl.jaak.environment.internal.ContinuousModel#getHeight()
	 */
	@Override
	public float getHeight() {
		return this.height;
	}

	@Override
	public boolean putTurtle(float x, float y, TurtleBody emitter) {
		// TODO check this method and turtle movement
	}

	@Override
	public boolean removeTurtle(UUID turtleId) {

		if (!this.bodies.containsKey(turtleId)) {
			return false;
		}
		this.world.destroyBody(bodies.get(turtleId));
		this.bodies.remove(turtleId);
		return true;
	}

	@Override
	public boolean setPhysicalState(float x, float y, float heading,
			float speed, TurtleBody emitter) {
		if (emitter instanceof RealTurtleBody) {
			((RealTurtleBody) emitter).setPhysicalState(x, y, heading, speed);
			return true;
		}
		return false;
	}

	@Override
	public EnvironmentalObject removeObject(EnvironmentalObject pickUpObject) {
		EnvironmentalObject change = null;
		UUID id = pickUpObject.getId();
		
		if (pickUpObject instanceof Substance) {
			Substance oldSubstance = (Substance) pickUpObject;

			EnvironmentalObject oldObject = environmentRef.get().getEnvironmentalObject(id);
			if (oldObject instanceof Substance) {
				Substance currentSubstance = (Substance) oldObject;
				change = this.objectManipulator.combine(currentSubstance, oldSubstance, false);
				if (change == null || !currentSubstance.isDisappeared()) {
					return change;
				}
			}
		}

		EnvironmentalObject obj;

		obj = environmentRef.get().removeEnvironmentalObject(id);
		this.environmentObjectBodies.remove(id);

		if (obj != null) {
			if (change == null) {
				change = obj;
			}
			if (obj instanceof Burrow) {
				deleteBurrow(id);
			}
			this.objectManipulator.setPosition(obj, Integer.MIN_VALUE,
					Integer.MIN_VALUE);
			if (obj instanceof AutonomousEndogenousProcess) {
				this.autonomousProcesses.remove(obj);
			}
		}
		return change;
	}

	private void deleteBurrow(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putObject(float x, float y, EnvironmentalObject dropOffObject) {
		// TODO Auto-generated method stub

	}

}