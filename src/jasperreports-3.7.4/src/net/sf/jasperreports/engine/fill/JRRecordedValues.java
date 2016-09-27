/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.engine.fill;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRConstants;

/**
 * Recorded values container used by elements with
 * {@link net.sf.jasperreports.engine.JRExpression#EVALUATION_TIME_AUTO Auto evaluation time}.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRRecordedValues.java 3034 2009-08-27 11:58:04Z teodord $
 */
public class JRRecordedValues implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;


	private Set evaluationTimes;
	private Map recordedVariableValues;
	private Map recordedFieldValues;

	/**
	 * Creates a recorded values set.
	 * 
	 * @param evaluationTimes future times when the values will be recorded
	 */
	public JRRecordedValues(Set evaluationTimes)
	{
		this.evaluationTimes = new HashSet(evaluationTimes);
	}

	
	/**
	 * Marks an evaluation time as done.
	 * 
	 * @param evaluationTime the evaluation time
	 */
	public void doneEvaluation(JREvaluationTime evaluationTime)
	{
		evaluationTimes.remove(evaluationTime);
	}
	
	
	/**
	 * Decides whether this is the last evaluation time.
	 * 
	 * @return whether this is the last evaluation time
	 */
	public boolean lastEvaluationTime()
	{
		return evaluationTimes.size() == 1;
	}
	
	
	/**
	 * Decides whether all required evaluations are done.
	 * 
	 * @return whether all required evaluations are done
	 */
	public boolean finishedEvaluations()
	{
		return evaluationTimes.isEmpty();
	}
	
	
	/**
	 * Records a variable value.
	 * 
	 * @param variableName the variable name
	 * @param value the variable value to record
	 */
	public void recordVariableValue(String variableName, Object value)
	{
		if (recordedVariableValues == null)
		{
			recordedVariableValues = new HashMap();
		}
		recordedVariableValues.put(variableName, value);
	}
	
	
	/**
	 * Records a field value.
	 * 
	 * @param fieldName the field name
	 * @param value the field value to record
	 */
	public void recordFieldValue(String fieldName, Object value)
	{
		if (recordedFieldValues == null)
		{
			recordedFieldValues = new HashMap();
		}
		recordedFieldValues.put(fieldName, value);
	}
	
	
	/**
	 * Returns the recorded variable values indexed by variable name.
	 * 
	 * @return the recorded variable values
	 */
	public Map getRecordedVariableValues()
	{
		return recordedVariableValues;
	}
	
	
	/**
	 * Returns the recorded field values indexed by field name.
	 * 
	 * @return the recorded field values
	 */
	public Map getRecordedFieldValues()
	{
		return recordedFieldValues;
	}
}
