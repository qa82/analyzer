package org.qa82.analyzer.core.impl;


public class SimpleInformation extends AbstractInformation {

    private Object informationObject;

    public SimpleInformation(Object informationObject) {
        super(informationObject.getClass().toString());
        this.informationObject = informationObject;
    }

    @Override
    public Object getValue() {
        return informationObject;
    }

    @Override
    public Boolean isInformationPresent() {
        return informationObject == null;
    }

}
