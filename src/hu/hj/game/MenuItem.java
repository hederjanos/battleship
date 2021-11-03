package hu.hj.game;

import hu.hj.exceptions.io.InvalidMenuPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuItem {

    private MenuItem parent;
    private final List<MenuItem> children = new ArrayList<>();
    private Runnable task;
    private String name;

    public MenuItem(MenuItem parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public MenuItem(String name) {
        this.name = name;
    }

    public MenuItem handleChoice(int actionNumber) throws InvalidMenuPoint {
        if (actionNumber - 1 < 0 || actionNumber - 1 >= children.size()) {
            throw new InvalidMenuPoint(String.valueOf(actionNumber));
        }

        MenuItem chosenMenuItem = children.get(actionNumber - 1);

        if (actionNumber == children.size() && chosenMenuItem.getTask() == null) {
            return null;
        }
        if (actionNumber == children.size() - 1 && chosenMenuItem.getTask() == null) {
            return getParent();
        }
        return chosenMenuItem;
    }

    public void run() {
        task.run();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-------------------------------------\n").append(name).append(" menu").append("\n")
                        .append("-------------------------------------\n").append("press:\n");
        for (int i = 0; i < children.size(); i++) {
            stringBuilder.append("\t\t").append(i + 1).append(" - ").append("to ").append(children.get(i).getName()).append("\n");
        }
        return stringBuilder.toString();
    }

    public MenuItem getParent() {
        return parent;
    }

    public MenuItem getRoot() {
        MenuItem currentMenuitem = this;
        while (currentMenuitem.getParent() != null) {
            currentMenuitem = currentMenuitem.getParent();
        }
        return currentMenuitem;
    }

    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getChildren() {
        return children;
    }

    public Runnable getTask() {
        return task;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    public void addChild(MenuItem menuItem) {
        children.add(menuItem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(name, menuItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
