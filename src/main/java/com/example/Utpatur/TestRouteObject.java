package com.example.Utpatur;

import java.util.List;

public class TestRouteObject {

    String name;
    String type;
    List<TestPosition> positions;

    public TestRouteObject(String name, String type, List<TestPosition> positions) {
        this.name = name;
        this.type = type;
        this.positions = positions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TestPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<TestPosition> positions) {
        this.positions = positions;
    }
}
