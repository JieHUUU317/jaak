/*
 * $Id$
 *
 * Jaak environment model is an open-source multiagent library.
 * More details on http://www.sarl.io
 *
 * Copyright (C) 2014 Stéphane GALLAND.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.sarl.jaak.environment.internal.solver;

import io.sarl.jaak.environment.external.body.TurtleBody;
import io.sarl.jaak.environment.external.perception.EnvironmentalObject;
import io.sarl.jaak.environment.internal.model.QuadTreeNode;

import java.util.Collection;

/** This interface defines the methods which are used
 * to apply actions in a Jaak environment.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public interface ActionApplier {

	/** Remove the turtle body on the given cell.
	 * <p>
	 * If the given body is not on the cell at the given
	 * position, this function does nothing.
	 *
	 * @param x is the position of the body.
	 * @param y is the position of the body.
	 * @param body is the body to remove.
	 * @return success state.
	 */
	boolean removeTurtle(TurtleBody body);

	/** Add the turtle body on the given cell.
	 * <p>
	 * If a body is already on the cell at the given
	 * position, this function does nothing.
	 *
	 * @param x is the position of the body.
	 * @param y is the position of the body.
	 * @param body is the body to add.
	 * @return success state.
	 */
	boolean putTurtle(float x, float y, TurtleBody body);

	/** Remove the environmental object on the given cell.
	 *
	 * @param x is the position of the object.
	 * @param y is the position of the object.
	 * @param object is the object to remove.
	 * @return the removed object, not always the given object in the case
	 * of substances.
	 */
	EnvironmentalObject removeObject(EnvironmentalObject object);

	/** Remove all the environmental objects on the given cell.
	 *
	 * @param x is the position of the cell.
	 * @param y is the position of the cell.
	 * @return the removed objects.
	 */
	Collection<EnvironmentalObject> removeObjects(QuadTreeNode n);

	/** Add the environmental object on the given cell.
	 *
	 * @param x is the position of the object.
	 * @param y is the position of the object.
	 * @param object is the object to add.
	 * @return the added object, not always the given object in the case
	 * of substances.
	 */
	EnvironmentalObject putObject(float x, float y, EnvironmentalObject object);

	/**
	 * Update the body state with the given informations.
	 *
	 * @param x is the position of the body.
	 * @param y is the position of the body.
	 * @param headingAngle is the heading direction
	 * @param speed is the instant speed of the body in cells per second.
	 * @param body is the body to change.
	 * @return success state.
	 */
	boolean setPhysicalState(float x, float y, float headingAngle, float speed, TurtleBody body);

}
