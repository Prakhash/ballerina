/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.ballerina.core.model;

import org.wso2.ballerina.core.model.statements.Statement;
import org.wso2.ballerina.core.runtime.core.BalCallback;
import org.wso2.ballerina.core.runtime.core.BalContext;
import org.wso2.ballerina.core.runtime.core.Executable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A {@code Resource} is a single request handler within a {@code Service}.
 * The resource concept is designed to be access protocol independent.
 * But in the initial release of the language it is intended to work with HTTP.
 * <p>
 *
 * The structure of a ResourceDefinition is as follows:
 *
 *  [ResourceAnnotations]
 *  resource ResourceName (Message VariableName[, ([ResourceParamAnnotations] TypeName VariableName)+]) {
 *      ConnectionDeclaration;*
 *      VariableDeclaration;*
 *      WorkerDeclaration;*
 *      Statement;+
 *  }*
 *
 *  @since 1.0.0
 *
 */
@SuppressWarnings("unused")
public class Resource implements Executable {

    private Map<String, Annotation> annotations;
    private List<Parameter> arguments;
    private List<Worker> workers;
    private Worker defaultWorker;

    public Resource() {
        defaultWorker = new Worker();
    }

    /**
     * Get an Annotation from a given name
     *
     * @param name name of the annotation
     * @return Annotation
     */
    public Annotation getAnnotation(String name) {
        return annotations.get(name);
    }

    /**
     * Get all the Annotations associated with a Resource
     *
     * @return map of Annotations
     */
    public Map<String, Annotation> getAnnotations() {
        return annotations;
    }

    /**
     * Set Annotations
     *
     * @param annotations map of Annotations
     */
    public void setAnnotations(Map<String, Annotation> annotations) {
        this.annotations = annotations;
    }

    /**
     * Add an {@code Annotation} to the Resource
     *
     * @param annotation Annotation to be added
     */
    public void addAnnotation(Annotation annotation) {
        if (annotations == null) {
            annotations = new HashMap<>();
        }
        annotations.put(annotation.getName(), annotation);
    }

    /**
     * Get list of Arguments associated with the Resource definition
     *
     * @return list of Arguments
     */
    public List<Parameter> getArguments() {
        return arguments;
    }

    /**
     * Set Arguments list to the Resource
     *
     * @param arguments list of Arguments
     */
    public void setArguments(List<Parameter> arguments) {
        this.arguments = arguments;
    }

    /**
     * Add an {@code Argument} to the Resource
     *
     * @param argument Argument to be added to the Resource definition
     */
    public void addArgument(Parameter argument) {
        if (arguments == null) {
            arguments = new ArrayList<Parameter>();
        }
        arguments.add(argument);
    }


    /**
     * Get all Connections declared within the default Worker scope of the Resource
     *
     * @return list of all the Connections belongs to the default Worker of the Resource
     */
    public List<Connection> getConnections() {
        return defaultWorker.getConnections();
    }

    /**
     * Assign connections to the default Worker of the Resource
     *
     * @param connections list of connections to be assigned to the default Worker of the Resource
     */
    public void setConnections(List<Connection> connections) {
        defaultWorker.setConnections(connections);
    }

    /**
     * Add a {@code Connection} to the default Worker of the Resource
     *
     * @param connection Connection to be added to the default Worker of the Resource
     */
    public void addConnection(Connection connection) {
        defaultWorker.addConnection(connection);
    }

    /**
     * Get all the variables declared in the default Worker scope of the Resource
     *
     * @return list of all default Worker scoped variables
     */
    public List<VariableDcl> getVariables() {
        return defaultWorker.getVariables();
    }

    /**
     * Assign variables to the default Worker of the Resource
     *
     * @param variables list of variables
     */
    public void setVariables(List<VariableDcl> variables) {
        defaultWorker.setVariables(variables);
    }

    /**
     * Add a {@code Variable} to the default Worker of the Resource
     *
     * @param variable variable to be added default Worker
     */
    public void addVariable(VariableDcl variable) {
        defaultWorker.addVariable(variable);
    }

    /**
     * Get all the Workers associated with a Resource
     *
     * @return list of Workers
     */
    public List<Worker> getWorkers() {
        return workers;
    }

    /**
     * Assign Workers to the Resource
     *
     * @param workers list of all the Workers
     */
    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    /**
     * Add a {@code Worker} to the Resource
     *
     * @param worker Worker to be added to the Resource
     */
    public void addWorker(Worker worker) {
        if (workers == null) {
            workers = new ArrayList<Worker>();
        }
        workers.add(worker);
    }

    /**
     * Get all the Statements associated with the default Worker
     *
     * @return list of Statements associated with the default Worker
     */
    public List<Statement> getStatements() {
        return defaultWorker.getStatements();
    }

    /**
     * Set Statements to be associated with the default Worker
     *
     * @param statements list of Statements
     */
    public void setStatements(List<Statement> statements) {
        defaultWorker.setStatements(statements);
    }

    /**
     * Add a {@code Statement} to the default Worker in the Resource
     *
     * @param statement a Statement to be added to the default Worker
     */
    public void addStatement(Statement statement) {
        defaultWorker.addStatement(statement);
    }

    @Override
    public boolean execute(BalContext context, BalCallback callback) {
        return defaultWorker.execute(context, callback);
    }
}