package dev.mjmarokane.model;

import dev.mjmarokane.gui.IDrawable;
import dev.mjmarokane.gui.IDrawVisitor;

public class Food implements IDrawable
{
    private int x;
    private int y;

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    @Override
    public void accept(IDrawVisitor visitor)
    {
        visitor.visit(this);
    }
}