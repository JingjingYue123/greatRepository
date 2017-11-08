package com.djcps.crm.integral.model;

public class UserIntegralChangeTips implements Comparable {
    private int row;
    private int column;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public int compareTo(Object o) {
        if (o != null && o instanceof UserIntegralChangeTips) {
            UserIntegralChangeTips tips = (UserIntegralChangeTips) o;
            if (this.row > tips.getRow()) {
                return 1;
            } else if (this.row == tips.getRow()) {
                if (this.column > tips.getColumn()) {
                    return 1;
                } else if (this.column == tips.getColumn()) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
        throw new RuntimeException("类型不一致");
    }

    @Override
    public String toString() {
        return "第 " + row + " 行，第 " + column + " 列";
    }
}
