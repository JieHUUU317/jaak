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
package io.sarl.jaak.environment.external;

import io.sarl.jaak.environment.external.perception.EnvironmentalObject;

import java.util.Collection;


/** This interface is for the area covered by the environment.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public interface EnvironmentArea {

	/** Replies the minimal x coordinate on the environment.
	 *
	 * @return the min x.
	 */
	float getX();

	/** Replies the minimal y coordinate on the environment.
	 *
	 * @return the min y.
	 */
	float getY();

	/** Replies the width of the environment.
	 *
	 * @return the width of the environment.
	 */
	float getWidth();

	/** Replies the height of the environment.
	 *
	 * @return the height of the environment.
	 */
	float getHeight();

	/** Replies if the cell at the given position contains an obstacle.
	 * <p>
	 * Any coordinate outside the environment grid is assumed to be
	 * an obstacle.
	 *
	 * @param x - the position to test.
	 * @param y - the position to test.
	 * @return <code>true</code> if the cell at the given position contains
	 * an obstacle, otherwise <code>false</code>
	 */
	boolean hasObstacle(int x, int y);

	/** Replies if a turtle body is on the cell at the
	 * given coordinate.
	 *
	 * @param x is the coordinate of the cell.
	 * @param y is the coordinate of the cell.
	 * @return <code>true</code> if a turtle body is
	 * on the cell, otherwise <code>false</code>.
	 */
	boolean hasTurtle(int x, int y);

	/** Replies the number of turtles on the environment.
	 *
	 * @return the number of turtles.
	 */
	int getTurtleCount();

	/** Replies a read-only collection of the environmental objects located
	 * in the cell at the given coordinate.
	 *
	 * @param x is the coordinate of the cell.
	 * @param y is the coordinate of the cell.
	 * @return the collection of objects, never <code>null</code>.
	 */
	Collection<EnvironmentalObject> getEnvironmentalObjects(float x, float y);

	/** Replies the instant speed of the turtle at the given position.
	 *
	 * @param x is the coordinate of the cell.
	 * @param y is the coordinate of the cell.
	 * @return the instant speed of the turtle in cells per second,
	 * or {@link Float#NaN} if no turtle.
	 */
	float getTurtleSpeed(float x, float y);

}
